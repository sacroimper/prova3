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
	Pizza p4 = new Pizza(4, "PC1", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_COSTUM_SAVED, Pizza.SIZE_SMALL);
	Pizza p5 = new Pizza(5, "PC2", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_COSTUM_SAVED, Pizza.SIZE_SMALL);
	Pizza p6 = new Pizza(6, "PT1", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_COSTUM_TEMPORARY, Pizza.SIZE_COWABUNGA);
	Pizza p7 = new Pizza(7, "PT2", 10, 150, 0, Pizza.MASSTYPE_THICK,
		Pizza.TYPE_COSTUM_TEMPORARY, Pizza.SIZE_MEDIUM);

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
