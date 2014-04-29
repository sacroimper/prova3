package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;



public class SecondActivity extends Activity {
	TabHost tabs;
	LinearLayout actualTab;
	float lastX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_second);
		

		this.overridePendingTransition(R.anim.animation_enter,
				R.anim.animation_leave);
		
		makeTabs();
	}
	
	@Override
	public void onPause() {
		this.overridePendingTransition(R.anim.animation_leave,
				R.anim.animation_enter);
		super.onPause();
	}

	private void makeTabs() {

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
		actualTab = (LinearLayout) findViewById(R.id.tab1);

		tabs.setOnTabChangedListener(new OnTabChangeListener() {

			LinearLayout t1l = (LinearLayout) findViewById(R.id.tab1);
			LinearLayout t2l = (LinearLayout) findViewById(R.id.tab2);
			LinearLayout t3l = (LinearLayout) findViewById(R.id.tab3);

			public void onTabChanged(String tabId) {
				if (actualTab.equals(t1l) && tabId.equals("mitab2")) {
					t1l.setAnimation(outToLeftAnimation());
					t2l.setAnimation(inFromRightAnimation());
					actualTab = t2l;
				} else if (actualTab.equals(t2l) && tabId.equals("mitab1")) {
					t2l.setAnimation(leftToLeftAnimation());
					t1l.setAnimation(leftFromRightAnimation());
					actualTab = t1l;
				} else if (actualTab.equals(t2l) && tabId.equals("mitab3")) {
					t2l.setAnimation(outToLeftAnimation());
					t3l.setAnimation(inFromRightAnimation());
					actualTab = t3l;
				} else if (actualTab.equals(t3l) && tabId.equals("mitab2")) {
					t3l.setAnimation(leftToLeftAnimation());
					t2l.setAnimation(leftFromRightAnimation());
					actualTab = t2l;
				} else if (actualTab.equals(t1l) && tabId.equals("mitab3")) {
					t1l.setAnimation(outToLeftAnimation());
					t3l.setAnimation(inFromRightAnimation());
					actualTab = t3l;
				} else if (actualTab.equals(t3l) && tabId.equals("mitab1")) {
					t3l.setAnimation(leftToLeftAnimation());
					t1l.setAnimation(leftFromRightAnimation());
					actualTab = t1l;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	public Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(400);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public Animation leftToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(400);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	public Animation leftFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(400);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(400);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
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
				tabs.setCurrentTab(tabs.getCurrentTab()-1);
			}

			// if right to left swipe on screen
			if (lastX > currentX + 250) {
				tabs.setCurrentTab(tabs.getCurrentTab()+1);
			}

			break;
		}
		}
		return false;
	}

}
