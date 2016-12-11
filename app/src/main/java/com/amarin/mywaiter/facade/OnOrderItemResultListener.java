package com.amarin.mywaiter.facade;

import com.amarin.mywaiter.model.OrderItem;

public interface OnOrderItemResultListener {
    public void onButtonClick(int result, OrderItem orderItem);
}
