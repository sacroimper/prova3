package org.escoladeltreball.arcowabungaproject.visualeffects;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class TabsStyle {

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

    public static TabHost tabsColor(TabHost tabs) {
	tabs.getTabWidget().getChildAt(0)
		.setBackgroundColor(Color.parseColor("#3be0d0"));
	tabs.getTabWidget().getChildAt(1)
		.setBackgroundColor(Color.parseColor("#3be0d0"));
	tabs.getTabWidget().getChildAt(2)
		.setBackgroundColor(Color.parseColor("#3be0d0"));
	return tabs;

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
