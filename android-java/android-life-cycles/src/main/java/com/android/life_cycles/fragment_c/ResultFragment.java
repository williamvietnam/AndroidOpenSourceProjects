package com.android.life_cycles.fragment_c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.life_cycles.databinding.FragmentResultBinding;
import com.android.life_cycles.utils.Constants;

public class ResultFragment extends Fragment {
    private FragmentResultBinding binding;
    int sum = 0;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saveResult", sum);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            sum = savedInstanceState.getInt("saveResult");
            binding.tvResult.setText(String.valueOf(sum));
        }
        getParentFragmentManager().setFragmentResultListener(Constants.REQUEST_RESULT, this, (requestKey, result) -> {
            sum = result.getInt(Constants.KEY_RESULT);
            binding.tvResult.setText(String.valueOf(sum));
        });

        binding.buttonBack.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().popBackStack());
    }
}
