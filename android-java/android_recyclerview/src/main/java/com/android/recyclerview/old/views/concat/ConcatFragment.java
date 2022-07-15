package com.android.recyclerview.old.views.concat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.recyclerview.databinding.FragmentConcatBinding;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemOneAdapter;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemThreeAdapter;
import com.android.recyclerview.old.adapters.old_way_recyclerview_adapter.SubItemTwoAdapter;
import com.android.recyclerview.old.data.mock.MainRepository;
import com.android.recyclerview.old.models.SubItem;

import java.util.List;

public class ConcatFragment extends Fragment {

    private FragmentConcatBinding binding;
    MainRepository mainRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainRepository = new MainRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConcatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SubItem> subItems1 = mainRepository.dataMockItemChildOne();
        SubItemOneAdapter subItemOneAdapter = new SubItemOneAdapter(subItems1);
        List<SubItem> subItems2 = mainRepository.dataMockItemChildTwo();
        SubItemTwoAdapter subItemTwoAdapter = new SubItemTwoAdapter(subItems2);
        List<SubItem> subItems3 = mainRepository.dataMockItemChildThree();
        SubItemThreeAdapter subItemThreeAdapter = new SubItemThreeAdapter(subItems3);

//        ConcatAdapter concatAdapter = new ConcatAdapter(subItemOneAdapter, subItemTwoAdapter, subItemThreeAdapter);
//        binding.rcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
//        binding.rcv.setAdapter(concatAdapter);
    }
}
