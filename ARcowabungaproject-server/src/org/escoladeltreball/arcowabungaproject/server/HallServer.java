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

    private static HallServer instance;

    // ====================
    // CONSTRUCTORS
    // ====================

    private HallServer() {
	super(ServerConstants.HALL_SERVER_PORT);
	listeningServers = new HashMap<Integer, Server>();
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public static HallServer getInstance() {
	if (instance == null) {
	    instance = new HallServer();
	}
	return instance;
    }

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
    public void run() {
	init();
	while (!isStopped()) {
	    try {
		waitClient();
		int opt = readInt();
		Server newServer = null;
		int newPort = 0;
		switch (opt) {
		case ServerConstants.SERVER_OPTION_DATABASE_UPDATE:
		    newPort = getValidPort();
		    newServer = new DatabaseUpdateServer(newPort);
		    break;
		case ServerConstants.SERVER_OPTION_SEND_ORDER:
		    newPort = getValidPort();
		    newServer = new OrderReceiverServer(newPort);
		    break;
		}
		if (newServer != null) {
		    newServer.startServer();
		    out.writeInt(newPort);
		    out.flush();
		}
		closeClient();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	close();
    }

    @Override
    public void stopServer() {
	super.stopServer();
	instance = null;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

}
