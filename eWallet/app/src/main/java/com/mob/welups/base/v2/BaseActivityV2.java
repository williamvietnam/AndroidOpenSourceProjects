package com.mob.welups.base.v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.mob.welups.databinding.ActionBarBinding;
import com.mob.welups.ui.home.settings.SettingAct;

public abstract class BaseActivityV2<VB extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity {

    private ActionBarBinding actionBarBinding;
    public VB binding;
    public VM viewModel;

    public abstract VB createViewBinding();

    public abstract ActionBarBinding createActionBarViewBinding();

    public abstract VM createViewModel();

    public abstract void initialize();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = createViewModel();

        binding = createViewBinding();
        setContentView(binding.getRoot());

        actionBarBinding = createActionBarViewBinding();
        initialize();
    }

    public void setShowClickBackButton() {
        actionBarBinding.imgBackActionbar.setVisibility(View.VISIBLE);
        actionBarBinding.imgBackActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setClickMenu() {
        actionBarBinding.imgMenuActionbar.setVisibility(View.VISIBLE);
        actionBarBinding.imgMenuActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingAct.class));
            }
        });
    }

    public void hideLogoWelupsAndMenu() {
        actionBarBinding.imgLogoWelups.setVisibility(View.GONE);
        actionBarBinding.imgMenuActionbar.setVisibility(View.GONE);
    }

    public void showBtnBackGenerateAcc() {
        actionBarBinding.imgLogoWelups.setVisibility(View.GONE);
        actionBarBinding.imgMenuActionbar.setVisibility(View.GONE);
        actionBarBinding.imgBackActionbar.setVisibility(View.GONE);
        actionBarBinding.imgBackGenerateAccActionbar.setVisibility(View.VISIBLE);
    }

    public void hideItemActionBar() {
        for (int i = 0; i < actionBarBinding.actionBarIsShow.getChildCount(); i++) {
            actionBarBinding.actionBarIsShow.getChildAt(i).setVisibility(View.GONE);
        }
    }

    public void showNameActionBar(String NameActionBar) {
        actionBarBinding.tvNameActionbar.setVisibility(View.VISIBLE);
        actionBarBinding.tvNameActionbar.setText(NameActionBar);
    }

    public void setClickBackGenerateAcc() {
    }
}