package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActivity extends Activity implements OnTabChangeListener {

	private TabHost mTabHost;
	private View actualTab;
	private int idTabCounter = 0;
	private float lastX;
	private View viewTab1;
	private View viewTab2;
	private View viewTab3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

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
		mTabHost.setOnTabChangedListener(this);
//		actualTab = mTabHost.findViewWithTag("Tab 1");
		actualTab = viewTab1;
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
	public void onTabChanged(String tabId) {
//		View vTab = null;
//		if (tabId.equals("Tab 1")) {
//			vTab = mTabHost.findViewWithTag("Tab 1");
//		} else if (tabId.equals("Tab 2")) {
//			vTab = mTabHost.findViewWithTag("Tab 2");
//		} else if (tabId.equals("Tab 3")) {
//			vTab = mTabHost.findViewWithTag("Tab 3");
//		}
//		TabsMaker.changeColor(vTab, true);
//		TabsMaker.changeColor(actualTab, false);
//
//		// set Animations
		View tab1 = mTabHost.findViewWithTag("Tab 1");
		View tab2 = mTabHost.findViewWithTag("Tab 2");
		View tab3 = mTabHost.findViewWithTag("Tab 3");

		if (actualTab.equals(viewTab1) && tabId.equals("Tab 2")) {
			viewTab1.setAnimation(TabsMaker.inFromRightAnimation());
			viewTab2.setAnimation(TabsMaker.outToLeftAnimation());
			TabsMaker.changeColor(tab1, false);
			TabsMaker.changeColor(tab2, true);
			actualTab = viewTab2;
		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 1")) {
			viewTab2.setAnimation(TabsMaker.leftFromRightAnimation());
			viewTab1.setAnimation(TabsMaker.leftToLeftAnimation());
			TabsMaker.changeColor(tab2, false);
			TabsMaker.changeColor(tab1, true);
			actualTab = viewTab1;
		} else if (actualTab.equals(viewTab2) && tabId.equals("Tab 3")) {
			viewTab2.setAnimation(TabsMaker.inFromRightAnimation());
			viewTab3.setAnimation(TabsMaker.outToLeftAnimation());
			TabsMaker.changeColor(tab2, false);
			TabsMaker.changeColor(tab3, true);
			actualTab = viewTab3;
		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 2")) {
			viewTab3.setAnimation(TabsMaker.leftFromRightAnimation());
			viewTab2.setAnimation(TabsMaker.leftToLeftAnimation());
			TabsMaker.changeColor(tab3, false);
			TabsMaker.changeColor(tab2, true);
			actualTab = viewTab2;
		} else if (actualTab.equals(viewTab1) && tabId.equals("Tab 3")) {
			viewTab1.setAnimation(TabsMaker.inFromRightAnimation());
			viewTab3.setAnimation(TabsMaker.outToLeftAnimation());
			TabsMaker.changeColor(tab1, false);
			TabsMaker.changeColor(tab3, true);
			actualTab = viewTab3;
		} else if (actualTab.equals(viewTab3) && tabId.equals("Tab 1")) {
			viewTab3.setAnimation(TabsMaker.leftFromRightAnimation());
			viewTab1.setAnimation(TabsMaker.leftToLeftAnimation());
			TabsMaker.changeColor(tab3, false);
			TabsMaker.changeColor(tab1, true);
			actualTab = viewTab1;
		}
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

}
