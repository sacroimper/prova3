package org.escoladeltreball.arcowabungaproject.model;

import java.util.HashMap;

public class IngredientsList extends HashMap<Ingredient, Integer> {

    // ====================
    // CONSTANTS
    // ====================

    public static final int MIN_INGREDIENTS = 3;
    public static final int MAX_INGREDIENTS = 16;
    private static final long serialVersionUID = 2916673702284211535L;

    // ====================
    // ATTRIBUTES
    // ====================

    private int id;

    // ====================
    // CONSTRUCTORS
    // ====================

    public IngredientsList(int id) {
	super();
	this.id = id;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public boolean add(Ingredient ingredient) {
	return add(ingredient, 1);
    }

    public boolean add(Ingredient ingredient, int value) {
	if (getTotalIngredients() + value <= MAX_INGREDIENTS) {
	    if (containsKey(ingredient)) {
		value = get(ingredient) + value;
	    }
	    put(ingredient, value);
	    return true;
	} else {
	    return false;
	}
    }

    public int getTotalIngredients() {
	int total = 0;
	for (int v : this.values()) {
	    total += v;
	}
	return total;
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
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + id;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	IngredientsList other = (IngredientsList) obj;
	if (id != other.id)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "IngredientsList [id=" + id + ", " + super.toString() + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

}
