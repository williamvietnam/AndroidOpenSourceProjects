package com.android.commons.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel>
        extends Fragment implements BaseViews {

    public VB binding;
    public VM viewModel;

    public abstract VB createViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    public abstract VM createViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = createViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews();
        initializeEvents();
    }
}
