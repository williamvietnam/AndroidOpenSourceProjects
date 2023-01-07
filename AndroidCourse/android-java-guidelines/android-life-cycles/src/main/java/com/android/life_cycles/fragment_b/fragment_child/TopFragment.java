package com.android.life_cycles.fragment_b.fragment_child;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.life_cycles.databinding.FragmentTopBinding;
import com.android.life_cycles.utils.Constants;

public class TopFragment extends Fragment {

    private FragmentTopBinding binding;

    private int valueTop;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Fragment", "onSaveInstanceState()...TopFragment");
        outState.putInt("saveValueTop", valueTop);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTopBinding.inflate(inflater, container, false);
        Log.d("Fragment", "onCreateView()...TopFragment");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Fragment", "onViewCreated()...TopFragment");
        if (savedInstanceState != null) {
            valueTop = savedInstanceState.getInt("saveValueTop");
        }
        binding.tvValueTop.setText(String.valueOf(valueTop));

        listener();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test3", "ValueTop of TopFragment: " + String.valueOf(valueTop));
        Log.d("Fragment", "onResume()...TopFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment", "onDestroyView()...TopFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment", "onDestroy()...TopFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment", "onDetach()...TopFragment");
    }

    //----------------------------------------------------------------------------------------------
    private void listener() {
        binding.buttonMinus.setOnClickListener(view -> {
            valueTop = valueTop - 1;
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_VALUE_TOP, valueTop);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_TOP, bundle);
            binding.tvValueTop.setText(String.valueOf(valueTop));
        });

        binding.buttonPlus.setOnClickListener(view -> {
            valueTop = valueTop + 1;
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_VALUE_TOP, valueTop);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_TOP, bundle);
            binding.tvValueTop.setText(String.valueOf(valueTop));
        });
    }
}
