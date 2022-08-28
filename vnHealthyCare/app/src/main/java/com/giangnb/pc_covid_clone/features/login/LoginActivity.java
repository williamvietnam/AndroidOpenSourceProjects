package com.giangnb.pc_covid_clone.features.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giangnb.pc_covid_clone.databinding.ActivityLoginBinding;
import com.giangnb.pc_covid_clone.features.login.signin.LoginLocalActivity;
import com.giangnb.pc_covid_clone.features.login.signin.LoginRemoteActivity;
import com.giangnb.pc_covid_clone.features.login.signup.RegisterLocalActivity;
import com.giangnb.pc_covid_clone.features.login.signup.RegisterRemoteActivity;

public class LoginActivity extends AppCompatActivity
        implements LoginContract.LoginView {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initEventsClick();
    }


    @Override
    public void initEventsClick() {
       binding.btnLoginFromLocal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openLoginLocalScreen();
           }
       });


       binding.btnLoginFromServer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openLoginRemoteScreen();
           }
       });

       binding.btnRegisterForLocal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openRegisterLocalScreen();
           }
       });

       binding.btnRegisterForServer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openRegisterRemoteScreen();
           }
       });
    }

    @Override
    public void openLoginLocalScreen() {
        Intent intent = new Intent(this, LoginLocalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginRemoteScreen() {
        Intent intent = new Intent(this, LoginRemoteActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRegisterLocalScreen() {
        Intent intent = new Intent(this, RegisterLocalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openRegisterRemoteScreen() {
        Intent intent = new Intent(this, RegisterRemoteActivity.class);
        startActivity(intent);
        finish();
    }
}
