/*
 *  OrderManagerPanel.java
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
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Ingredients;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.joda.time.DateTime;

public class OrderManagerPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpOrders;
    private JPanel jpInfo;
    private JSplitPane split;
    private JPanel jpWaitOrders;
    private JPanel jpMakingOrders;
    private JPanel jpSendedOrders;
    private static OrderManagerPanel instance;

    // ====================
    // CONSTRUCTORS
    // ====================
    private OrderManagerPanel() {
	this.initComponents();
    }

    public static OrderManagerPanel getInstance() {
	if (instance == null) {
	    instance = new OrderManagerPanel();
	}
	return instance;
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
	this.jpOrders = new JPanel();
	this.jpOrders.setLayout(new BorderLayout());
	this.jpOrders.setBorder(BorderFactory.createEtchedBorder());
	this.jpInfo = new OrderInfoPanel();
	this.jpInfo.setBorder(BorderFactory.createEtchedBorder());

	this.jpWaitOrders = new JPanel();

	this.jpMakingOrders = new JPanel();
	this.jpSendedOrders = new JPanel();

	this.addWaitOrder();

	this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpOrders,
		jpInfo);
	this.split.setOneTouchExpandable(true);
	this.split.setResizeWeight(0.5);

	this.jpOrders.add(createTabs());
	this.add(split);
    }

    private JTabbedPane createTabs() {

	JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);
	jtp.addTab("Wait Orders", this.jpWaitOrders);
	jtp.addTab("Making Orders", this.jpMakingOrders);
	jtp.addTab("Sended Orders", this.jpSendedOrders);

	return jtp;
    }

    public void setJpInfo(JPanel jpInfo) {
	if (this.jpInfo != null) {
	    this.split.remove(this.jpInfo);
	}
	this.jpInfo = jpInfo;
	this.split.setRightComponent(this.jpInfo);
    }

    private void addWaitOrder() {
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

	this.jpWaitOrders.add(new OrderPanel(or1));
	int[] numProducts = { 1, 0, 0 };

    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}