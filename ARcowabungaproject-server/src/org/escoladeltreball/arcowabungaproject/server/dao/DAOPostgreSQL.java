/*
 *  DAOPostgreSQL.java
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

package org.escoladeltreball.arcowabungaproject.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.joda.time.DateTime;

public class DAOPostgreSQL extends DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private static DAOPostgreSQL instance;
    private Connection con;

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOPostgreSQL() {
	super();
	connectToDatabase();
    }

    private void connectToDatabase() {
	try {
	    Class.forName("org.postgresql.Driver");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("Driver Cargado");
	Connection con = null;
	try {
	    this.con = DriverManager.getConnection(
		    "jdbc:postgresql://localhost:5432/cowabunga", "usr", "");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	System.out.println("Conenexion realizada");
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public static DAOPostgreSQL getInstance() {
	if (instance == null) {
	    instance = new DAOPostgreSQL();
	}
	return instance;
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

    @Override
    protected Ingredients selectIngredientsById(int id) {
	Ingredients ingredients = new Ingredients(id);
	Statement stm;
	try {
	    stm = this.con.createStatement();

	    /*
	     * Select all rows with the same id_ingredients from ingredients
	     * table
	     */
	    ResultSet rsIngredients = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_INGREDIENTS + " WHERE "
		    + DAOFactory.COLUMNS_NAME_INGREDIENTS[0] + "=" + id + ";");
	    while (rsIngredients.next()) {
		/*
		 * Select all rows with the same id_ingredient from ingredient
		 * table and put in the map
		 */
		ResultSet rsIngredient = stm.executeQuery("SELECT * FROM "
			+ DAOFactory.TABLE_INGREDIENT
			+ " WHERE "
			+ DAOFactory.COLUMNS_NAME_INGREDIENT[0]
			+ "="
			+ rsIngredients
				.getInt(DAOFactory.COLUMNS_NAME_INGREDIENTS[1])
			+ ";");
		while (rsIngredient.next()) {

		    Ingredient ingredient = new Ingredient(
			    rsIngredient.getInt(0), rsIngredient.getString(1),
			    rsIngredient.getInt(2), rsIngredient.getInt(3),
			    rsIngredient.getInt(4));
		    ingredients.put(ingredient, rsIngredient.getInt(2));
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ingredients;
    }

    @Override
    protected List<Product> selectProductsOffersById(int id) {
	List<Product> productsList = new ArrayList<Product>();
	try {
	    Statement stm = this.con.createStatement();
	    // Select from offers products table the offers with the same id
	    ResultSet rsProducts = stm.executeQuery("SELCET * FROM "
		    + DAOFactory.TABLE_OFFERS_PRODUCTS + " WHERE "
		    + DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[0] + "=" + id
		    + ";");
	    while (rsProducts.next()) {
		// Product can be a pizza product or drink product
		// Select pizza with the same id as product.
		ResultSet rsPizza = stm
			.executeQuery("SELECT * FROM "
				+ DAOFactory.TABLE_PIZZAS
				+ " WHERE "
				+ DAOFactory.COLUMNS_NAME_PIZZAS[0]
				+ "="
				+ rsProducts
					.getInt(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[1])
				+ ";");
		rsPizza.next();
		if (rsPizza != null) {
		    Pizza pizza = new Pizza(
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[0]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[1]),
			    rsPizza.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[2]),
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[3]),
			    rsPizza.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[4]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[5]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[6]),
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[7]));
		    Ingredients ingredients = selectIngredientsById(rsPizza
			    .getInt(DAOFactory.COLUMNS_NAME_PIZZAS[8]));
		    pizza.setIngredients(ingredients);
		    productsList.add(pizza);
		}
		// Select drink with the same id as product.
		ResultSet rsDrink = stm
			.executeQuery("SELECT * FROM "
				+ DAOFactory.TABLE_DRINKS
				+ " WHERE "
				+ DAOFactory.COLUMNS_NAME_DRINKS[0]
				+ "="
				+ rsProducts
					.getInt(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[1])
				+ ";");
		rsDrink.next();
		if (rsDrink != null) {
		    Drink drink = new Drink(
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[0]),
			    rsDrink.getString(DAOFactory.COLUMNS_NAME_DRINKS[1]),
			    rsDrink.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[2]),
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[3]),
			    rsDrink.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[4]),
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[5]));
		    productsList.add(drink);
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return productsList;
    }

    @Override
    protected List<Product> selectShoppingCartProductsById(int id) {
	List<Product> productsList = new ArrayList<Product>();
	try {
	    Statement stm = this.con.createStatement();
	    ResultSet rsShoppingCartProducts = stm
		    .executeQuery("SELECT * FROM "
			    + DAOFactory.TABLE_SHOPPINGCART_PRODUCTS
			    + " WHERE "
			    + DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[0]
			    + "=" + id + ";");
	    while (rsShoppingCartProducts.next()) {
		// Product can be a pizza product, drink product or offer
		// product.
		// Select pizza with the same id as product.
		ResultSet rsPizza = stm
			.executeQuery("SELECT * FROM "
				+ DAOFactory.TABLE_PIZZAS
				+ " WHERE "
				+ DAOFactory.COLUMNS_NAME_PIZZAS[0]
				+ "="
				+ rsShoppingCartProducts
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[1])
				+ ";");
		rsPizza.next();
		if (rsPizza != null) {
		    Pizza pizza = new Pizza(
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[0]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[1]),
			    rsPizza.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[2]),
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[3]),
			    rsPizza.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[4]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[5]),
			    rsPizza.getString(DAOFactory.COLUMNS_NAME_PIZZAS[6]),
			    rsPizza.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[7]));
		    Ingredients ingredients = selectIngredientsById(rsPizza
			    .getInt(DAOFactory.COLUMNS_NAME_PIZZAS[8]));
		    pizza.setIngredients(ingredients);
		    productsList.add(pizza);
		}
		// Select drink with the same id as product.
		ResultSet rsDrink = stm
			.executeQuery("SELECT * FROM "
				+ DAOFactory.TABLE_DRINKS
				+ " WHERE "
				+ DAOFactory.COLUMNS_NAME_DRINKS[0]
				+ "="
				+ rsShoppingCartProducts
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[1])
				+ ";");
		rsDrink.next();
		if (rsDrink != null) {
		    Drink drink = new Drink(
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[0]),
			    rsDrink.getString(DAOFactory.COLUMNS_NAME_DRINKS[1]),
			    rsDrink.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[2]),
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[3]),
			    rsDrink.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[4]),
			    rsDrink.getInt(DAOFactory.COLUMNS_NAME_DRINKS[5]));
		    productsList.add(drink);
		}
		// Select offer with the same id as product.
		ResultSet rsOffer = stm
			.executeQuery("SELECT * FROM "
				+ DAOFactory.TABLE_OFFERS
				+ " WHERE "
				+ DAOFactory.COLUMNS_NAME_OFFERS[0]
				+ "="
				+ rsShoppingCartProducts
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[1])
				+ ";");
		rsOffer.next();
		if (rsOffer != null) {
		    Offer offer = new Offer(
			    rsOffer.getInt(DAOFactory.COLUMNS_NAME_OFFERS[0]),
			    rsOffer.getString(DAOFactory.COLUMNS_NAME_OFFERS[1]),
			    rsOffer.getFloat(DAOFactory.COLUMNS_NAME_OFFERS[2]),
			    rsOffer.getInt(DAOFactory.COLUMNS_NAME_OFFERS[3]),
			    rsOffer.getFloat(DAOFactory.COLUMNS_NAME_OFFERS[4]));
		    ArrayList<Product> productOfferList = (ArrayList<Product>) selectProductsOffersById(rsOffer
			    .getInt(DAOFactory.COLUMNS_NAME_OFFERS[5]));
		    offer.setProductList(productOfferList);
		    productsList.add(offer);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return productsList;
    }

    @Override
    protected Set<Product> readProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Ingredient> readIngredient() {
	Set<Ingredient> ingredientsSet = new HashSet<Ingredient>();
	try {
	    Statement stm = this.con.createStatement();
	    // Select all rows of ingredient table
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_INGREDIENT + ";");
	    while (rs.next()) {
		// Create a ingredient object and put in the HashSet
		Ingredient ingredient = new Ingredient(
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[0]),
			rs.getString(DAOFactory.COLUMNS_NAME_INGREDIENT[1]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_INGREDIENT[2]),
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[3]),
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[4]));
		ingredientsSet.add(ingredient);
	    }
	    stm.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ingredientsSet;
    }

    @Override
    protected Set<Pizza> readPizza() {
	Set<Pizza> pizzaSet = new HashSet<Pizza>();
	Statement stm;
	try {
	    stm = this.con.createStatement();
	    // Select all rows of Pizza table
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_PIZZAS + ";");
	    while (rs.next()) {
		// Create a pizza object and put in the HashSet
		Pizza pizza = new Pizza(
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[0]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[1]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[2]),
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[3]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[4]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[5]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[6]),
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[7]));
		// get the pizza ingredients map.
		Ingredients ingredients = selectIngredientsById(rs
			.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[8]));
		pizza.setIngredients(ingredients);
		pizzaSet.add(pizza);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return pizzaSet;
    }

    @Override
    protected Set<Offer> readOffer() {
	Set<Offer> offersSet = new HashSet<Offer>();
	try {
	    Statement stm = this.con.createStatement();
	    // Select all rows from Offer table.
	    ResultSet rsOffer = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_OFFERS + ";");
	    while (rsOffer.next()) {
		// Create a offer object and put in the HashSet
		Offer offer = new Offer(
			rsOffer.getInt(DAOFactory.COLUMNS_NAME_OFFERS[0]),
			rsOffer.getString(DAOFactory.COLUMNS_NAME_OFFERS[1]),
			rsOffer.getFloat(DAOFactory.COLUMNS_NAME_OFFERS[2]),
			rsOffer.getInt(DAOFactory.COLUMNS_NAME_OFFERS[3]),
			rsOffer.getFloat(DAOFactory.COLUMNS_NAME_OFFERS[4]));
		// Get the offer products list
		ArrayList<Product> productList = (ArrayList<Product>) selectProductsOffersById(rsOffer
			.getInt(DAOFactory.COLUMNS_NAME_OFFERS[5]));
		offer.setProductList(productList);
		offersSet.add(offer);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return offersSet;
    }

    @Override
    protected Set<Drink> readDrink() {
	Set<Drink> drinksSet = new HashSet<Drink>();
	Statement stm;
	try {
	    stm = this.con.createStatement();
	    // Select all rows from drink table
	    ResultSet rsDrinks = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_DRINKS + ";");
	    while (rsDrinks.next()) {
		// Create a drink object and put it in the HashSet
		Drink drink = new Drink(
			rsDrinks.getInt(DAOFactory.COLUMNS_NAME_DRINKS[0]),
			rsDrinks.getString(DAOFactory.COLUMNS_NAME_DRINKS[1]),
			rsDrinks.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[2]),
			rsDrinks.getInt(DAOFactory.COLUMNS_NAME_DRINKS[3]),
			rsDrinks.getFloat(DAOFactory.COLUMNS_NAME_DRINKS[4]),
			rsDrinks.getInt(DAOFactory.COLUMNS_NAME_DRINKS[5]));
		drinksSet.add(drink);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return drinksSet;
    }

    @Override
    protected ShoppingCart readShoppingCart(int idShoppingCart) {
	ShoppingCart shoppingCart = null;
	try {
	    Statement stm = this.con.createStatement();
	    ResultSet rsShoppingCart = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_SHOPPINGCARTS + " WHERE "
		    + DAOFactory.COLUMNS_NAME_SHOPPINGCARTS[0] + "="
		    + idShoppingCart + ";");
	    rsShoppingCart.next();
	    if (rsShoppingCart != null) {
		shoppingCart = new ShoppingCart(
			rsShoppingCart.getInt(idShoppingCart));
		ArrayList<Product> productsList = (ArrayList<Product>) selectShoppingCartProductsById(idShoppingCart);
		shoppingCart.setProducts(productsList);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return shoppingCart;
    }

    @Override
    protected Set<Order> readOrder() {
	HashSet<Order> OrdersSet = new HashSet<Order>();
	try {
	    Statement stm = this.con.createStatement();
	    // Select all rows of order table.
	    ResultSet rsOrder = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_ORDERS + ";");
	    while (rsOrder.next()) {
		DateTime dateTime = DateTime.parse(rsOrder
			.getString(DAOFactory.COLUMNS_NAME_ORDERS[3]));
		Order order = new Order(
			rsOrder.getInt(DAOFactory.COLUMNS_NAME_ORDERS[0]),
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[1]),
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[2]),
			dateTime,
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[4]),
			readAddress(rsOrder
				.getInt(DAOFactory.COLUMNS_NAME_ORDERS[5])),
			readShoppingCart(rsOrder
				.getInt(DAOFactory.COLUMNS_NAME_ORDERS[6])));
		OrdersSet.add(order);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return OrdersSet;
    }

    @Override
    protected Address readAddress(int idAddress) {
	Address address = null;
	try {
	    Statement stm = this.con.createStatement();
	    ResultSet rsAddress = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_ADDRESS + " WHERE "
		    + DAOFactory.COLUMNS_NAME_ADDRESS[0] + "=" + idAddress
		    + ";");
	    if (rsAddress.next()) {
		address = new Address(
			rsAddress.getInt(DAOFactory.COLUMNS_NAME_ADDRESS[0]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[1]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[2]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[3]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[4]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[5]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[6]));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return address;
    }

    @Override
    protected Map<String, String> readPreferences() {
	Map<String, String> preferences = new HashMap<String, String>();
	Statement stm;
	try {
	    stm = this.con.createStatement();
	    ResultSet rsPreferences = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_PREFERENCES);

	    while (rsPreferences.next()) {

		preferences
			.put(rsPreferences
				.getString(DAOFactory.COLUMNS_NAME_PREFERENCES[0]),
				rsPreferences
					.getString(DAOFactory.COLUMNS_NAME_PREFERENCES[1]));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return preferences;
    }

    @Override
    protected void writeProduct(int idProduct) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeIngredients(Set<Ingredient> ingredients) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writePizzas(Set<Pizza> pizzas) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeOffers(Set<Offer> offers) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeDrinks(Set<Drink> drinks) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeShoppingCarts(ShoppingCart shoppingCart) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeOrders(Set<Order> orders) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeAddresses(Address address) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writePreferences(Map<String, String> preferences) {
	// TODO Auto-generated method stub

    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
