package org.escoladeltreball.arcowabungaproject.visualeffects;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


public class TabsSwipeAnimation {

    //====================
    // CONSTANTS
    //====================

    //====================
    // ATTRIBUTES
    //====================

    //====================
    // CONSTRUCTORS
    //====================

    //====================
    // PUBLIC METHODS
    //====================
    
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

    //====================
    // PROTECTED METHODS
    //====================

    //====================
    // PRIVATE METHODS
    //====================

    //====================
    // OVERRIDE METHODS
    //====================

    //====================
    // GETTERS & SETTERS
    //====================
}
