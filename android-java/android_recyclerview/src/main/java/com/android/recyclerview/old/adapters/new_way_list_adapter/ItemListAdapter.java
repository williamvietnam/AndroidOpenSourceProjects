package com.android.recyclerview.old.adapters.new_way_list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.ItemParentBinding;
import com.android.recyclerview.old.adapters.new_way_list_adapter.clicked.OnSubItem1Clicked;
import com.android.recyclerview.old.adapters.new_way_list_adapter.clicked.OnSubItem2Clicked;
import com.android.recyclerview.old.adapters.new_way_list_adapter.clicked.OnSubItem3Clicked;
import com.android.recyclerview.old.models.Item;
import com.android.recyclerview.old.models.SubItem;

public class ItemListAdapter extends ListAdapter<Item, ItemListAdapter.MyViewHolder>
        implements OnSubItem1Clicked, OnSubItem2Clicked, OnSubItem3Clicked {

    private SubItem1ListAdapter subItem1ListAdapter;
    private SubItem2ListAdapter subItem2ListAdapter;
    private SubItem3ListAdapter subItem3ListAdapter;
    private final Context context;

    public ItemListAdapter(@NonNull DiffUtil.ItemCallback<Item> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemParentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = getItem(position);
        if (position == 0) {
            subItem1ListAdapter = new SubItem1ListAdapter(SubItem.subItemCallback, this);
            subItem1ListAdapter.submitList(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.verticalScroll.setVisibility(View.GONE);
            holder.binding.horizontalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainHorizontal.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.binding.rcvMainHorizontal.setAdapter(subItem1ListAdapter);

        } else if (position == 1) {
            subItem2ListAdapter = new SubItem2ListAdapter(SubItem.subItemCallback, this);
            subItem2ListAdapter.submitList(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.horizontalScroll.setVisibility(View.GONE);
            holder.binding.verticalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainVertical.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.binding.rcvMainVertical.setAdapter(subItem2ListAdapter);

        } else if (position == 2) {
            subItem3ListAdapter = new SubItem3ListAdapter(SubItem.subItemCallback, this);
            subItem3ListAdapter.submitList(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.horizontalScroll.setVisibility(View.GONE);
            holder.binding.verticalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainVertical.setLayoutManager(new GridLayoutManager(context, 2));
            holder.binding.rcvMainVertical.setAdapter(subItem3ListAdapter);
        }
    }

    @Override
    public void onDelete1(int position) {
        getItem(0).getSubItems().remove(position);
        subItem1ListAdapter.submitList(getItem(0).getSubItems());
    }

    @Override
    public void onDelete2(int position) {
        getItem(1).getSubItems().remove(position);
        subItem2ListAdapter.submitList(getItem(1).getSubItems());
    }

    @Override
    public void onDelete3(int position) {
        getItem(2).getSubItems().remove(position);
        subItem3ListAdapter.submitList(getItem(2).getSubItems());
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemParentBinding binding;

        public MyViewHolder(ItemParentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
