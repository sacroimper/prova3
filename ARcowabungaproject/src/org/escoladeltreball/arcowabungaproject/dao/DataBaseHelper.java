/*
 *  DataBaseHelper.java
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

package org.escoladeltreball.arcowabungaproject.dao;

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    // ====================
    // CONSTANTS
    // ====================

    private static final String DATABASE_NAME = "cowabunga.db";
    private static final int DATABASE_VERSION = 1;

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public DataBaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL(DAOFactory.CREATE_TABLE_ADDRESS);
	db.execSQL(DAOFactory.CREATE_TABLE_DRINKS);
	db.execSQL(DAOFactory.CREATE_TABLE_INGREDIENT);
	db.execSQL(DAOFactory.CREATE_TABLE_INGREDIENTS);
	db.execSQL(DAOFactory.CREATE_TABLE_OFFERS);
	db.execSQL(DAOFactory.CREATE_TABLE_OFFERS_PRODUCTS);
	db.execSQL(DAOFactory.CREATE_TABLE_ORDERS);
	db.execSQL(DAOFactory.CREATE_TABLE_PIZZAS);
	db.execSQL(DAOFactory.CREATE_TABLE_PREFERENCES);
	db.execSQL(DAOFactory.CREATE_TABLE_RESOURCES);
	db.execSQL(DAOFactory.CREATE_TABLE_SHOPPINGCART_PRODUCTS);
	db.execSQL(DAOFactory.CREATE_TABLE_SHOPPINGCARTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL(DAOFactory.DROP_TABLE_ADDRESS);
	db.execSQL(DAOFactory.DROP_TABLE_DRINKS);
	db.execSQL(DAOFactory.DROP_TABLE_INGREDIENT);
	db.execSQL(DAOFactory.DROP_TABLE_INGREDIENTS);
	db.execSQL(DAOFactory.DROP_TABLE_OFFERS);
	db.execSQL(DAOFactory.DROP_TABLE_OFFERS_PRODUCTS);
	db.execSQL(DAOFactory.DROP_TABLE_ORDERS);
	db.execSQL(DAOFactory.DROP_TABLE_PIZZAS);
	db.execSQL(DAOFactory.DROP_TABLE_PREFERENCES);
	db.execSQL(DAOFactory.DROP_TABLE_RESOURCES);
	db.execSQL(DAOFactory.DROP_TABLE_SHOPPINGCARTS);
	db.execSQL(DAOFactory.DROP_TABLE_SHOPPINGCARTS_PRODUCTS);

    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
