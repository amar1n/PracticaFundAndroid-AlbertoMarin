package com.amarin.mywaiter.model;

import android.util.Log;

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
        if (getNote() == null) {
            Log.v(this.getClass().getCanonicalName(), "......................getNote is null");
        } else {
            Log.v(this.getClass().getCanonicalName(), "......................getNote is... " + getNote() + "###");
        }
        return getDish().getName() + (getNote() != null ? " (*)" : "");
    }
}
