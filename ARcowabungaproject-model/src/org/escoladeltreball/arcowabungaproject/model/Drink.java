/*
 *  Drink.java
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

package org.escoladeltreball.arcowabungaproject.model;

public class Drink extends Product {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 1298024289874646633L;

    public static final int SIZE_SMALL = 1;
    public static final int SIZE_MEDIUM = 2;
    public static final int SIZE_LARGE = 3;

    // ====================
    // ATTRIBUTES
    // ====================

    private int size;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Drink(int id) {
	super(id);
    }

    public Drink(int id, String name, float price, int icon, float discount,
	    int size) {
	super(id, name, price, icon, discount);
	this.size = size;
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
    public String toString() {
	return "Drink [id=" + id + ", size=" + size + ", name=" + name
		+ ", price=" + price + ", icon=" + icon + ", discount="
		+ discount + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }
}
