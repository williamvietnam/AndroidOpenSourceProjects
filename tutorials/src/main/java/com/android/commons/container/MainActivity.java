package com.android.commons.container;

import androidx.lifecycle.ViewModelProvider;

import com.android.commons.base.BaseActivity;
import com.android.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public ActivityMainBinding createViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public MainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void initializeEvents() {

    }
}