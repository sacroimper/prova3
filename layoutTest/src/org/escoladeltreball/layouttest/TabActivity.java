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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		// mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		TextView tv = new TextView(this);
		tv.setText("");
		TextView tv2 = new TextView(this);
		tv.setText("");
		TextView tv3 = new TextView(this);
		tv.setText("");
		
		setupTab(tv, "Tab 1");
		setupTab(tv2, "Tab 2");
		setupTab(tv3, "Tab 3");
		
		LinearLayout ly1 = (LinearLayout) findViewById(R.id.tab_dos_1);
		tv = (TextView) ly1.findViewById(R.id.content_text_tab);
		LinearLayout ly2 = (LinearLayout) findViewById(R.id.tab_dos_2);
		tv2 = (TextView) ly2.findViewById(R.id.content_text_tab);
		LinearLayout ly3 = (LinearLayout) findViewById(R.id.tab_dos_3);
		tv3 = (TextView) ly3.findViewById(R.id.content_text_tab);
		
		tv.setText("hola que ase");
		tv2.setText("asdasdoasdasd");
		tv3.setText("HHHHHHHHHHH");
		
		
		// tv.setText("adadsad");
		// tv.setTag("Tab 2-text");
		// setupTab(ly, "Tab 2");

		// tv.setText("gggggg");
		// tv.setTag("Tab 3-text");
		// setupTab(ly, "Tab 3");

		mTabHost.setCurrentTab(0);
		View tab1 = mTabHost.findViewWithTag("Tab 1");
		TabsMaker.changeColor(tab1, true);

		mTabHost.setOnTabChangedListener(this);
		actualTab = mTabHost.findViewWithTag("Tab 1");
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
		View vTab = null;
		if (tabId.equals("Tab 1")) {
			vTab = mTabHost.findViewWithTag("Tab 1");
		} else if (tabId.equals("Tab 2")) {
			vTab = mTabHost.findViewWithTag("Tab 2");
		} else if (tabId.equals("Tab 3")) {
			vTab = mTabHost.findViewWithTag("Tab 3");
		}
		TabsMaker.changeColor(vTab, true);
		TabsMaker.changeColor(actualTab, false);

		// set Animations
		View tab1 = mTabHost.findViewWithTag("Tab 1");
		View tab2 = mTabHost.findViewWithTag("Tab 2");
		View tab3 = mTabHost.findViewWithTag("Tab 3");

		if (actualTab.equals(tab1) && tabId.equals("Tab 2")) {
			// actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			// tab2.setAnimation(TabsMaker.inFromRightAnimation());
			LinearLayout lyActual = (LinearLayout) findViewById(R.id.tab_dos_1);
			LinearLayout lyNew = (LinearLayout) findViewById(R.id.tab_dos_2);
			lyActual.setAnimation(TabsMaker.outToLeftAnimation());
			lyNew.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab2) && tabId.equals("Tab 1")) {
//			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
//			tab1.setAnimation(TabsMaker.inFromRightAnimation());
			LinearLayout lyActual = (LinearLayout) findViewById(R.id.tab_dos_2);
			LinearLayout lyNew = (LinearLayout) findViewById(R.id.tab_dos_1);
			lyActual.setAnimation(TabsMaker.inFromRightAnimation());
			lyNew.setAnimation(TabsMaker.outToLeftAnimation());
		} else if (actualTab.equals(tab2) && tabId.equals("Tab 3")) {
//			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
//			tab3.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab3) && tabId.equals("Tab 2")) {
//			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
//			tab2.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab1) && tabId.equals("Tab 3")) {
//			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
//			tab3.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab3) && tabId.equals("Tab 1")) {
//			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
//			tab1.setAnimation(TabsMaker.inFromRightAnimation());
		}
		actualTab = vTab;
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

