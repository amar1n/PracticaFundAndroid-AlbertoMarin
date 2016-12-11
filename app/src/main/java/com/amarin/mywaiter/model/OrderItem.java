package com.amarin.mywaiter.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private Dish mDish;
    private String mNote;

    public OrderItem(Dish dish, String note) {
        mNote = note;
        mDish = dish;
    }

    public Dish getDish() {
        return mDish;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    @Override
    public String toString() {
        return getDish().getName() + (getNote() != null ? " (*)" : "");
    }
}
