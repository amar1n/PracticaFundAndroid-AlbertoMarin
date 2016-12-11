package com.amarin.mywaiter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.amarin.mywaiter.model.Restaurant;

import java.util.List;

public class TableArrayAdapter<Table> extends ArrayAdapter {

    public TableArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (Restaurant.getInstance().getTableByPosition(position).getOrder() != null
                && !Restaurant.getInstance().getTableByPosition(position).getOrder().getOrderItems().isEmpty()) {
            view.setBackgroundColor(Color.LTGRAY);
        }

        return view;
    }
}
