package com.mob.welups.view.transfer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mob.welups.R;
import com.mob.welups.base.BaseFragment;

import java.text.ParseException;

import butterknife.BindView;

public class TransferFrag extends BaseFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabLayoutTransfer)
    TabLayout tabLayoutTransfer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager2Transfer)
    ViewPager2 viewPager2Transfer;

    @Override
    protected void initView(View mView, Bundle savedInstanceState) throws ParseException {
        hideLogoWelupsAndMenu();
        hideMenuWelups();
        showNameActionBar(getString(R.string.tab_transfer));

        TransferAdapter transferAdapter = new TransferAdapter(this);
        viewPager2Transfer.setAdapter(transferAdapter);

        new TabLayoutMediator(tabLayoutTransfer, viewPager2Transfer, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.text_send);
                    break;
                case 1:
                    tab.setText(R.string.text_receive);
                    break;
            }
        }).attach();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_transfer;
    }
}
