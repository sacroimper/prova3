/*
 *  Server.java
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HallServer extends Thread {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private ServerSocket serverSocket;
    private Socket socketService;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Map<Integer, HallServer> listeningServers;

    // ====================
    // CONSTRUCTORS
    // ====================

    public HallServer() {
	super();
	listeningServers = new HashMap<Integer, HallServer>();
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

    private void waitClient() throws IOException {
	print("Waiting client ...");
	socketService = serverSocket.accept();
	out = new ObjectOutputStream(socketService.getOutputStream());
	out.flush();
	in = new ObjectInputStream(socketService.getInputStream());
    }

    private void closeClient() throws IOException {
	out.close();
	in.close();
	socketService.close();
	print("Client closed");
    }

    private void close() {
	try {
	    closeClient();
	} catch (IOException e) {
	    System.out.println(e);
	} finally {
	    print("Closed");
	}
    }

    private void init() {
	try {
	    serverSocket = new ServerSocket(5432);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void print(String msg) {
	System.out.println(getClass().getSimpleName() + "> " + msg);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void run() {
	init();
	try {
	    waitClient();

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
