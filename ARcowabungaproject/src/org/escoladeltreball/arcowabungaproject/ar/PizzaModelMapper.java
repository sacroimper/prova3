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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public final static String BASIC_PIZZA_MODEL = "/data/models/pizza_V5.obj";
    public final static String BASIC_PIZZA_TEXTURE = "/data/models/arpizza_texture.obj";

    // INGREDIENTS
    final static String INGREDIENT_MODEL = "/data/models/ingredient_V5.obj";
    final static String INGREDIENT_MUSHROOM_TEXTURE = "/data/models/mushroom_texture.obj";
    final static String INGREDIENT_REDPEPPER_TEXTURE = "/data/models/redpepper_texture.obj";
    final static String INGREDIENT_GREENPEPPER_TEXTURE = "/data/models/greenpepper_texture.obj";
    final static String INGREDIENT_TUNA_TEXTURE = "/data/models/tuna_texture.obj";
    final static String INGREDIENT_PINEAPPLE_TEXTURE = "/data/models/pineapple_texture.obj";
    final static String INGREDIENT_HAM_TEXTURE = "/data/models/ham_texture.obj";
    final static String INGREDIENT_BACON_TEXTURE = "/data/models/bacon_texture.obj";
    final static String INGREDIENT_BLUECHEESE_TEXTURE = "/data/models/bluecheese_texture.obj";
    final static String INGREDIENT_GOATCHEESE_TEXTURE = "/data/models/goatcheese_texture.obj";
    final static String INGREDIENT_GOUDA_TEXTURE = "/data/models/gouda_texture.obj";
    final static String INGREDIENT_GORGONZOLA_TEXTURE = "/data/models/gorgonzola_texture.obj";
    final static String INGREDIENT_EMENTAL_TEXTURE = "/data/models/emental_texture.obj";
    final static String INGREDIENT_PARMESAN_TEXTURE = "/data/models/parmesan_texture.obj";
    final static String INGREDIENT_ONION_TEXTURE = "/data/models/onion_texture.obj";
    final static String INGREDIENT_BEARSJENA_TEXTURE = "/data/models/bearsjena_texture.obj";
    final static String INGREDIENT_CORN_TEXTURE = "/data/models/corn_texture.obj";
    final static String INGREDIENT_CHICKEN_TEXTURE = "/data/models/chicken_texture.obj";
    final static String INGREDIENT_MINCE_TEXTURE = "/data/models/mince_texture.obj";
    final static String INGREDIENT_EGG_TEXTURE = "/data/models/egg_texture.obj";
    final static String INGREDIENT_MARJORAM_TEXTURE = "/data/models/marjoram_texture.obj";
    final static String INGREDIENT_ARTICHOKE_TEXTURE = "/data/models/artichoke_texture.obj";

    // ====================
    // ATTRIBUTES
    // ====================

    private Set<Ingredient> ingredients;
    private static Map<String, String> ingredientTextureMap;
    private static List<String> modelIngredientTextures;
    private static int ingredientsSize;

    // ====================
    // CONSTRUCTORS
    // ====================

    /**
     * 
     */
    public PizzaModelMapper(Pizza pizza) {
	super();
	this.ingredients = pizza.getIngredientsSet();
	ingredientsSize = this.ingredients.size();
	modelIngredientTextures = new ArrayList<String>();
	if (ingredientTextureMap == null) {
	    ingredientTextureMapAsigner();
	}
	makeTheModelIngredientTextureList();
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

    /**
     * 
     */
    private void makeTheModelIngredientTextureList() {
	for (Ingredient ingredient : ingredients) {
	    addToList(ingredient);
	}
    }

    /**
     * @param ingredient
     * @param string
     */
    private void addToList(Ingredient ingredient) {
	modelIngredientTextures.add(ingredientTextureMap.get(ingredient
		.getName()));
    }

    /**
     * 
     */
    private void ingredientTextureMapAsigner() {
	ingredientTextureMap = new HashMap<String, String>();

	// ADD VALUES TO MAP
	ingredientTextureMap.put("mushroom", INGREDIENT_MUSHROOM_TEXTURE);
	ingredientTextureMap.put("redpepper", INGREDIENT_REDPEPPER_TEXTURE);
	ingredientTextureMap.put("greenpepper", INGREDIENT_GREENPEPPER_TEXTURE);
	ingredientTextureMap.put("onion", INGREDIENT_ONION_TEXTURE);
	ingredientTextureMap.put("artichoke", INGREDIENT_ARTICHOKE_TEXTURE);
	ingredientTextureMap.put("bearsjena", INGREDIENT_BEARSJENA_TEXTURE);
	ingredientTextureMap.put("corn", INGREDIENT_CORN_TEXTURE);
	ingredientTextureMap.put("marjoram", INGREDIENT_MARJORAM_TEXTURE);
	ingredientTextureMap.put("egg", INGREDIENT_EGG_TEXTURE);
	ingredientTextureMap.put("ham", INGREDIENT_HAM_TEXTURE);
	ingredientTextureMap.put("bacon", INGREDIENT_BACON_TEXTURE);
	ingredientTextureMap.put("mince", INGREDIENT_MINCE_TEXTURE);
	ingredientTextureMap.put("chicken", INGREDIENT_CHICKEN_TEXTURE);
	ingredientTextureMap.put("bluecheese", INGREDIENT_BLUECHEESE_TEXTURE);
	ingredientTextureMap.put("goatcheese", INGREDIENT_GOATCHEESE_TEXTURE);
	ingredientTextureMap.put("gouda", INGREDIENT_GOUDA_TEXTURE);
	ingredientTextureMap.put("gorgonzola", INGREDIENT_GORGONZOLA_TEXTURE);
	ingredientTextureMap.put("parmesan", INGREDIENT_PARMESAN_TEXTURE);
	ingredientTextureMap.put("pineapple", INGREDIENT_PINEAPPLE_TEXTURE);
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

    /**
     * @return the ingredientTextureMap
     */
    public static Map<String, String> getIngredientTextureMap() {
	return ingredientTextureMap;
    }

    /**
     * @param ingredientTextureMap
     *            the ingredientTextureMap to set
     */
    public static void setIngredientTextureMap(
	    Map<String, String> ingredientTextureMap) {
	PizzaModelMapper.ingredientTextureMap = ingredientTextureMap;
    }
}
