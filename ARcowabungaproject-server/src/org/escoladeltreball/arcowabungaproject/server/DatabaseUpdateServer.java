/*
 *  DatabaseUpdateServer.java
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

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;
import org.escoladeltreball.arcowabungaproject.server.dao.DAOPostgreSQL;

public class DatabaseUpdateServer extends Server {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public DatabaseUpdateServer(int port) {
	super(port);
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
    public void run() {
	init();
	try {
	    waitClient();
	    int clientDBVersion = in.readInt();
	    Pizzeria pizzeria = Pizzeria.getInstance();
	    DAOFactory dao = DAOPostgreSQL.getInstance();
	    int currentVersion = dao.getCurrentVersion();
	    if (clientDBVersion != currentVersion) {
		out.writeObject(pizzeria.getIngredients());
		out.writeObject(pizzeria.getPredefinedPizzas());
		out.writeObject(pizzeria.getDrinks());
		out.writeObject(pizzeria.getOffers());
		out.writeInt(currentVersion);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	close();
    }
    // ====================
    // GETTERS & SETTERS
    // ====================

}
