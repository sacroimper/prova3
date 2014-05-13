/*
 *  OrderSetAdapter.java
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
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public int getChildTypeCount() {
	return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
	if (getChildrenCount(groupPosition) == childPosition + 1) {
	    return 1;
	}
	return 0;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
	if (getChildrenCount(groupPosition) == childPosition + 1) {
	    return null;
	}
	return orders.get(groupPosition).getShoppingCart().getProducts()
		.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {
	if (isLastChild) {
	    Order order = (Order) getGroup(groupPosition);
	    convertView = inflater.inflate(
		    R.layout.expanded_order_ending_layout, null);
	    int[] ids = { R.id.subtotalTextInOrderEndingSubItem,
		    R.id.taxasTextInOrderEndingSubItem,
		    R.id.shippingCostTextInOrderEndingSubItem,
		    R.id.totalTextInOrderEndingSubItem,
		    R.id.textButtonInPizzaSubItem };
	    for (int id : ids) {
		CustomTextView.customTextView(activity,
			(TextView) convertView.findViewById(id));
	    }
	    TextView tvSubTotal = (TextView) convertView
		    .findViewById(R.id.subtotalPriceInOrderEndingSubItem);
	    TextView tvTaxa = (TextView) convertView
		    .findViewById(R.id.taxasValueInOrderEndingSubItem);
	    TextView tvShipping = (TextView) convertView
		    .findViewById(R.id.shippingCostValueInOrderEndingSubItem);
	    TextView tvTotal = (TextView) convertView
		    .findViewById(R.id.totalValueInOrderEndingSubItem);
	    CustomTextView.customTextView(activity, tvSubTotal);
	    CustomTextView.customTextView(activity, tvTaxa);
	    CustomTextView.customTextView(activity, tvShipping);
	    CustomTextView.customTextView(activity, tvTotal);

	    tvSubTotal.setText(order.getShoppingCart().getFormatedPrice());
	    tvSubTotal.setText(order.getShoppingCart().getFormatedPrice());
	    tvSubTotal.setText(order.getShoppingCart().getFormatedPrice());
	    tvSubTotal.setText(order.getShoppingCart().getFormatedPrice());
	    // TODO
	} else {
	    final Product children = (Product) getChild(groupPosition,
		    childPosition);
	    ChildViewHolder holder = null;
	    if (convertView == null) {
		holder = new ChildViewHolder();
		convertView = inflater.inflate(R.layout.expanded_order_layout,
			null);
		holder.ivIcon = (ImageView) convertView
			.findViewById(R.id.imageInOrderSubItem);
		holder.tvTitle = (TextView) convertView
			.findViewById(R.id.titleInOrderSubItem);
		holder.tvPrice = (TextView) convertView
			.findViewById(R.id.priceInOrderSubItem);
		CustomTextView.customTextView(activity, holder.tvTitle);
		CustomTextView.customTextView(activity, holder.tvPrice);
		CustomTextView
			.customTextView(
				activity,
				(TextView) convertView
					.findViewById(R.id.additionalIngredientsTitleInOrderSubItem));
		convertView.setTag(holder);
	    } else {
		holder = (ChildViewHolder) convertView.getTag();
	    }
	    Drawable icon = DAOAndroid.getInstance().getDrawableFromAssets(
		    activity, children.getIcon());
	    holder.ivIcon.setImageDrawable(icon);
	    holder.tvTitle.setText(children.getName());
	    holder.tvPrice.setText(children.getFormatedPrice());

	}

	return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	return orders.get(groupPosition).getShoppingCart().getProducts().size() + 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
	return orders.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
	return orders.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
	return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
	    View convertView, ViewGroup parent) {

	final Order group = (Order) getGroup(groupPosition);
	GroupViewHolder holder = null;
	if (convertView == null) {
	    holder = new GroupViewHolder();
	    convertView = inflater
		    .inflate(R.layout.listitem_order_layout, null);
	    holder.tvDateTime = (TextView) convertView
		    .findViewById(R.id.dateTextInOrderItem);
	    holder.tvNumberOfProducts = (TextView) convertView
		    .findViewById(R.id.descTextInOrderItem);
	    holder.tvPrice = (TextView) convertView
		    .findViewById(R.id.priceTextInOrderItem);
	    holder.tvCursor = (TextView) convertView
		    .findViewById(R.id.cursorTextInOrderItem);
	    CustomTextView.customTextView(activity, holder.tvDateTime);
	    CustomTextView.customTextView(activity, holder.tvNumberOfProducts);
	    CustomTextView.customTextView(activity, holder.tvPrice);
	    convertView.setTag(holder);
	} else {
	    holder = (GroupViewHolder) convertView.getTag();
	}

	holder.tvDateTime.setText(group.getDateTime().toDate().toString());
	holder.tvNumberOfProducts.setText(group.getShoppingCart()
		.sizeProducts() + " " + activity.getString(R.string.products));
	holder.tvPrice.setText(group.getFormatedPrice());
	String cursor = isExpanded ? activity.getString(R.string.cursor_up)
		: activity.getString(R.string.cursor_down);
	holder.tvCursor.setText(cursor);
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

    static class ChildViewHolder {
	ImageView ivIcon;
	TextView tvTitle;
	TextView tvPrice;
	LinearLayout llIngredients;
    }

    static class GroupViewHolder {
	TextView tvDateTime;
	TextView tvNumberOfProducts;
	TextView tvPrice;
	TextView tvCursor;
    }
}
