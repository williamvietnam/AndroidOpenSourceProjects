package com.mob.welups.view.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.model.OnBoarding;
import com.mob.welups.view.login.LoginActivity;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class OnBoardingActivity extends BaseActivity {

    private ViewPager2 pagerOnBoarding;
    @Override
    protected void initView(Bundle savedInstanceState) {
        hideItemActionBar();
        hideLogoWelupsAndMenu();

        pagerOnBoarding = findViewById(R.id.paperOnBoarding);
        CircleIndicator3 indicatorOnBoarding = findViewById(R.id.indicatorOnBoarding);

        OnBoardingAdapter adapter = new OnBoardingAdapter(getListOnBoarding());
        pagerOnBoarding.setAdapter(adapter);
        indicatorOnBoarding.setViewPager(pagerOnBoarding);

        registerEvent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_on_boarding;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @NonNull
    private List<OnBoarding> getListOnBoarding() {
        List<OnBoarding> list = new ArrayList<>();
        list.add(new OnBoarding(R.drawable.img_privacy_security, R.string.title_on_boarding1, R.string.content_on_boarding1));
        list.add(new OnBoarding(R.drawable.img_address_book, R.string.title_on_boarding2, R.string.content_on_boarding2));
        list.add(new OnBoarding(R.drawable.img_multiples_accounts, R.string.title_on_boarding3, R.string.content_on_boarding3));
        list.add(new OnBoarding(R.drawable.img_receive_send_pay, R.string.title_on_boarding4, R.string.content_on_boarding4));
        return list;
    }

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (pagerOnBoarding.getCurrentItem() == getListOnBoarding().size() - 1) {
                pagerOnBoarding.setCurrentItem(0);
            } else {
                pagerOnBoarding.setCurrentItem(pagerOnBoarding.getCurrentItem() + 1);
            }
        }
    };

    private void registerEvent() {
        //auto run:
        pagerOnBoarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 1500);
            }
        });

        //Next to LoginScreen:
        TextView btnGetStarted = (TextView) findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginActivity();
            }
        });
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
