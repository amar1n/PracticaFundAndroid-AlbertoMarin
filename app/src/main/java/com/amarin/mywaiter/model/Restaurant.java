package com.amarin.mywaiter.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Restaurant implements Serializable {
    private static Restaurant ourInstance = new Restaurant();
    private Menu mMenu;
    private LinkedList<Table> mTables;

    private Restaurant() {
        initTables();
    }

    public static Restaurant getInstance() {
        return ourInstance;
    }

    private void initTables() {
        mTables = new LinkedList<>();
        mTables.add(new Table("Mesa 1"));
        mTables.add(new Table("Mesa 2"));
        mTables.add(new Table("Mesa 3"));
        mTables.add(new Table("Mesa 4"));
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public Table getTableByPosition(int position) {
        return getTables().get(position);
    }

    public Menu getMenu() {
        return mMenu;
    }

    public void setMenu(LinkedList<Dish> dishes) {
        mMenu = new Menu();
        mMenu.setDishes(dishes);
    }

    public Dish getDishByPosition(int position) {
        return getMenu().getDishes().get(position);
    }

    public Integer getTablePositionByTableCode(String code) {
        for (int i = 0; i < mTables.size(); i++) {
            Table table = mTables.get(i);
            if (table.getCode().equals(code)) return i;
        }
        return null;
    }

    public void replaceTable(int position, Table newTable) {
        if (mTables.size() < position) return;

        mTables.set(position, newTable);
    }
}
