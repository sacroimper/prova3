package org.escoladeltreball.arcowabungaproject.StandardObjects;

import org.escoladeltreball.arcowabungaproject.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

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
	
	public static TabHost setTab(TabHost mTabHost, int idTabCounter, final View view, final String tag, int initialColor) {
		View tabview = createTabView(mTabHost.getContext(), tag, initialColor);

		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview)
			.setContent(new TabContentFactory() {
			    public View createTabContent(String tag) {
				return view;
			    }
			});

		if (idTabCounter == 0) {
		    tabview.setTag("Tab 1");
		    idTabCounter++;
		} else if (idTabCounter == 1) {
		    tabview.setTag("Tab 2");
		    idTabCounter++;
		} else if (idTabCounter == 2) {
		    tabview.setTag("Tab 3");
		}
		mTabHost.addTab(setContent);
		return mTabHost;
	    }
	
	 private static View createTabView(final Context context, final String text, int initialColor) {
		View view = LayoutInflater.from(context)
			.inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setTextColor(initialColor);
		tv.setText(text);
		return view;
	    }
	 
	 public static View setTabColor(String tabId,View actualTab, final View viewTab1, final View viewTab2,
		    final View viewTab3, View tab1, View tab2,
		    View tab3) {
		if (actualTab.equals(viewTab1) && tabId.equals("Tab 2")) {
		    TabsMaker.changeColor(tab1, false);
		    TabsMaker.changeColor(tab2, true);
		    actualTab = viewTab2;
		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 1")) {
		    TabsMaker.changeColor(tab2, false);
		    TabsMaker.changeColor(tab1, true);
		    actualTab = viewTab1;
		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 3")) {
		    TabsMaker.changeColor(tab2, false);
		    TabsMaker.changeColor(tab3, true);
		    actualTab = viewTab3;
		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 2")) {
		    TabsMaker.changeColor(tab3, false);
		    TabsMaker.changeColor(tab2, true);
		    actualTab = viewTab2;
		} else if (actualTab.equals(viewTab1) && tabId.equals("Tab 3")) {
		    TabsMaker.changeColor(tab1, false);
		    TabsMaker.changeColor(tab3, true);
		    actualTab = viewTab3;
		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 1")) {
		    TabsMaker.changeColor(tab3, false);
		    TabsMaker.changeColor(tab1, true);
		    actualTab = viewTab1;
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
