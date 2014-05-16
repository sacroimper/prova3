/*
 *  OfferPanel.java
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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;

public class OfferPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private Offer offer;
    private JPanel[] jpPizzas;
    private JPanel[] jpDrinks;
    private JLabel jlOfferName;
    private JLabel jlPrice;
    private JLabel jlDiscount;
    private Border border;
    private int numOfPizzasOffer;
    private int numOfDrinksOffer;

    // ====================
    // CONSTRUCTORS
    // ====================
    public OfferPanel(Offer offer, int[] numOfProductsInOffer) {
	this.offer = offer;
	this.numOfPizzasOffer = numOfProductsInOffer[3];
	this.numOfDrinksOffer = numOfProductsInOffer[4];
	this.initComonents();
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
    private void initComonents() {
	int index = 0;
	this.border = BorderFactory.createEtchedBorder();
	this.jlOfferName = new JLabel("Offer Name : " + this.offer.getName());
	this.jlDiscount = new JLabel("Discount: " + this.offer.getDiscount());
	this.jlPrice = new JLabel("Price: " + this.offer.getPrice());

	this.setLayout(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = index;
	constraints.gridwidth = 2;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	this.add(this.jlOfferName, constraints);

	for (int i = 0; i < jpPizzas.length; i++) {
	    constraints.gridy = index++;
	    this.add(jpPizzas[i], constraints);
	}
	for (int i = 0; i < jpDrinks.length; i++) {
	    constraints.gridy = index++;
	    this.add(jpDrinks[i], constraints);
	}
	constraints.gridx = 1;
	constraints.gridy = index++;
	this.add(this.jlDiscount);
	constraints.gridy = index++;
	this.add(this.jlPrice);

    }

    private void addProductsInfo() {
	this.jpPizzas = new JPanel[this.numOfPizzasOffer];
	this.jpDrinks = new JPanel[this.numOfDrinksOffer];
	int indexPizzas = 0;
	int indexDrink = 0;
	ArrayList<Product> products = (ArrayList<Product>) this.offer
		.getProductList();
	for (Product product : products) {
	    if (product instanceof Pizza) {
		Pizza pizza = (Pizza) product;
		this.jpPizzas[indexPizzas] = new PizzaPanel(pizza);
		indexPizzas++;
	    } else if (product instanceof Drink) {
		Drink drink = (Drink) product;
		this.jpDrinks[indexDrink] = new DrinkPanel(drink);
		indexDrink++;
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
