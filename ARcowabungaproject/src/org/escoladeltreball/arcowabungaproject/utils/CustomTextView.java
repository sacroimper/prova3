package org.escoladeltreball.arcowabungaproject.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class CustomTextView {

    // ====================
    // CONSTANTS
    // ====================
    private static final String FONT_TYPE = "gnuolane.ttf";
    private static Typeface tf;

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================
    public static void customTextView(Context context, TextView tv) {
	// Font path
	String fontPath = "fonts/" + FONT_TYPE;

	// Loading Font Face
	if (tf == null) {
	    Typeface tf = Typeface.createFromAsset(context.getAssets(),
		    fontPath);
	}

	// Applying font
	tv.setTypeface(tf);
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
