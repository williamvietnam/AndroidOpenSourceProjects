package com.android.recyclerview.old.adapters.old_way_recyclerview_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.SubItemOneBinding;
import com.android.recyclerview.old.models.SubItem;

import java.util.List;

public class SubItemOneAdapter extends RecyclerView.Adapter<SubItemOneAdapter.ViewHolder> {

    private final List<SubItem> listItem;

    public SubItemOneAdapter(List<SubItem> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SubItemOneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubItem itemChild = listItem.get(position);
        holder.setData(itemChild);
        holder.binding.tvDelete.setOnClickListener(view -> {
            listItem.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final SubItemOneBinding binding;

        ViewHolder(@NonNull SubItemOneBinding itemOneBinding) {
            super(itemOneBinding.getRoot());
            binding = itemOneBinding;
        }

        void setData(@NonNull SubItem item) {
            binding.imageView.setImageResource(item.getImageRes());
        }
    }
}
