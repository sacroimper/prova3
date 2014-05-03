package org.escoladeltreball.arcowabungaproject.StandardObjects;

import org.escoladeltreball.arcowabungaproject.R;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class TabsMaker2 {

	// ====================
	// CONSTANTS
	// ====================

	// ====================
	// ATTRIBUTES
	// ====================

	// ====================
	// CONSTRUCTORS
	// ====================

	// ====================
	// PUBLIC METHODS
	// ====================

	
    /**
	 * Custom animation that animates in from right
	 * 
	 * @return Animation the Animation object
	 */
    	public static Animation inFromRightAnimation(int ANIMATION_TIME) {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		return setProperties(inFromRight, ANIMATION_TIME);
	}

	/**
	 * Custom animation that animates out to the right
	 * 
	 * @return Animation the Animation object
	 */
	public static Animation outToRightAnimation(int ANIMATION_TIME) {
		Animation outToRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		return setProperties(outToRight, ANIMATION_TIME);
	}

	/**
	 * Custom animation that animates in from left
	 * 
	 * @return Animation the Animation object
	 */
	public static Animation inFromLeftAnimation(int ANIMATION_TIME) {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		return setProperties(inFromLeft, ANIMATION_TIME);
	}

	/**
	 * Custom animation that animates out to the left
	 * 
	 * @return Animation the Animation object
	 */
	public static Animation outToLeftAnimation(int ANIMATION_TIME) {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		return setProperties(outtoLeft, ANIMATION_TIME);
	}

	public static void changeColor(View view, boolean isSelected) {
		int childCount = ((ViewGroup) view).getChildCount();
		for (int i = 0; i < childCount; i++) {
			View viewElement =((ViewGroup)view).getChildAt(i);
			if (viewElement != null) {
				// get title text view
				final View textView = viewElement.findViewById(R.id.tabsText);
				if (textView instanceof TextView) {
					if (!isSelected) {
						((TextView) textView).setTextColor(Color.WHITE);
					} else if (isSelected) {
						((TextView) textView).setTextColor(Color.GRAY);
					}
				}
			}
		}
	}
	
	/**
	 * Helper method that sets some common properties
	 * 
	 * @param animation
	 *            the animation to give common properties
	 * @return the animation with common properties
	 */
	private static Animation setProperties(Animation animation, int ANIMATION_TIME) {
		animation.setDuration(ANIMATION_TIME);
		animation.setInterpolator(new AccelerateInterpolator());
		return animation;
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
