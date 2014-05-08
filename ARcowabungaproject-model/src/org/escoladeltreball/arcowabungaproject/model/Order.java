/*
 *  Order.java
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

import org.joda.time.DateTime;

public class Order extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = -2135877001141999580L;

    // ====================
    // ATTRIBUTES
    // ====================

    private String phone;
    private String email;
    private DateTime dateTime;
    private String paymentMethod;
    private Address address;
    private ShoppingCart shoppingCart;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Order(int id) {
	super(id);
    }

    public Order(int id, String phone, String email, DateTime dateTime,
	    String paymentMethod, Address address, ShoppingCart shoppingCart) {
	super(id);
	this.phone = phone;
	this.email = email;
	this.dateTime = dateTime;
	this.paymentMethod = paymentMethod;
	this.address = address;
	this.shoppingCart = shoppingCart;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    // Falta comentar
    public void removeYou() {
	this.shoppingCart = null;
	this.address = null;
    }

    // FALTA IMPLEMENTAR
    public boolean send() {
	return false;
    }

    public String getFormatedPrice() {
	return String.format("%.2f€", getTotalPrice());
    }

    private float getTotalPrice() {
	float totalPrice = 0;
	for (Product product : getShoppingCart().getProducts()) {
	    totalPrice += product.getPrice();
	}
	return totalPrice;
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
	return "Order [phone=" + phone + ", email=" + email + ", dateTime="
		+ dateTime + ", paymentMethod=" + paymentMethod + ", address="
		+ address + ", shoppingCart=" + shoppingCart + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    public void printAddress() {
	System.out.println(this.address.toString());
    }

    public void printShoppingCart() {
	System.out.println(this.shoppingCart.toString());
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public DateTime getDateTime() {
	return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
	this.dateTime = dateTime;
    }

    public String getPaymentMethod() {
	return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public ShoppingCart getShoppingCart() {
	return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
	this.shoppingCart = shoppingCart;
    }

}
