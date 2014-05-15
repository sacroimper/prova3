/*
 *  ContactActivity.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Copyright 2014 	Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  			Marc Sabate Piñol <masapim@hotmail.com>
 *  			Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *  			Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.escoladeltreball.arcowabungaproject.activities;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactActivity extends Activity implements OnClickListener {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// Remove title bar
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	// Remove notification bar
	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);

	super.onCreate(savedInstanceState);
	// set content view AFTER ABOVE sequence (to avoid crash)
	this.setContentView(R.layout.activity_contact);

	// Set custom text to all the TextView's
	TextView tv = (TextView) findViewById(R.id.contact_text_description);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_item_first);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_item_second);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_item_third);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_item_four);
	CustomTextView.customTextView(this, tv);

	tv = (TextView) findViewById(R.id.contact_text_telephone);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_text_address);
	CustomTextView.customTextView(this, tv);
	tv = (TextView) findViewById(R.id.contact_text_email);
	CustomTextView.customTextView(this, tv);

	tv = (TextView) findViewById(R.id.contact_text_menu_bottom);
	CustomTextView.customTextView(this, tv);

	LinearLayout ly = (LinearLayout) findViewById(R.id.contact_linear_bottom);
	ly.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	finish();
    }

    @Override
    public void onPause() {
	super.onPause();
	finish();
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
