package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class ARViewActivity extends Activity{

    //====================
    // CONSTANTS
    //====================

    //====================
    // ATTRIBUTES
    //====================

    //====================
    // CONSTRUCTORS
    //====================

    //====================
    // PUBLIC METHODS
    //====================

    //====================
    // PROTECTED METHODS
    //====================

    //====================
    // PRIVATE METHODS
    //====================

    //====================
    // OVERRIDE METHODS
    //====================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	super.onCreate(savedInstanceState);
	// set content view AFTER ABOVE sequence (to avoid crash)
	this.setContentView(R.layout.activity_arpizza);
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
    
    //====================
    // GETTERS & SETTERS
    //====================
    
   
}
