/*
 *  MainMenuActivity.java
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainMenuActivity extends Activity implements OnClickListener {

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

	// Buttons
	LinearLayout bMenu = (LinearLayout) findViewById(R.id.menu_selection_menu);
	bMenu.setOnClickListener(this);

	LinearLayout bActualOrder = (LinearLayout) findViewById(R.id.menu_selection_actualorder);
	bActualOrder.setOnClickListener(this);

	LinearLayout bLastOrders = (LinearLayout) findViewById(R.id.menu_selection_contact);
	bLastOrders.setOnClickListener(this);

	LinearLayout bContact = (LinearLayout) findViewById(R.id.menu_selection_lastorders);
	bContact.setOnClickListener(this);

	this.overridePendingTransition(R.anim.animation_horizontal_enter,
		R.anim.animation_horizontal_leave);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	if (v.getId() == R.id.menu_selection_menu) {
	    Intent intent = new Intent(this.getApplicationContext(),
		    MenuActivity.class);
	    startActivity(intent);
	} else if (v.getId() == R.id.menu_selection_actualorder) {
	    Intent intent = new Intent(this.getApplicationContext(),
		    OrderActivity.class);
	    startActivity(intent);
	} else if (v.getId() == R.id.menu_selection_contact) {
	    Intent intent = new Intent(this.getApplicationContext(),
		    ContactActivity.class);
	    startActivity(intent);
	} else if (v.getId() == R.id.menu_selection_lastorders) {
	    Intent intent = new Intent(this.getApplicationContext(),
		    LastOrdersActivity.class);
	    startActivity(intent);
	}

    }

}
