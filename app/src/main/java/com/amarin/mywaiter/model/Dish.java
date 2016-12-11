package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;

public class Dish implements Serializable {
    private String mName;
    private String mDescription;
    private int mIcon;
    private BigDecimal mPrice;
    private LinkedList<Allergen> mAllergens;

    public Dish(String name, String description, int icon, BigDecimal price, LinkedList<Allergen> allergens) {
        mName = name;
        mDescription = description;
        mIcon = icon;
        mPrice = price;
        mAllergens = allergens;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getIcon() {
        return mIcon;
    }

    public BigDecimal getPrice() {
        return mPrice;
    }

    public LinkedList<Allergen> getAllergens() {
        return mAllergens != null ? mAllergens : new LinkedList<Allergen>();
    }

    @Override
    public String toString() {
        return "Name[" + mName + "]\\n" +
                "Icon[" + mIcon + "]\\n" +
                "Price[" + getPrice().toString() + "]\\n" +
                "Allergens[" + getAllergens().size() + "]";
    }
}
