package com.amarin.mywaiter.model;

import java.io.Serializable;

/**
 * Created by alberto on 4/12/16.
 */

public class Allergen implements Serializable {
    protected String mName;
    protected int mIcon;

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
