package com.android.recyclerview.recyclerview.models;

public class Item {
    private int viewType;
    private String title;

    public Item(int viewType) {
        this.viewType = viewType;
    }

    public Item(int viewType, String title) {
        setViewType(viewType);
        setTitle(title);
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
