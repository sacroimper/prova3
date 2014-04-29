package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class LastOrdersActivity extends Activity{
    
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
	super.onCreate(savedInstanceState);
	// set content view AFTER ABOVE sequence (to avoid crash)
	this.setContentView(R.layout.activity_main);
	

	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    //====================
    // GETTERS & SETTERS
    //====================
}
