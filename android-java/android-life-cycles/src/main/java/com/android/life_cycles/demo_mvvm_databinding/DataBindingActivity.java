package com.android.life_cycles.demo_mvvm_databinding;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.android.life_cycles.R;
import com.android.life_cycles.databinding.ActivityDatabindingBinding;

/**
 * Demo dataBinding + MVVM
 */
public class DataBindingActivity extends AppCompatActivity {
    private ActivityDatabindingBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        binding.setViewModel(viewModel);
    }

//    private void initView() {
//        binding.buttonSubmit.setOnClickListener(view ->
//                showToast("User name:" + viewModel.getUserName() + "Number phone: " + viewModel.getPhone()));
//    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
