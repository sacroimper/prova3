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

import java.util.ArrayList;

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
	return shoppingCart.getFormatedPrice();
    }

    public String getFormatedPriceWithTax() {
	return shoppingCart.getFormatedPrice();
    }

    /**
     * Calculate the number of different products in the order.
     * 
     * @return an array where the number of pizzas are stored in index 0, the
     *         number of drinks are stored in index 1 and the number of offers
     *         are stored in index 2
     */
    public int[] numOfDifferentsProductsInOrder() {
	int numOfPizzas = 0;
	int numOfDriks = 0;
	int numOfOffers = 0;
	int numOfPizzasOffer = 0;
	int numOfDrinksOffer = 0;
	int[] differentProducts = new int[5];
	ArrayList<Product> products = (ArrayList<Product>) this
		.getShoppingCart().getProducts();
	for (Product product : products) {
	    if (product instanceof Pizza) {
		numOfPizzas++;
	    } else if (product instanceof Drink) {
		numOfDriks++;
	    } else if (product instanceof Offer) {
		numOfOffers++;
		Offer offer = (Offer) product;
		ArrayList<Product> offerProducts = (ArrayList<Product>) offer
			.getProductList();
		for (Product offerProduct : offerProducts) {
		    if (offerProduct instanceof Pizza) {
			numOfPizzasOffer++;
		    } else if (offerProduct instanceof Drink) {
			numOfDrinksOffer++;
		    }
		}
	    }
	}
	differentProducts[0] = numOfPizzas;
	differentProducts[1] = numOfDriks;
	differentProducts[2] = numOfOffers;
	differentProducts[3] = numOfPizzasOffer;
	differentProducts[4] = numOfDrinksOffer;
	return differentProducts;
    }

    public float getTotalPrice() {
	return shoppingCart.getPrice();
    }

    public float getTotalPriceWithTax() {
	return shoppingCart.getPriceWithTax();
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
