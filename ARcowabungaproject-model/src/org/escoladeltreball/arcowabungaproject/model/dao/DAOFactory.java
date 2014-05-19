/*
 *  DAOFactory.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Copyright 2014 	Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  			Marc Sabate Piñol <masapim@hotmail.com>
 *  			Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *  			Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.escoladeltreball.arcowabungaproject.model.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.IdObject;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Ingredients;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;
import org.joda.time.DateTime;

public abstract class DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    public static final String CREATE_DATA_BASE = "CREATE DATABASE cowabunga;";

    /* TABLE NAMES */
    public static final String TABLE_PRODUCTS = "products";
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
    /* COLUMNS TYPE */
    public static final String[] COLUMNS_TYPE_PRODUCTS = { "INTEGER" };
    public static final String[] COLUMNS_TYPE_RESOURCES = { "INTEGER",
	    "VARCHAR" };
    public static final String[] COLUMNS_TYPE_INGREDIENT = { "INTEGER",
	    "VARCHAR", "SMALLINT", "SMALLINT", "NUMERIC", "VARCHAR" };
    public static final String[] COLUMNS_TYPE_INGREDIENTS = { "INTEGER",
	    "INTEGER", "SMALLINT" };
    public static final String[] COLUMNS_TYPE_PIZZAS = { "INTEGER", "VARCHAR",
	    "NUMERIC", "SMALLINT", "NUMERIC", "VARCHAR", "VARCHAR", "SMALLINT",
	    "INTEGER" };
    public static final String[] COLUMNS_TYPE_DRINKS = { "INTEGER", "VARCHAR",
	    "NUMERIC", "SMALLINT", "NUMERIC", "SMALLINT" };
    public static final String[] COLUMNS_TYPE_OFFERS = { "INTEGER", "VARCHAR",
	    "NUMERIC", "SMALLINT", "NUMERIC", "INTEGER" };
    public static final String[] COLUMNS_TYPE_OFFERS_PRODUCTS = { "INTEGER",
	    "INTEGER", "INTEGER" };
    public static final String[] COLUMNS_TYPE_SHOPPINGCARTS = { "INTEGER",
	    "INTEGER" };
    public static final String[] COLUMNS_TYPE_SHOPPINCART_PRODUCTS = {
	    "INTEGER", "INTEGER", "INTEGER" };
    public static final String[] COLUMNS_TYPE_ORDERS = { "INTEGER", "VARCHAR",
	    "VARCHAR", "DATE", "VARCHAR", "INTEGER", "INTEGER" };
    public static final String[] COLUMNS_TYPE_ADDRESS = { "INTEGER", "VARCHAR",
	    "VARCHAR", "VARCHAR", "VARCHAR", "CHAR", "VARCHAR" };
    public static final String[] COLUMNS_TYPE_PREFERENCES = { "VARCHAR",
	    "VARCHAR" };
    /* COLUMNS NAME */
    public static final String[] COLUMNS_NAME_PRODUCTS = { "id_product" };
    public static final String[] COLUMNS_NAME_RESOURCES = { "id_resource",
	    "path" };
    public static final String[] COLUMNS_NAME_INGREDIENT = { "id_ingredient",
	    "name", "icon", "model", "price", "texture" };
    public static final String[] COLUMNS_NAME_INGREDIENTS = { "id_ingredients",
	    "ingredient", "num_ingredient" };
    public static final String[] COLUMNS_NAME_PIZZAS = { "id_pizza", "name",
	    "price", "icon", "discount", "massType", "type", "size",
	    "ingredients" };
    public static final String[] COLUMNS_NAME_DRINKS = { "id_drink", "name",
	    "price", "icon", "discount", "size" };
    public static final String[] COLUMNS_NAME_OFFERS = { "id_offer", "name",
	    "price", "icon", "discount", "id_offers_product" };
    public static final String[] COLUMNS_NAME_OFFERS_PRODUCTS = {
	    "id_offers_product", "offer", "product" };
    public static final String[] COLUMNS_NAME_SHOPPINGCARTS = {
	    "id_shoopingcart", "id_shoopingcart_products" };
    public static final String[] COLUMNS_NAME_SHOPPINCART_PRODUCTS = {
	    "id_shoopingcart_products", "shoppingcart", "product" };
    public static final String[] COLUMNS_NAME_ORDERS = { "id_order", "email",
	    "phone", "date_time", "payment_method", "addres", "shopping_cart" };
    public static final String[] COLUMNS_NAME_ADDRESS = { "id_address",
	    "street", "number", "post_code", "floor", "stair", "door" };
    public static final String[] COLUMNS_NAME_PREFERENCES = { "key", "value" };

    /* CREATE TABLES */

    public static final String CREATE_TABLE_PRODUCTS = " CREATE TABLE "
	    + TABLE_PRODUCTS + " (" + COLUMNS_NAME_PRODUCTS[0]
	    + " INTEGER PRIMARY KEY);";
    public static final String CREATE_TABLE_RESOURCES = "CREATE TABLE "
	    + TABLE_RESOURCES + " (" + COLUMNS_NAME_RESOURCES[0]
	    + " INTEGER PRIMARY KEY," + COLUMNS_NAME_RESOURCES[1]
	    + " VARCHAR(150));";
    public static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE "
	    + TABLE_INGREDIENT + " (" + COLUMNS_NAME_INGREDIENT[0]
	    + " INTEGER PRIMARY KEY," + COLUMNS_NAME_INGREDIENT[1]
	    + " VARCHAR(50)," + COLUMNS_NAME_INGREDIENT[2]
	    + " SMALLINT REFERENCES " + TABLE_RESOURCES
	    + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_INGREDIENT[3] + " SMALLINT REFERENCES "
	    + TABLE_RESOURCES + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_INGREDIENT[4] + " NUMERIC, "
	    + COLUMNS_NAME_INGREDIENT[5] + " VARCHAR(100));";
    public static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE "
	    + TABLE_INGREDIENTS + " (" + COLUMNS_NAME_INGREDIENTS[0]
	    + " INTEGER," + COLUMNS_NAME_INGREDIENTS[1]
	    + " INTEGER REFERENCES " + TABLE_INGREDIENT
	    + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_INGREDIENTS[2] + " SMALLINT NOT NULL);";
    public static final String CREATE_TABLE_PIZZAS = "CREATE TABLE "
	    + TABLE_PIZZAS + " (" + COLUMNS_NAME_PIZZAS[0]
	    + " INTEGER REFERENCES " + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + COLUMNS_NAME_PIZZAS[1]
	    + " VARCHAR(50)," + COLUMNS_NAME_PIZZAS[2] + " NUMERIC,"
	    + COLUMNS_NAME_PIZZAS[3] + " SMALLINT REFERENCES "
	    + TABLE_RESOURCES + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_PIZZAS[4] + " NUMERIC," + COLUMNS_NAME_PIZZAS[5]
	    + " VARCHAR(10)," + COLUMNS_NAME_PIZZAS[6] + " VARCHAR(10),"
	    + COLUMNS_NAME_PIZZAS[7] + " SMALLINT, " + COLUMNS_NAME_PIZZAS[8]
	    + " INTEGER);";
    public static final String CREATE_TABLE_DRINKS = "CREATE TABLE "
	    + TABLE_DRINKS + " (" + COLUMNS_NAME_DRINKS[0]
	    + " INTEGER REFERENCES " + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + COLUMNS_NAME_DRINKS[1]
	    + " VARCHAR(50)," + COLUMNS_NAME_DRINKS[2] + " NUMERIC,"
	    + COLUMNS_NAME_DRINKS[3] + " SMALLINT REFERENCES "
	    + TABLE_RESOURCES + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_DRINKS[4] + " NUMERIC," + COLUMNS_NAME_DRINKS[5]
	    + " SMALLINT);";
    public static final String CREATE_TABLE_OFFERS = "CREATE TABLE "
	    + TABLE_OFFERS + " (" + COLUMNS_NAME_OFFERS[0]
	    + " INTEGER REFERENCES " + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + COLUMNS_NAME_OFFERS[1]
	    + " VARCHAR(30)," + COLUMNS_NAME_OFFERS[2] + " NUMERIC,"
	    + COLUMNS_NAME_OFFERS[3] + " SMALLINT REFERENCES "
	    + TABLE_RESOURCES + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_OFFERS[4] + " NUMERIC," + COLUMNS_NAME_OFFERS[5]
	    + " INTEGER);";
    public static final String CREATE_TABLE_OFFERS_PRODUCTS = "CREATE TABLE "
	    + TABLE_OFFERS_PRODUCTS + " (" + COLUMNS_NAME_OFFERS_PRODUCTS[0]
	    + " INTEGER," + COLUMNS_NAME_OFFERS_PRODUCTS[1]
	    + " INTEGER REFERENCES " + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + COLUMNS_NAME_OFFERS[2]
	    + " INTEGER REFERENCES " + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_SHOPPINGCARTS = "CREATE TABLE "
	    + TABLE_SHOPPINGCARTS + " (" + COLUMNS_NAME_SHOPPINGCARTS[0]
	    + " INTEGER PRIMARY KEY, " + COLUMNS_NAME_SHOPPINGCARTS[1]
	    + " INTEGER);";
    public static final String CREATE_TABLE_SHOPPINCART_PRODUCTS = "CREATE TABLE "
	    + TABLE_SHOPPINGCART_PRODUCTS
	    + " ("
	    + COLUMNS_NAME_SHOPPINCART_PRODUCTS[0]
	    + " INTEGER,"
	    + COLUMNS_NAME_SHOPPINCART_PRODUCTS[1]
	    + " INTEGER REFERENCES "
	    + TABLE_SHOPPINGCARTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE,"
	    + COLUMNS_NAME_OFFERS[2]
	    + " INTEGER REFERENCES "
	    + TABLE_PRODUCTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
	    + TABLE_ORDERS + "(" + COLUMNS_NAME_ORDERS[0]
	    + " INTEGER PRIMARY KEY," + COLUMNS_NAME_ORDERS[1] + " VARCHAR(9),"
	    + COLUMNS_NAME_ORDERS[2] + " VARCHAR(50)," + COLUMNS_NAME_ORDERS[3]
	    + " DATE," + COLUMNS_NAME_ORDERS[4] + " VARCHAR(15),"
	    + COLUMNS_NAME_ORDERS[5] + " INTEGER REFERENCES " + TABLE_ADDRESS
	    + " ON DELETE CASCADE ON UPDATE CASCADE," + COLUMNS_NAME_ORDERS[6]
	    + " INTEGER REFERENCES " + TABLE_SHOPPINGCARTS
	    + " ON DELETE CASCADE ON UPDATE CASCADE)";
    public static final String CREATE_TABLE_ADDRESS = "CREATE TABLE "
	    + TABLE_ADDRESS + " (" + COLUMNS_NAME_ADDRESS[0]
	    + " INTEGER PRIMARY KEY," + COLUMNS_NAME_ADDRESS[1]
	    + " VARCHAR(15)," + COLUMNS_NAME_ADDRESS[2] + " VARCHAR(3),"
	    + COLUMNS_NAME_ADDRESS[3] + " VARCHAR(5),"
	    + COLUMNS_NAME_ADDRESS[4] + " VARCHAR(3),"
	    + COLUMNS_NAME_ADDRESS[5] + " CHAR," + COLUMNS_NAME_ADDRESS[6]
	    + " VARCHAR(2));";
    public static final String CREATE_TABLE_PREFERENCES = "CREATE TABLE "
	    + TABLE_PREFERENCES + " (" + COLUMNS_NAME_PREFERENCES[0]
	    + " VARCHAR(30) PRIMARY KEY," + COLUMNS_NAME_PREFERENCES[1]
	    + " VARCHAR(50));";

    /* DROP TABLES */
    public static final String DROP_TABLE_PRODUCTS = "DROP TABLE "
	    + TABLE_PRODUCTS + ";";
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

    // ====================
    // ATTRIBUTES
    // ====================

    private int currentVersion;
    protected static DAOFactory instance;
    private Pizzeria pizzeria;

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOFactory() {
	this.pizzeria = Pizzeria.getInstance(this);
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public boolean loadData() {
	Map<String, String> preferences = readPreferences();
	for (Map.Entry<String, String> entry : preferences.entrySet()) {
	    if (entry.getKey().equals("next_id")) {
		IdObject.setNextId(Integer.parseInt(entry.getValue()));
	    } else if (entry.getKey().equals("next_custom_id")) {
		IdObject.setNextCostumId(Integer.parseInt(entry.getValue()));
	    } else if (entry.getKey().equals("current_version")) {
		currentVersion = Integer.parseInt(entry.getValue());
	    }
	}
	// INCOMPLETE
	return false;
    }

    public boolean loadLocalData() {
	Set<Pizza> pizzes = readPizza();
	for (Pizza pizza : pizzes) {
	    if (pizza.getType().equals(Pizza.TYPE_COSTUM_SAVED)) {
		pizzeria.addCustomSavedPizza(pizza);
	    } else if (pizza.getType().equals(Pizza.TYPE_PREDEFINED)) {
		pizzeria.addPredefinedPizza(pizza);
	    }
	}

	pizzeria.setDrinks(readDrink());
	pizzeria.setIngredients(readIngredient());
	pizzeria.setOffers(readOffer());
	pizzeria.setOrdersSaved(readOrder());

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

	Ingredient i1 = new Ingredient(8, "Mushroom", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i2 = new Ingredient(9, "Red Pepper", 1.5f, 151, 152,
		"data/models/ingredients/redpepper_texture.png");
	Ingredient i3 = new Ingredient(10, "Ham", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i4 = new Ingredient(11, "Bacon", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i5 = new Ingredient(12, "Pineapple", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i6 = new Ingredient(13, "Corn", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i7 = new Ingredient(14, "Chicken", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i8 = new Ingredient(15, "Blue cheese", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i9 = new Ingredient(16, "Goat cheese", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");
	Ingredient i10 = new Ingredient(17, "Gouda", 1.5f, 151, 152,
		"data/models/ingredients/mushroom_texture.png");

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

	Pizza p1 = new Pizza(1, "P1", 30, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_SMALL);
	Pizza p2 = new Pizza(2, "P2", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_LARGE);
	Pizza p3 = new Pizza(3, "P3", 7, 150, 0, Pizza.MASSTYPE_THIN,
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
	p1.addIngredient(i3, 1);
	p1.addIngredient(i4, 1);
	p1.addIngredient(i5, 1);
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

	pizzeria.setShoppingCart(s2);

	IdObject.setNextId(44);
	IdObject.setNextCostumId(10044);
	currentVersion = -1;

	return true;
    }

    public void writeDataBase() {
	writeIngredients(pizzeria.getIngredients());
	writePizzas(pizzeria.getCustomSavedPizzas());
	writePizzas(pizzeria.getPredefinedPizzas());
	writeOffers(pizzeria.getOffers());
	writeDrinks(pizzeria.getDrinks());
	writeOrders(pizzeria.getOrdersSaved());
	Map<String, String> preferences = new HashMap<String, String>();
	preferences.put("current_version", currentVersion + "");
	preferences.put("next_id", IdObject.getNextId() + "");
	preferences.put("next_custom_id", IdObject.getNextCustomId() + "");

	writePreferences(preferences);
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    /**
     * Returns a map with Ingredient object as key and number of that ingredient
     * as value by the id of ingredients table.
     * 
     * @param id
     *            of ingredients table.
     * @return Ingredients Object
     */
    protected abstract Ingredients selectIngredientsById(int id);

    /**
     * Returns a list of products that can have a offer by its id. the product
     * offer can have a pizza or a drink
     * 
     * @param id
     *            of product
     * @return Return a list of products.
     */
    protected abstract List<Product> selectProductsOffersById(int id);

    /**
     * Returns a list of products that can have a shopping cart by its id. the
     * product of shopping cart can have a pizza, a drink or a offer.
     * 
     * @param id
     *            of product
     * @return Return a list of products.
     */
    protected abstract List<Product> selectShoppingCartProductsById(int id);

    protected abstract Set<Ingredient> readIngredient();

    protected abstract Set<Pizza> readPizza();

    protected abstract Set<Offer> readOffer();

    protected abstract Set<Drink> readDrink();

    protected abstract ShoppingCart readShoppingCart(int idShoppingCart);

    protected abstract Set<Order> readOrder();

    protected abstract Address readAddressById(int idAddress);

    protected abstract Map<String, String> readPreferences();

    protected abstract Map<String, String> readResources();

    protected abstract void writeProduct(int idProduct);

    protected abstract void writeIngredients(Set<Ingredient> ingredients);

    protected abstract void writePizzas(Set<Pizza> pizzas);

    protected abstract void writeOffers(Set<Offer> offers);

    protected abstract void writeDrinks(Set<Drink> drinks);

    protected abstract void writeShoppingCarts(ShoppingCart shoppingCart);

    protected abstract void writeOrders(Set<Order> orders);

    protected abstract void writeAddresses(Address address);

    protected abstract void writePreferences(Map<String, String> preferences);

    protected abstract void writeResources(Map<String, String> resources);

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

    public void setPizzeria(Pizzeria pizzeria) {
	this.pizzeria = pizzeria;
    }

    public int getCurrentVersion() {
	return currentVersion;
    }
}
