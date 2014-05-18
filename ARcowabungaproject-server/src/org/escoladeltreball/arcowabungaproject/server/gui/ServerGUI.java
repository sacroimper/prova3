/*
 *  ServerGUI.java
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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;
import org.escoladeltreball.arcowabungaproject.server.dao.DAOPostgreSQL;
import org.escoladeltreball.arcowabungaproject.server.gui.console.ServerPanel;

public class ServerGUI extends JFrame {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpOrderManager;
    private JPanel jpDataBaseManger;
    private JPanel jpServerConsole;
    private static ServerGUI instance;

    // ====================
    // CONSTRUCTORS
    // ====================
    private ServerGUI() {
	DAOFactory dao = DAOPostgreSQL.getInstance();
	Pizzeria pizzeria = Pizzeria.getInstance(dao);
	dao.loadDemo();
	this.initComponents();
    }

    // ====================
    // PUBLIC METHODS
    // ====================
    public static ServerGUI getInstance() {
	if (instance == null) {
	    instance = new ServerGUI();
	}
	return instance;
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    private void initComponents() {
	this.setSize(800, 600);
	setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

	this.setResizable(true);
	this.setTitle("Cowabunga Server");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setLayout(new BorderLayout());

	this.add(createMainTabs());
	this.setJMenuBar(createMenuBar());
	this.setVisible(true);
    }

    private JTabbedPane createMainTabs() {
	this.jpOrderManager = OrderManagerPanel.getInstance();
	this.jpServerConsole = new ServerPanel();
	this.jpDataBaseManger = new DataBaseManagerPanel();
	JTabbedPane jtpMain = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);
	jtpMain.addTab("Oreder Manager", this.jpOrderManager);
	jtpMain.addTab("DataBase Manager", this.jpDataBaseManger);
	jtpMain.addTab("Server Manager", this.jpServerConsole);

	return jtpMain;
    }

    private JMenuBar createMenuBar() {
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem menuItem = new JMenuItem("Data Base Manager");
	menu.add(menuItem);
	menuItem = new JMenuItem("Close");
	menu.add(menuItem);
	menuBar.add(menu);
	menu = new JMenu("Server");
	menuItem = new JMenuItem("Start Server");
	menu.add(menuItem);
	menuItem = new JMenuItem("Stop Server");
	menu.add(menuItem);
	menuBar.add(menu);
	return menuBar;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	getInstance();
    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
