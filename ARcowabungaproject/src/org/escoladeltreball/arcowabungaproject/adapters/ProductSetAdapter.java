package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Product;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductSetAdapter extends BaseAdapter {

    // ====================
    // CONSTANTS
    // ====================

    private final List<Product> products = new ArrayList<Product>();
    public LayoutInflater inflater;
    public Activity activity;

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public ProductSetAdapter(Activity activity, Set<Product> products) {
	super();
	this.activity = activity;
	for (Product product : products) {
	    this.products.add(product);
	}
	inflater = activity.getLayoutInflater();
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
    public int getCount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Object getItem(int position) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long getItemId(int position) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	return null;
    }

    static class ViewHolder {
	ImageView productImage;
	TextView productName;
	// Not implemented
	// int numberOfProducts;
	LinearLayout extraIngrents;
	ImageButton trash;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
