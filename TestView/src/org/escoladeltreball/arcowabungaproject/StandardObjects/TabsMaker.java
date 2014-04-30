package org.escoladeltreball.arcowabungaproject.StandardObjects;

import org.escoladeltreball.arcowabungaproject.R;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
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
    
    //MAKE A SINGLE TAB
    public static TabSpec makeTab(TabHost tabs, String tabSpec, int content, String indicator) {
	TabHost.TabSpec spec = tabs.newTabSpec(tabSpec);
	spec.setContent(content);
	spec.setIndicator(indicator);
	return spec;
    }
    
    
    //STYLE
    
    public static TabHost tabsAspectRatio(TabHost tabs) {
   	int tabCount = tabs.getTabWidget().getTabCount();
   	for (int i = 0; i < tabCount; i++) {
   	    final View view = tabs.getTabWidget().getChildTabViewAt(i);
   	    if (view != null) {
   		// reduce height of the tab
   		view.getLayoutParams().height *= 0.66;

   		// get title text view
   		final View textView = view.findViewById(android.R.id.title);
   		if (textView instanceof TextView) {
   		    // just in case check the type

   		    // center text
   		    ((TextView) textView).setGravity(Gravity.CENTER);
   		    // wrap text
   		    ((TextView) textView).setSingleLine(false);

   		    // explicitly set layout parameters
   		    textView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
   		    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

   		}
   	    }
   	}
   	return tabs;
       }

       public static TabHost tabsColor(TabHost tabs, String color) {
	   int numberOfTabs =  tabs.getTabWidget().getChildCount();
	   for (int i = 0 ; i < numberOfTabs ; i++){
	       tabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor(color));
	   }
   	return tabs;

       }
    
    //ANIMATIONS

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

    //OJO mytab2... y los demas Strings que se comparan
    //TENDRAN QUE ENTRAR POR EL METODO
    public static LinearLayout OnChangeTabAnimation(String tabId,
	    TabHost tabs, LinearLayout actualTab, LinearLayout t1l, LinearLayout t2l,
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
