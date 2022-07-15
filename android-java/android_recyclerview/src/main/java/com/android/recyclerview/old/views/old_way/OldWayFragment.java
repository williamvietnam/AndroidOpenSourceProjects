package com.android.recyclerview.old.views.old_way;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.recyclerview.databinding.FragmentOldWayBinding;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.ItemAdapter;
import com.android.recyclerview.old.data.mock.MainRepository;
import com.android.recyclerview.old.models.Item;
import com.android.recyclerview.old.models.SubItem;

import java.util.ArrayList;
import java.util.List;

public class OldWayFragment extends Fragment {
    private FragmentOldWayBinding binding;
    private ArrayList<Item> items;
    MainRepository mainRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment", "onCreate()...OldWay");
        items = new ArrayList<>();
        mainRepository = new MainRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Fragment", "onCreateView()...OldWay");
        binding = FragmentOldWayBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SubItem> subItems1 = mainRepository.dataMockItemChildOne();
        items.add(new Item("List songs", subItems1));

        List<SubItem> subItems2 = mainRepository.dataMockItemChildTwo();
        items.add(new Item("New songs", subItems2));

        List<SubItem> subItems3 = mainRepository.dataMockItemChildThree();
        items.add(new Item("Favorite songs", subItems3));

        ItemAdapter itemAdapter = new ItemAdapter(items, requireContext());

        binding.rcvMain.setAdapter(itemAdapter);
    }
}
