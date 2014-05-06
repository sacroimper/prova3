package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.model.Drink;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DrinkSetAdatper extends BaseAdapter {

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

    public DrinkSetAdatper(Activity activity, Set<Drink> drinks) {
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

	if (convertView == null) {
	    convertView = inflater
		    .inflate(R.layout.listitem_drink_layout, null);
	}
	return null;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
