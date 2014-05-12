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
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ServerGUI extends JFrame {

    private JButton startServer;
    private Border border;
    private JPanel panel;

    public ServerGUI() {
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
	this.panel.setLayout(new GridLayout(2, 2));
	this.border = BorderFactory.createCompoundBorder();
	this.panel.setBorder(BorderFactory.createTitledBorder("Orders"));
	this.startServer = new JButton("Start Server");
	this.panel.add(addOrder("order1"));
	this.panel.add(addOrder("order2"));
	this.panel.add(addOrder("order3"));
	this.add(this.panel, BorderLayout.CENTER);
	this.add(startServer, BorderLayout.SOUTH);
	this.setVisible(true);
    }

    private JInternalFrame addOrder(String nameOrder) {
	JInternalFrame orderFrame = new JInternalFrame();

	JButton accept = new JButton("Accept Order");
	orderFrame.setLayout(new BorderLayout());
	orderFrame.setTitle(nameOrder);
	orderFrame.add(accept, BorderLayout.SOUTH);
	orderFrame.setVisible(true);
	return orderFrame;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	new ServerGUI();

    }

}
