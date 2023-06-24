package com.android.commons.container.tutorialsMenu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.android.commons.base.BaseFragment;
import com.android.databinding.FragmentTutorialsMenuBinding;

public class TutorialsMenuFragment extends BaseFragment<FragmentTutorialsMenuBinding, TutorialsMenuViewModel> {
    @Override
    public FragmentTutorialsMenuBinding createViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentTutorialsMenuBinding.inflate(inflater, container, false);
    }

    @Override
    public TutorialsMenuViewModel createViewModel() {
        return new ViewModelProvider(this).get(TutorialsMenuViewModel.class);
    }

    @Override
    public void initializeViews() {

    }

    @Override
    public void initializeEvents() {

    }
}
