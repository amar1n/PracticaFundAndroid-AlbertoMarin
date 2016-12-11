package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Order implements Serializable {
    protected LinkedList<OrderItem> mOrderItems;

    public Order() {
        mOrderItems = new LinkedList<>();
    }

    public LinkedList<OrderItem> getOrderItems() {
        return mOrderItems;
    }

}
