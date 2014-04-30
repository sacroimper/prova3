package org.escoladeltreball.arcowabungaproject.dao;

import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
    //====================
    // CONSTANTS
    //====================

    private static final String DATABASE_NAME ="cowabunga.db";
    private static final int DATABASE_VERSION = 1;
    
    //====================
    // ATTRIBUTES
    //====================

    //====================
    // CONSTRUCTORS
    //====================

    public DataBaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //====================
    // PUBLIC METHODS
    //====================
    
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
	db.execSQL(DAOFactory.CREATE_TABLE_SHOPPINCART_PRODUCTS);
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

   
   
    //====================
    // PROTECTED METHODS
    //====================

    //====================
    // PRIVATE METHODS
    //====================

    //====================
    // OVERRIDE METHODS
    //====================

    //====================
    // GETTERS & SETTERS
    //====================
}
