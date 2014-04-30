package org.escoladeltreball.arcowabungaproject.model.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;

public abstract class DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    public static final String CREATE_DATA_BASE = "CREATE DATABASE cowabunga;";

    /* CREATE TABLES */

    public static final String CREATE_TABLE_RESOURCES = "CREATE TABLE resources("
	    + "id_resources INTEGER PRIMARY KEY," + "path VARCHAR(150));";
    public static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE ingredient("
	    + "id_ingredient INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50), "
	    + "icon SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "model SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "price NUMERIC);";
    public static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE ingredients("
	    + "id_ingredients INTEGER PRIMARY KEY,"
	    + "ingredient INTEGER FOREIGN KEY REFERNECES ingredient ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "num_ingredients SMALLINT NOT NULL);";
    public static final String CREATE_TABLE_PIZZAS = "CREATE TABLE pizzas("
	    + "id_pizza INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERNCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "massType VARCHAR(10)," + "type VARCHAR(10)," + "size SMALLINT,"
	    + "discount NUMERIC);";
    public static final String CREATE_TABLE_DRINKS = "CREATE TABLE drinks("
	    + "id_drink INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "discount NUMERIC," + "size SMALLINT);";
    public static final String CREATE_TABLE_OFFERS = "CREATE TABLE offers("
	    + "id_offers INTEGER PRIMARY KEY,"
	    + "name VARCHAR(30),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY  REFERNCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "discount NUMERIC);";
    public static final String CREATE_TABLE_OFFERS_PRODUCTS = "CREATE TABLE offers_products("
	    + "offer INTEGER FOREIGN KEY REFERENCES offers ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES pizza ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES drink ON DELTE CASCADE ON UPDATE CASCADE"
	    + ");";
    public static final String CREATE_TABLE_SHOPPINGCARTS = "CREATE TABLE shoppingcarts(id_shoopingcart INTEGER PRIMARY KEY);";
    public static final String CREATE_TABLE_SHOPPINCART_PRODUCTS = "CREATE TABLE shoopingcart_products("
	    + "shoppincart INTEGER FOREIGN KEY REFERENCES shoppincart ON DELTE CASCADE ON UPDATE CASCADE "
	    + "offer INTEGER FOREIGN KEY REFERENCES offers ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES pizza ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES drink ON DELTE CASCADE ON UPDATE CASCADE);";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE orders("
	    + "id_order PRIMARY KEY,"
	    + "email VARCHAR(50),"
	    + "date_time DATE,"
	    + "payment_method VARCHAR(15),"
	    + "addres SMALLINT FOREIGN KEY REFERENCES addres ON DELTE CASCADE ON UPDATE CASCADE)";
    public static final String CREATE_TABLE_ADDRESS = "CREATE TABLE address("
	    + "id_addres INTEGER PRIMARY KEY," + "number VARCHAR(3),"
	    + "post_code VARCHAR(5)," + "floor VARCHAR(3)" + "stair CHAR,"
	    + "door VARCHAR(2));";
    public static final String CREATE_TABLE_PREFERENCES = "CREATE TABLE preferences(key VARCHAR(30) PRIMARY KEY,"
	    + "value VARCHAR(50));";

    /* DROP TABLES */
    public static final String DROP_TABLE_RESOURCES = "DROP TABLE resources;";
    public static final String DROP_TABLE_INGREDIENT = "DROP TABLE ingredient;";
    public static final String DROP_TABLE_INGREDIENTS = "DROP TABLE ingredients;";
    public static final String DROP_TABLE_PIZZAS = "DROP TABLE pizzas;";
    public static final String DROP_TABLE_DRINKS = "DROP TABLE drinks;";
    public static final String DROP_TABLE_OFFERS = "DROP TABLE offers;";
    public static final String DROP_TABLE_OFFERS_PRODUCTS = "DROP TABLE offers_products;";
    public static final String DROP_TABLE_SHOPPINGCARTS = "DROP TABLE shoppingcarts;";
    public static final String DROP_TABLE_SHOPPINGCARTS_PRODUCTS = "DROP TABLE shoppingcart_products;";
    public static final String DROP_TABLE_ORDERS = "DROP TABLE orders;";
    public static final String DROP_TABLE_ADDRESS = "DROP TABLE address;";
    public static final String DROP_TABLE_PREFERENCES = "DROP TABLE preferences;";

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
	    "number", "post_code", "floor", "stair", "door" };
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

	// Pizza p1 = new Pizza(1, "A", 10, 150, 0, massType, type, size)

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

    protected abstract void writeAddress(Set<Address> address);

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
