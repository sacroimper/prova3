/*
 *  ContactInfoPanel.java
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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Order;

public class ContactInfoPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private Order order;
    private Address address;
    private Border border;
    private JLabel jlStreet;
    private JLabel jlNumber;
    private JLabel jlFloor;
    private JLabel jlStair;
    private JLabel jlDoor;
    private JLabel jlPostCode;
    private JLabel jlPhone;
    private JLabel jlEmail;

    // ====================
    // CONSTRUCTORS
    // ====================
    public ContactInfoPanel(Order order) {
	this.order = order;
	this.address = order.getAddress();
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
	this.border = BorderFactory.createLineBorder(Color.BLACK, 6);
	this.setBorder(BorderFactory.createTitledBorder(border, "Contac Info"));

	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	this.jlStreet = new JLabel("Street: " + this.address.getStreet());
	this.jlNumber = new JLabel("Number: " + this.address.getNumber());
	this.jlStair = new JLabel("Satir: " + this.address.getStair());
	this.jlFloor = new JLabel("Floor: " + this.address.getFloor());
	this.jlDoor = new JLabel("Door: " + this.address.getDoor());
	this.jlPostCode = new JLabel("Post Code: " + this.address.getPostCode());
	this.jlPhone = new JLabel("Phone: " + this.order.getPhone());
	this.jlEmail = new JLabel("Email: " + this.order.getEmail());

	this.add(this.jlStreet);
	this.add(this.jlNumber);
	this.add(this.jlStair);
	this.add(this.jlFloor);
	this.add(this.jlDoor);
	this.add(this.jlPostCode);
	this.add(this.jlPhone);
	this.add(this.jlEmail);

    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
