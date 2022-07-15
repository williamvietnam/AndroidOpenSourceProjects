package com.android.recyclerview.old.adapters.new_way_list_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.SubItemOneBinding;
import com.android.recyclerview.old.adapters.new_way_list_adapter.clicked.OnSubItem1Clicked;
import com.android.recyclerview.old.models.SubItem;

import java.util.ArrayList;
import java.util.List;

public class SubItem1ListAdapter extends ListAdapter<SubItem, SubItem1ListAdapter.SubItem1ViewHolder> {

    private final OnSubItem1Clicked onSubItem1Clicked;

    public SubItem1ListAdapter(@NonNull DiffUtil.ItemCallback<SubItem> diffCallback, OnSubItem1Clicked onSubItem1Clicked) {
        super(diffCallback);
        this.onSubItem1Clicked = onSubItem1Clicked;
    }

    @NonNull
    @Override
    public SubItem1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubItem1ViewHolder(SubItemOneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubItem1ViewHolder holder, int position) {
        SubItem subItem = getItem(position);
        holder.setData(subItem);
    }

    class SubItem1ViewHolder extends RecyclerView.ViewHolder {
        private final SubItemOneBinding binding;

        SubItem1ViewHolder(SubItemOneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.tvDelete.setOnClickListener(view -> onSubItem1Clicked.onDelete1(getAdapterPosition()));
        }

        void setData(@NonNull SubItem item) {
            binding.imageView.setImageResource(item.getImageRes());
        }
    }

    @Override
    public void submitList(@Nullable List<SubItem> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }
}
