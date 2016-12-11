package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;

public class Table implements Serializable {
    protected String mCode;
    protected Order mOrder;

    public Table(String code) {
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (mOrder == null) mOrder = new Order();
        mOrder.getOrderItems().add(orderItem);
    }

    public BigDecimal getBill() {
        BigDecimal result = BigDecimal.ZERO;

        if (getOrder() != null) {
            Iterator ite = getOrder().getOrderItems().iterator();
            while (ite.hasNext()) {
                OrderItem orderItem = (OrderItem) ite.next();
                result = result.add(orderItem.getDish().getPrice());
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
