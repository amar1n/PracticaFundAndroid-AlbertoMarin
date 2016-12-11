package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Menu implements Serializable {
    protected LinkedList<Dish> mDishes;

    public Menu() {
        mDishes = new LinkedList<>();
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }
}
