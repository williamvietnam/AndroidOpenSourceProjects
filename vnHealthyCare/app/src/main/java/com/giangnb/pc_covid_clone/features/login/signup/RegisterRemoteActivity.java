package com.giangnb.pc_covid_clone.features.login.signup;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giangnb.pc_covid_clone.databinding.ActivityRegisterRemoteBinding;

public class RegisterRemoteActivity extends AppCompatActivity
        implements RegisterContract.RegisterRemoteView {

    private ActivityRegisterRemoteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterRemoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
