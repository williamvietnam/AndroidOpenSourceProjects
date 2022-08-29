package com.mob.welups.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mob.welups.ui.container.MainActivity;
import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.config.LocaleHelper;


import java.util.Locale;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    private WebView webView;

    @BindView(R.id.btnLogin)
    ViewGroup btnLogin;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setLanguage();
        hideItemActionBar();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }



    @SuppressLint("SetJavaScriptEnabled")
    private void openIdShare() {
        WebView webView = new WebView(LoginActivity.this);
        setContentView(webView);
        webView.loadUrl("https://www.google.com.vn/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
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
