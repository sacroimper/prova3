/*
 *  CustomTextView.java
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

package org.escoladeltreball.arcowabungaproject.utils;

import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class CustomTextView {

    // ====================
    // CONSTANTS
    // ====================
    private static final String FONT_TYPE = "gnuolane.ttf";
    private static Typeface tf = null;

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    public static void customTextView(Context context, TextView tv, int style) {
	// Font path
	String fontPath = "fonts/" + FONT_TYPE;

	// Loading Font Face
	if (tf == null) {
	    tf = Typeface.createFromAsset(context.getAssets(), fontPath);
	}

	// Applying font
	tv.setTypeface(tf, style);
    }

    public static void customTextView(Context context, TextView tv) {
	// Font path
	String fontPath = "fonts/" + FONT_TYPE;

	// Loading Font Face
	if (tf == null) {
	    tf = Typeface.createFromAsset(context.getAssets(), fontPath);
	}

	// Applying font
	tv.setTypeface(tf);
    }

    public static void plusPriceOrder(TextView tv) {
	ShoppingCart shopCart = Pizzeria.getInstance().getShoppingCart();
	int numberProducts = shopCart.getProducts().size();
	tv.setText("(" + numberProducts + ")"
		+ shopCart.getFormatedPriceWithTax());
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
}
