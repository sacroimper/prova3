/*
 *  PizzaModelMapper.java
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
package org.escoladeltreball.arcowabungaproject.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Pizza;

/**
 * @author victor
 * 
 */
public class PizzaModelMapper {

    // ====================
    // CONSTANTS
    // ====================

    // PIZZA
    public final static String BASIC_PIZZA_MODEL = "data/models/pizza_V5.obj";
    public final static String BASIC_PIZZA_TEXTURE = "data/models/arpizza_texture.jpg";

    // INGREDIENTS
    public final static String INGREDIENT_MODEL = "data/models/ingredient_V5.obj";
    public final static String INGREDIENT_ALPHA_TEXTURE = "data/models/alpha_texture.png";

    // ====================
    // ATTRIBUTES
    // ====================

    private static ArrayList<String> modelIngredientTextures;
    // private static int ingredientsSize = 0;
    private static Set<Ingredient> ingredients;
    private static int pizzaScale;
    private static float pizzaMassType;

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    public static void run(Pizza pizza) {
	ingredients = pizza.getIngredientsSet();
	pizzaScale = pizza.getSize();
	modelIngredientTextures = new ArrayList<String>();
	makeTheModelIngredientTextureList();
	pizzaMassTypeFloatTranslator(pizza.getMassType());
    }

    /**
     * @param string
     * 
     */
    private static void pizzaMassTypeFloatTranslator(String pizzaMeshType) {
	if (pizzaMeshType.equals(Pizza.MASSTYPE_THIN)) {
	    pizzaMassType = 0.5f;
	} else if (pizzaMeshType.equals(Pizza.MASSTYPE_NORMAL)) {
	    pizzaMassType = 1f;
	} else if (pizzaMeshType.equals(Pizza.MASSTYPE_THIN)) {
	    pizzaMassType = 2f;
	}

    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // MODIFICAR
    private static void makeTheModelIngredientTextureList() {
	DAOAndroid daoA = DAOAndroid.getInstance();
	for (Ingredient ingredient : ingredients) {
	    modelIngredientTextures.add(daoA.getResourcePath(ingredient
		    .getTexture()));
	}
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

    /**
     * @return the modelIngredientTextures
     */
    public static List<String> getModelIngredientTextures() {
	return modelIngredientTextures;
    }

    /**
     * @param modelIngredientTextures
     *            the modelIngredientTextures to set
     */
    public static void setModelIngredientTextures(
	    ArrayList<String> modelIngredientTextures) {
	PizzaModelMapper.modelIngredientTextures = modelIngredientTextures;
    }

    /**
     * @return the pizzaScale
     */
    public static int getPizzaScale() {
	return pizzaScale;
    }

    /**
     * @param pizzaScale
     *            the pizzaScale to set
     */
    public static void setPizzaScale(int pizzaScale) {
	PizzaModelMapper.pizzaScale = pizzaScale;
    }

    /**
     * @return the pizzaMassType
     */
    public static float getPizzaMassType() {
	return pizzaMassType;
    }

    /**
     * @param pizzaMassType
     *            the pizzaMassType to set
     */
    public static void setPizzaMassType(float pizzaMassType) {
	PizzaModelMapper.pizzaMassType = pizzaMassType;
    }
}
