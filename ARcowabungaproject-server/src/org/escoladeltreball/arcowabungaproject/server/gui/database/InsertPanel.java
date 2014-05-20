/*
 *  InsertPanel.java
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
package org.escoladeltreball.arcowabungaproject.server.gui.database;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsertPanel extends JPanel implements ActionListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpDoInsert;
    private JPanel jpShowTables;
    private JButton jbInserData;
    private JTextField[] jtfList;

    // ====================
    // CONSTRUCTORS
    // ====================
    public InsertPanel() {
	this.initComponents();
	this.registListeners();
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
	this.jpDoInsert = new ShowRowsTextFieldsPanel();
	this.jpDoInsert.setLayout(new GridBagLayout());
	this.jpShowTables = new JPanel();
	this.jbInserData = ((ShowRowsTextFieldsPanel) jpDoInsert)
		.getJbExecuteQuery();
	this.jbInserData.setText("Insert Data");

	this.add(this.jpDoInsert, BorderLayout.WEST);
	this.add(this.jpShowTables, BorderLayout.CENTER);
    }

    private void registListeners() {
	this.jbInserData.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.jtfList = ((ShowRowsTextFieldsPanel) jpDoInsert).getJtfList();
	boolean textFieldsIsEmpty = true;
	int i = 0;
	while (i < jtfList.length && textFieldsIsEmpty) {
	    if (!jtfList[i].getText().isEmpty()) {
		textFieldsIsEmpty = false;
	    }
	    i++;
	}
	if (textFieldsIsEmpty) {
	    this.jpShowTables
		    .add(new JLabel(
			    "The insertion was not done. You have not inserted any data"));
	}
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
