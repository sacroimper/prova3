package org.escoladeltreball.arcowabungaproject.model.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Ingredients;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;
import org.joda.time.DateTime;

public abstract class DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    public static final String CREATE_DATA_BASE = "CREATE DATABASE cowabunga;";

    /* TABLE NAMES */
    public static final String TABLE_RESOURCES = "resources";
    public static final String TABLE_INGREDIENT = "ingredient";
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String TABLE_PIZZAS = "pizzas";
    public static final String TABLE_DRINKS = "drinks";
    public static final String TABLE_OFFERS = "offers";
    public static final String TABLE_OFFERS_PRODUCTS = "offers_products";
    public static final String TABLE_SHOPPINGCARTS = "shoppingcarts";
    public static final String TABLE_SHOPPINGCART_PRODUCTS = "shoppingcart_products";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_ADDRESS = "address";
    public static final String TABLE_PREFERENCES = "preferences";

    /* CREATE TABLES */

    public static final String CREATE_TABLE_RESOURCES = "CREATE TABLE "
	    + TABLE_RESOURCES + "(" + "id_resources INTEGER PRIMARY KEY,"
	    + "path VARCHAR(150));";
    public static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE "
	    + TABLE_INGREDIENT + "(" + "id_ingredient INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50), " + "icon SMALLINT FOREIGN KEY REFERENCES "
	    + TABLE_RESOURCES + "ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "model SMALLINT FOREIGN KEY REFERENCES " + TABLE_RESOURCES
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + "price NUMERIC);";
    public static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE "
	    + TABLE_INGREDIENTS + "(" + "id_ingredients INTEGER PRIMARY KEY,"
	    + "ingredient INTEGER FOREIGN KEY REFERNECES " + TABLE_INGREDIENT
	    + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "num_ingredients SMALLINT NOT NULL);";
    public static final String CREATE_TABLE_PIZZAS = "CREATE TABLE "
	    + TABLE_PIZZAS + "(" + "id_pizza INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50)," + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERNCES " + TABLE_RESOURCES
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + "massType VARCHAR(10),"
	    + "type VARCHAR(10)," + "size SMALLINT,"
	    + "discount NUMERIC, ingredients INTEGER FOREIGN KEY REFERENCES "
	    + TABLE_INGREDIENTS + "ON DELETE CASCADE ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_DRINKS = "CREATE TABLE "
	    + TABLE_DRINKS + "(" + "id_drink INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50)," + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERENCES " + TABLE_RESOURCES
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + "discount NUMERIC,"
	    + "size SMALLINT);";
    public static final String CREATE_TABLE_OFFERS = "CREATE TABLE "
	    + TABLE_OFFERS + "(" + "id_offers INTEGER PRIMARY KEY,"
	    + "name VARCHAR(30)," + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY  REFERNCES " + TABLE_RESOURCES
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + "discount NUMERIC);";
    public static final String CREATE_TABLE_OFFERS_PRODUCTS = "CREATE TABLE "
	    + TABLE_OFFERS_PRODUCTS + "("
	    + "offer INTEGER FOREIGN KEY REFERENCES " + TABLE_OFFERS
	    + " ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES " + TABLE_PIZZAS
	    + " ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES " + TABLE_DRINKS
	    + " ON DELTE CASCADE ON UPDATE CASCADE" + ");";
    public static final String CREATE_TABLE_SHOPPINGCARTS = "CREATE TABLE "
	    + TABLE_SHOPPINGCARTS + "(id_shoopingcart INTEGER PRIMARY KEY);";
    public static final String CREATE_TABLE_SHOPPINCART_PRODUCTS = "CREATE TABLE "
	    + TABLE_SHOPPINGCART_PRODUCTS
	    + "("
	    + "shoppincart INTEGER FOREIGN KEY REFERENCES "
	    + TABLE_SHOPPINGCARTS
	    + " ON DELTE CASCADE ON UPDATE CASCADE "
	    + "offer INTEGER FOREIGN KEY REFERENCES "
	    + TABLE_OFFERS
	    + " ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES "
	    + TABLE_PIZZAS
	    + " ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES "
	    + TABLE_DRINKS
	    + " ON DELTE CASCADE ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
	    + TABLE_ORDERS + "(" + "id_order PRIMARY KEY,"
	    + "email VARCHAR(50)," + "date_time DATE,"
	    + "payment_method VARCHAR(15),"
	    + "addres SMALLINT FOREIGN KEY REFERENCES " + TABLE_ADDRESS
	    + " ON DELTE CASCADE ON UPDATE CASCADE)";
    public static final String CREATE_TABLE_ADDRESS = "CREATE TABLE "
	    + TABLE_ADDRESS + "(" + "id_addres INTEGER PRIMARY KEY,"
	    + "street VARCHAR(15)" + "number VARCHAR(3),"
	    + "post_code VARCHAR(5)," + "floor VARCHAR(3)" + "stair CHAR,"
	    + "door VARCHAR(2));";
    public static final String CREATE_TABLE_PREFERENCES = "CREATE TABLE "
	    + TABLE_PREFERENCES + "(key VARCHAR(30) PRIMARY KEY,"
	    + "value VARCHAR(50));";

    /* DROP TABLES */
    public static final String DROP_TABLE_RESOURCES = "DROP TABLE "
	    + TABLE_RESOURCES + ";";
    public static final String DROP_TABLE_INGREDIENT = "DROP TABLE "
	    + TABLE_INGREDIENT + ";";
    public static final String DROP_TABLE_INGREDIENTS = "DROP TABLE "
	    + TABLE_INGREDIENTS + ";";
    public static final String DROP_TABLE_PIZZAS = "DROP TABLE " + TABLE_PIZZAS
	    + ";";
    public static final String DROP_TABLE_DRINKS = "DROP TABLE " + TABLE_DRINKS
	    + ";";
    public static final String DROP_TABLE_OFFERS = "DROP TABLE " + TABLE_OFFERS
	    + ";";
    public static final String DROP_TABLE_OFFERS_PRODUCTS = "DROP TABLE "
	    + TABLE_OFFERS_PRODUCTS + ";";
    public static final String DROP_TABLE_SHOPPINGCARTS = "DROP TABLE "
	    + TABLE_SHOPPINGCARTS + ";";
    public static final String DROP_TABLE_SHOPPINGCARTS_PRODUCTS = "DROP TABLE "
	    + TABLE_SHOPPINGCART_PRODUCTS + ";";
    public static final String DROP_TABLE_ORDERS = "DROP TABLE " + TABLE_ORDERS
	    + ";";
    public static final String DROP_TABLE_ADDRESS = "DROP TABLE "
	    + TABLE_ADDRESS + ";";
    public static final String DROP_TABLE_PREFERENCES = "DROP TABLE "
	    + TABLE_PREFERENCES + ";";

    /* COLUMNS NAME */
    public static final String[] COLUMNS_NAME_RESOURCES = { "id_resources",
	    "path" };
    public static final String[] COLUMNS_NAME_INGREDIENT = { "id_ingredient",
	    "name", "icon", "model", "price" };
    public static final String[] COLUMNS_NAME_INGREDIENTS = { "id_ingredients",
	    "ingredient", "num_ingredient" };
    public static final String[] COLUMNS_NAME_PIZZAS = { "id_pizzas", "name",
	    "price", "icon", "massType", "type", "size", "discount" };
    public static final String[] COLUMNS_NAME_DRINKS = { "id_drink", "name",
	    "price", "icon", "discount", "size" };
    public static final String[] COLUMNS_NAME_OFFERS = { "id_offers", "name",
	    "price", "icon", "discount" };
    public static final String[] COLUMNS_NAME_OFFERS_PRODUCTS = { "offer",
	    "pizza", "drink" };
    public static final String[] COLUMNS_NAME_SHOPPINGCARTS = { "id_shoopingcart" };
    public static final String[] COLUMNS_NAME_SHOPPINCART_PRODUCTS = {
	    "shoppingcart", "offer", "pizza", "drink" };
    public static final String[] COLUMNS_NAME_ORDERS = { "id_order", "email",
	    "date_time", "payment_method", "addres" };
    public static final String[] COLUMNS_NAME_ADDRESS = { "id_address",
	    "street", "number", "post_code", "floor", "stair", "door" };
    public static final String[] COLUMNS_NAME_PREFERENCES = { "key", "value" };

    // ====================
    // ATTRIBUTES
    // ====================

    private int currentVersion;
    protected static DAOFactory instance;
    private Pizzeria pizzeria;

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOFactory(Pizzeria pizzeria) {
	this.pizzeria = pizzeria;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public boolean loadData() {

	return false;
    }

    public boolean loadDemo() {
	Set<Pizza> predefinedPizzas = new HashSet<Pizza>();
	Set<Pizza> customSavedPizzas = new HashSet<Pizza>();
	Set<Pizza> customTemporaryPizzas = new HashSet<Pizza>();
	Set<Order> ordersSaved = new HashSet<Order>();
	Set<Ingredient> ingredients = new HashSet<Ingredient>();
	Set<Drink> drinks = new HashSet<Drink>();
	Set<Offer> offers = new HashSet<Offer>();

	Ingredient i1 = new Ingredient(8, "I1", 1.5f, 151, 152);
	Ingredient i2 = new Ingredient(9, "I2", 1.5f, 151, 152);
	Ingredient i3 = new Ingredient(10, "I3", 1.5f, 151, 152);
	Ingredient i4 = new Ingredient(11, "I4", 1.5f, 151, 152);
	Ingredient i5 = new Ingredient(12, "I5", 1.5f, 151, 152);
	Ingredient i6 = new Ingredient(13, "I6", 1.5f, 151, 152);
	Ingredient i7 = new Ingredient(14, "I7", 1.5f, 151, 152);
	Ingredient i8 = new Ingredient(15, "I8", 1.5f, 151, 152);
	Ingredient i9 = new Ingredient(16, "I9", 1.5f, 151, 152);
	Ingredient i10 = new Ingredient(17, "I10", 1.5f, 151, 152);

	ingredients.add(i1);
	ingredients.add(i2);
	ingredients.add(i3);
	ingredients.add(i4);
	ingredients.add(i5);
	ingredients.add(i6);
	ingredients.add(i7);
	ingredients.add(i8);
	ingredients.add(i9);
	ingredients.add(i10);

	Pizza p1 = new Pizza(1, "P1", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_SMALL);
	Pizza p2 = new Pizza(2, "P2", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_LARGE);
	Pizza p3 = new Pizza(3, "P3", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_MEDIUM);
	Pizza p4 = new Pizza(10004, "PC1", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_COSTUM_SAVED, Pizza.SIZE_SMALL);
	Pizza p5 = new Pizza(10005, "PC2", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_COSTUM_SAVED, Pizza.SIZE_SMALL);
	Pizza p6 = new Pizza(10006, "PT1", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_COSTUM_TEMPORARY, Pizza.SIZE_COWABUNGA);
	Pizza p7 = new Pizza(10007, "PT2", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_COSTUM_TEMPORARY, Pizza.SIZE_MEDIUM);

	Ingredients is1 = new Ingredients(28);
	Ingredients is2 = new Ingredients(29);
	Ingredients is3 = new Ingredients(30);
	Ingredients is4 = new Ingredients(10031);
	Ingredients is5 = new Ingredients(10032);
	Ingredients is6 = new Ingredients(10033);
	Ingredients is7 = new Ingredients(10034);

	p1.setIngredients(is1);
	p1.setIngredients(is2);
	p1.setIngredients(is3);
	p1.setIngredients(is4);
	p1.setIngredients(is5);
	p1.setIngredients(is6);
	p1.setIngredients(is7);

	p1.addIngredient(i1, 1);
	p1.addIngredient(i2, 1);
	p1.addIngredient(i6, 1);
	p1.addIngredient(i7, 1);
	p2.addIngredient(i2, 1);
	p2.addIngredient(i10, 1);
	p2.addIngredient(i5, 1);
	p2.addIngredient(i6, 1);
	p2.addIngredient(i8, 1);
	p3.addIngredient(i1, 1);
	p3.addIngredient(i10, 1);
	p3.addIngredient(i3, 1);
	p4.addIngredient(i4, 1);
	p4.addIngredient(i8, 1);
	p4.addIngredient(i4, 1);
	p4.addIngredient(i6, 1);
	p4.addIngredient(i8, 1);
	p5.addIngredient(i6, 1);
	p5.addIngredient(i2, 1);
	p5.addIngredient(i1, 1);
	p6.addIngredient(i7, 1);
	p6.addIngredient(i3, 1);
	p6.addIngredient(i6, 1);
	p6.addIngredient(i10, 1);
	p7.addIngredient(i6, 1);
	p7.addIngredient(i8, 1);
	p7.addIngredient(i5, 1);
	p7.addIngredient(i4, 1);

	predefinedPizzas.add(p1);
	predefinedPizzas.add(p2);
	predefinedPizzas.add(p3);
	customSavedPizzas.add(p4);
	customSavedPizzas.add(p5);
	customTemporaryPizzas.add(p6);
	customTemporaryPizzas.add(p7);

	Drink d1 = new Drink(18, "D1", 5, 153, 0, Drink.SIZE_MEDIUM);
	Drink d2 = new Drink(19, "D2", 6, 153, 0, Drink.SIZE_LARGE);
	Drink d3 = new Drink(20, "D3", 5, 153, 0, Drink.SIZE_MEDIUM);
	Drink d4 = new Drink(21, "D4", 4, 153, 0, Drink.SIZE_LARGE);
	Drink d5 = new Drink(22, "D5", 3, 153, 0, Drink.SIZE_SMALL);
	Drink d6 = new Drink(23, "D6", 5, 153, 0, Drink.SIZE_SMALL);

	drinks.add(d1);
	drinks.add(d2);
	drinks.add(d3);
	drinks.add(d4);
	drinks.add(d5);
	drinks.add(d6);

	Offer of1 = new Offer(24, "OF1", 15, 154, 0);
	Offer of2 = new Offer(25, "OF2", 15, 154, 0);
	Offer of3 = new Offer(26, "OF3", 15, 154, 0);
	Offer of4 = new Offer(27, "OF4", 15, 154, 0);

	of1.addProduct(p1);
	of1.addProduct(p3);
	of1.addProduct(d1);
	of2.addProduct(p2);
	of2.addProduct(p2);
	of3.addProduct(d5);
	of3.addProduct(d2);
	of3.addProduct(d3);
	of4.addProduct(p1);
	of4.addProduct(p2);
	of4.addProduct(d3);
	of4.addProduct(d4);

	offers.add(of1);
	offers.add(of2);
	offers.add(of3);
	offers.add(of4);

	Address a1 = new Address(35, "a", "a", "a", "a", "a", "a");
	Address a2 = new Address(36, "b", "b", "b", "b", "b", "b");
	Address a3 = new Address(37, "c", "c", "c", "c", "c", "c");

	DateTime dt1 = DateTime.now().minusDays(3);
	DateTime dt2 = DateTime.now().minusDays(25);
	DateTime dt3 = DateTime.now().minusDays(10);

	ShoppingCart s1 = new ShoppingCart(38);
	ShoppingCart s2 = new ShoppingCart(39);
	ShoppingCart s3 = new ShoppingCart(40);

	s1.addProduct(p1);
	s1.addProduct(d2);
	s1.addProduct(p2);
	s1.addProduct(d3);
	s2.addProduct(p6);
	s2.addProduct(p7);
	s2.addProduct(p5);
	s2.addProduct(of1);
	s2.addProduct(d4);
	s3.addProduct(p7);
	s3.addProduct(d5);
	s3.addProduct(p4);
	s3.addProduct(p3);

	Order or1 = new Order(41, "a", "a", dt1, "a", a1, s1);
	Order or2 = new Order(42, "b", "b", dt2, "b", a1, s2);
	Order or3 = new Order(43, "c", "c", dt3, "c", a1, s3);

	ordersSaved.add(or1);
	ordersSaved.add(or2);
	ordersSaved.add(or3);

	pizzeria.setPredefinedPizzas(predefinedPizzas);
	pizzeria.setCustomSavedPizzas(customSavedPizzas);
	pizzeria.setCustomTemporaryPizzas(customTemporaryPizzas);
	pizzeria.setIngredients(ingredients);
	pizzeria.setDrinks(drinks);
	pizzeria.setOffers(offers);
	pizzeria.setOrdersSaved(ordersSaved);

	return true;
    }

    public void writeDataBase() {
	writeIngredients(pizzeria.getIngredients());
	writePizzas(pizzeria.getCustomSavedPizzas());
	writePizzas(pizzeria.getPredefinedPizzas());
	writeOffers(pizzeria.getOffers());
	writeDrinks(pizzeria.getDrinks());
	// writeShoppingCarts(pizzeria.getShoppingCart());
	writeOrders(pizzeria.getOrdersSaved());
	// writeAddres();
	// writePreferences();
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    protected abstract Set<Ingredient> readIngredient();

    protected abstract Set<Pizza> readPizza();

    protected abstract Set<Offer> readOffer();

    protected abstract Set<Drink> readDrink();

    protected abstract Set<ShoppingCart> readShoppingCart();

    protected abstract Set<Order> readOrder();

    protected abstract Set<Address> readAddress();

    protected abstract Map<String, Object> readPreferences();

    protected abstract void writeIngredients(Set<Ingredient> ingredients);

    protected abstract void writePizzas(Set<Pizza> pizzas);

    protected abstract void writeOffers(Set<Offer> offers);

    protected abstract void writeDrinks(Set<Drink> drinks);

    protected abstract void writeShoppingCarts(Set<ShoppingCart> shoppingCarts);

    protected abstract void writeOrders(Set<Order> orders);

    protected abstract void writeAddresses(Set<Address> addresses);

    protected abstract void writePreferences(Map<String, Object> preferences);

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

}
