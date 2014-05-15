/*
 *  ServerPanel.java
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
package org.escoladeltreball.arcowabungaproject.server.gui.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.escoladeltreball.arcowabungaproject.server.HallServer;

public class ServerPanel extends JPanel implements ActionListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private JTextArea jtaConsole;
    private JLabel jlTitle;
    private JPanel jpLeft;
    private JButton jbStart;
    private JButton jbStop;

    // ====================
    // CONSTRUCTORS
    // ====================

    public ServerPanel() {
	super();
	initComponents();
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

	this.setLayout(new BorderLayout());

	jlTitle = new JLabel("Servers Output");
	jtaConsole = new JTextArea("init");

	PrintStream in = System.out;
	PrintStreamCapturer psc = new PrintStreamCapturer(jtaConsole, in);
	System.setOut(psc);

	jpLeft = new JPanel();
	jpLeft.setLayout(new BoxLayout(jpLeft, BoxLayout.Y_AXIS));

	jbStart = new JButton("Start");
	jbStop = new JButton("Start");

	jpLeft.add(jbStart);
	jpLeft.add(jbStop);

	this.add(jpLeft, BorderLayout.WEST);
	this.add(jlTitle, BorderLayout.NORTH);
	this.add(jtaConsole, BorderLayout.CENTER);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public void actionPerformed(ActionEvent e) {
	Object src = e.getSource();
	if (src.equals(jbStart)) {
	    HallServer.getInstance().startServer();
	    jbStart.setEnabled(false);
	    jbStop.setEnabled(true);
	} else {
	    HallServer.getInstance().stopServer();
	    jbStop.setEnabled(false);
	    jbStart.setEnabled(true);
	}
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
