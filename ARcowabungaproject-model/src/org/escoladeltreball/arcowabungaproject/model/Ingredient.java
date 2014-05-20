/*
 *  Ingredient.java
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

public class Ingredient extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 8143762553201667318L;

    // ====================
    // ATTRIBUTES
    // ====================

    private String name;
    private float price;
    private int model;
    private int icon;
    private int texture;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Ingredient(int id) {
	super(id);
	// TODO Auto-generated constructor stub
    }

    public Ingredient(int id, String name, float price, int model, int icon,
	    int texture) {
	super(id);
	this.name = name;
	this.price = price;
	this.model = model;
	this.icon = icon;
	this.texture = texture;
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
	return "Ingredient [name=" + name + ", price=" + price + ", model="
		+ model + ", icon=" + icon + ", texture=" + texture + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public float getPrice() {
	return price;
    }

    public void setPrice(float price) {
	this.price = price;
    }

    public int getModel() {
	return model;
    }

    public void setModel(int model) {
	this.model = model;
    }

    public int getIcon() {
	return icon;
    }

    public void setIcon(int icon) {
	this.icon = icon;
    }

    public int getTexture() {
	return texture;
    }

    public void setTexture(int texture) {
	this.texture = texture;
    }
}
