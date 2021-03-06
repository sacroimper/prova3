/*
 *  Pizza.java
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

import java.util.Set;

public class Pizza extends Product {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 3520483599417593606L;

    public static final String MASSTYPE_THIN = "thin";
    public static final String MASSTYPE_NORMAL = "normal";
    public static final String MASSTYPE_THICK = "thick";
    public static final String TYPE_CUSTOM_SAVED = "customSaved";
    public static final String TYPE_CUSTOM_TEMPORARY = "customTemporary";
    public static final String TYPE_PREDEFINED = "predefined";
    public static final int SIZE_SMALL = 1;
    public static final int SIZE_MEDIUM = 2;
    public static final int SIZE_LARGE = 3;
    public static final int SIZE_COWABUNGA = 4;

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

    public Pizza(int id) {
	super(id);
    }

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

    public boolean addIngredient(Ingredient ingredient, int value) {
	if (ingredient != null) {
	    if (ingredients == null) {
		ingredients = new Ingredients(id);
	    }
	    return ingredients.add(ingredient, value);
	}
	return false;
    }

    public boolean removeIngredient(Ingredient ingredient) {
	if (ingredient != null) {
	    ingredients.remove(ingredient);
	    return true;
	}
	return false;
    }

    public Set<Ingredient> getIngredientsSet() {
	return ingredients.getIngredients();
    }

    public void remove() {
	ingredients = null;
    }

    public String getIngedientsDescription() {
	String description = "";
	for (Ingredient ingredient : getIngredientsSet()) {
	    description += ingredient.getName() + ", ";
	}
	description = description.substring(0, description.lastIndexOf(","));
	return description;
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
	return "Pizza [" + "id=" + id + ", name=" + name + ", price=" + price
		+ ", icon=" + icon + ", discount=" + discount + ", massType="
		+ massType + ", type=" + type + ", size=" + size
		+ ", ingredients=" + ingredients.toString() + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    public void printIngredients() {
	System.out.println(ingredients.toString());
    }

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

    public Ingredients getIngredients() {
	return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
	this.ingredients = ingredients;
    }
}
