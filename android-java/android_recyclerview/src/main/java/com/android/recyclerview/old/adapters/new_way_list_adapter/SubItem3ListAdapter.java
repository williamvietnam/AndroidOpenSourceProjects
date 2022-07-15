package com.android.recyclerview.old.adapters.new_way_list_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.SubItemThreeBinding;
import com.android.recyclerview.old.adapters.new_way_list_adapter.clicked.OnSubItem3Clicked;
import com.android.recyclerview.old.models.SubItem;

import java.util.ArrayList;
import java.util.List;

public class SubItem3ListAdapter extends ListAdapter<SubItem, SubItem3ListAdapter.SubItem3ViewHolder> {

    private final OnSubItem3Clicked onSubItem3Clicked;

    public SubItem3ListAdapter(@NonNull DiffUtil.ItemCallback<SubItem> diffCallback, OnSubItem3Clicked onSubItem3Clicked) {
        super(diffCallback);
        this.onSubItem3Clicked = onSubItem3Clicked;
    }

    @NonNull
    @Override
    public SubItem3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubItem3ViewHolder(SubItemThreeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubItem3ViewHolder holder, int position) {
        holder.setData(getItem(position));
    }


    class SubItem3ViewHolder extends RecyclerView.ViewHolder {

        private final SubItemThreeBinding binding;

        public SubItem3ViewHolder(SubItemThreeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.tvDelete.setOnClickListener(view -> onSubItem3Clicked.onDelete3(getAdapterPosition()));
        }

        void setData(SubItem item) {
            binding.imageView.setImageResource(item.getImageRes());
            binding.tvContent.setText(item.getContent());
        }
    }

    @Override
    public void submitList(@Nullable List<SubItem> list) {
        super.submitList(list != null ? new ArrayList<>(list) : null);
    }
}
