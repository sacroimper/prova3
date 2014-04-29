package org.escoladeltreball.arcowabungaproject.model.dao;

public class DAOFactory {
    // ====================
    // CONSTANTS
    // ====================
    private static final String CREATE_DATA_BASE = "CREATE DATABASE cowabunga;";
    private static final String CREATE_TABLE_RESOURCES = "CREATE TABLE resources("
	    + "id_resources INTEGER PRIMARY KEY," + "path VARCHAR(150));";
    private static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE ingredient("
	    + "id_ingredient INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50), "
	    + "icon SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "model SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "price NUMERIC);";
    private static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE ingredients("
	    + "id_ingredients INTEGER PRIMARY KEY,"
	    + "ingredient INTEGER FOREIGN KEY REFERNECES ingredient ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "num_ingredients SMALLINT NOT NULL);";
    private static final String CREATE_TABLE_PIZZAS = "CREATE TABLE pizzas("
	    + "id_pizza INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERNCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "massType VARCHAR(10)," + "type VARCHAR(10)," + "size SMALLINT);";
    private static final String CREATE_TABLE_DRINKS = "CREATE TABLE drinks("
	    + "id_drink INTEGER PRIMARY KEY,"
	    + "name VARCHAR(50),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY REFERENCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "discount NUMERIC," + "size SMALLINT);";
    private static final String CREATE_TABLE_OFFERS = "CREATE TABLE offers("
	    + "id_offers INTEGER PRIMARY KEY,"
	    + "name VARCHAR(30),"
	    + "price NUMERIC,"
	    + "icon SMALLINT FOREIGN KEY  REFERNCES resources ON DELETE CASCADE ON UPDATE CASCADE,"
	    + "discount NUMERIC);";
    private static final String CREATE_TABLE_OFFERS_PRODUCTS = "CREATE TABLE offers_products("
	    + "offer INTEGER FOREIGN KEY REFERENCES offers ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES pizza ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES drink ON DELTE CASCADE ON UPDATE CASCADE"
	    + ");";
    private static final String CREATE_TABLE_SHOPPINGCARTS = "CREATE TABLE shoppingcarts(id_shoopingcart INTEGER PRIMARY KEY);";
    private static final String CREATE_TABLE_SHOPPINCART_PRODUCTS = "CREATE TABLE shoopingcart_products("
	    + "shoppincart INTEGER FOREIGN KEY REFERENCES shoppincart ON DELTE CASCADE ON UPDATE CASCADE "
	    + "offer INTEGER FOREIGN KEY REFERENCES offers ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "pizza INTEGER FOREIGN KEY REFERNCES pizza ON DELTE CASCADE ON UPDATE CASCADE,"
	    + "drink INTEGER FOREIGN KEY REFERNCES drink ON DELTE CASCADE ON UPDATE CASCADE);";
    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE orders("
	    + "id_order PRIMARY KEY,"
	    + "email VARCHAR(50),"
	    + "date_time DATE,"
	    + "payment_method VARCHAR(15),"
	    + "addres SMALLINT FOREIGN KEY REFERENCES addres ON DELTE CASCADE ON UPDATE CASCADE)";
    private static final String CREATE_TABLE_ADDRES = "CREATE TABLE addres("
	    + "id_addres INTEGER PRIMARY KEY," + "number VARCHAR(3),"
	    + "post_code VARCHAR(5)," + "floor VARCHAR(3)" + "stair CHAR,"
	    + "door VARCHAR(2));";
    private static final String CREATE_TABLE_PREFERENCES = "CREATE TABLE preferences(key VARCHAR(30) PRIMARY KEY,"
	    + "value VARCHAR(50));";
    // ====================
    // ATTRIBUTES
    // ====================
    private int currentVersion;
    private static DAOFactory instance;
    private System system;

    // ====================
    // CONSTRUCTORS
    // ====================
    private DAOFactory(System system) {
	this.system = system;
    }

    // ====================
    // PUBLIC METHODS
    // ====================
    public static DAOFactory getInstance(System system) {
	if (instance == null) {
	    instance = new DAOFactory(system);
	}
	return instance;
    }

    public boolean loadData() {

	return false;
    }

    public boolean loadLocalData() {
	return false;
    }

    public boolean loadServerData() {
	return false;
    }

    public boolean writeData() {
	return false;
    }
    // ====================
    // PROTECTED METHODS
    // ====================

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
