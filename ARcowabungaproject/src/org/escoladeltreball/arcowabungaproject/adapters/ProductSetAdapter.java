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
	return products.size();
    }

    @Override
    public Object getItem(int position) {
	return products.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder = null;

	if (convertView == null) {
	    // Se ha de añadir el layout
	    // convertView = this.inflater.inflate(resource, root);

	}
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
