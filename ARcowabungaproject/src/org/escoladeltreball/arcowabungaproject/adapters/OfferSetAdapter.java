/*
 *  OfferSetAdapter.java
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

import org.escoladeltreball.arcowabungaproject.model.Offer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public class OfferSetAdapter extends BaseExpandableListAdapter {

    // ====================
    // CONSTANTS
    // ====================

    // ====================
    // ATTRIBUTES
    // ====================
    private final List<Offer> offers = new ArrayList<Offer>();
    public LayoutInflater inflater;
    public Activity activity;

    // ====================
    // CONSTRUCTORS
    // ====================
    public OfferSetAdapter(Activity activity, Set<Offer> offers) {
	this.activity = activity;
	for (Offer offer : offers) {
	    this.offers.add(offer);
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
}
