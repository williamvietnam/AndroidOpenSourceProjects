package com.android.recyclerview.old.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class Item {
    private int id;
    private String title;
    private List<SubItem> subItems;

    public Item(String title, List<SubItem> subItems) {
        setTitle(title);
        setSubItems(subItems);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SubItem> subItems) {
        this.subItems = subItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Objects.equals(title, item.title) && Objects.equals(subItems, item.subItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subItems);
    }

    public static DiffUtil.ItemCallback<Item> itemCallback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };
}
