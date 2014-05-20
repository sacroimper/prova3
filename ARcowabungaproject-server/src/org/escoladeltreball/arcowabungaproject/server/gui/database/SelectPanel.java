/*
 *  SelectPanel.java
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
package org.escoladeltreball.arcowabungaproject.server.gui.database;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.server.dao.DAOPostgreSQL;

public class SelectPanel extends JPanel implements ItemListener, ActionListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpDoSelect;
    private JPanel jpShowTable;
    private JLabel[] jlLists;
    private JTextField[] jtfList;
    private JLabel jlChooseTable;
    private JComboBox<String> jcbTables;
    private GridBagConstraints constraints;
    private JButton jbExecuteQuery;
    private JTable jtTable;
    private JScrollPane sp;
    public static String where = "";
    private int indexConstrainstX = 0;
    private int indexConstrainstY = 0;

    // ====================
    // CONSTRUCTORS
    // ====================
    public SelectPanel() {
	this.initComponents();
	this.registListeners();
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================
    private void initComponents() {
	this.setLayout(new BorderLayout());
	this.jpDoSelect = new JPanel();
	this.jpDoSelect.setLayout(new GridBagLayout());
	this.jpShowTable = new JPanel();
	this.jbExecuteQuery = new JButton("Execute Query");
	this.sp = new JScrollPane();
	String[] items = { "", DAOFactory.TABLE_ADDRESS,
		DAOFactory.TABLE_DRINKS, DAOFactory.TABLE_INGREDIENT,
		DAOFactory.TABLE_OFFERS, DAOFactory.TABLE_ORDERS,
		DAOFactory.TABLE_PIZZAS, DAOFactory.TABLE_PREFERENCES,
		DAOFactory.TABLE_RESOURCES };
	this.jlChooseTable = new JLabel("Choose Table");
	this.jcbTables = new JComboBox<>(items);
	this.constraints = new GridBagConstraints();
	this.constraints.gridx = this.indexConstrainstX;
	this.constraints.gridy = this.indexConstrainstY;
	this.jpDoSelect.add(jlChooseTable, this.constraints);
	this.constraints.gridx = ++this.indexConstrainstX;
	this.jpDoSelect.add(jcbTables, this.constraints);
	this.add(this.jpDoSelect, BorderLayout.WEST);
	this.add(this.jpShowTable, BorderLayout.CENTER);
    }

    private void registListeners() {
	this.jcbTables.addItemListener(this);
	this.jbExecuteQuery.addActionListener(this);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.DESELECTED) {
	    if (this.jlLists != null) {
		for (int i = 0; i < this.jlLists.length; i++) {
		    this.jpDoSelect.remove(this.jlLists[i]);
		    this.jpDoSelect.remove(this.jtfList[i]);
		}
		this.jpDoSelect.remove(this.jbExecuteQuery);
	    }
	}
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    String item = (String) e.getItem();
	    switch (item) {
	    case DAOFactory.TABLE_ADDRESS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_ADDRESS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_ADDRESS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_ADDRESS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_ADDRESS[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_DRINKS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_DRINKS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_DRINKS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_DRINKS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_DRINKS[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_INGREDIENT:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_INGREDIENT.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_INGREDIENT.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_INGREDIENT.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_INGREDIENT[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_PIZZAS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_PIZZAS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_PIZZAS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_PIZZAS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_PIZZAS[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_OFFERS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_OFFERS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_OFFERS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_OFFERS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_OFFERS[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_ORDERS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_ORDERS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_ORDERS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_ORDERS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_ORDERS[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_PREFERENCES:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_PREFERENCES.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_PREFERENCES.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_PREFERENCES.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_PREFERENCES[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    case DAOFactory.TABLE_RESOURCES:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_RESOURCES.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_RESOURCES.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_RESOURCES.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_RESOURCES[i]);
		    this.jtfList[i] = new JTextField();
		    this.constraints.gridx = 0;
		    this.constraints.gridy = ++this.indexConstrainstY;
		    this.constraints.fill = GridBagConstraints.HORIZONTAL;
		    this.jpDoSelect.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.jpDoSelect.add(this.jtfList[i], this.constraints);
		}
		break;
	    default:
		break;
	    }
	    this.constraints.gridy = ++this.indexConstrainstY;
	    this.jpDoSelect.add(this.jbExecuteQuery, constraints);
	    this.indexConstrainstY = 0;
	}
	this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	where = "";
	this.jpShowTable.removeAll();
	this.repaint();
	if (this.jbExecuteQuery != null) {
	    if (this.jtfList != null) {
		showTable();
	    }
	}

	this.jtTable.setPreferredScrollableViewportSize(this.jtTable
		.getPreferredSize());
	this.sp = new JScrollPane(this.jtTable);
	this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	this.sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	this.jpShowTable.add(this.sp);
	this.validate();
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    /**
     * Show table depends on the selected table in JComboBox object
     * 
     */
    private void showTable() {
	String item = (String) this.jcbTables.getSelectedItem();
	String[][] rowData = null;
	switch (item) {
	case DAOFactory.TABLE_ADDRESS:
	    // Fill string with "where" clause
	    for (int i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_ADDRESS[i].equals("VARCHAR")
			    || DAOFactory.COLUMNS_TYPE_ADDRESS[i]
				    .equals("CHAR")) {
			where += DAOFactory.COLUMNS_NAME_ADDRESS[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_ADDRESS[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }
	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    // Select address with concrete fields
	    HashSet<Address> addressList = (HashSet<Address>) DAOPostgreSQL
		    .getInstance().readAddress();
	    rowData = new String[addressList.size()][DAOFactory.COLUMNS_NAME_ADDRESS.length];
	    int i = 0;
	    // fill table with results of query
	    for (Address address : addressList) {
		rowData[i][0] = address.getId() + "";
		rowData[i][1] = address.getStreet();
		rowData[i][2] = address.getNumber();
		rowData[i][3] = address.getPostCode();
		rowData[i][4] = address.getFloor();
		rowData[i][5] = address.getStair();
		rowData[i][6] = address.getDoor();
		i++;
	    }
	    this.jtTable = new JTable(rowData, DAOFactory.COLUMNS_NAME_ADDRESS);
	    break;
	case DAOFactory.TABLE_INGREDIENT:
	    // Fill string with "where" clause
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_INGREDIENT[i].equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_INGREDIENT[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_INGREDIENT[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    // Select Ingredient with concrete fields
	    HashSet<Ingredient> ingredientsList = (HashSet<Ingredient>) DAOPostgreSQL
		    .getInstance().readIngredient();
	    rowData = new String[ingredientsList.size()][DAOFactory.COLUMNS_NAME_INGREDIENT.length];
	    i = 0;
	    // Fill table with the results of query
	    for (Ingredient ingredient : ingredientsList) {
		rowData[i][0] = ingredient.getId() + "";
		rowData[i][1] = ingredient.getName();
		rowData[i][2] = ingredient.getPrice() + "";
		rowData[i][3] = ingredient.getModel() + "";
		rowData[i][4] = ingredient.getIcon() + "";
		rowData[i][5] = ingredient.getTexture() + "";
		i++;
	    }
	    this.jtTable = new JTable(rowData,
		    DAOFactory.COLUMNS_NAME_INGREDIENT);
	    break;
	case DAOFactory.TABLE_PIZZAS:
	    // Fill string with "where" clause
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_PIZZAS[i].equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_PIZZAS[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_PIZZAS[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    // Select pizza with concrete fields
	    HashSet<Pizza> pizzasList = (HashSet<Pizza>) DAOPostgreSQL
		    .getInstance().readPizza();
	    rowData = new String[pizzasList.size()][DAOFactory.COLUMNS_NAME_PIZZAS.length];
	    int ingredientsTableSize = 0;
	    i = 0;
	    // Fill pizza table with the results of query
	    for (Pizza pizza : pizzasList) {
		rowData[i][0] = pizza.getId() + "";
		rowData[i][1] = pizza.getName();
		rowData[i][2] = pizza.getPrice() + "";
		rowData[i][3] = pizza.getIcon() + "";
		rowData[i][4] = pizza.getMassType();
		rowData[i][5] = pizza.getType();
		rowData[i][6] = pizza.getSize() + "";
		rowData[i][7] = pizza.getDiscount() + "";
		rowData[i][8] = pizza.getIngredients().getId() + "";
		ingredientsTableSize += pizza.getIngredients().size();
		i++;
	    }
	    // Show pizza table
	    this.jtTable = new JTable(rowData, DAOFactory.COLUMNS_NAME_PIZZAS);
	    this.jtTable.setPreferredScrollableViewportSize(this.jtTable
		    .getPreferredSize());
	    this.sp = new JScrollPane(this.jtTable);
	    this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.jpShowTable.add(this.sp);

	    // Show List of ingredients table associated to pizza ingredients
	    // id's
	    rowData = new String[ingredientsTableSize][DAOFactory.COLUMNS_NAME_INGREDIENTS.length];
	    i = 0;
	    for (Pizza pizza : pizzasList) {
		for (Map.Entry<Ingredient, Integer> entry : pizza
			.getIngredients().entrySet()) {
		    rowData[i][0] = pizza.getIngredients().getId() + "";
		    rowData[i][1] = entry.getKey().getId() + "";
		    rowData[i][2] = entry.getValue() + "";
		    i++;
		}
	    }
	    this.jtTable = new JTable(rowData,
		    DAOFactory.COLUMNS_NAME_INGREDIENTS);

	    break;
	case DAOFactory.TABLE_DRINKS:
	    // Fill string with "where" clause
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_DRINKS[i].equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_DRINKS[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_DRINKS[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    // Select drink with concrete fields
	    HashSet<Drink> drinksList = (HashSet<Drink>) DAOPostgreSQL
		    .getInstance().readDrink();
	    rowData = new String[drinksList.size()][DAOFactory.COLUMNS_NAME_DRINKS.length];
	    i = 0;
	    // Fill drink table
	    for (Drink drink : drinksList) {
		rowData[i][0] = drink.getId() + "";
		rowData[i][1] = drink.getName();
		rowData[i][2] = drink.getPrice() + "";
		rowData[i][3] = drink.getIcon() + "";
		rowData[i][4] = drink.getDiscount() + "";
		rowData[i][5] = drink.getSize() + "";
		i++;
	    }
	    this.jtTable = new JTable(rowData, DAOFactory.COLUMNS_NAME_DRINKS);
	    break;
	case DAOFactory.TABLE_OFFERS:
	    // Fill string with "where" clause
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_OFFERS[i].equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_OFFERS[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_OFFERS[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    // Select the offers with concrete fields
	    HashSet<Offer> offerList = (HashSet<Offer>) DAOPostgreSQL
		    .getInstance().readOffer();
	    rowData = new String[offerList.size()][DAOFactory.COLUMNS_NAME_OFFERS.length];
	    i = 0;
	    // Fill offers table with the results of query
	    int offersProductsTableSize = 0;
	    for (Offer offer : offerList) {
		rowData[i][0] = offer.getId() + "";
		rowData[i][1] = offer.getName();
		rowData[i][2] = offer.getPrice() + "";
		rowData[i][3] = offer.getIcon() + "";
		rowData[i][4] = offer.getDiscount() + "";
		offersProductsTableSize += offer.getProductList().size();
		i++;
	    }
	    // Show offer table
	    this.jtTable = new JTable(rowData, DAOFactory.COLUMNS_NAME_OFFERS);
	    this.jtTable.setPreferredScrollableViewportSize(this.jtTable
		    .getPreferredSize());
	    this.sp = new JScrollPane(this.jtTable);
	    this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    this.sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.jpShowTable.add(this.sp);

	    // Show List of products table associated to offer id's
	    rowData = new String[offersProductsTableSize][DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS.length];
	    i = 0;
	    for (Offer offer : offerList) {
		for (Product product : offer.getProductList()) {
		    rowData[i][0] = offer.getId() + "";
		    rowData[i][1] = product.getId() + "";
		    i++;
		}
	    }
	    this.jtTable = new JTable(rowData,
		    DAOFactory.COLUMNS_NAME_OFFERS_PRODUCTS);
	    break;
	case DAOFactory.TABLE_ORDERS:
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_ORDERS[i].equals("VARCHAR")
			    || DAOFactory.COLUMNS_TYPE_ORDERS[i].equals("DATE")) {
			where += DAOFactory.COLUMNS_NAME_ORDERS[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_ORDERS[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    HashSet<Order> orderList = (HashSet<Order>) DAOPostgreSQL
		    .getInstance().readOrder();
	    rowData = new String[orderList.size()][DAOFactory.COLUMNS_NAME_ORDERS.length];
	    i = 0;
	    for (Order order : orderList) {
		rowData[i][0] = order.getId() + "";
		rowData[i][1] = order.getEmail();
		rowData[i][2] = order.getPhone();
		rowData[i][3] = order.getDateTime() + "";
		rowData[i][4] = order.getPaymentMethod();
		rowData[i][5] = order.getAddress().getId() + "";
		rowData[i][6] = order.getShoppingCart().getId() + "";
		i++;
	    }
	    this.jtTable = new JTable(rowData, DAOFactory.COLUMNS_NAME_ORDERS);
	    break;
	case DAOFactory.TABLE_PREFERENCES:
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_PREFERENCES[i]
			    .equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_PREFERENCES[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_PREFERENCES[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    HashMap<String, String> preferences = (HashMap<String, String>) DAOPostgreSQL
		    .getInstance().readPreferences();
	    rowData = new String[preferences.size()][DAOFactory.COLUMNS_NAME_ORDERS.length];
	    i = 0;

	    for (Map.Entry<String, String> entry : preferences.entrySet()) {
		rowData[i][0] = entry.getKey();
		rowData[i][1] = entry.getValue();
		i++;
	    }
	    this.jtTable = new JTable(rowData,
		    DAOFactory.COLUMNS_NAME_PREFERENCES);
	    break;
	case DAOFactory.TABLE_RESOURCES:
	    for (i = 0; i < this.jtfList.length; i++) {
		if (!this.jtfList[i].getText().isEmpty()) {
		    if (DAOFactory.COLUMNS_TYPE_RESOURCES[i].equals("VARCHAR")) {
			where += DAOFactory.COLUMNS_NAME_RESOURCES[i] + "='"
				+ this.jtfList[i].getText() + "'";
		    } else {
			where += DAOFactory.COLUMNS_NAME_RESOURCES[i] + "="
				+ this.jtfList[i].getText();
		    }
		    where += ",";
		}
	    }

	    if (!where.isEmpty()) {
		where = where.substring(0, where.length() - 1);
		where = " WHERE " + where;
	    }
	    HashMap<Integer, String> resources = (HashMap<Integer, String>) DAOPostgreSQL
		    .getInstance().readResources();
	    rowData = new String[resources.size()][DAOFactory.COLUMNS_NAME_RESOURCES.length];
	    i = 0;
	    for (Map.Entry<Integer, String> entry : resources.entrySet()) {
		rowData[i][0] = entry.getKey() + "";
		rowData[i][1] = entry.getValue();
		i++;
	    }
	    this.jtTable = new JTable(rowData,
		    DAOFactory.COLUMNS_NAME_RESOURCES);
	    break;
	default:
	    break;
	}
    }
}
