package com.mob.welups;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTabHost;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.config.LocaleHelper;
import com.mob.welups.view.address_book.AddressBookFrag;
import com.mob.welups.view.home.HomeFrag;
import com.mob.welups.view.transfer.TransferFrag;

import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_nav)
    BottomNavigationView bottomNavigationView;

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setLanguage();

        bottomNavigationView.setItemIconTintList(null);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("", null),
                HomeFrag.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("", null),
                TransferFrag.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("", null),
                AddressBookFrag.class, null);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTabHost.setCurrentTab(0);
                    return true;
                case R.id.navigation_transfer:
                    mTabHost.setCurrentTab(1);
                    return true;
                case R.id.navigation_addressBook:
                    mTabHost.setCurrentTab(2);
                    return true;
            }
            return false;
        }
    };

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






