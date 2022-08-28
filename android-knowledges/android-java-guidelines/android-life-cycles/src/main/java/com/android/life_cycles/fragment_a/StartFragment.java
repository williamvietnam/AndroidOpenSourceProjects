package com.android.life_cycles.fragment_a;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.life_cycles.container.ContainerActivity;
import com.android.life_cycles.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {
    private FragmentStartBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ContainerActivity) requireActivity()).showContainerFragment();
            }
        });

        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonMvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ContainerActivity) requireActivity()).moveToMvvmActivity();
                binding = null;
            }
        });
    }
}
