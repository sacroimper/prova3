/*
 *  TestGUI.java
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Ingredients;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.joda.time.DateTime;

/**
 * @author local
 * 
 */
public class TestGUI extends JFrame {
    private JButton startServer;
    private Border border;
    private JPanel panel;

    public TestGUI() {
	this.initComponents();
    }

    private void initComponents() {
	this.setSize(900, 700);
	this.setResizable(true);
	this.setTitle("Cowabunga Server");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setLayout(new BorderLayout());

	this.panel = new JPanel();
	this.panel.setLayout(new GridBagLayout());

	this.border = BorderFactory.createEtchedBorder();
	this.panel
		.setBorder(BorderFactory.createTitledBorder(border, "Orders"));
	this.startServer = new JButton("Start Server");
	Ingredient i1 = new Ingredient(8, "I1", 1.5f, 151, 152);
	Ingredient i2 = new Ingredient(9, "I2", 1.5f, 151, 152);
	Ingredient i3 = new Ingredient(10, "I3", 1.5f, 151, 152);
	Ingredient i4 = new Ingredient(11, "I4", 1.5f, 151, 152);
	Ingredient i5 = new Ingredient(12, "I5", 1.5f, 151, 152);
	Ingredient i6 = new Ingredient(13, "I6", 1.5f, 151, 152);
	Ingredient i7 = new Ingredient(14, "I7", 1.5f, 151, 152);
	Ingredient i8 = new Ingredient(15, "I8", 1.5f, 151, 152);
	Ingredient i9 = new Ingredient(16, "I9", 1.5f, 151, 152);
	Ingredient i10 = new Ingredient(17, "I10", 1.5f, 151, 152);

	Pizza p1 = new Pizza(1, "P1", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_SMALL);

	Ingredients is1 = new Ingredients(28);
	is1.add(i1);
	is1.add(i2);
	is1.add(i3);
	is1.add(i4);
	is1.add(i5);
	is1.add(i6);
	is1.add(i7);
	is1.add(i8);
	is1.add(i9);
	is1.add(i10);

	p1.setIngredients(is1);
	this.panel.add(new PizzaPanel(p1));
	// this.panel.add(addOrder("order2", getDemoOrder()));
	this.add(this.panel);
	// this.add(startServer, BorderLayout.SOUTH);
	this.setVisible(true);
    }

    private JPanel addOrder(String nameOrder, Order order) {
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	JPanel orderPanel = new JPanel();
	orderPanel.setBorder(BorderFactory
		.createTitledBorder(border, nameOrder));
	JButton accept = new JButton("Accept Order");
	ShoppingCart shoppingCart = order.getShoppingCart();
	orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
	ArrayList<Product> products = (ArrayList<Product>) shoppingCart
		.getProducts();

	for (Product product : products) {
	    orderPanel.add(addPrdouctItem(product));
	}
	JPanel jpContact = new JPanel();
	jpContact.setLayout(new BoxLayout(jpContact, BoxLayout.Y_AXIS));
	jpContact
		.setBorder(BorderFactory.createTitledBorder(border, "Contact"));
	JLabel jlStreet = new JLabel("Street : "
		+ order.getAddress().getStreet());
	JLabel jlNumber = new JLabel("Number : "
		+ order.getAddress().getNumber());
	JLabel jlFloor = new JLabel("Number : " + order.getAddress().getFloor());
	JLabel jlStair = new JLabel("Stair : " + order.getAddress().getStair());
	JLabel jlPostCode = new JLabel("PostCode : "
		+ order.getAddress().getPostCode());
	JLabel jlEmail = new JLabel("Email : " + order.getEmail());
	jpContact.add(jlStreet);
	jpContact.add(jlNumber);
	jpContact.add(jlFloor);
	jpContact.add(jlStair);
	jpContact.add(jlPostCode);
	jpContact.add(jlEmail);

	orderPanel.add(jpContact);
	orderPanel.add(accept, BorderLayout.SOUTH);

	return orderPanel;
    }

    private JPanel addPrdouctItem(Product product) {
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	JPanel jpItemProduct = new JPanel();
	jpItemProduct.setBorder(border);
	JLabel jlNameProduct = new JLabel(product.getName());
	jpItemProduct.add(jlNameProduct);
	if (product instanceof Pizza) {

	}

	return jpItemProduct;
    }

    private Order getDemoOrder() {
	Set<Ingredient> ingredients = new HashSet<Ingredient>();

	Ingredient i1 = new Ingredient(8, "I1", 1.5f, 151, 152);
	Ingredient i2 = new Ingredient(9, "I2", 1.5f, 151, 152);
	Ingredient i3 = new Ingredient(10, "I3", 1.5f, 151, 152);
	Ingredient i4 = new Ingredient(11, "I4", 1.5f, 151, 152);
	Ingredient i5 = new Ingredient(12, "I5", 1.5f, 151, 152);
	Ingredient i6 = new Ingredient(13, "I6", 1.5f, 151, 152);
	Ingredient i7 = new Ingredient(14, "I7", 1.5f, 151, 152);
	Ingredient i8 = new Ingredient(15, "I8", 1.5f, 151, 152);
	Ingredient i9 = new Ingredient(16, "I9", 1.5f, 151, 152);
	Ingredient i10 = new Ingredient(17, "I10", 1.5f, 151, 152);

	Pizza p1 = new Pizza(1, "P1", 10, 150, 0, Pizza.MASSTYPE_THIN,
		Pizza.TYPE_PREDEFINED, Pizza.SIZE_SMALL);

	Ingredients is1 = new Ingredients(28);
	is1.add(i1);
	is1.add(i2);
	is1.add(i3);
	is1.add(i4);
	is1.add(i5);
	is1.add(i6);
	is1.add(i7);
	is1.add(i8);
	is1.add(i9);
	is1.add(i10);

	p1.setIngredients(is1);

	Address a1 = new Address(35, "a", "a", "a", "a", "a", "a");
	DateTime dt1 = DateTime.now().minusDays(3);
	ShoppingCart s1 = new ShoppingCart(38);
	s1.addProduct(p1);
	Order or1 = new Order(41, "a", "a", dt1, "a", a1, s1);
	return or1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	new TestGUI();

    }

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
