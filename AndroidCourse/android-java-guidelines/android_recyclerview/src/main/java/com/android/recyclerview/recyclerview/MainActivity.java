package com.android.recyclerview.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.recyclerview.databinding.ActivityMainBinding;
import com.android.recyclerview.old.data.mock.MainRepository;
import com.android.recyclerview.recyclerview.adapters.ItemRecyclerViewAdapter;
import com.android.recyclerview.recyclerview.models.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Item> items;
    MainRepository mainRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainRepository = new MainRepository();
        items = new ArrayList<>();

        initData();
    }

    private void initData() {
        //item position 0:
        items.add(new Item(0, "Preview Song"));
        //item position 1:
        items.add(new Item(1));
        //item position 2:
        items.add(new Item(0, "List song"));
        //item position 3:
        items.add(new Item(2));
        //item position 4:
        items.add(new Item(0, "Album Song"));
        //item position 5:
        items.add(new Item(3));

        //setAdapter cho recyclerView
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(items, mainRepository);
        binding.recyclerView.setAdapter(adapter);
    }
}
