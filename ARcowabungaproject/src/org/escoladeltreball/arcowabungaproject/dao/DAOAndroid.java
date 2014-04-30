package org.escoladeltreball.arcowabungaproject.dao;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Address;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Pizza;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.model.dao.DAOFactory;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class DAOAndroid extends DAOFactory {

    private Map<Integer,String> resources;
    
    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
private SQLiteDatabase database;
private DataBaseHelper dbHepler;
    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOAndroid(Context context,Pizzeria pizzeria) {
	super(pizzeria);
	dbHepler = new DataBaseHelper(context);
	
    }
    
    // ====================
    // PUBLIC METHODS
    // ====================

    public Drawable getDrawableFromAssets(Activity activity, String path){
	Drawable drawable = null;
	try {
	    drawable = Drawable.createFromStream(activity.getAssets().open(path), null);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return drawable;
    }
    

    public Drawable getDrawableFromAssets(Activity activity, int resourceId){
	return getDrawableFromAssets(activity, getResourcePath(resourceId));
    }
    
    public String getResourcePath(int resourceId){
	return resources.get(resourceId);
    }
    
    public static DAOAndroid getInstance(){
	return (DAOAndroid) instance;
    }
    
    public static DAOAndroid getInstance(Context context, Pizzeria pizzeria){
	if (instance == null){
	    instance = new DAOAndroid(context,pizzeria);
	}
	return (DAOAndroid) instance;
    }
    
    public void open() throws SQLException {
	database = dbHepler.getWritableDatabase();
    }
    
    public void close(){
	dbHepler.close();
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

    @Override
    protected Set<Ingredient> readIngredient() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Pizza> readPizza() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Offer> readOffer() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Drink> readDrink() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<ShoppingCart> readShoppingCart() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Order> readOrder() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Set<Address> readAddress() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected Map<String, Object> readPreferences() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void writeIngredients(Set<Ingredient> ingredients) {
	for(Ingredient ingredient : ingredients){
	    ContentValues values = new ContentValues();
	    
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[0], ingredient.getId());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[1], ingredient.getName());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[2], ingredient.getIcon());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[3], ingredient.getModel());
	    values.put(DAOFactory.COLUMNS_NAME_INGREDIENT[4], ingredient.getPrice());
	    
	    database.insert("ingredient", null, values);
	}
    }

    @Override
    protected void writePizzas(Set<Pizza> pizzas) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeOffers(Set<Offer> offers) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeDrinks(Set<Drink> drinks) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeShoppingCarts(Set<ShoppingCart> shoppingCarts) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeOrders(Set<Order> orders) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writeAddress(Set<Address> address) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void writePreferences(Map<String, Object> preferences) {
	// TODO Auto-generated method stub

    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
