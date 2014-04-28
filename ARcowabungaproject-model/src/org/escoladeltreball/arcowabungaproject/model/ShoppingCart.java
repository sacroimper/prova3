package org.escoladeltreball.arcowabungaproject.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart extends IdObject {

    // ====================
    // CONSTANTS
    // ====================

    private static final long serialVersionUID = 1966476365496691321L;

    // ====================
    // ATTRIBUTES
    // ====================

    private List<Product> productList;

    // ====================
    // CONSTRUCTORS
    // ====================

    public ShoppingCart(int id) {
	super(id);
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public boolean addProduct(final Product product) {
	if (product != null) {
	    if (productList == null) {
		productList = new ArrayList<Product>();
	    }
	    return productList.add(product);
	}
	return false;
    }

    public boolean removeProduct(final Product product) {
	if (product != null) {
	    Iterator<Product> it = iteratorProducts();
	    while (it.hasNext()) {
		Product p = (Product) it.next();
		if (p.equals(product)) {
		    productList.remove(product);
		    return true;
		}
	    }
	}
	return false;
    }

    public List<Product> getProduct() {
	return productList;
    }

    public boolean hasInProducts(Product product) {
	return productList.contains(product);
    }

    public Iterator<Product> iteratorProducts() {
	return productList.iterator();
    }

    public int sizeProducts() {
	return productList.size();
    }

    public boolean removeAll() {
	if (productList != null) {
	    productList.clear();
	    return true;
	}
	return false;
    }

    public void removeYou() {
	productList = null;
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
    public String toString() {
	return "ShoppingCart [Id=" + id + "productList=" + productList + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

}
