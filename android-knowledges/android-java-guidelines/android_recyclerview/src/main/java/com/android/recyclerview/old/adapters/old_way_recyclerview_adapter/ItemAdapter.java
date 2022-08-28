package com.android.recyclerview.old.adapters.old_way_recyclerview_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.ItemParentBinding;
import com.android.recyclerview.old.models.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private final ArrayList<Item> listItemItem;
    private final Context context;

    public ItemAdapter(ArrayList<Item> listItemItem, Context context) {
        this.listItemItem = listItemItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemParentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = listItemItem.get(position);
        if (position == 0) {
            SubItemOneAdapter subItemOneAdapter = new SubItemOneAdapter(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.verticalScroll.setVisibility(View.GONE);
            holder.binding.horizontalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainHorizontal.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.binding.rcvMainHorizontal.setAdapter(subItemOneAdapter);

        } else if (position == 1) {
            SubItemTwoAdapter subItemTwoAdapter = new SubItemTwoAdapter(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.horizontalScroll.setVisibility(View.GONE);
            holder.binding.verticalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainVertical.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.binding.rcvMainVertical.setAdapter(subItemTwoAdapter);

        } else if (position == 2) {
            SubItemThreeAdapter subItemThreeAdapter = new SubItemThreeAdapter(item.getSubItems());
            holder.binding.tvTitle.setText(item.getTitle());
            holder.binding.horizontalScroll.setVisibility(View.GONE);
            holder.binding.verticalScroll.setVisibility(View.VISIBLE);
            holder.binding.rcvMainVertical.setLayoutManager(new GridLayoutManager(context, 2));
            holder.binding.rcvMainVertical.setAdapter(subItemThreeAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return listItemItem.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemParentBinding binding;

        public MyViewHolder(ItemParentBinding itemParentBinding) {
            super(itemParentBinding.getRoot());
            binding = itemParentBinding;
        }
    }
}
