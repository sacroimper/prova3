/*
 *  DrinkPanel.java
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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.escoladeltreball.arcowabungaproject.model.Drink;

public class DrinkPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private Drink drink;
    private JLabel jldrinkName;
    private JLabel jldrinkPrice;
    private Border border;

    // ====================
    // CONSTRUCTORS
    // ====================
    public DrinkPanel(Drink drink) {
	this.drink = drink;
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
	this.border = BorderFactory.createEtchedBorder();
	this.setBorder(BorderFactory.createTitledBorder(border, "DRINK"));
	this.jldrinkName = new JLabel("Name: " + this.drink.getName());
	this.jldrinkPrice = new JLabel("Price: " + this.drink.getPrice() + "€");

	this.add(this.jldrinkName);
	this.add(this.jldrinkPrice);

    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
