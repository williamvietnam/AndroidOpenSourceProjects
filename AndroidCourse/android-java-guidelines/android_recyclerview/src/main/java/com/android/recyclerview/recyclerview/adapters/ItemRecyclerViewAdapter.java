package com.android.recyclerview.recyclerview.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.recyclerview.databinding.WidgetSubItem1Binding;
import com.android.recyclerview.databinding.WidgetSubItem2Binding;
import com.android.recyclerview.databinding.WidgetSubItem3Binding;
import com.android.recyclerview.databinding.WidgetSubItemTitleBinding;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemOneAdapter;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemThreeAdapter;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemTwoAdapter;
import com.android.recyclerview.old.data.mock.MainRepository;
import com.android.recyclerview.old.models.SubItem;
import com.android.recyclerview.recyclerview.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Item> itemList;
    private final MainRepository mainRepository;

    public static final int VIEW_TYPE_TITLE = 0;
    public static final int VIEW_TYPE_ONE = 1;
    public static final int VIEW_TYPE_TWO = 2;
    public static final int VIEW_TYPE_THREE = 3;

    public ItemRecyclerViewAdapter(List<Item> itemList, MainRepository mainRepository) {
        this.itemList = itemList;
        this.mainRepository = mainRepository;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            return new WidgetSubItemOneHolder(
                    WidgetSubItem1Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else if (viewType == VIEW_TYPE_TWO) {
            return new WidgetSubItemTwoHolder(
                    WidgetSubItem2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else if (viewType == VIEW_TYPE_THREE) {
            return new WidgetSubItemThreeHolder(
                    WidgetSubItem3Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new WidgetSubItemTitleHolder(
                    WidgetSubItemTitleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_ONE) {
            ArrayList<SubItem> subItems = mainRepository.dataMockItemChildOne();
            SubItemOneAdapter adapter = new SubItemOneAdapter(subItems);
            ((WidgetSubItemOneHolder) holder).bindData(adapter);
        } else if (getItemViewType(position) == VIEW_TYPE_TWO) {
            ArrayList<SubItem> subItems = mainRepository.dataMockItemChildTwo();
            SubItemTwoAdapter adapter = new SubItemTwoAdapter(subItems);
            ((WidgetSubItemTwoHolder) holder).bindData(adapter);
        } else if (getItemViewType(position) == VIEW_TYPE_THREE) {
            ArrayList<SubItem> subItems = mainRepository.dataMockItemChildThree();
            SubItemThreeAdapter adapter = new SubItemThreeAdapter(subItems);
            ((WidgetSubItemThreeHolder) holder).bindData(adapter);
        } else {
            ((WidgetSubItemTitleHolder) holder).bindData(itemList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        } else {
            return itemList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position).getViewType() == VIEW_TYPE_ONE) {
            return VIEW_TYPE_ONE;
        } else if (itemList.get(position).getViewType() == VIEW_TYPE_TWO) {
            return VIEW_TYPE_TWO;
        } else if (itemList.get(position).getViewType() == VIEW_TYPE_THREE) {
            return VIEW_TYPE_THREE;
        }
        return VIEW_TYPE_TITLE;
    }

    static class WidgetSubItemTitleHolder extends RecyclerView.ViewHolder {
        WidgetSubItemTitleBinding binding;

        public WidgetSubItemTitleHolder(@NonNull WidgetSubItemTitleBinding widgetSubItemTitleBinding) {
            super(widgetSubItemTitleBinding.getRoot());
            this.binding = widgetSubItemTitleBinding;
        }

        public void bindData(@NonNull Item item) {
            binding.textViewTitle.setText(item.getTitle());
        }
    }

    static class WidgetSubItemOneHolder extends RecyclerView.ViewHolder {
        WidgetSubItem1Binding binding;

        public WidgetSubItemOneHolder(@NonNull WidgetSubItem1Binding subItemOneBinding) {
            super(subItemOneBinding.getRoot());
            this.binding = subItemOneBinding;
        }

        public void bindData(@NonNull SubItemOneAdapter adapter) {
            binding.rcvSubItem1.setAdapter(adapter);
        }
    }

    static class WidgetSubItemTwoHolder extends RecyclerView.ViewHolder {
        WidgetSubItem2Binding binding;

        public WidgetSubItemTwoHolder(@NonNull WidgetSubItem2Binding subItemTwoBinding) {
            super(subItemTwoBinding.getRoot());
            this.binding = subItemTwoBinding;
        }

        public void bindData(@NonNull SubItemTwoAdapter adapter) {
            binding.rcvSubItem2.setAdapter(adapter);
        }
    }

    static class WidgetSubItemThreeHolder extends RecyclerView.ViewHolder {
        WidgetSubItem3Binding binding;

        public WidgetSubItemThreeHolder(@NonNull WidgetSubItem3Binding subItemThreeBinding) {
            super(subItemThreeBinding.getRoot());
            this.binding = subItemThreeBinding;
        }

        public void bindData(@NonNull SubItemThreeAdapter adapter) {
            binding.rcvSubItem3.setAdapter(adapter);
        }
    }
}
