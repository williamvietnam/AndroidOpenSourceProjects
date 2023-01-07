package com.android.recyclerview.old.adapters.old_way_recyclerview_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.SubItemThreeBinding;
import com.android.recyclerview.old.models.SubItem;

import java.util.List;

public class SubItemThreeAdapter extends RecyclerView.Adapter<SubItemThreeAdapter.ViewHolder> {
    private final List<SubItem> listItem;

    public SubItemThreeAdapter(List<SubItem> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SubItemThreeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(listItem.get(position));
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
        private final SubItemThreeBinding binding;

        ViewHolder(@NonNull SubItemThreeBinding itemThreeBinding) {
            super(itemThreeBinding.getRoot());
            binding = itemThreeBinding;
        }

        void setData(@NonNull SubItem item) {
            binding.imageView.setImageResource(item.getImageRes());
            binding.tvContent.setText(item.getContent());
        }
    }
}
