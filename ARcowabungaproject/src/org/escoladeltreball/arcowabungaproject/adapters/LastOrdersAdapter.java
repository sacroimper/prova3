package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Order;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LastOrdersAdapter extends BaseAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================

    private List<Order> orders = new ArrayList<Order>();;
    private LayoutInflater inflater;
    public Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================

    public LastOrdersAdapter(Activity activity, Set<Order> orders) {
	this.activity = activity;
	for (Order order : orders) {
	    this.orders.add(order);
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
    public Object getItem(int arg0) {
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
	TextView dateTime;
	TextView numberOfProducts;
	TextView price;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================
}
