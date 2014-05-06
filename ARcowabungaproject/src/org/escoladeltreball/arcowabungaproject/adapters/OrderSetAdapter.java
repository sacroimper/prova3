package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.model.Order;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class OrderSetAdapter extends BaseExpandableListAdapter {

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

    public OrderSetAdapter(Activity activity, Set<Order> orders) {
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
    public Object getChild(int groupPosition, int childPosition) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getGroupCount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
	    View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean hasStableIds() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
	// TODO Auto-generated method stub
	return false;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    static class ChildViewHolder {
	TextView dateTime;
	TextView numberOfProducts;
	TextView price;
    }

    static class GroupViewHolder {
	TextView dateTime;
	TextView numberOfProducts;
	TextView price;
    }
}
