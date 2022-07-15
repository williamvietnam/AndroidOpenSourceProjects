package com.android.life_cycles.fragment_b.fragment_child;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.life_cycles.databinding.FragmentBottomBinding;
import com.android.life_cycles.utils.Constants;

public class BottomFragment extends Fragment {

    private FragmentBottomBinding binding;

    private int valueBottom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBottomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            valueBottom = savedInstanceState.getInt("saveValueBottom");
        }
        binding.tvValueBottom.setText(String.valueOf(valueBottom));

        listener();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("test3", "ValueTop of BottomFragment: " + String.valueOf(valueBottom));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saveValueBottom", valueBottom);
    }

    //----------------------------------------------------------------------------------------------
    private void listener() {
        binding.buttonMinus.setOnClickListener(view -> {
            valueBottom = valueBottom - 1;
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_VALUE_BOTTOM, valueBottom);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_BOTTOM, bundle);
            binding.tvValueBottom.setText(String.valueOf(valueBottom));
        });

        binding.buttonPlus.setOnClickListener(view -> {
            valueBottom = valueBottom + 1;
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.KEY_VALUE_BOTTOM, valueBottom);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_BOTTOM, bundle);
            binding.tvValueBottom.setText(String.valueOf(valueBottom));
        });
    }
}
