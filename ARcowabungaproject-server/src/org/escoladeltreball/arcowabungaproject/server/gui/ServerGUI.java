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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class ServerGUI extends JFrame {

    private Border border;
    private JPanel jpOrders;
    private JPanel jpInfo;

    private JSplitPane split;
    private JPanel panelWaitOrders;
    private JPanel panelMakingOrders;
    private JPanel panelSendedOrders;

    public ServerGUI() {
	this.initComponents();
    }

    private void initComponents() {
	this.setSize(900, 700);
	this.setResizable(true);
	this.setTitle("Cowabunga Server");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setLayout(new BorderLayout());

	this.jpOrders = new JPanel();
	this.jpOrders.setLayout(new BorderLayout());
	this.jpOrders.setBorder(BorderFactory.createEtchedBorder());
	this.jpOrders.add(createTabs());

	this.jpInfo = new JPanel();
	this.jpInfo.setBorder(BorderFactory.createEtchedBorder());

	this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpOrders,
		jpInfo);
	this.split.setOneTouchExpandable(true);
	this.split.setResizeWeight(0.5);

	this.add(split);
	this.setJMenuBar(CreateMenuBar());
	this.setVisible(true);
    }

    private JTabbedPane createTabs() {
	JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);

	jtp.addTab("Wait Orders", this.panelWaitOrders);
	jtp.addTab("Making Orders", this.panelMakingOrders);
	jtp.addTab("Sended Orders", this.panelSendedOrders);

	return jtp;
    }

    private JMenuBar CreateMenuBar() {
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
	new ServerGUI();

    }

}
