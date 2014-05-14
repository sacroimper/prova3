/*
 *  HallServer.java
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

package org.escoladeltreball.arcowabungaproject.server;

import java.io.IOException;
import java.util.HashMap;

import org.escoladeltreball.arcowabungaproject.model.system.ServerConstants;

public class HallServer extends Server {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public HallServer() {
	super(HALL_PORT);
	listeningServers = new HashMap<Integer, Server>();
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

    private int validPort() {
	int validPort = HALL_PORT + 1;
	synchronized (listeningServers) {
	    while (listeningServers.containsKey(validPort)) {
		validPort++;
	    }
	}
	return validPort;
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void run() {
	init();
	try {
	    waitClient();
	    int opt = in.readInt();
	    Server newServer = null;
	    int newPort = validPort();
	    switch (opt) {
	    case ServerConstants.SERVER_OPTION_DATABASE_UPDATE:
		newServer = new DatabaseUpdateServer(newPort);
		break;
	    case ServerConstants.SERVER_OPTION_SEND_ORDER:
		newServer = new OrderReceiverServer(newPort);
		break;
	    }
	    if (newServer != null) {
		newServer.start();
		out.write(newPort);
	    }
	    closeClient();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	close();
    }
    // ====================
    // GETTERS & SETTERS
    // ====================

}
