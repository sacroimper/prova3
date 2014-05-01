package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabActivity extends Activity implements OnTabChangeListener {

	private TabHost mTabHost;
	private View actualTab;

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
		setupTab(tv, "Tab 2");
		setupTab(tv, "Tab 3");

		mTabHost.setOnTabChangedListener(this);
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
		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			View v = mTabHost.getTabWidget().getChildAt(i);
			if (v instanceof TextView && ((TextView) v).getText().equals("Tab 1")) {
				TextView tv = (TextView) v;
				tv.setTextColor(Color.GRAY);
			} else if (v instanceof TextView && ((TextView) v).getText().equals("Tab 1")) {
				TextView tv = (TextView) v;
				tv.setTextColor(Color.GRAY);
			} else {
				TextView tv = (TextView) v;
				tv.setTextColor(Color.GRAY);
			}

		}
	}
}
