/*
 *  ActionSendPizzaListener.java
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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSendOrderListener implements ActionListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    OrderPanel jpOrder;

    // ====================
    // CONSTRUCTORS
    // ====================
    public ActionSendOrderListener(OrderPanel jpOrder) {
	this.jpOrder = jpOrder;
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

    // ====================
    // OVERRIDE METHODS
    // ====================
    @Override
    public void actionPerformed(ActionEvent e) {
	OrderManagerPanel omp = OrderManagerPanel.getInstance();
	this.jpOrder.remove(this.jpOrder.getJbSendPizza());
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 3;
	constraints.gridy = 0;
	constraints.gridheight = 3;
	constraints.fill = 0;
	constraints.insets = new Insets(10, 0, 10, 10);
	this.jpOrder.add(this.jpOrder.getJbSavePizza(), constraints);
	omp.getJpMakingOrders().remove(this.jpOrder);
	omp.getJpSendedOrders().add(this.jpOrder);
	omp.repaint();
    }
    // ====================
    // GETTERS & SETTERS
    // ====================
}
