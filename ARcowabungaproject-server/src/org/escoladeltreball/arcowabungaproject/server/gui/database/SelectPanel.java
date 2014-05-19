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
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
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
		DAOFactory.TABLE_RESOURCES, DAOFactory.TABLE_SHOPPINGCARTS };
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
	    case DAOFactory.TABLE_SHOPPINGCARTS:
		this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_SHOPPINGCARTS.length];
		this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_SHOPPINGCARTS.length];
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_SHOPPINGCARTS.length; i++) {
		    this.jlLists[i] = new JLabel(
			    DAOFactory.COLUMNS_NAME_SHOPPINGCARTS[i]);
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
	this.jpShowTable.remove(this.sp);
	this.repaint();
	if (this.jbExecuteQuery != null) {
	    if (this.jtfList != null) {
		String item = (String) this.jcbTables.getSelectedItem();
		String[][] rowData = null;
		switch (item) {
		case DAOFactory.TABLE_ADDRESS:
		    for (int i = 0; i < this.jtfList.length; i++) {
			if (!this.jtfList[i].getText().isEmpty()) {
			    if (DAOFactory.COLUMNS_TYPE_ADDRESS[i]
				    .equals("VARCHAR")
				    || DAOFactory.COLUMNS_TYPE_ADDRESS[i]
					    .equals("CHAR")) {
				where += DAOFactory.COLUMNS_NAME_ADDRESS[i]
					+ "='" + this.jtfList[i].getText()
					+ "'";
			    } else {
				where += DAOFactory.COLUMNS_NAME_ADDRESS[i]
					+ "=" + this.jtfList[i].getText();
			    }
			    where += ",";
			}
		    }
		    if (!where.isEmpty()) {
			where = where.substring(0, where.length() - 1);
			where = " WHERE " + where;
		    }
		    HashSet<Address> addressList = (HashSet<Address>) DAOPostgreSQL
			    .getInstance().readAddress();
		    rowData = new String[addressList.size()][DAOFactory.COLUMNS_NAME_ADDRESS.length];
		    int i = 0;
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
		    this.jtTable = new JTable(rowData,
			    DAOFactory.COLUMNS_NAME_ADDRESS);
		    this.sp = new JScrollPane(this.jtTable);
		    this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		    this.jtTable
			    .setPreferredScrollableViewportSize(this.jtTable
				    .getPreferredSize());
		    this.jtTable.setFillsViewportHeight(true);
		    this.jpShowTable.add(this.sp);
		    break;
		case DAOFactory.TABLE_INGREDIENT:
		    for (i = 0; i < this.jtfList.length; i++) {
			if (!this.jtfList[i].getText().isEmpty()) {
			    if (DAOFactory.COLUMNS_TYPE_INGREDIENT[i]
				    .equals("VARCHAR")) {
				where += DAOFactory.COLUMNS_NAME_INGREDIENT[i]
					+ "='" + this.jtfList[i].getText()
					+ "'";
			    } else {
				where += DAOFactory.COLUMNS_NAME_INGREDIENT[i]
					+ "=" + this.jtfList[i].getText();
			    }
			    where += ",";
			}
		    }

		    if (!where.isEmpty()) {
			where = where.substring(0, where.length() - 1);
			where = " WHERE " + where;
		    }
		    HashSet<Ingredient> ingredientsList = (HashSet<Ingredient>) DAOPostgreSQL
			    .getInstance().readIngredient();
		    rowData = new String[ingredientsList.size()][DAOFactory.COLUMNS_NAME_INGREDIENT.length];
		    i = 0;
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
		    this.sp = new JScrollPane(this.jtTable);
		    this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		    this.jtTable
			    .setPreferredScrollableViewportSize(this.jtTable
				    .getPreferredSize());
		    this.jtTable.setFillsViewportHeight(true);
		    this.jpShowTable.add(this.sp);
		    break;
		case DAOFactory.TABLE_PIZZAS:
		    for (i = 0; i < this.jtfList.length; i++) {
			if (!this.jtfList[i].getText().isEmpty()) {
			    if (DAOFactory.COLUMNS_TYPE_PIZZAS[i]
				    .equals("VARCHAR")) {
				where += DAOFactory.COLUMNS_NAME_PIZZAS[i]
					+ "='" + this.jtfList[i].getText()
					+ "'";
			    } else {
				where += DAOFactory.COLUMNS_NAME_PIZZAS[i]
					+ "=" + this.jtfList[i].getText();
			    }
			    where += ",";
			}
		    }

		    if (!where.isEmpty()) {
			where = where.substring(0, where.length() - 1);
			where = " WHERE " + where;
		    }
		    HashSet<Pizza> pizzasList = (HashSet<Pizza>) DAOPostgreSQL
			    .getInstance().readPizza();
		    rowData = new String[pizzasList.size()][DAOFactory.COLUMNS_NAME_PIZZAS.length];
		    i = 0;
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
			i++;
		    }
		    this.jtTable = new JTable(rowData,
			    DAOFactory.COLUMNS_NAME_PIZZAS);
		    this.sp = new JScrollPane(this.jtTable);
		    this.sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		    this.jtTable
			    .setPreferredScrollableViewportSize(this.jtTable
				    .getPreferredSize());
		    this.jtTable.setFillsViewportHeight(true);
		    this.jpShowTable.add(this.sp);

		    break;

		default:
		    break;
		}
	    }
	}
	this.validate();
    }
    // ====================
    // GETTERS & SETTERS
    // ====================
}
