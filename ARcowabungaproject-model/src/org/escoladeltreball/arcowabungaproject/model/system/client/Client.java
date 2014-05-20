/*
 *  Client.java
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.escoladeltreball.arcowabungaproject.model.system.ServerConstants;

public abstract class Client {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    protected Socket socket;

    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    protected int option;

    protected static String ipAddress;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Client() {

    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public abstract void connect();

    // ====================
    // PROTECTED METHODS
    // ====================

    protected int connectToHallServer(int option) {
	init(4444);
	int newport = 0;
	try {
	    out.writeInt(option);
	    out.flush();
	    newport = readInt();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	close();
	return newport;
    }

    protected void init(int port) {
	try {
	    socket = new Socket("localhost", port);
	    out = new ObjectOutputStream(new BufferedOutputStream(
		    socket.getOutputStream()));
	    out.flush();
	    in = new ObjectInputStream(new BufferedInputStream(
		    socket.getInputStream()));
	    System.out.println("conectat al port " + port);
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    protected void close() {
	try {
	    out.close();
	    in.close();
	    socket.close();
	    out = null;
	    in = null;
	    socket = null;
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    protected int readInt() {
	int n = 0;
	while (n == 0) {
	    try {
		n = in.readInt();
	    } catch (Exception e) {
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
	    }
	}
	return n;
    }

    protected Object readObject() {
	Object obj = null;
	while (obj == null) {
	    try {
		obj = in.readObject();
	    } catch (Exception e) {
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
	    }
	}
	return obj;
    }

    protected boolean tryIP() {
	try {
	    socket = new Socket(ipAddress, ServerConstants.HALL_SERVER_PORT);
	    socket.close();
	    socket = null;
	} catch (UnknownHostException e) {
	    return false;
	} catch (IOException e) {
	    return false;
	}
	return true;
    }

    protected boolean tryIPs() {
	boolean foundIP = false;
	for (int n = 11; n < 255 && !foundIP; n++) {
	    ipAddress = ServerConstants.IP_ADRESS_PREFIX + n;
	    foundIP = tryIP();
	}
	return true;
    }

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
