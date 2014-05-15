/*
 *  OrderPanel.java
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
package org.escoladeltreball.arcowabungaproject.server.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import org.escoladeltreball.arcowabungaproject.model.Order;

public class OrderPanel extends JPanel {
    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private Order order;
    private Border border;
    private JLabel jlOrderId;
    private JLabel jlNumOfPizzas;
    private JLabel jlNumOfDrinks;
    private JLabel jlNumOfOffers;
    private JLabel jlTotalPrice;
    private JButton jbMakePizza;
    private JButton jbShowInfo;

    // ====================
    // CONSTRUCTORS
    // ====================
    public OrderPanel(Order order) {
	this.order = order;
	this.initComponents();
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
	this.setMaximumSize(new Dimension(900, 90));
	this.border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
		new Color(205, 9, 9), new Color(232, 68, 68));
	this.setBorder(border);
	this.setLayout(new GridBagLayout());

	// Put information in components
	this.jlOrderId = new JLabel("ID: " + this.order.getId());

	this.jlNumOfPizzas = new JLabel("Pizzas: "
		+ this.order.numOfDifferentsProductsInOrder()[0]);

	this.jlNumOfDrinks = new JLabel("Drinks: "
		+ this.order.numOfDifferentsProductsInOrder()[1]);

	this.jlNumOfOffers = new JLabel("Offers: "
		+ this.order.numOfDifferentsProductsInOrder()[2]);

	this.jlTotalPrice = new JLabel("Total Price: "
		+ this.order.getShoppingCart().getPrice() + "€");

	this.jbMakePizza = new JButton("Make Pizza!");
	this.jbShowInfo = new JButton("Show More Info");

	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridheight = 3;
	constraints.ipadx = 5;
	constraints.insets = new Insets(10, 10, 10, 10);
	constraints.fill = GridBagConstraints.BOTH;
	this.add(this.jlOrderId, constraints);
	constraints.gridx = 1;
	constraints.gridy = 0;
	constraints.gridheight = 1;
	constraints.ipady = 5;
	constraints.insets = new Insets(10, 0, 10, 10);
	this.add(this.jlNumOfPizzas, constraints);
	constraints.gridx = 1;
	constraints.gridy = 1;
	constraints.insets = new Insets(0, 0, 10, 10);
	this.add(this.jlNumOfDrinks, constraints);
	constraints.gridx = 1;
	constraints.gridy = 2;
	this.add(this.jlNumOfOffers, constraints);
	constraints.gridx = 2;
	constraints.gridy = 0;
	constraints.gridheight = 3;
	constraints.insets = new Insets(10, 0, 10, 10);
	this.add(this.jlTotalPrice, constraints);
	constraints.gridx = 3;
	constraints.gridy = 0;
	constraints.gridheight = 3;
	constraints.fill = 0;
	this.add(this.jbMakePizza, constraints);
	constraints.gridx = 4;
	constraints.gridy = 0;
	constraints.gridheight = 3;
	this.add(this.jbShowInfo, constraints);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
