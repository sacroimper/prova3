package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActivity extends Activity {

	private TabHost mTabHost;
	private View actualTab;
	private int idTabCounter = 0;
	private float lastX;
	private View viewTab1;
	private View viewTab2;
	private View viewTab3;
	private static final int ANIMATION_TIME = 700;
	private View previousView;
	private View currentView;
	private int currentTab;
	private GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_tab);

		this.overridePendingTransition(R.anim.animation_horizontal_enter,
				R.anim.animation_horizontal_leave);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewTab1 = layoutInflater.inflate(R.layout.content_tab, null);
		LayoutInflater layoutInflater2 = (LayoutInflater) getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewTab2 = layoutInflater2.inflate(R.layout.content_second_tab, null);
		LayoutInflater layoutInflater3 = (LayoutInflater) getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		viewTab3 = layoutInflater3.inflate(R.layout.content_third_tab, null);

		setupTab(viewTab1, "Tab 1");
		setupTab(viewTab2, "Tab 2");
		setupTab(viewTab3, "Tab 3");

		mTabHost.setCurrentTab(0);
		View tab1 = mTabHost.findViewWithTag("Tab 1");
		TabsMaker.changeColor(tab1, true);
		//
		// mTabHost.setOnTabChangedListener(this);
		// actualTab = mTabHost.findViewWithTag("Tab 1");
		actualTab = viewTab1;
		previousView = mTabHost.getCurrentView();
		gestureDetector = new GestureDetector(getApplicationContext(),
				new MyGestureDetector());

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				
				currentView = mTabHost.getCurrentView();
				
				if (mTabHost.getCurrentTab() >= currentTab) {
					previousView.setAnimation(outToLeftAnimation());
					currentView.setAnimation(inFromRightAnimation());
				} else if (mTabHost.getCurrentTab() < currentTab) {
					previousView.setAnimation(outToRightAnimation());
					currentView.setAnimation(inFromLeftAnimation());
				}
				
				previousView = currentView;
				currentTab = mTabHost.getCurrentTab();

				View tab1 = mTabHost.findViewWithTag("Tab 1");
				View tab2 = mTabHost.findViewWithTag("Tab 2");
				View tab3 = mTabHost.findViewWithTag("Tab 3");

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
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
	}

	private void setupTab(final View view, final String tag) {
		View tabview = createTabView(mTabHost.getContext(), tag);

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
	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setTextColor(Color.WHITE);
		tv.setText(text);
		return view;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		// when user first touches the screen to swap
		case MotionEvent.ACTION_DOWN: {
			lastX = event.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float currentX = event.getX();

			// if left to right swipe on screen
			if (lastX < currentX - 250) {
				mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
			}

			// if right to left swipe on screen
			if (lastX > currentX + 250) {
				mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
			}

			break;
		}
		}
		return false;
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

	class MyGestureDetector extends SimpleOnGestureListener {
		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 250;
		private static final int SWIPE_THRESHOLD_VELOCITY = 200;
		private int maxTabs;

		/**
		 * An empty constructor that uses the tabhosts content view to decide
		 * how many tabs there are.
		 */
		public MyGestureDetector() {
			maxTabs = mTabHost.getTabContentView().getChildCount();
		}

		/**
		 * Listens for the onFling event and performs some calculations between
		 * the touch down point and the touch up point. It then uses that
		 * information to calculate if the swipe was long enough. It also uses
		 * the swiping velocity to decide if it was a "true" swipe or just some
		 * random touching.
		 */
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			int newTab = 0;
			if (Math.abs(event1.getY() - event2.getY()) <= SWIPE_MAX_OFF_PATH) {
				return false;
			}
			if (event1.getX() - event2.getX() >= SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY) {
				// Swipe right to left
				newTab = currentTab + 1;
			} else if (event2.getX() - event1.getX() >= SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) <= SWIPE_THRESHOLD_VELOCITY) {
				// Swipe left to right
				newTab = currentTab - 1;
			}
			if (newTab == 0 || newTab <= (maxTabs - 1)) {
				return false;
			}
			mTabHost.setCurrentTab(newTab);
			return super.onFling(event1, event2, velocityX, velocityY);
		}
	}

}
