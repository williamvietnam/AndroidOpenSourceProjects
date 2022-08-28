package com.mob.welups.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.config.LocaleHelper;
import com.mob.welups.ui.onboarding.OnBoardingActivity;

import java.util.Locale;

public class SplashActivity extends BaseActivity {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
            finish();
        }
    };

    @Override
    protected void initView(Bundle savedInstanceState) {
        setLanguage();

        hideItemActionBar();
        hideLogoWelupsAndMenu();

        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
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
