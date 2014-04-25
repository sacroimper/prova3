package org.escoladeltreball.arcowabungaproject.model;

public class Ingredient extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private String name;
    private float price;
    private int model;
    private int icon;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Ingredient(int id) {
	super(id);
	// TODO Auto-generated constructor stub
    }

    public Ingredient(int id, String name, float price, int model, int icon) {
	super(id);
	this.name = name;
	this.price = price;
	this.model = model;
	this.icon = icon;
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
	return "Ingredient [id=" + id + ", price=" + price + ", model=" + model
		+ ", icon=" + icon + ", name=" + name + "]";
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
}
