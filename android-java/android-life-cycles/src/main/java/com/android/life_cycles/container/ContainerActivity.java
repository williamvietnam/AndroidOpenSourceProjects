package com.android.life_cycles.container;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.life_cycles.R;
import com.android.life_cycles.databinding.ActivityContainerBinding;
import com.android.life_cycles.demo_mvvm_livedata.MvvmContainerActivity;
import com.android.life_cycles.fragment_a.StartFragment;
import com.android.life_cycles.fragment_b.fragment_parent.ContainerFragment;
import com.android.life_cycles.fragment_c.ResultFragment;

public class ContainerActivity extends AppCompatActivity {

    private ActivityContainerBinding binding;
    private StartFragment startFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ContainerActivity", "onCreate()...");
        binding = ActivityContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showStartFragment();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("temp", false);
        Log.d("ContainerActivity", "onSaveInstanceState()...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ContainerActivity", "onDestroy()...");
    }

    public void showStartFragment() {
        startFragment = (StartFragment) getSupportFragmentManager().findFragmentByTag(StartFragment.class.getName());
        if (startFragment == null) {
            startFragment = new StartFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, startFragment, StartFragment.class.getName())
                    .addToBackStack(StartFragment.class.getName())
                    .show(startFragment)
                    .commit();
        }
    }

    public void showContainerFragment() {
        ContainerFragment containerFragment = new ContainerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, containerFragment, ContainerFragment.class.getName())
                .addToBackStack(ContainerFragment.class.getName())
                .show(containerFragment)
                .commit();
    }

    public void showResultFragment() {
        ResultFragment resultFragment = new ResultFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, resultFragment, ResultFragment.class.getName())
                .addToBackStack(ResultFragment.class.getName())
                .show(resultFragment)
                .commit();
    }

    public void moveToMvvmActivity() {
        Intent intent = new Intent(ContainerActivity.this, MvvmContainerActivity.class);
        startActivity(intent);
        finish();
    }
}
