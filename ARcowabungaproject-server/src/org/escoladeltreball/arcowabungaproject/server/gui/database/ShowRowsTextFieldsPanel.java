/*
 *  ShowRowsTextFieldsPanel.java
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.server.dao.DAOPostgreSQL;

public class ShowRowsTextFieldsPanel extends JPanel implements ItemListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JLabel jlChooseTable;
    private JComboBox<String> jcbTables;
    private GridBagConstraints constraints;
    private JButton jbExecuteQuery;
    private JLabel[] jlLists;
    private JTextField[] jtfList;
    private JTable jtIngredientsTable;
    private int indexConstrainstX = 0;
    private int indexConstrainstY = 0;
    private boolean insert;

    // ====================
    // CONSTRUCTORS
    // ====================
    public ShowRowsTextFieldsPanel(boolean insert) {
	this.initComponents();
	this.registListeners();
	this.insert = insert;
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
	this.setLayout(new GridBagLayout());
	this.jbExecuteQuery = new JButton("Execute Query");
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
	this.add(jlChooseTable, this.constraints);
	this.constraints.gridx = ++this.indexConstrainstX;
	this.add(jcbTables, this.constraints);
    }

    private void registListeners() {
	this.jcbTables.addItemListener(this);
    }

    /**
     * Show the text fields of the tables depends on table selected in JComboBox
     * 
     * @param e
     *            the item event
     */
    private void showTextFields(ItemEvent e) {
	String item = (String) e.getItem();
	switch (item) {
	case DAOFactory.TABLE_ADDRESS:
	    this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_ADDRESS.length];
	    this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_ADDRESS.length];
	    for (int i = 0; i < DAOFactory.COLUMNS_NAME_ADDRESS.length; i++) {
		this.jlLists[i] = new JLabel(DAOFactory.COLUMNS_NAME_ADDRESS[i]);
		this.jtfList[i] = new JTextField();
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
	    }
	    break;
	case DAOFactory.TABLE_DRINKS:
	    this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_DRINKS.length];
	    this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_DRINKS.length];
	    for (int i = 0; i < DAOFactory.COLUMNS_NAME_DRINKS.length; i++) {
		this.jlLists[i] = new JLabel(DAOFactory.COLUMNS_NAME_DRINKS[i]);
		this.jtfList[i] = new JTextField();
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
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
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
	    }
	    break;
	case DAOFactory.TABLE_PIZZAS:
	    this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_PIZZAS.length];
	    this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_PIZZAS.length];
	    for (int i = 0; i < DAOFactory.COLUMNS_NAME_PIZZAS.length; i++) {
		if (insert) {
		    if (i == 0) {
			i = 1;
		    }
		}
		this.jlLists[i] = new JLabel(DAOFactory.COLUMNS_NAME_PIZZAS[i]);
		this.jtfList[i] = new JTextField();
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
	    }
	    if (insert) {
		String[][] rowDataIngredients = new String[DAOFactory.COLUMNS_NAME_INGREDIENT.length][3];
		int i = 0;
		for (Ingredient ingredient : DAOPostgreSQL.getInstance()
			.readIngredient()) {
		    rowDataIngredients[i][0] = ingredient.getId() + "";
		    rowDataIngredients[i][1] = ingredient.getName();
		    rowDataIngredients[i][2] = "";
		    i++;
		}

		String[] columnsName = { "Id Ingredient", "Ingredient",
			"Quantity" };
		this.jtIngredientsTable = new JTable(rowDataIngredients,
			columnsName);
		this.jtIngredientsTable
			.setPreferredScrollableViewportSize(this.jtIngredientsTable
				.getPreferredSize());
		JScrollPane sp = new JScrollPane(this.jtIngredientsTable);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.gridwidth = 2;
		this.constraints.fill = GridBagConstraints.BOTH;
		this.add(sp, constraints);
	    }
	    break;
	case DAOFactory.TABLE_OFFERS:
	    this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_OFFERS.length];
	    this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_OFFERS.length];
	    for (int i = 0; i < DAOFactory.COLUMNS_NAME_OFFERS.length; i++) {
		this.jlLists[i] = new JLabel(DAOFactory.COLUMNS_NAME_OFFERS[i]);
		this.jtfList[i] = new JTextField();
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
	    }
	    break;
	case DAOFactory.TABLE_ORDERS:
	    this.jlLists = new JLabel[DAOFactory.COLUMNS_NAME_ORDERS.length];
	    this.jtfList = new JTextField[DAOFactory.COLUMNS_NAME_ORDERS.length];
	    for (int i = 0; i < DAOFactory.COLUMNS_NAME_ORDERS.length; i++) {
		this.jlLists[i] = new JLabel(DAOFactory.COLUMNS_NAME_ORDERS[i]);
		this.jtfList[i] = new JTextField();
		this.constraints.gridx = 0;
		this.constraints.gridy = ++this.indexConstrainstY;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
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
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
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
		this.add(this.jlLists[i], this.constraints);
		this.constraints.gridx = 1;
		this.add(this.jtfList[i], this.constraints);
	    }
	    break;
	default:
	    break;
	}
	this.constraints.gridy = ++this.indexConstrainstY;
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.DESELECTED) {
	    if (this.jlLists != null) {
		for (int i = 0; i < this.jlLists.length; i++) {
		    this.remove(this.jlLists[i]);
		    this.remove(this.jtfList[i]);
		}
		this.remove(this.jbExecuteQuery);
	    }
	}
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    showTextFields(e);
	    this.add(this.jbExecuteQuery, constraints);
	    this.indexConstrainstY = 0;
	}
	this.validate();

    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public JButton getJbExecuteQuery() {
	return jbExecuteQuery;
    }

    public JLabel[] getJlLists() {
	return jlLists;
    }

    public JTextField[] getJtfList() {
	return jtfList;
    }

    public JComboBox<String> getJcbTables() {
	return jcbTables;
    }

    public JTable getJtIngredientsTable() {
	return jtIngredientsTable;
    }

}
