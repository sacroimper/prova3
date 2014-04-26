package org.escoladeltreball.arcowabungaproject.model;

public class Pizza extends Product {
    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private String massType;
    private String type;
    private int size;

    // private IngredientList ingredientList;

    // ====================
    // CONSTRUCTORS
    // ====================
    public Pizza(int id, String name, float price, int icon, float discount,
	    String massType, String type, int size) {
	super(id, name, price, icon, discount);
	this.massType = massType;
	this.type = type;
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

    // ====================
    // GETTERS & SETTERS
    // ====================

}
