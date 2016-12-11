package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Menu implements Serializable {
    private LinkedList<Dish> mDishes;

    public Menu() {
        mDishes = new LinkedList<>();
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }
}
