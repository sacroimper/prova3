/*
 *  OrderInfoPanel.java
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;

public class OrderInfoPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private Order order;
    private JLabel jlIdOrder;
    private JLabel jlTotalPrice;
    private JPanel jpContacInfo;
    private JPanel[] jpPizzas;
    private JPanel[] jpDrinks;
    private JPanel[] jpOffers;
    private int numOfPizzas;
    private int numOfDrinks;
    private int numOfOffers;
    private int numOfPizzasOffer;
    private int numOfDrinksOffer;

    // ====================
    // CONSTRUCTORS
    // ====================
    public OrderInfoPanel(Order order, int[] numOfProducts) {
	this.order = order;
	this.numOfPizzas = numOfProducts[0];
	this.numOfDrinks = numOfProducts[1];
	this.numOfOffers = numOfProducts[2];
	this.numOfPizzasOffer = numOfProducts[3];
	this.numOfDrinksOffer = numOfProducts[4];
	this.initComponents();
    }

    public OrderInfoPanel() {

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
	int index = 0;
	this.setLayout(new GridBagLayout());
	this.jlIdOrder = new JLabel("Order Id: " + this.order.getId());
	this.jpContacInfo = new ContactInfoPanel(this.order);
	this.jlTotalPrice = new JLabel("TOTAL PRICE (with IVA): "
		+ this.order.getTotalPriceWithTax() + "€");
	addProductsInfo();

	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = index;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	this.add(jlIdOrder, constraints);
	constraints.gridx = 0;
	constraints.gridy = ++index;
	this.add(jpContacInfo, constraints);

	for (int i = 0; i < jpPizzas.length; i++) {
	    constraints.gridy = ++index;
	    this.add(jpPizzas[i], constraints);
	}
	for (int i = 0; i < jpDrinks.length; i++) {
	    constraints.gridy = ++index;
	    this.add(jpDrinks[i], constraints);
	}
	for (int i = 0; i < jpOffers.length; i++) {
	    constraints.gridy = ++index;
	    this.add(jpOffers[i], constraints);
	}
	constraints.gridy = ++index;
	this.add(this.jlTotalPrice, constraints);
    }

    private void addProductsInfo() {
	this.jpPizzas = new JPanel[this.numOfPizzas];
	this.jpDrinks = new JPanel[this.numOfDrinks];
	this.jpOffers = new JPanel[this.numOfOffers];
	int indexPizzas = 0;
	int indexDrink = 0;
	int indexOffer = 0;
	ArrayList<Product> products = (ArrayList<Product>) this.order
		.getShoppingCart().getProducts();
	for (Product product : products) {
	    if (product instanceof Pizza) {
		Pizza pizza = (Pizza) product;
		this.jpPizzas[indexPizzas] = new PizzaPanel(pizza);
		indexPizzas++;
	    } else if (product instanceof Drink) {
		Drink drink = (Drink) product;
		this.jpDrinks[indexDrink] = new DrinkPanel(drink);
		indexDrink++;
	    } else if (product instanceof Offer) {
		Offer offer = (Offer) product;
		this.jpOffers[indexOffer] = new OfferPanel(offer,
			this.numOfPizzasOffer, this.numOfDrinksOffer);
		indexOffer++;
	    }

	}
    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
