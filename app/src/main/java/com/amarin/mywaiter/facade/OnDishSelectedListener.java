package com.amarin.mywaiter.facade;

import android.view.View;

import com.amarin.mywaiter.model.Dish;

public interface OnDishSelectedListener {
    public void onDishClick(Dish dish, View view);
}
