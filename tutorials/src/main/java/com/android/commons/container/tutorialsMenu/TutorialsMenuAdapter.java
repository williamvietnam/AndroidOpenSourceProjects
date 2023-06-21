package com.android.commons.container.tutorialsMenu;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.databinding.ItemTutorialBinding;

public class TutorialsMenuAdapter extends RecyclerView.Adapter<TutorialsMenuAdapter.TutorialsMenuHolder>{

    private

    public TutorialsMenuAdapter(){

    }

    @NonNull
    @Override
    public TutorialsMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialsMenuHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class TutorialsMenuHolder extends RecyclerView.ViewHolder {

        private ItemTutorialBinding binding;

        public TutorialsMenuHolder(@NonNull ItemTutorialBinding binding) {
            super(binding.getRoot());
        }

        public void onBind(int position) {

        }
    }

    interface ITutorialsMenuCallBack{

    }
}
