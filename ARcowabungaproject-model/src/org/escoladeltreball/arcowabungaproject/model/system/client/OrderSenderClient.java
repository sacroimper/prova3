/*
 *  OrderSenderClient.java
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
package org.escoladeltreball.arcowabungaproject.model.system.client;

import java.io.IOException;

import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.system.ServerConstants;

/**
 * @author local
 * 
 */
public class OrderSenderClient extends Client {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private Order order;

    // ====================
    // CONSTRUCTORS
    // ====================

    public OrderSenderClient(Order order) {
	this.order = order;
	option = ServerConstants.SERVER_OPTION_SEND_ORDER;
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

    private void connectToOrderReceiverServer(int port) {
	if (port != 0) {
	    init(port);
	    try {
		out.writeObject(order);
		out.flush();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    readInt();
	    close();
	}
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void connect() {
	int newPort = connectToHallServer(option);
	connectToOrderReceiverServer(newPort);
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
