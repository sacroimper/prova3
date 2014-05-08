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
