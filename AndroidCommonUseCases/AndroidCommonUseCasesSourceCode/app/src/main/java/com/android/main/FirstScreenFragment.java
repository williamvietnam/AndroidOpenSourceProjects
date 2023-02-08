package com.android.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.databinding.FragmentFirstScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstScreenFragment extends Fragment {
    private FragmentFirstScreenBinding binding;
    private LayoutItemUseCaseAdapter adapter;
    private List<String> useCases;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentFirstScreenBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        this.binding = null;
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.createUseCases();
        adapter = new LayoutItemUseCaseAdapter(useCases, new LayoutItemUseCaseAdapter.UseCaseCallBack() {
            @Override
            public void onClicked(String useCase) {
                useCaseOptions(useCase);
            }
        });
        this.binding.rcv.setAdapter(this.adapter);
    }

    private void createUseCases() {
        this.useCases = new ArrayList<>();
        this.useCases.add(Constants.USE_CASE_1);
        this.useCases.add(Constants.USE_CASE_2);
        this.useCases.add(Constants.USE_CASE_3);
        this.useCases.add(Constants.USE_CASE_4);
        this.useCases.add(Constants.USE_CASE_5);
        this.useCases.add(Constants.USE_CASE_6);
    }

    private void useCaseOptions(@NonNull String useCase) {
        switch (useCase) {
            case Constants.USE_CASE_1:
                ((MainActivity) requireActivity()).navigateLoginFormUseCase();
                break;
            case Constants.USE_CASE_2:
                ((MainActivity) requireActivity()).navigateInputFormValidationUseCase();
                break;
            case Constants.USE_CASE_3:
                Toast.makeText(getContext(), useCase, Toast.LENGTH_SHORT).show();
                break;
            case Constants.USE_CASE_4:
                Toast.makeText(getContext(), useCase, Toast.LENGTH_SHORT).show();
                break;
            case Constants.USE_CASE_5:
                Toast.makeText(getContext(), useCase, Toast.LENGTH_SHORT).show();
                break;
            case Constants.USE_CASE_6:
                Toast.makeText(getContext(), useCase, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
