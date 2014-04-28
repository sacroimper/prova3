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

    private Ingredients ingredients;

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
    public boolean addIngredient(Ingredient ingredient) {
	if (ingredient != null) {
	    if (ingredients == null) {
		ingredients = new Ingredients(id);
	    }
	    if (ingredients.containsKey(ingredient)) {
		int numOfIngredient = ingredients.get(ingredient);
		return ingredients.add(ingredient, numOfIngredient++);
	    } else {
		return ingredients.add(ingredient);
	    }
	}
	return false;
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

    // ====================
    // GETTERS & SETTERS
    // ====================
    public String getMassType() {
	return massType;
    }

    public void setMassType(String massType) {
	this.massType = massType;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }
}
