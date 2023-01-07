package com.android.recyclerview.old.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class SubItem {

    private int id;
    private int imageRes;
    private String content;

    public SubItem(int id, int imageRes, String content) {
        this.id = id;
        this.imageRes = imageRes;
        this.content = content;
    }

    public SubItem(int id, int imageRes) {
        this.id = id;
        this.imageRes = imageRes;
    }

    public SubItem(int imageRes) {
        this.imageRes = imageRes;
    }

    public SubItem(int imageRes, String content) {
        setImageRes(imageRes);
        setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubItem subItem = (SubItem) o;
        return id == subItem.id && imageRes == subItem.imageRes && Objects.equals(content, subItem.content);
    }

    /**
     * DiffUtil sử dụng cho NewWayAdapter (cụ thể là: ListAdapter)
     */
    public static DiffUtil.ItemCallback<SubItem> subItemCallback = new DiffUtil.ItemCallback<SubItem>() {

        /**
         * Nếu data truyền vào SubItem thiếu thuộc tính id thì sẽ ko xác định được vị trí delete trong list,
         *  khi đó dù click delete ở bất kỳ item nào thì item đầu tiên sẽ luôn bị xoá
         * */
        @Override
        public boolean areItemsTheSame(@NonNull SubItem oldItem, @NonNull SubItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SubItem oldItem, @NonNull SubItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
