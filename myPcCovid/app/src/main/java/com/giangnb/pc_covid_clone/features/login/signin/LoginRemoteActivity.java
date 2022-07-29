package com.giangnb.pc_covid_clone.features.login.signin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giangnb.pc_covid_clone.databinding.ActivityLoginRemoteBinding;
import com.giangnb.pc_covid_clone.features.login.LoginContract;

public class LoginRemoteActivity extends AppCompatActivity
        implements LoginContract.LoginRemoteView {

    private ActivityLoginRemoteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginRemoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    public void openMainScreen() {
        //TODO
    }
}
