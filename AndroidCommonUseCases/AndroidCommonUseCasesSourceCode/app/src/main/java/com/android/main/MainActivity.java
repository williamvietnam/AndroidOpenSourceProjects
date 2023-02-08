package com.android.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.databinding.ActivityMainBinding;
import com.android.input_form_validation_use_case.InputFormValidationFragment;
import com.android.login_form_use_case.LoginFormFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirstScreenFragment firstScreenFragment;
    private SecondScreenFragment secondScreenFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.navigateFirstScreen();
    }

    @Override
    protected void onDestroy() {
        this.binding = null;
        super.onDestroy();
    }

    public void navigateFirstScreen() {
        if (firstScreenFragment == null) {
            firstScreenFragment = new FirstScreenFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), firstScreenFragment, FirstScreenFragment.class.getName())
                .addToBackStack(null)
                .show(firstScreenFragment)
                .commit();
    }

    public void navigateLoginFormUseCase() {
        LoginFormFragment fragment = new LoginFormFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment, LoginFormFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }

    public void navigateInputFormValidationUseCase(){
        InputFormValidationFragment fragment = new InputFormValidationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment, InputFormValidationFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }
}