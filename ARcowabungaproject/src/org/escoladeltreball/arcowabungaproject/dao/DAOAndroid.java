package org.escoladeltreball.arcowabungaproject.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Ingredients;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class DAOAndroid extends DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private SQLiteDatabase database;
    private DataBaseHelper dbHepler;

    private Map<Integer, String> resources;

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOAndroid(Context context, Pizzeria pizzeria) {
	super(pizzeria);
	dbHepler = new DataBaseHelper(context);

    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public Drawable getDrawableFromAssets(Activity activity, String path) {
	Drawable drawable = null;
	try {
	    drawable = Drawable.createFromStream(activity.getAssets()
		    .open(path), null);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return drawable;
    }

    public Drawable getDrawableFromAssets(Activity activity, int resourceId) {
	return getDrawableFromAssets(activity, getResourcePath(resourceId));
    }

    public String getResourcePath(int resourceId) {
	return resources.get(resourceId);
    }

    public static DAOAndroid getInstance() {
	return (DAOAndroid) instance;
    }

    public static DAOAndroid getInstance(Context context, Pizzeria pizzeria) {
	if (instance == null) {
	    instance = new DAOAndroid(context, pizzeria);
	}
	return (DAOAndroid) instance;
    }

    public void open() throws SQLException {
	database = dbHepler.getWritableDatabase();
    }

    public void close() {
	dbHepler.close();
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================
    private Ingredients selectIngredientsById(int id) {
	Ingredients ingredients = new Ingredients(id);
	/*
	 * Select all rows with the same id_ingredients from ingredients table
	 */
	Cursor cIngredients = database.query(DAOFactory.TABLE_INGREDIENTS,
		DAOFactory.COLUMNS_NAME_INGREDIENTS,
		DAOFactory.COLUMNS_NAME_INGREDIENTS[1] + "=" + id, null, null,
		null, null);
	int i = 0;
	while (i < cIngredients.getCount()) {
	    cIngredients.move(i);
	    /*
	     * Select all rows with the same id_ingredient from ingredient table
	     * and put in the map
	     */
	    Cursor cIngredient = database.query(
		    DAOFactory.TABLE_INGREDIENT,
		    COLUMNS_NAME_INGREDIENT,
		    DAOFactory.COLUMNS_NAME_INGREDIENT[0] + "="
			    + cIngredients.getInt(1), null, null, null, null);
	    int j = 0;
	    while (j < cIngredient.getCount()) {
		cIngredient.move(j);
		Ingredient ingredient = new Ingredient(cIngredient.getInt(0),
			cIngredient.getString(1), cIngredient.getInt(2),
			cIngredient.getInt(3), cIngredient.getInt(4));
		ingredients.put(ingredient, cIngredient.getInt(2));
		j++;
	    }
	    i++;
	}
	return ingredients;
    }

    private List<Product> selectProductsOffersById(int id) {
	List<Product> productList = new ArrayList<Product>();
	/* Select from offer_product table the rows with offer = id_offer */
	Cursor cOffersProduct = database.query(
		DAOFactory.TABLE_OFFERS_PRODUCTS,
		DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS,
		DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[0] + "=" + id, null,
		null, null, null);
	int i = 0;
	while (i < cOffersProduct.getCount()) {
	    cOffersProduct.move(i);
	    Product product = null;
	    /* Product can be a pizza product or drink product */
	    Cursor cPizza = database.query(
		    DAOFactory.TABLE_PIZZAS,
		    DAOFactory.COLUMNS_NAME_PIZZAS,
		    DAOFactory.COLUMNS_NAME_PIZZAS[0] + " = "
			    + cOffersProduct.getInt(1), null, null, null, null);
	    if (cPizza != null) {
		cPizza.moveToFirst();
		product = new Pizza(cPizza.getInt(0), cPizza.getString(1),
			cPizza.getFloat(2), cPizza.getInt(3),
			cPizza.getFloat(4), cPizza.getString(5),
			cPizza.getString(6), cPizza.getInt(7));
	    }
	    Cursor cDrink = database.query(
		    DAOFactory.TABLE_DRINKS,
		    DAOFactory.COLUMNS_NAME_DRINKS,
		    DAOFactory.COLUMNS_NAME_DRINKS[0] + " = "
			    + cOffersProduct.getInt(1), null, null, null, null);
	    if (cDrink != null) {
		cDrink.moveToFirst();
		product = new Drink(cDrink.getInt(0), cDrink.getString(1),
			cDrink.getFloat(2), cDrink.getInt(3),
			cDrink.getFloat(4), cDrink.getInt(5));
	    }
	    productList.add(product);
	    i++;
	}
	return productList;
    }

    private void selectShoppingCartProductsById(int id) {
	List<Product> productList = new ArrayList<Product>();
	Cursor cShoppingCartsProducts = database.query(
		DAOFactory.TABLE_SHOPPINGCART_PRODUCTS,
		DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS,
		DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[0] + "=" + id,
		null, null, null, null);
	int i = 0;
	while (i < cShoppingCartsProducts.getCount()) {
	    cShoppingCartsProducts.move(i);
	    /* Product can be a pizza product, drink or offer product */
	    Cursor cPizza = database.query(DAOFactory.TABLE_PIZZAS,
		    DAOFactory.COLUMNS_NAME_PIZZAS,
		    DAOFactory.COLUMNS_NAME_PIZZAS[0] + " = "
			    + cShoppingCartsProducts.getInt(1), null, null,
		    null, null);
	    if (cPizza != null) {
		cPizza.moveToFirst();
		Pizza pizza = new Pizza(cPizza.getInt(0), cPizza.getString(1),
			cPizza.getFloat(2), cPizza.getInt(3),
			cPizza.getFloat(4), cPizza.getString(5),
			cPizza.getString(6), cPizza.getInt(7));
		 productList.add(pizza);
	    }
	    Cursor cDrink = database.query(DAOFactory.TABLE_DRINKS,
		    DAOFactory.COLUMNS_NAME_DRINKS,
		    DAOFactory.COLUMNS_NAME_DRINKS[0] + " = "
			    + cShoppingCartsProducts.getInt(1), null, null,
		    null, null);
	    if (cDrink != null) {
		cDrink.moveToFirst();
		Drink drink = new Drink(cDrink.getInt(0), cDrink.getString(1),
			cDrink.getFloat(2), cDrink.getInt(3),
			cDrink.getFloat(4), cDrink.getInt(5));
		 productList.add(drink);
		
	    }
	    Cursor cOffer = database.query(DAOFactory.TABLE_OFFERS,
		    DAOFactory.COLUMNS_NAME_OFFERS,
		    DAOFactory.COLUMNS_NAME_OFFERS[0] + " = "
			    + cShoppingCartsProducts.getInt(1), null, null,
		    null, null);
	    if (cOffer != null) {
		cOffer.moveToFirst();
		Offer offer = new Offer(cOffer.getInt(0), cOffer.getString(1),
			cOffer.getFloat(2), cOffer.getInt(3),
			cOffer.getFloat(4));
		List<Product> productOfferList = selectProductsOffersById(cOffer
			.getInt(0));
		offer.setProductList(productOfferList);
		productList.add(offer);
	    }
	    i++;
	}
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public boolean loadDemo() {
	resources.put(150, "images/home_image.png");
	resources.put(151, "images/home_image.png");
	resources.put(152, "images/home_image.png");
	resources.put(153, "images/home_image.png");
	resources.put(154, "images/home_image.png");
	return super.loadDemo();
    }

    @Override
    protected Set<Ingredient> readIngredient() {
	Set<Ingredient> ingredients = new HashSet<Ingredient>();
	Cursor c = database.query(DAOFactory.TABLE_INGREDIENT,
		DAOFactory.COLUMNS_NAME_INGREDIENT, null, null, null, null,
		null);
	int i = 0;
	while (i < c.getCount()) {
	    c.move(i);
	    Ingredient ingredient = new Ingredient(c.getInt(0), c.getString(1),
		    c.getInt(2), c.getInt(3), c.getInt(4));
	    ingredients.add(ingredient);
	    i++;
	}
	return ingredients;
    }

    @Override
    protected Set<Pizza> readPizza() {
	Set<Pizza> pizzas = new HashSet<Pizza>();
	/* Select all rows from pizzas table */
	Cursor cPizzas = database.query(DAOFactory.TABLE_PIZZAS,
		DAOFactory.COLUMNS_NAME_PIZZAS, null, null, null, null, null);
	int i = 0;
	while (i < cPizzas.getCount()) {
	    cPizzas.move(i);
	    Pizza pizza = new Pizza(cPizzas.getInt(0), cPizzas.getString(1),
		    cPizzas.getFloat(2), cPizzas.getInt(3),
		    cPizzas.getFloat(4), cPizzas.getString(5),
		    cPizzas.getString(6), cPizzas.getInt(7));
	    Ingredients ingredients = selectIngredientsById(cPizzas.getInt(8));
	    pizza.setIngredients(ingredients);
	    pizza.setIngredients(ingredients);
	    pizzas.add(pizza);
	    i++;
	}
	return pizzas;
    }

    @Override
    protected Set<Offer> readOffer() {
	Set<Offer> offers = new HashSet<Offer>();
	Cursor cOffer = database.query(DAOFactory.TABLE_OFFERS,
		DAOFactory.COLUMNS_NAME_OFFERS, null, null, null, null, null);
	int i = 0;
	while (i < cOffer.getCount()) {
	    cOffer.move(i);
	    Offer offer = new Offer(cOffer.getInt(0), cOffer.getString(1),
		    cOffer.getFloat(2), cOffer.getInt(3), cOffer.getFloat(4));
	    List<Product> productList = selectProductsOffersById(cOffer
		    .getInt(0));
	    offer.setProductList(productList);
	    i++;
	}
	return offers;
    }

    @Override
    protected Set<Drink> readDrink() {
	Set<Drink> drinks = new HashSet<Drink>();
	Cursor cDrinks = database.query(DAOFactory.TABLE_DRINKS,
		DAOFactory.COLUMNS_NAME_DRINKS, null, null, null, null, null);
	int i = 0;
	while (i < cDrinks.getCount()) {
	    cDrinks.move(i);
	    Drink drink = new Drink(cDrinks.getInt(0), cDrinks.getString(1),
		    cDrinks.getFloat(2), cDrinks.getInt(3),
		    cDrinks.getFloat(4), cDrinks.getInt(5));
	    drinks.add(drink);
	    i++;
	}
	return drinks;
    }

    @Override
    protected Set<ShoppingCart> readShoppingCart() {
	Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>();
	Cursor cShoppingCarts = database.query(DAOFactory.TABLE_SHOPPINGCARTS,
		DAOFactory.COLUMNS_NAME_SHOPPINGCARTS, null, null, null, null,
		null);
	int i = 0;
	while (i < cShoppingCarts.getCount()) {
	    cShoppingCarts.move(i);
	    ShoppingCart shoppingCart = new ShoppingCart(
		    cShoppingCarts.getInt(0));
	    selectShoppingCartProductsById(cShoppingCarts.getInt(0));
	    shoppingCarts.add(shoppingCart);
	    i++;
	}
	return shoppingCarts;
    }

    @Override
    protected Set<Order> readOrder() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Address> readAddress() {
	Set<Address> addresses = new HashSet<Address>();
	Cursor cAddress = database.query(DAOFactory.TABLE_ADDRESS,
		DAOFactory.COLUMNS_NAME_ADDRESS, null, null, null, null, null);
	int i = 0;
	while (i < cAddress.getCount()) {
	    cAddress.move(i);
	    Address address = new Address(cAddress.getInt(0),
		    cAddress.getString(1), cAddress.getString(2),
		    cAddress.getString(3), cAddress.getString(4),
		    cAddress.getString(5), cAddress.getString(6));
	    addresses.add(address);
	    i++;
	}
	return addresses;
    }

    @Override
    protected Map<String, Object> readPreferences() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Product> readProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void writeIngredients(Set<Ingredient> ingredients) {

	for (Ingredient ingredient : ingredients) {
	    ContentValues values = new ContentValues();

	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[0],
		    ingredient.getId());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[1],
		    ingredient.getName());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[2],
		    ingredient.getIcon());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[3],
		    ingredient.getModel());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[4],
		    ingredient.getPrice());

	    database.insert(DAOFactory.TABLE_INGREDIENT, null, values);
	}
    }

    @Override
    protected void writePizzas(Set<Pizza> pizzas) {
	for (Pizza pizza : pizzas) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[0], pizza.getId());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[1], pizza.getName());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[2], pizza.getPrice());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[3], pizza.getIcon());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[4], pizza.getMassType());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[5], pizza.getType());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[6], pizza.getSize());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[7], pizza.getDiscount());
	    values.put(DAOFactory.COLUMNS_NAME_PIZZAS[8], pizza
		    .getIngredients().getId());

	    database.insert(DAOFactory.TABLE_PIZZAS, null, values);
	    values.clear();
	    for (Ingredient ingredient : pizza.getIngredientsSet()) {
		values.put(DAOFactory.COLUMNS_NAME_INGREDIENTS[0], pizza
			.getIngredients().getId());
		values.put(DAOFactory.COLUMNS_NAME_INGREDIENTS[1],
			ingredient.getId());
		values.put(DAOFactory.COLUMNS_NAME_INGREDIENTS[2], pizza
			.getIngredients().get(ingredient));
		database.insert(DAOFactory.TABLE_INGREDIENTS, null, values);
	    }
	}

    }

    @Override
    protected void writeOffers(Set<Offer> offers) {
	for (Offer offer : offers) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_OFFERS[0], offer.getId());
	    values.put(DAOFactory.COLUMNS_NAME_OFFERS[1], offer.getName());
	    values.put(DAOFactory.COLUMNS_NAME_OFFERS[2], offer.getPrice());
	    values.put(DAOFactory.COLUMNS_NAME_OFFERS[3], offer.getIcon());
	    values.put(DAOFactory.COLUMNS_NAME_OFFERS[4], offer.getDiscount());

	    database.insert(DAOFactory.TABLE_OFFERS, null, values);
	    values.clear();
	    for (Product product : offer.getProduct()) {
		values.put(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[0],
			offer.getId());
		values.put(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[1],
			product.getId());

		database.insert(DAOFactory.TABLE_OFFERS_PRODUCTS, null, values);
	    }
	}

    }

    @Override
    protected void writeDrinks(Set<Drink> drinks) {
	for (Drink drink : drinks) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[0], drink.getId());
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[1], drink.getName());
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[2], drink.getPrice());
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[3], drink.getIcon());
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[4], drink.getDiscount());
	    values.put(DAOFactory.COLUMNS_NAME_DRINKS[5], drink.getSize());

	    database.insert(DAOFactory.TABLE_DRINKS, null, values);
	}
    }

    @Override
    protected void writeShoppingCarts(Set<ShoppingCart> shoppingCarts) {
	for (ShoppingCart shoppingCart : shoppingCarts) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_SHOPPINGCARTS[0],
		    shoppingCart.getId());
	    database.insert(DAOFactory.TABLE_SHOPPINGCARTS, null, values);
	    values.clear();
	    for (Product product : shoppingCart.getProducts()) {
		values.put(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[0],
			shoppingCart.getId());
		values.put(COLUMNS_NAME_SHOPPINCART_PRODUCTS[1],
			product.getId());
		database.insert(DAOFactory.TABLE_SHOPPINGCART_PRODUCTS, null,
			values);
	    }
	}

    }

    @Override
    protected void writeOrders(Set<Order> orders) {
	for (Order order : orders) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_ORDERS[0], order.getId());
	    values.put(DAOFactory.COLUMNS_NAME_ORDERS[1], order.getEmail());
	    SimpleDateFormat dateFormat = new SimpleDateFormat(
		    "dd-MM-yyyy HH:mm:ss");
	    values.put(DAOFactory.COLUMNS_NAME_ORDERS[2],
		    dateFormat.format(order.getDateTime()));
	    values.put(DAOFactory.COLUMNS_NAME_ORDERS[3],
		    order.getPaymentMethod());
	    values.put(DAOFactory.COLUMNS_NAME_ORDERS[4], order.getAddress()
		    .getId());

	    database.insert(DAOFactory.TABLE_ORDERS, null, values);
	}

    }

    @Override
    protected void writeAddresses(Set<Address> addresses) {
	for (Address address : addresses) {
	    ContentValues values = new ContentValues();
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[0], address.getId());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[1], address.getStreet());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[2], address.getNumber());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[3],
		    address.getPostCode());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[4], address.getFloor());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[5], address.getStair());
	    values.put(DAOFactory.COLUMNS_NAME_ADDRESS[6], address.getFloor());

	    database.insert(DAOFactory.TABLE_ADDRESS, null, values);
	}

    }

    @Override
    protected void writePreferences(Map<String, Object> preferences) {

    }

    @Override
    protected void writeProducts(Set<Product> products) {
	// TODO Auto-generated method stub

    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
