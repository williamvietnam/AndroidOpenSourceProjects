package com.android.commons.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements BaseViews {

    public VB binding;
    public VM viewModel;

    public abstract VB createViewBinding();
    public abstract VM createViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();
        binding = createViewBinding();

        initializeViews();
        initializeEvents();
    }
}
