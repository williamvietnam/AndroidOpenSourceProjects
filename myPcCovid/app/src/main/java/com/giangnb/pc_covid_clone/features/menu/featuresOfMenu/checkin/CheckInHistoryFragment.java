package com.giangnb.pc_covid_clone.features.menu.featuresOfMenu.checkin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.giangnb.pc_covid_clone.databinding.FragmentCheckinHistoryBinding;
import com.giangnb.pc_covid_clone.features.home.MainActivity;

public class CheckInHistoryFragment extends Fragment
        implements CheckInHistoryContract.View {

    private FragmentCheckinHistoryBinding binding;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater,
                                          @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {
        binding = FragmentCheckinHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDataForRecyclerView();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showMenuFragment();
            }
        });
    }

    private void showDataForRecyclerView() {
        CheckInHistoryAdapter checkInHistoryAdapter = new CheckInHistoryAdapter(new CheckInHistoryViewModel().mockDataUiForAdapter());
        binding.rcvCheckInHistory.setAdapter(checkInHistoryAdapter);
        binding.rcvCheckInHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
