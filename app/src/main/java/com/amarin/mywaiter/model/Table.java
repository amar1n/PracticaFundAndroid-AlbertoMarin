package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Table implements Serializable {
    private String mCode;
    private Order mOrder;

    Table(String code) {
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
            for (OrderItem orderItem : getOrder().getOrderItems()) {
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
