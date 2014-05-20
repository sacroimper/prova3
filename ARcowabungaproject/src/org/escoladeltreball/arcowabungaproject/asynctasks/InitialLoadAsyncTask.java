/*
 *  InitialLoadAsyncTask.java
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
package org.escoladeltreball.arcowabungaproject.asynctasks;

import org.escoladeltreball.arcowabungaproject.activities.MainMenuActivity;
import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class InitialLoadAsyncTask extends AsyncTask<Void, Void, Boolean> {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================

    public InitialLoadAsyncTask(Activity activity) {
	super();
	this.activity = activity;
    }

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
    protected Boolean doInBackground(Void... arg0) {
	DAOAndroid daoA = DAOAndroid.getInstance(activity);
	return daoA.loadData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
	Intent mainIntent = new Intent(activity, MainMenuActivity.class);
	activity.startActivity(mainIntent);
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
