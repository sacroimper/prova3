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
import org.escoladeltreball.arcowabungaproject.server.gui.database.SelectPanel;
import org.joda.time.DateTime;

public class DAOPostgreSQL extends DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private static DAOPostgreSQL instance;

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOPostgreSQL() {
	super();
    }

    private Connection connectToDatabase() {
	try {
	    Class.forName("org.postgresql.Driver");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("Driver Cargado");
	Connection con = null;
	try {
	    con = DriverManager.getConnection(
		    "jdbc:postgresql://localhost:5432/cowabunga", "postgres",
		    "postgres");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	System.out.println("Conenexion realizada");
	return con;
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

    public void initDataBase() {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Create the database
	    // stm.executeUpdate(DAOFactory.CREATE_DATA_BASE);
	    // Create all tables

	    stm.executeUpdate(DAOFactory.CREATE_TABLE_PREFERENCES);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_RESOURCES);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_PRODUCTS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_ADDRESS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_DRINKS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_INGREDIENT);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_INGREDIENTS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_PIZZAS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_OFFERS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_OFFERS_PRODUCTS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_SHOPPINGCARTS);

	    stm.executeUpdate(DAOFactory.CREATE_TABLE_SHOPPINCART_PRODUCTS);
	    stm.executeUpdate(DAOFactory.CREATE_TABLE_ORDERS);

	    stm.executeUpdate("INSERT INTO RESOURCES VALUES(1,'path1');");
	    stm.executeUpdate("INSERT INTO RESOURCES VALUES(2,'path2');");
	    stm.executeUpdate("INSERT INTO RESOURCES VALUES(3,'path3');");

	    stm.executeUpdate("INSERT INTO INGREDIENT VALUES(10,'pepperoni',0.5,1,2,'path_texture1');");
	    stm.executeUpdate("INSERT INTO INGREDIENT VALUES(11,'cheese',0.5,1,2,'path_texture2');");
	    stm.executeUpdate("INSERT INTO INGREDIENT VALUES(12,'mushroom',0.5,2,3,'path_texture3');");
	    stm.executeUpdate("INSERT INTO INGREDIENT VALUES(13,'tomatoe',0.5,1,3,'path_texture4');");

	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(20,10,2);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(20,11,1);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(20,12,3);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(21,13,1);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(21,12,2);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(22,11,3);");
	    stm.executeUpdate("INSERT INTO INGREDIENTS VALUES(22,13,1);");

	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(30);");
	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(31);");
	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(32);");
	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(40);");
	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(41);");
	    stm.executeUpdate("INSERT INTO PRODUCTS VALUES(50);");

	    stm.executeUpdate("INSERT INTO PIZZAS VALUES(30,'PIZZA1',15,1,2.5,'thin','type1',2,20);");
	    stm.executeUpdate("INSERT INTO PIZZAS VALUES(31,'PIZZA2',15,2,2.5,'thick','type2',3,21);");
	    stm.executeUpdate("INSERT INTO PIZZAS VALUES(32,'PIZZA3',15,3,2.5,'thin','type2',1,22);");

	    stm.executeUpdate("INSERT INTO DRINKS VALUES(40, 'coke', 2.5, 2, 0, 1);");
	    stm.executeUpdate("INSERT INTO DRINKS VALUES(41, 'water', 1.5, 3, 0, 1);");

	    stm.executeUpdate("INSERT INTO OFFERS VALUES(50, '2X1', 15, 2, 60);");

	    stm.executeUpdate("INSERT INTO OFFERS_PRODUCTS VALUES(60, 50, 30);");
	    stm.executeUpdate("INSERT INTO OFFERS_PRODUCTS VALUES(60, 50, 31);");
	    stm.executeUpdate("INSERT INTO OFFERS_PRODUCTS VALUES(60, 50, 40);");
	    stm.executeUpdate("INSERT INTO OFFERS_PRODUCTS VALUES(60, 50, 41);");

	    stm.executeUpdate("INSERT INTO SHOPPINGCARTS VALUES(80,70);");

	    stm.executeUpdate("INSERT INTO SHOPPINGCART_PRODUCTS VALUES(70,80,50);");
	    stm.executeUpdate("INSERT INTO SHOPPINGCART_PRODUCTS VALUES(70,80,32);");

	    stm.executeUpdate("INSERT INTO ADDRESS VALUES(90, 'maracana', '268', '00200', '2', 'A', '2');");
	    stm.executeUpdate("INSERT INTO ADDRESS VALUES(91, 'merindrade', '12', '00100', '4', 'N', '1');");
	    stm.executeUpdate("INSERT INTO ADDRESS VALUES(92, 'barandero', '435', '00600', '3', 'B', '6');");

	    stm.executeUpdate("INSERT INTO ORDERS VALUES(100,'222222','wewew@wewe.com','12/03/2014-12:00:00','visa',90,80);");

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
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
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();

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
			    rsIngredient.getInt(4), rsIngredient.getString(5));
		    ingredients.put(ingredient, rsIngredient.getInt(2));
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return ingredients;
    }

    @Override
    protected List<Product> selectProductsOffersById(int id) {
	List<Product> productsList = new ArrayList<Product>();
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
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
					.getInt(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[2])
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
					.getInt(DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS[2])
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return productsList;
    }

    @Override
    protected List<Product> selectShoppingCartProductsById(int id) {
	List<Product> productsList = new ArrayList<Product>();
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
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
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[2])
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
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[2])
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
					.getInt(DAOFactory.COLUMNS_NAME_SHOPPINCART_PRODUCTS[2])
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return productsList;
    }

    @Override
    protected Set<Product> readProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<Ingredient> readIngredient() {
	Set<Ingredient> ingredientsSet = new HashSet<Ingredient>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select all rows of ingredient table
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_INGREDIENT + where + ";");
	    while (rs.next()) {
		// Create a ingredient object and put in the HashSet
		Ingredient ingredient = new Ingredient(
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[0]),
			rs.getString(DAOFactory.COLUMNS_NAME_INGREDIENT[1]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_INGREDIENT[2]),
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[3]),
			rs.getInt(DAOFactory.COLUMNS_NAME_INGREDIENT[4]),
			rs.getString(DAOFactory.COLUMNS_NAME_INGREDIENT[5]));
		ingredientsSet.add(ingredient);
	    }
	    stm.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return ingredientsSet;
    }

    @Override
    public Set<Pizza> readPizza() {
	Set<Pizza> pizzaSet = new HashSet<Pizza>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select all rows of Pizza table
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_PIZZAS + where + ";");
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return pizzaSet;
    }

    @Override
    public Set<Offer> readOffer() {
	Set<Offer> offersSet = new HashSet<Offer>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select all rows from Offer table.
	    ResultSet rsOffer = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_OFFERS + where + ";");
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return offersSet;
    }

    @Override
    public Set<Drink> readDrink() {
	Set<Drink> drinksSet = new HashSet<Drink>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select all rows from drink table
	    ResultSet rsDrinks = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_DRINKS + where + ";");
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return drinksSet;
    }

    @Override
    protected ShoppingCart readShoppingCart(int idShoppingCart) {
	ShoppingCart shoppingCart = null;
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select the shopping card with idShoppingCart
	    ResultSet rsShoppingCart = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_SHOPPINGCARTS + " WHERE "
		    + DAOFactory.COLUMNS_NAME_SHOPPINGCARTS[0] + "="
		    + idShoppingCart + ";");
	    rsShoppingCart.next();
	    if (rsShoppingCart != null) {
		shoppingCart = new ShoppingCart(
			rsShoppingCart.getInt(idShoppingCart));
		ArrayList<Product> productsList = (ArrayList<Product>) selectShoppingCartProductsById(rsShoppingCart
			.getInt(DAOFactory.COLUMNS_NAME_SHOPPINGCARTS[1]));
		shoppingCart.setProducts(productsList);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return shoppingCart;
    }

    @Override
    public Set<Order> readOrder() {
	HashSet<Order> ordersSet = new HashSet<Order>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();
	    // Select all rows of order table.
	    ResultSet rsOrder = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_ORDERS + where + ";");
	    while (rsOrder.next()) {
		DateTime dateTime = DateTime.parse(rsOrder
			.getString(DAOFactory.COLUMNS_NAME_ORDERS[3]));
		Order order = new Order(
			rsOrder.getInt(DAOFactory.COLUMNS_NAME_ORDERS[0]),
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[1]),
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[2]),
			dateTime,
			rsOrder.getString(DAOFactory.COLUMNS_NAME_ORDERS[4]),
			readAddressById(rsOrder
				.getInt(DAOFactory.COLUMNS_NAME_ORDERS[5])),
			readShoppingCart(rsOrder
				.getInt(DAOFactory.COLUMNS_NAME_ORDERS[6])));
		ordersSet.add(order);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return ordersSet;
    }

    @Override
    protected Address readAddressById(int idAddress) {
	Address address = null;
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return address;
    }

    public Set<Address> readAddress() {
	HashSet<Address> addressSet = new HashSet<Address>();
	Connection con = null;
	Statement stm = null;
	try {
	    String where = SelectPanel.where;
	    con = connectToDatabase();
	    stm = con.createStatement();

	    ResultSet rsAddress = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_ADDRESS + where + ";");
	    while (rsAddress.next()) {
		Address address = new Address(
			rsAddress.getInt(DAOFactory.COLUMNS_NAME_ADDRESS[0]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[1]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[2]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[3]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[4]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[5]),
			rsAddress.getString(DAOFactory.COLUMNS_NAME_ADDRESS[6]));
		addressSet.add(address);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return addressSet;
    }

    @Override
    protected Map<String, String> readPreferences() {
	Map<String, String> preferences = new HashMap<String, String>();
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
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
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
	return preferences;
    }

    @Override
    protected void writeProduct(int idProduct) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeIngredients(Set<Ingredient> ingredients) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Ingredient ingredient : ingredients) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_INGREDIENT
			+ "VALUES(" + ingredient.getId() + ",'"
			+ ingredient.getName() + "'," + ingredient.getIcon()
			+ "," + ingredient.getModel() + ","
			+ ingredient.getPrice() + ");");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writePizzas(Set<Pizza> pizzas) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Pizza pizza : pizzas) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_PIZZAS
			+ "VALUES(" + pizza.getId() + ",'" + pizza.getName()
			+ "'," + pizza.getPrice() + "," + pizza.getIcon()
			+ ",'" + pizza.getMassType() + "','" + pizza.getType()
			+ "'," + pizza.getSize() + "," + pizza.getDiscount()
			+ "," + pizza.getIngredients().getId() + ");");
		for (Ingredient ingredient : pizza.getIngredientsSet()) {
		    stm.executeUpdate("INSERT INTO "
			    + DAOFactory.TABLE_INGREDIENTS + "VALUES("
			    + pizza.getIngredients().getId() + ","
			    + ingredient.getId() + ","
			    + pizza.getIngredients().get(ingredient) + ");");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writeOffers(Set<Offer> offers) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Offer offer : offers) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_OFFERS
			+ " VALUES(" + offer.getId() + ",'" + offer.getName()
			+ "'," + offer.getPrice() + "," + offer.getIcon() + ","
			+ offer.getDiscount() + ");");
		for (Product product : offer.getProductList()) {
		    stm.executeUpdate("INSERT INTO "
			    + DAOFactory.TABLE_OFFERS_PRODUCTS + " VALUES("
			    + offer.getId() + "," + product.getId() + ")");
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writeDrinks(Set<Drink> drinks) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Drink drink : drinks) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_DRINKS
			+ " VALUES(" + drink.getId() + ",'" + drink.getName()
			+ "'," + drink.getPrice() + "," + drink.getIcon() + ","
			+ drink.getDiscount() + "," + drink.getSize() + ");");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writeShoppingCarts(ShoppingCart shoppingCart) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_SHOPPINGCARTS
		    + " VALUES(" + shoppingCart.getId() + ");");
	    for (Product product : shoppingCart.getProducts()) {
		stm.executeUpdate("INSERT INTO "
			+ DAOFactory.TABLE_SHOPPINGCART_PRODUCTS + " VALUES("
			+ shoppingCart.getId() + "," + product.getId() + ");");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writeOrders(Set<Order> orders) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Order order : orders) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_ORDERS
			+ " VALUES(" + order.getId() + ",'" + order.getEmail()
			+ "','" + order.getPhone() + "','"
			+ order.getDateTime() + "','"
			+ order.getPaymentMethod() + "',"
			+ order.getAddress().getId() + ","
			+ order.getShoppingCart().getId() + ");");
		writeShoppingCarts(order.getShoppingCart());
		writeAddresses(order.getAddress());
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writeAddresses(Address address) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_ADDRESS
		    + " VALUES(" + address.getId() + ",'" + address.getStreet()
		    + "','" + address.getNumber() + "','"
		    + address.getPostCode() + "','" + address.getFloor()
		    + "','" + address.getStair() + "','" + address.getDoor()
		    + "');");
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    @Override
    protected void writePreferences(Map<String, String> preferences) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = connectToDatabase();
	    stm = con.createStatement();
	    for (Map.Entry<String, String> entry : preferences.entrySet()) {
		stm.executeUpdate("INSERT INTO " + DAOFactory.TABLE_PREFERENCES
			+ " VALUES('" + entry.getKey() + "','"
			+ entry.getValue() + "');");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    if (stm != null) {
		try {
		    stm.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	    if (con != null) {
		try {
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

}
