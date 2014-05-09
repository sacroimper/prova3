/*
 *  DrinkSetAdapter.java
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
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;
import org.escoladeltreball.arcowabungaproject.listeners.AddButtonClickListener;
import org.escoladeltreball.arcowabungaproject.model.Drink;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkSetAdapter extends BaseAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private List<Drink> drinks = new ArrayList<Drink>();
    public LayoutInflater inflater;
    public Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================

    public DrinkSetAdapter(Activity activity, Set<Drink> drinks) {
	this.activity = activity;
	for (Drink drink : drinks) {
	    this.drinks.add(drink);
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
	return drinks.size();
    }

    @Override
    public Object getItem(int position) {
	return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
	return drinks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder = null;
	if (convertView == null) {
	    holder = new ViewHolder();
	    convertView = inflater
		    .inflate(R.layout.listitem_drink_layout, null);
	    holder.ivIcon = (ImageView) convertView
		    .findViewById(R.id.imageInDrinkItem);
	    holder.tvTitle = (TextView) convertView
		    .findViewById(R.id.titleTextInDrinkItem);
	    holder.tvPrice = (TextView) convertView
		    .findViewById(R.id.priceTextInDrinkItem);
	    holder.ibAdd = (ImageButton) convertView
		    .findViewById(R.id.imageInDrinkItem);
	    CustomTextView.customTextView(activity, holder.tvTitle);
	    CustomTextView.customTextView(activity, holder.tvPrice);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	Drink drink = (Drink) getItem(position);
	Drawable icon = DAOAndroid.getInstance().getDrawableFromAssets(
		activity, drink.getIcon());
	holder.ivIcon.setImageDrawable(icon);
	holder.tvTitle.setText(drink.getName());
	holder.tvPrice.setText(drink.getFormatedPrice());
	holder.ibAdd.setOnClickListener(new AddButtonClickListener(drink,
		activity));

	return convertView;
    }

    static class ViewHolder {
	public ImageView ivIcon;
	public TextView tvTitle;
	public TextView tvPrice;
	public ImageButton ibAdd;
    }
    // ====================
    // GETTERS & SETTERS
    // ====================
}
