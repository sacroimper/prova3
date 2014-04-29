package org.escoladeltreball.layouttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.activity_main);
		
		
		LinearLayout l = (LinearLayout) findViewById(R.id.linearButton1);
		l.setOnClickListener(this);
		
		this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

}
