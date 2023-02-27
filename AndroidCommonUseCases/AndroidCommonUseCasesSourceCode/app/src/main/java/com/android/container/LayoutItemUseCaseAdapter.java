package com.android.container;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.databinding.LayoutItemUseCaseBinding;

import java.util.List;

public class LayoutItemUseCaseAdapter extends RecyclerView.Adapter<LayoutItemUseCaseAdapter.LayoutItemUseCaseViewHolder> {
    private final List<String> itemList;
    private final UseCaseCallBack callBack;

    public LayoutItemUseCaseAdapter(List<String> itemList, UseCaseCallBack callBack) {
        this.itemList = itemList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public LayoutItemUseCaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutItemUseCaseBinding binding = LayoutItemUseCaseBinding.inflate(inflater, parent, false);
        return new LayoutItemUseCaseAdapter.LayoutItemUseCaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutItemUseCaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    class LayoutItemUseCaseViewHolder extends RecyclerView.ViewHolder {
        private final LayoutItemUseCaseBinding binding;

        public LayoutItemUseCaseViewHolder(@NonNull LayoutItemUseCaseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(int position) {
            String textUseCase = itemList.get(position);
            this.binding.tvUseCase.setText(textUseCase);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onClicked(textUseCase);
                }
            });
        }
    }

    public interface UseCaseCallBack {
        void onClicked(String useCase);
    }
}