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

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.dao.DAOAndroid;
import org.escoladeltreball.arcowabungaproject.listeners.AddButtonClickListener;
import org.escoladeltreball.arcowabungaproject.model.Offer;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
	return offers.get(groupPosition).getProductList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
	final Product children = (Product) getChild(groupPosition,
		childPosition);
	ChildViewHolder holder = null;
	if (convertView == null) {
	    holder = new ChildViewHolder();
	    convertView = inflater
		    .inflate(R.layout.expanded_offer_layout, null);
	    holder.ivIcon = (ImageView) convertView
		    .findViewById(R.id.imageInOfferSubItem);
	    holder.tvPrice = (TextView) convertView
		    .findViewById(R.id.priceTextInOfferSubItem);
	    holder.tvTitle = (TextView) convertView
		    .findViewById(R.id.titleTextInOfferSubItem);
	    CustomTextView.customTextView(activity, holder.tvTitle);
	    CustomTextView.customTextView(activity, holder.tvPrice);
	    convertView.setTag(holder);
	} else {
	    holder = (ChildViewHolder) convertView.getTag();
	}
	holder.tvTitle.setText(children.getName());
	holder.tvPrice.setText(children.getFormatedPrice());

	DAOAndroid dao = DAOAndroid.getInstance();
	Drawable icon = dao.getDrawableFromAssets(activity, children.getIcon());
	holder.ivIcon.setImageDrawable(icon);

	return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	return offers.get(groupPosition).getProductList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
	return offers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
	return offers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
	return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
	    View convertView, ViewGroup parent) {
	Offer group = (Offer) getGroup(groupPosition);
	GroupViewHolder holder = null;
	if (convertView == null) {
	    holder = new GroupViewHolder();
	    convertView = inflater
		    .inflate(R.layout.listitem_offer_layout, null);
	    holder.ivIcon = (ImageView) convertView
		    .findViewById(R.id.imageInOfferItem);
	    holder.tvTitle = (TextView) convertView
		    .findViewById(R.id.titleTextInOfferItem);
	    holder.tvPrice = (TextView) convertView
		    .findViewById(R.id.priceTextInOfferItem);
	    holder.tvDesc = (TextView) convertView
		    .findViewById(R.id.descTextInOfferItem);
	    holder.ibAdd = (ImageButton) convertView
		    .findViewById(R.id.imageButtonInOfferItem);
	    holder.ibAdd.setFocusable(false);
	    CustomTextView.customTextView(activity, holder.tvTitle);
	    CustomTextView.customTextView(activity, holder.tvPrice);
	    CustomTextView.customTextView(activity, holder.tvDesc);
	    convertView.setTag(holder);

	} else {
	    holder = (GroupViewHolder) convertView.getTag();
	}

	DAOAndroid dao = DAOAndroid.getInstance();
	Drawable icon = dao.getDrawableFromAssets(activity, group.getIcon());
	holder.ivIcon.setImageDrawable(icon);
	holder.tvTitle.setText(group.getName());
	holder.tvPrice.setText(group.getFormatedPrice());
	String description = "";
	for (Product p : group.getProductList()) {
	    description += p.getName() + ", ";
	}
	description = description.substring(0, description.length() - 2);
	if (description.length() > 50) {
	    description = description.substring(0, 50) + "...";
	}
	String showMore = "<font color='#e13546'>"
		+ activity.getResources().getString(R.string.show_more)
		+ "</font>";
	holder.tvDesc.setText(Html.fromHtml(description + " " + showMore));
	holder.ibAdd.setOnClickListener(new AddButtonClickListener(group,
		activity));
	return convertView;
    }

    @Override
    public boolean hasStableIds() {
	return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
	return false;
    }

    // ====================
    // GETTERS & SETTERS
    // ====================

    public static class ChildViewHolder {
	public ImageView ivIcon;
	public TextView tvTitle;
	public TextView tvPrice;
    }

    public static class GroupViewHolder {
	public ImageView ivIcon;
	public TextView tvTitle;
	public TextView tvPrice;
	public TextView tvDesc;
	public ImageButton ibAdd;
    }

}
