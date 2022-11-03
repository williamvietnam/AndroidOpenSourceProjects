package com.mob.welups.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.ViewModelProvider;

import com.mob.welups.base.v2.BaseActivityV2;
import com.mob.welups.config.LocaleHelper;
import com.mob.welups.databinding.ActionBarBinding;
import com.mob.welups.databinding.ActivitySplashBinding;
import com.mob.welups.ui.onboarding.OnBoardingActivity;

import java.util.Locale;

public class SplashActivity extends BaseActivityV2<ActivitySplashBinding, SplashViewModel> {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
            finish();
        }
    };

    @Override
    public ActivitySplashBinding createViewBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
    }

    @Override
    public ActionBarBinding createActionBarViewBinding() {
        return null;
    }

    @Override
    public SplashViewModel createViewModel() {
        return new ViewModelProvider(this).get(SplashViewModel.class);
    }

    @Override
    public void initialize() {
        setLanguage();

        hideItemActionBar();
        hideLogoWelupsAndMenu();

        handler.postDelayed(runnable, 3000);
    }

    private void setLanguage() {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        LocaleHelper.language = prefs.getString("Language", LocaleHelper.LOCALE_VI_VN);
        Locale locale = new Locale(LocaleHelper.language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}