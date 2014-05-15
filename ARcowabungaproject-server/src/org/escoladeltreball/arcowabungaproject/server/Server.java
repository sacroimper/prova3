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
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public abstract class Server extends Thread {

    // ====================
    // CONSTANTS
    // ====================

    public static final int HALL_PORT = 4444;

    // ====================
    // ATTRIBUTES
    // ====================

    protected ServerSocket serverSocket;
    protected Socket socketService;

    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    private int port;
    private boolean stop;

    protected static Map<Integer, Server> listeningServers = new HashMap<Integer, Server>();

    // ====================
    // CONSTRUCTORS
    // ====================

    public Server(int port) {
	super();
	setName(getClass().getSimpleName() + ":" + port);
	this.port = port;
	stop = true;
	synchronized (listeningServers) {
	    listeningServers.put(port, this);
	}
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public void stopServer() {
	if (!stop) {
	    stop = true;
	    close();
	}
    }

    public void startServer() {
	if (stop) {
	    stop = false;
	    start();
	}
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    protected void waitClient() throws IOException {
	print("Waiting client ...");
	socketService = serverSocket.accept();
	out = new ObjectOutputStream(socketService.getOutputStream());
	out.flush();
	in = new ObjectInputStream(socketService.getInputStream());
	print("Client conected");
    }

    protected void closeClient() throws IOException {
	out.close();
	in.close();
	socketService.close();
	print("Client closed");
    }

    protected void close() {
	try {
	    closeClient();
	    serverSocket.close();
	} catch (IOException e) {
	    System.out.println(e);
	} finally {
	    print("Closed");
	}
	synchronized (listeningServers) {
	    listeningServers.remove(port);
	}
    }

    protected void init() {
	try {
	    serverSocket = new ServerSocket(port);
	    print("Opened");
	} catch (IOException e) {
	    e.printStackTrace();
	    stop = true;
	    throw new RuntimeException("Couln't open server");
	}
    }

    protected void print(String msg) {
	System.out.println(getName() + "> " + msg);
    }

    protected int getValidPort() {
	int newPort = HALL_PORT + 1;
	synchronized (listeningServers) {
	    while (listeningServers.containsKey(newPort)
		    || !isValidPort(newPort)) {
		newPort++;
	    }
	}
	return newPort;
    }

    protected boolean isValidPort(int port) {
	ServerSocket ss = null;
	DatagramSocket ds = null;
	try {
	    ss = new ServerSocket(port);
	    ss.setReuseAddress(true);
	    ds = new DatagramSocket(port);
	    ds.setReuseAddress(true);
	    return true;
	} catch (IOException e) {
	} finally {
	    if (ds != null) {
		ds.close();
	    }

	    if (ss != null) {
		try {
		    ss.close();
		} catch (IOException e) {
		    /* should not be thrown */
		}
	    }
	}
	return false;
    }

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public abstract void run();

    // ====================
    // GETTERS & SETTERS
    // ====================

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public boolean isStopped() {
	return stop;
    }

}
