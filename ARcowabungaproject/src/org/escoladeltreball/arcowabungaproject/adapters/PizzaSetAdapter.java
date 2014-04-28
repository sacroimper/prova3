package org.escoladeltreball.arcowabungaproject.adapters;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.model.Pizza;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PizzaSetAdapter extends BaseExpandableListAdapter {

    

    //====================
    // CONSTANTS
    //====================

    //====================
    // ATTRIBUTES
    //====================
    
    private final List<Pizza> pizzas = new ArrayList<Pizza>();
    public LayoutInflater inflater;
    public Activity activity;
    
    //====================
    // CONSTRUCTORS
    //====================

    public PizzaSetAdapter(Activity activity, Set<Pizza> pizzas) {
	this.activity = activity;
	for (Pizza pizza : pizzas){
	    this.pizzas.add(pizza);
	}
	inflater = activity.getLayoutInflater();
    }
    
    //====================
    // PUBLIC METHODS
    //====================

    //====================
    // PROTECTED METHODS
    //====================

    //====================
    // PRIVATE METHODS
    //====================

    //====================
    // OVERRIDE METHODS
    //====================

    @Override
    public Object getChild(int groupPosition, int childPosition) {
	return pizzas.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
	final Pizza children = (Pizza) getChild(groupPosition, childPosition);
	TextView tvDesc;
	ImageView ivIcon;
	LinearLayout llButton;
	if (convertView == null) {
	   // convertView = inflater.inflate(R.layout.subitems_layout, null);
	}
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
    
    //====================
    // GETTERS & SETTERS
    //====================
}
