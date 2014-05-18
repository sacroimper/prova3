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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;

public class SelectPanel extends JPanel implements ItemListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JLabel[] jlLists;
    private JTextField[] jtfList;
    private JLabel jlChooseTable;
    private JComboBox<String> jcbTables;
    private GridBagConstraints constraints;
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
	this.setLayout(new GridBagLayout());
	String[] items = { "", DAOFactory.TABLE_ADDRESS,
		DAOFactory.TABLE_DRINKS, DAOFactory.TABLE_INGREDIENT,
		DAOFactory.TABLE_INGREDIENTS, DAOFactory.TABLE_OFFERS,
		DAOFactory.TABLE_OFFERS_PRODUCTS, DAOFactory.TABLE_ORDERS,
		DAOFactory.TABLE_PIZZAS, DAOFactory.TABLE_PREFERENCES,
		DAOFactory.TABLE_PRODUCTS, DAOFactory.TABLE_RESOURCES,
		DAOFactory.TABLE_SHOPPINGCART_PRODUCTS,
		DAOFactory.TABLE_SHOPPINGCARTS };
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

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void itemStateChanged(ItemEvent e) {
	if (e.getStateChange() == ItemEvent.DESELECTED) {
	    String item = (String) e.getItem();
	    switch (item) {
	    case DAOFactory.TABLE_ADDRESS:
		for (int i = 0; i < DAOFactory.COLUMNS_NAME_ADDRESS.length; i++) {
		    this.remove(this.jlLists[i]);
		    this.remove(this.jtfList[i]);
		}
		break;
	    default:
		break;
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
		    this.add(this.jlLists[i], this.constraints);
		    this.constraints.gridx = 1;
		    this.add(this.jtfList[i], this.constraints);
		}
		this.indexConstrainstY = 0;
		break;

	    default:
		break;
	    }
	}
	this.repaint();
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
