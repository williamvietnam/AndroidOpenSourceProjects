package com.android.life_cycles.demo_mvvm_livedata.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.life_cycles.databinding.FragmentMainBinding;
import com.android.life_cycles.demo_mvvm_livedata.MvvmContainerActivity;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerListener();
        updateValueWhenClickButtonIncreaseReduction();

        listenerEvents();

        updateValueWhenInputEditText();
    }

    private void registerListener() {
        viewModel.getInputX().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvValueX.setText(String.valueOf(integer));
            }
        });

        viewModel.getInputY().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvValueY.setText(String.valueOf(integer));
            }
        });
    }

    private void updateValueWhenClickButtonIncreaseReduction() {
        binding.buttonMinusValueX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.reductionInputX();
            }
        });
        binding.buttonPlusValueX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.increaseInputX();
            }
        });
        binding.buttonMinusValueY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.reductionInputY();
            }
        });
        binding.buttonPlusValueY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.increaseInputY();
            }
        });
    }

    private void updateValueWhenInputEditText() {
        String inputX = binding.edtInputX.getText().toString();
        Log.d("input", inputX);
        String inputY = binding.edtInputX.getText().toString();
        Log.d("input", inputY);
//        viewModel.setInputX(Integer.parseInt(binding.edtInputX.getText().toString()));
//        viewModel.setInputY(Integer.parseInt(binding.edtInputY.getText().toString()));
    }

    private void listenerEvents() {
        //tính tổng:
        binding.buttonTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setSum();
                viewModel.setResult(viewModel.getSum());
                ((MvvmContainerActivity) requireActivity()).showResultMVVMFragment();
            }
        });

        binding.buttonHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setDifference();
                viewModel.setResult(viewModel.getDifference());
                ((MvvmContainerActivity) requireActivity()).showResultMVVMFragment();
            }
        });

        binding.buttonTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setProduct();
                viewModel.setResult(viewModel.getProduct());
                ((MvvmContainerActivity) requireActivity()).showResultMVVMFragment();
            }
        });
    }
}
