package org.escoladeltreball.arcowabungaproject.visualeffects;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class TabsAnimations {

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

    public static LinearLayout OnChangeTabAnimation(String tabId,
	    LinearLayout actualTab, LinearLayout t1l, LinearLayout t2l,
	    LinearLayout t3l) {
	if (actualTab.equals(t1l) && tabId.equals("mytab2")) {
	    t1l.setAnimation(outToLeftAnimation());
	    t2l.setAnimation(inFromRightAnimation());
	    actualTab = t2l;
	} else if (actualTab.equals(t2l) && tabId.equals("mytab1")) {
	    t2l.setAnimation(leftToLeftAnimation());
	    t1l.setAnimation(leftFromRightAnimation());
	    actualTab = t1l;
	} else if (actualTab.equals(t2l) && tabId.equals("mytab3")) {
	    t2l.setAnimation(outToLeftAnimation());
	    t3l.setAnimation(inFromRightAnimation());
	    actualTab = t3l;
	} else if (actualTab.equals(t3l) && tabId.equals("mytab2")) {
	    t3l.setAnimation(leftToLeftAnimation());
	    t2l.setAnimation(leftFromRightAnimation());
	    actualTab = t2l;
	} else if (actualTab.equals(t1l) && tabId.equals("mytab3")) {
	    t1l.setAnimation(outToLeftAnimation());
	    t3l.setAnimation(inFromRightAnimation());
	    actualTab = t3l;
	} else if (actualTab.equals(t3l) && tabId.equals("mytab1")) {
	    t3l.setAnimation(leftToLeftAnimation());
	    t1l.setAnimation(leftFromRightAnimation());
	    actualTab = t1l;
	}
	return actualTab;
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
