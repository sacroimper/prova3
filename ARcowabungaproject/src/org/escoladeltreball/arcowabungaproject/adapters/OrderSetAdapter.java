package org.escoladeltreball.arcowabungaproject.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.escoladeltreball.arcowabungaproject.R;
import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.Product;
import org.escoladeltreball.arcowabungaproject.utils.CustomTextView;

import android.app.Activity;
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
    public Object getChild(int groupPosition, int childPosition) {
	return orders.get(groupPosition).getShoppingCart().getProducts()
		.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
	return ((Product) getChild(groupPosition, childPosition)).getId();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
	    boolean isLastChild, View convertView, ViewGroup parent) {

	return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
	return orders.get(groupPosition).getShoppingCart().getProducts().size();
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
	return orders.get(groupPosition).getId();
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
    }
}
