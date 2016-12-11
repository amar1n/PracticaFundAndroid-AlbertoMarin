package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Order implements Serializable {
    private LinkedList<OrderItem> mOrderItems;

    Order() {
        mOrderItems = new LinkedList<>();
    }

    public LinkedList<OrderItem> getOrderItems() {
        return mOrderItems;
    }
}
