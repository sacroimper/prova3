package org.escoladeltreball.arcowabungaproject.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Offer extends Product {
    /**
     * 
     */
    private static final long serialVersionUID = -422646089915638752L;
    /**
     * 
     */

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private List<Product> productList;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Offer(int id, String name, float price, int icon, float discount) {
	super(id, name, price, icon, discount);
	this.productList = new ArrayList<>();
    }

    // ====================
    // PUBLIC METHODS
    // ====================
    public boolean addProduct(Product product) {
	return this.productList.add(product);
    }

    public boolean removeProduct(Product product) {
	return this.productList.remove(product);
    }

    public List<Product> getProduct() {
	return this.productList;
    }

    public boolean hasInProducts(Product product) {
	return this.productList.contains(product);
    }

    public Iterator<Product> iteratorProducts() {
	return this.productList.iterator();
    }

    public int sizeProducts() {
	return this.productList.size();
    }

    public boolean removeAll() {
	this.productList.clear();
	if (this.productList.size() == 0) {
	    return true;
	}
	return false;
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
	return "Offer [productList=" + productList + "]";
    }

    public void print() {
	System.out.println(toString());
    }

    public void printProductList() {
	System.out.println(this.productList.toString());
    }

    // ====================
    // ====================
    // GETTERS & SETTERS

    public List<Product> getProductList() {
	return productList;
    }

    public void setProductList(List<Product> productList) {
	this.productList = productList;
    }
}
