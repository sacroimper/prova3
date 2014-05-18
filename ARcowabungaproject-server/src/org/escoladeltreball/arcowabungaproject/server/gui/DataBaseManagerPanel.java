/*
 *  DataBaseManagerPanel.java
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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DataBaseManagerPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpSelectData;
    private JPanel jpInsertData;
    private JPanel jpUpdateData;
    private JPanel jpDeleteData;

    // ====================
    // CONSTRUCTORS
    // ====================
    public DataBaseManagerPanel() {
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
	this.setLayout(new BorderLayout());
	this.add(createTabs());
    }

    private JTabbedPane createTabs() {
	this.jpSelectData = new JPanel();
	this.jpInsertData = new JPanel();
	this.jpUpdateData = new JPanel();
	this.jpDeleteData = new JPanel();

	JTabbedPane jtpMain = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);
	jtpMain.addTab("Select Data", this.jpSelectData);
	jtpMain.addTab("Insert Data", this.jpInsertData);
	jtpMain.addTab("Update Data", this.jpUpdateData);
	jtpMain.addTab("Delete Data", this.jpDeleteData);

	return jtpMain;
    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
