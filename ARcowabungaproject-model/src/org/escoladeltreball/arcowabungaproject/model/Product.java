/*
 *  Product.java
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

public class Product extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = -1719816694255972083L;

    // ====================
    // ATTRIBUTES
    // ====================

    protected String name;
    protected float price;
    protected int icon;
    protected float discount;
    public static float TAX_PERCENT = .1f;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Product(int id) {
	super(id);
    }

    public Product(int id, String name, float price, int icon, float discount) {
	super(id);
	this.name = name;
	this.price = price;
	this.icon = icon;
	this.discount = discount;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public String getFormatedPrice() {
	return String.format("%.2f€", price);
    }

    public String getFormatedPriceWithTax() {
	return String.format("%.2f€", getPriceWithTax());
    }

    public float getPriceWithTax() {
	return getPrice() * (TAX_PERCENT + 1);
    }

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
	return "Product [id=" + id + ", name=" + name + ", price=" + price
		+ ", icon=" + icon + ", discount=" + discount + "]";
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

    public int getIcon() {
	return icon;
    }

    public void setIcon(int icon) {
	this.icon = icon;
    }

    public float getDiscount() {
	return discount;
    }

    public void setDiscount(float discount) {
	this.discount = discount;
    }

}
