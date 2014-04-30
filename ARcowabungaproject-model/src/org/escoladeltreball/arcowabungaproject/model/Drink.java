package org.escoladeltreball.arcowabungaproject.model;

public class Drink extends Product {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 1298024289874646633L;

    private static final int SIZE_SMALL = 1;
    private static final int SIZE_MEDIUM = 2;
    private static final int SIZE_LARGE = 3;

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
