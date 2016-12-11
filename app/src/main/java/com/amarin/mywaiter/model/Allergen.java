package com.amarin.mywaiter.model;

import java.io.Serializable;

public class Allergen implements Serializable {
    private String mName;
    private int mIcon;

    public Allergen(String name, int icon) {
        mName = name;
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }
}
