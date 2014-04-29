package org.escoladeltreball.arcowabungaproject.dao;

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

public class DAOAndroid extends DAOFactory {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public DAOAndroid(System system) {
	super();
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
