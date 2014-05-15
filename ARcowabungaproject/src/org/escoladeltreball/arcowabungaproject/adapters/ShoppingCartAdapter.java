/*
 *  ProductSetAdapter.java
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

package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.model.ShoppingCart;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShoppingCartAdapter extends BaseAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // private final List<Product> products = new ArrayList<Product>();
    private List<Product> products = new ArrayList<Product>();
    public LayoutInflater inflater;
    public Activity activity;
    public String price;

    // ====================
    // ATTRIBUTES
    // ====================

    // ====================
    // CONSTRUCTORS
    // ====================

    public ShoppingCartAdapter(Activity activity,
	    ShoppingCart customShoppingCart) {
	super();
	this.activity = activity;
	this.products = customShoppingCart.getProducts();
	this.price = customShoppingCart.getFormatedPrice();
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
    public int getViewTypeCount() {
	return 3;
    }

    @Override
    public int getItemViewType(int position) {
	if (position == 0) {
	    return 1;
	} else if (position == products.size() + 1) {
	    return 2;
	}
	return 0;
    }

    @Override
    public int getCount() {

	return (products.size() + 2);
    }

    @Override
    public Object getItem(int position) {
	if (position == 0 || position == products.size() + 1) {
	    return null;
	}
	return products.get(position - 1);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ProductViewHolder holder = null;

	if (position == 0) {
	    convertView = inflater.inflate(
		    R.layout.listitem_product_intro_layout, null);
	    TextView tv = (TextView) convertView
		    .findViewById(R.id.productIntroText);
	    CustomTextView.customTextView(activity, tv);
	} else if (position == products.size() + 1) {

	    // Fills the proper text prices in listitem_product_final_layout
	    convertView = inflater.inflate(
		    R.layout.listitem_product_final_layout, null);

	    // Find the TextViews by id
	    TextView subtotalPrice = (TextView) convertView
		    .findViewById(R.id.order_subtotal_price);
	    // TextView shippingCostPrice = (TextView) convertView
	    // .findViewById(R.id.shipping_cost_value);
	    // TextView taxesPrice = (TextView) convertView
	    // .findViewById(R.id.taxes_value);

	    // Set the prices
	    subtotalPrice.setText(this.price + "€");
	    // shippingCostPrice.setText(this.price + "€");
	    // taxesPrice.setText(this.price + "€");

	    // Text final list
	    TextView tv = (TextView) convertView
		    .findViewById(R.id.order_price_total);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_shipping_cost);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView
		    .findViewById(R.id.order_shipping_cost_text);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_subtotal_price);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_taxes_text);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_text_subtotal);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_text_total);
	    CustomTextView.customTextView(activity, tv);
	    tv = (TextView) convertView.findViewById(R.id.order_withtax_price);
	    CustomTextView.customTextView(activity, tv);

	} else {
	    if (convertView == null) {
		convertView = this.inflater.inflate(
			R.layout.listitem_product_layout, parent, false);

		holder = new ProductViewHolder();

		holder.productImage = (ImageView) convertView
			.findViewById(R.id.imageInProductItem);
		holder.productTitle = (TextView) convertView
			.findViewById(R.id.titleTextInProductItem);
		holder.trashIcon = (ImageButton) convertView
			.findViewById(R.id.trashIcon);
		holder.productPrice = (TextView) convertView
			.findViewById(R.id.priceTextInProductItem);
		// Is necesary to develope this element to inflate the content
		// of this LinearLayout Maybe it has another Adapter?
		holder.extraIngrentsLayout = (LinearLayout) convertView
			.findViewById(R.id.extraIngredientLayoutInProductItem);

		// Apply custom textview
		CustomTextView.customTextView(activity, holder.productTitle);
		CustomTextView.customTextView(activity, holder.productPrice);
		TextView tv = (TextView) convertView
			.findViewById(R.id.extraIngredientIntro);
		CustomTextView.customTextView(activity, tv);

		convertView.setTag(holder);

	    } else {
		holder = (ProductViewHolder) convertView.getTag();
	    }

	    Product product = this.products.get(position - 1);

	    DAOAndroid dao = DAOAndroid.getInstance();
	    Drawable icon = dao.getDrawableFromAssets(activity,
		    product.getIcon());
	    holder.productImage.setImageDrawable(icon);
	    holder.productTitle.setText(product.getName());
	    holder.productPrice.setText(product.getFormatedPrice());

	    // It will be necessary to inflate this layout only if extra
	    // ingredients were added
	    // holder.extraIngrentsLayout.
	    // (R.id.extraIngredientLayoutInProductItem);
	}

	return convertView;
    }

    static class ProductViewHolder {
	ImageView productImage;
	TextView productTitle;
	// Not implemented yet
	// int numberOfEqualProducts;
	ImageButton trashIcon;
	TextView productPrice;
	LinearLayout extraIngrentsLayout;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
