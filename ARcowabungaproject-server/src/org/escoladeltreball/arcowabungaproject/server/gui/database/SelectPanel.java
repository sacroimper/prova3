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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;

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
    }

    private void registListeners() {
	this.jcbTables.addItemListener(this);
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
	    }
	}
	if (e.getStateChange() == ItemEvent.SELECTED) {
	    String item = (String) e.getItem();
	    switch (item) {
	    // case DAOFactory.TABLE_ADDRESS:
	    // this.jlLists = new
	    // JLabel[DAOFactory.COLUMNS_NAME_ADDRESS.length];
	    // this.jtfList = new
	    // JTextField[DAOFactory.COLUMNS_NAME_ADDRESS.length];
	    // for (int i = 0; i < DAOFactory.COLUMNS_NAME_ADDRESS.length; i++)
	    // {
	    // this.jlLists[i] = new JLabel(
	    // DAOFactory.COLUMNS_NAME_ADDRESS[i]);
	    // this.jtfList[i] = new JTextField();
	    // this.constraints.gridx = 0;
	    // this.constraints.gridy = ++this.indexConstrainstY;
	    // this.constraints.fill = GridBagConstraints.HORIZONTAL;
	    // this.jpDoSelect.add(this.jlLists[i], this.constraints);
	    // this.constraints.gridx = 1;
	    // this.jpDoSelect.add(this.jtfList[i], this.constraints);
	    // }
	    // break;
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
	    this.jbExecuteQuery = new JButton("Execute Query");
	    this.constraints.gridy = ++this.indexConstrainstY;
	    this.jpDoSelect.add(this.jbExecuteQuery, constraints);
	    this.indexConstrainstY = 0;
	}
	this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (this.jbExecuteQuery != null) {
	    if (this.jtfList != null) {
		String item = (String) this.jcbTables.getSelectedItem();

		switch (item) {
		case DAOFactory.TABLE_ADDRESS:
		    for (int i = 0; i < this.jtfList.length; i++) {

		    }
		    break;

		default:
		    break;
		}
	    }
	}

    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
