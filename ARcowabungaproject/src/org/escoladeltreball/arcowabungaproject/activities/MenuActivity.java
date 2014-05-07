package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MenuActivity extends Activity {

    // ====================
    // CONSTANTS
    // ====================
    private static final int ANIMATION_TIME = 700;

    // ====================
    // ATTRIBUTES
    // ====================
    private TabHost tabHost;
    private View viewMenuPizza;
    private View viewMenuDrinks;
    private View viewMenuOffers;
    private View previousView;
    private View currentView;
    private View actualTab;
    private int idTabCounter = 0;
    private float lastX;
    private int currentTab;

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================
    private void setupTab(final View view, final String tag) {
	View tabview = createTabView(tabHost.getContext(), tag);

	TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview)
		.setContent(new TabContentFactory() {
		    public View createTabContent(String tag) {
			return view;
		    }
		});

	if (idTabCounter == 0) {
	    tabview.setTag("pizzas");
	    idTabCounter++;
	} else if (idTabCounter == 1) {
	    tabview.setTag("drinks");
	    idTabCounter++;
	} else if (idTabCounter == 2) {
	    tabview.setTag("offers");
	}
	tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
	View view;
	if (text.equals("pizzas")) {
	    view = LayoutInflater.from(context).inflate(R.layout.tab_pizzas,
		    null);
	} else if (text.equals("drinks")) {
	    view = LayoutInflater.from(context).inflate(R.layout.tab_drinks,
		    null);
	} else {
	    view = LayoutInflater.from(context).inflate(R.layout.tab_offers,
		    null);
	}
	TextView tv = (TextView) view.findViewById(R.id.tabTittle);
	tv.setTextColor(Color.WHITE);
	String finalText = text.toUpperCase();
	tv.setText(finalText);
	return view;
    }

    public static void changeColor(View view, boolean isSelected) {
	int childCount = ((ViewGroup) view).getChildCount();
	for (int i = 0; i < childCount; i++) {
	    View viewElement = ((ViewGroup) view).getChildAt(i);
	    if (viewElement != null) {
		// get title text view
		final View textView = viewElement.findViewById(R.id.tabTittle);
		final View imageView = viewElement.findViewById(R.id.tabImg);
		if (textView instanceof TextView) {
		    if (!isSelected) {
			((TextView) textView).setTextColor(Color.WHITE);
		    } else if (isSelected) {
			((TextView) textView).setTextColor(Color.GRAY);
		    }
		} else if (imageView instanceof ImageView) {
		    if (imageView.getTag().equals("drink_image")) {
			if (!isSelected) {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontabdrinks);
			} else {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontabdrinksoff);
			}
		    } else if (imageView.getTag().equals("pizza_image")) {
			if (!isSelected) {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontabpizzas);
			} else {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontabpizzasoff);
			}
		    } else if (imageView.getTag().equals("offers_image")) {
			if (!isSelected) {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontaboffers);
			} else {
			    ((ImageView) imageView)
				    .setImageResource(R.drawable.icontaboffersoff);
			}
		    }
		}
	    }
	}
    }

    /**
     * Custom animation that animates in from right
     * 
     * @return Animation the Animation object
     */
    private Animation inFromRightAnimation() {
	Animation inFromRight = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT, 1.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f);
	return setProperties(inFromRight);
    }

    /**
     * Custom animation that animates out to the right
     * 
     * @return Animation the Animation object
     */
    private Animation outToRightAnimation() {
	Animation outToRight = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 1.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f);
	return setProperties(outToRight);
    }

    /**
     * Custom animation that animates in from left
     * 
     * @return Animation the Animation object
     */
    private Animation inFromLeftAnimation() {
	Animation inFromLeft = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT, -1.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f);
	return setProperties(inFromLeft);
    }

    /**
     * Custom animation that animates out to the left
     * 
     * @return Animation the Animation object
     */
    private Animation outToLeftAnimation() {
	Animation outtoLeft = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, -1.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f,
		Animation.RELATIVE_TO_PARENT, 0.0f);
	return setProperties(outtoLeft);
    }

    /**
     * Helper method that sets some common properties
     * 
     * @param animation
     *            the animation to give common properties
     * @return the animation with common properties
     */
    private Animation setProperties(Animation animation) {
	animation.setDuration(ANIMATION_TIME);
	animation.setInterpolator(new AccelerateInterpolator());
	return animation;
    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	this.setContentView(R.layout.activity_menu);

	// Set transition animation
	// this.overridePendingTransition(R.anim.animation_horizontal_enter,
	// R.anim.animation_horizontal_leave);

	// Up tabhost
	tabHost = (TabHost) findViewById(android.R.id.tabhost);
	tabHost.setup();

	// Create views by layout
	LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext()
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	viewMenuPizza = layoutInflater.inflate(R.layout.content_tab, null);
	LayoutInflater layoutInflater2 = (LayoutInflater) getApplicationContext()
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	viewMenuDrinks = layoutInflater2.inflate(R.layout.content_second_tab,
		null);
	LayoutInflater layoutInflater3 = (LayoutInflater) getApplicationContext()
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	viewMenuOffers = layoutInflater3.inflate(R.layout.content_third_tab,
		null);

	// Inflate custom tabhost with custom views.
	setupTab(viewMenuPizza, "pizzas");
	setupTab(viewMenuDrinks, "drinks");
	setupTab(viewMenuOffers, "offers");

	// Set default tab before first init and change his look&feel
	tabHost.setCurrentTab(0);
	View firstTab = tabHost.findViewWithTag("pizzas");
	changeColor(firstTab, true);
	actualTab = viewMenuPizza;

	previousView = tabHost.getCurrentView();

	tabHost.setOnTabChangedListener(new OnTabChangeListener() {
	    @Override
	    public void onTabChanged(String tabId) {

		currentView = tabHost.getCurrentView();

		if (tabHost.getCurrentTab() >= currentTab) {
		    previousView.setAnimation(outToLeftAnimation());
		    currentView.setAnimation(inFromRightAnimation());
		} else if (tabHost.getCurrentTab() < currentTab) {
		    previousView.setAnimation(outToRightAnimation());
		    currentView.setAnimation(inFromLeftAnimation());
		}

		previousView = currentView;
		currentTab = tabHost.getCurrentTab();

		View tab1 = tabHost.findViewWithTag("pizzas");
		View tab2 = tabHost.findViewWithTag("drinks");
		View tab3 = tabHost.findViewWithTag("offers");

		if (actualTab.equals(viewMenuPizza) && tabId.equals("drinks")) {
		    changeColor(tab1, false);
		    changeColor(tab2, true);
		    actualTab = viewMenuDrinks;
		} else if (actualTab.equals(viewMenuDrinks)
			&& tabId.equals("pizzas")) {
		    changeColor(tab2, false);
		    changeColor(tab1, true);
		    actualTab = viewMenuPizza;
		} else if (actualTab.equals(viewMenuDrinks)
			&& tabId.equals("offers")) {
		    changeColor(tab2, false);
		    changeColor(tab3, true);
		    actualTab = viewMenuOffers;
		} else if (actualTab.equals(viewMenuOffers)
			&& tabId.equals("drinks")) {
		    changeColor(tab3, false);
		    changeColor(tab2, true);
		    actualTab = viewMenuDrinks;
		} else if (actualTab.equals(viewMenuPizza)
			&& tabId.equals("offers")) {
		    changeColor(tab1, false);
		    changeColor(tab3, true);
		    actualTab = viewMenuOffers;
		} else if (actualTab.equals(viewMenuOffers)
			&& tabId.equals("pizzas")) {
		    changeColor(tab3, false);
		    changeColor(tab1, true);
		    actualTab = viewMenuPizza;
		}
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}