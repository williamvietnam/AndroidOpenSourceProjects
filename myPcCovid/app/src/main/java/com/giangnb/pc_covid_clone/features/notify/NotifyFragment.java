package com.giangnb.pc_covid_clone.features.notify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.giangnb.pc_covid_clone.databinding.FragmentNotifyBinding;

public class NotifyFragment extends Fragment
        implements NotifyContract.View {

    private FragmentNotifyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = FragmentNotifyBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showDataForNotifyList();
    }


    @Override
    public void showDataForNotifyList() {
        NotifyAdapter notifyAdapter = new NotifyAdapter(new NotifyViewModel().fakeUiForAdapter());
        binding.rcvNotifyList.setAdapter(notifyAdapter);
        binding.rcvNotifyList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
