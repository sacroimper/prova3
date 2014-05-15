/*
 *  ServerGUI.java
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
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class ServerGUI extends JFrame {

    private JPanel jpOrders;
    public static JPanel jpInfo;

    private JSplitPane split;
    private JPanel jpWaitOrders;
    private JPanel jpMakingOrders;
    private JPanel jpSendedOrders;
    private static ServerGUI instance;

    private ServerGUI() {
	this.initComponents();
    }

    public static ServerGUI getInstance() {
	if (instance == null) {
	    instance = new ServerGUI();
	}
	return instance;
    }

    private void initComponents() {
	this.setSize(900, 700);
	this.setResizable(true);
	this.setTitle("Cowabunga Server");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setLayout(new BorderLayout());

	this.jpWaitOrders = new JPanel();
	this.jpWaitOrders.setLayout(new BoxLayout(this.jpWaitOrders,
		BoxLayout.Y_AXIS));
	this.addWaitOrder();

	this.jpOrders = new JPanel();
	this.jpOrders.setLayout(new BorderLayout());
	this.jpOrders.setBorder(BorderFactory.createEtchedBorder());
	this.jpOrders.add(createTabs());

	this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpOrders,
		jpInfo);

	this.split.setOneTouchExpandable(true);
	this.split.setResizeWeight(0.5);

	this.add(split);
	this.setJMenuBar(createMenuBar());
	this.setVisible(true);
    }

    private JTabbedPane createTabs() {
	JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);
	jtp.addTab("Wait Orders", this.jpWaitOrders);
	jtp.addTab("Making Orders", this.jpMakingOrders);
	jtp.addTab("Sended Orders", this.jpSendedOrders);
	return jtp;
    }

    private JMenuBar createMenuBar() {
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem menuItem = new JMenuItem("Data Base Manager");
	menu.add(menuItem);
	menuItem = new JMenuItem("Close");
	menu.add(menuItem);
	menuBar.add(menu);
	menu = new JMenu("Server");
	menuItem = new JMenuItem("Start Server");
	menu.add(menuItem);
	menuItem = new JMenuItem("Stop Server");
	menu.add(menuItem);
	menuBar.add(menu);
	return menuBar;
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
	this.jpInfo = new OrderInfoPanel();
	this.jpInfo.setBorder(BorderFactory.createEtchedBorder());

    }

    // public void setJpInfo(JPanel jpInfo) {
    // this.jpInfo = jpInfo;
    // }

    /**
     * @param args
     */
    public static void main(String[] args) {
	getInstance();
    }

}
