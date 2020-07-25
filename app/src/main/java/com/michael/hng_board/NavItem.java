package com.michael.hng_board;

public class NavItem {
    private String name;
    private int icon;

    NavItem(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
