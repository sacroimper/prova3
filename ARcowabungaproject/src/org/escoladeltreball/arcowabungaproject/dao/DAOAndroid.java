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

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class DAOAndroid extends DAOFactory {

    private Map<Integer,String> resources; 
    
    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    protected DAOAndroid(System system) {
	super(system);
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
    
    public String getResourcePath(int resourceId){
	return resources.get(resourceId);
    }
    
    public static DAOAndroid getInstance(){
	return (DAOAndroid) instance;
    }
    
    public static DAOAndroid getInstance(System system){
	if (instance == null){
	    instance = new DAOAndroid(system);
	}
	return (DAOAndroid) instance;
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
	// TODO Auto-generated method stub

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
