package com.android.recyclerview.old.views.new_way;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.recyclerview.databinding.FragmentNewWayBinding;
import com.android.recyclerview.old.adapters.new_way_list_adapter.ItemListAdapter;
import com.android.recyclerview.old.data.mock.MainRepository;
import com.android.recyclerview.old.models.Item;
import com.android.recyclerview.old.models.SubItem;

import java.util.ArrayList;
import java.util.List;

public class NewWayFragment extends Fragment {

    private FragmentNewWayBinding binding;
    private MainRepository mainRepository;
    private List<Item> items;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items = new ArrayList<>();
        mainRepository = new MainRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment", "onCreateView()...NewWay");
        binding = FragmentNewWayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SubItem> subItems1 = mainRepository.dataMockItemChild1();
        items.add(new Item("List songs (ListAdapter)", subItems1));

        List<SubItem> subItems2 = mainRepository.dataMockItemChild2();
        items.add(new Item("New songs (ListAdapter)", subItems2));

        List<SubItem> subItems3 = mainRepository.dataMockItemChild3();
        items.add(new Item("Favorite songs (ListAdapter)", subItems3));

        ItemListAdapter itemListAdapter = new ItemListAdapter(Item.itemCallback, requireContext());
        binding.rcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.rcv.setAdapter(itemListAdapter);
        itemListAdapter.submitList(items);

    }

}
