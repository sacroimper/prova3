package org.escoladeltreball.layouttest;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class TabsMaker {

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

	
	public static Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(400);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public static Animation leftToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(400);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	public static Animation leftFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(400);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(400);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
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
