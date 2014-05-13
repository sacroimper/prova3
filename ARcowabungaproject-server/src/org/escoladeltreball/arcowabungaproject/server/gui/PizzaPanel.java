/*
 *  PizzaPanel.java
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

import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Pizza;

public class PizzaPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private Pizza pizza;
    private Border border;
    private JTable jtIngredients;
    private JLabel jlPizzaName;
    private JLabel jlPizzaSize;
    private JLabel jlPizzaMassType;
    private JLabel jlPizzaType;
    private JLabel jlPizzaPrice;

    // ====================
    // CONSTRUCTORS
    // ====================
    public PizzaPanel(Pizza pizza) {
	this.pizza = pizza;
	initComponents();
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
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	// Put information
	this.jlPizzaName = new JLabel(this.pizza.getName());
	this.jlPizzaName.setFont(jlPizzaName.getFont().deriveFont(30f));
	this.jlPizzaSize = new JLabel("Size: " + this.pizza.getSize());
	this.jlPizzaMassType = new JLabel("Mass Type: "
		+ this.pizza.getMassType());
	this.jlPizzaType = new JLabel("Type: " + this.pizza.getType());
	this.jlPizzaPrice = new JLabel("Price: " + this.pizza.getPrice() + "€");

	String[] columnNames = { "Ingredient", "Quantity", };
	String[][] ingredientsData = new String[this.pizza.getIngredients()
		.size()][2];

	int i = 0;
	for (Map.Entry<Ingredient, Integer> entry : this.pizza.getIngredients()
		.entrySet()) {
	    ingredientsData[i][0] = entry.getKey().getName();
	    ingredientsData[i][1] = entry.getValue().toString();
	    i++;
	}
	this.jtIngredients = new JTable(ingredientsData, columnNames);
	// Center text in table
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	jtIngredients.getColumn("Ingredient").setCellRenderer(centerRenderer);
	jtIngredients.getColumn("Quantity").setCellRenderer(centerRenderer);

	JScrollPane sp = new JScrollPane(this.jtIngredients);

	this.add(this.jlPizzaName);
	this.add(this.jlPizzaSize);
	this.add(this.jlPizzaMassType);
	this.add(this.jlPizzaType);
	this.add(sp);
	this.add(this.jlPizzaPrice);
    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
