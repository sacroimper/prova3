package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class TabActivity extends Activity implements OnTabChangeListener {

	private TabHost mTabHost;
	private View actualTab;
	private int idTabCounter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		// mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		TextView tv = new TextView(this);
		tv.setText("idioten");
		setupTab(tv, "Tab 1");
		tv.setText("adadsad");
		setupTab(tv, "Tab 2");
		tv.setText("gggggg");
		setupTab(tv, "Tab 3");

		mTabHost.setCurrentTab(0);

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
			idTabCounter++;
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
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab2.setAnimation(TabsMaker.inFromRightAnimation());
			
			((ViewGroup) tab2).getChildAt(0);
			
		} else if (actualTab.equals(tab2) && tabId.equals("Tab 1")) {
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab1.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab2) && tabId.equals("Tab 3")) {
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab3.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab3) && tabId.equals("Tab 2")) {
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab2.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab1) && tabId.equals("Tab 3")) {
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab3.setAnimation(TabsMaker.inFromRightAnimation());
		} else if (actualTab.equals(tab3) && tabId.equals("Tab 1")) {
			actualTab.setAnimation(TabsMaker.outToLeftAnimation());
			tab1.setAnimation(TabsMaker.inFromRightAnimation());
		}
		actualTab = vTab;
	}
}
