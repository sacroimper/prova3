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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected List<Product> selectProductsOffersById(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected List<Product> selectShoppingCartProductsById(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Product> readProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Ingredient> readIngredient() {
	HashSet<Ingredient> ingredientsSet = new HashSet<Ingredient>();
	Statement stm;
	try {
	    stm = this.con.createStatement();
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_INGREDIENT + ";");
	    while (rs.next()) {
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
	HashSet<Pizza> pizzaSet = new HashSet<Pizza>();
	Statement stm;
	try {
	    stm = this.con.createStatement();
	    ResultSet rs = stm.executeQuery("SELECT * FROM "
		    + DAOFactory.TABLE_PIZZAS + ";");
	    while (rs.next()) {
		Pizza pizza = new Pizza(
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[0]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[1]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[2]),
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[3]),
			rs.getFloat(DAOFactory.COLUMNS_NAME_PIZZAS[4]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[5]),
			rs.getString(DAOFactory.COLUMNS_NAME_PIZZAS[6]),
			rs.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[7]));

		Ingredients ingredients = selectIngredientsById(rs
			.getInt(DAOFactory.COLUMNS_NAME_PIZZAS[8]));
		pizza.setIngredients(ingredients);
		pizzaSet.add(pizza);
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    @Override
    protected Set<Offer> readOffer() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Drink> readDrink() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected ShoppingCart readShoppingCart(int idShoppingCart) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Order> readOrder() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Address readAddress(int idAddress) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Map<String, String> readPreferences() {
	// TODO Auto-generated method stub
	return null;
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
