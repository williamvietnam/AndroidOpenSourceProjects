package com.android.container;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.databinding.ActivityContainerBinding;
import com.android.templates.input_form_validation.InputFormValidationFragment;
import com.android.templates.login_form.LoginFormFragment;

public class ContainerActivity extends AppCompatActivity {

    private ActivityContainerBinding binding;
    private FirstScreenFragment firstScreenFragment;
    private SecondScreenFragment secondScreenFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityContainerBinding.inflate(getLayoutInflater());
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

    public void navigateInputFormValidationUseCase() {
        InputFormValidationFragment fragment = new InputFormValidationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(this.binding.fragmentContainerView.getId(), fragment, InputFormValidationFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }
}