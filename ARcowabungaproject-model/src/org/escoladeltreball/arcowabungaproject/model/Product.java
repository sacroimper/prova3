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
