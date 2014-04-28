package org.escoladeltreball.arcowabungaproject;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TabHost;

public class DishesMenu extends Activity {
	TabHost tabs;
	float lastX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);
		
		tabs = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("TAB1");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("mitab2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("TAB2");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("mitab3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("TAB3");
		tabs.addTab(spec);

		tabs.setCurrentTab(0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent touchevent) {
		switch (touchevent.getAction()) {
		// when user first touches the screen to swap
		case MotionEvent.ACTION_DOWN: {
			lastX = touchevent.getRawX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			float currentX = touchevent.getRawX();

			// if left to right swipe on screen
			if (lastX < currentX) {

				switchTabs(true);
			}

			// if right to left swipe on screen
			if (lastX > currentX) {
				switchTabs(false);
			}

			break;
		}
		}
		return false;
	}

	public void switchTabs(boolean direction) {
		if (direction) // true = move left
		{
			if (tabs.getCurrentTab() == 0)
				tabs.setCurrentTab(tabs.getTabWidget().getTabCount() - 1);
			else
				tabs.setCurrentTab(tabs.getCurrentTab() - 1);
		} else
		// move right
		{
			if (tabs.getCurrentTab() != (tabs.getTabWidget().getTabCount() - 1))
				tabs.setCurrentTab(tabs.getCurrentTab() + 1);
			else
				tabs.setCurrentTab(0);
		}
	}

}
