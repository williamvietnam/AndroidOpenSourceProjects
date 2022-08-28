package com.mob.welups.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.mob.welups.R;
import com.mob.welups.ui.SettingAct;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.img_back_actionbar)
    ImageView imgBackActionBar;

    @BindView(R.id.img_menu_actionbar)
    ImageView imgMenuActionbar;

    @BindView(R.id.tv_name_actionbar)
    TextView tvNameActionbar;

    @BindView(R.id.img_logo_Welups)
    ImageView imgLogoWelups;

    @BindView(R.id.img_backGenerateAcc_actionbar)
    ImageView imgBackGenerateAccActionBar;

    @OnClick(R.id.img_backGenerateAcc_actionbar)
    void clickBackGenerateAcc(){
        setClickBackGenerateAcc();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected void setShowClickBackButton(){
        imgBackActionBar.setVisibility(View.VISIBLE);
        imgBackActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void setClickMenu(){
        imgMenuActionbar.setVisibility(View.VISIBLE);
        imgMenuActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingAct.class));
            }
        });
    }

    protected void hideLogoWelupsAndMenu(){
        imgLogoWelups.setVisibility(View.GONE);
        imgMenuActionbar.setVisibility(View.GONE);
    }

    protected void showBtnBackGenerateAcc(){
        imgLogoWelups.setVisibility(View.GONE);
        imgMenuActionbar.setVisibility(View.GONE);
        imgBackActionBar.setVisibility(View.GONE);
        imgBackGenerateAccActionBar.setVisibility(View.VISIBLE);
    }

    protected void hideItemActionBar(){
        ViewGroup viewGroup =  findViewById(R.id.action_bar_isShow);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.GONE);
        }
    }

    protected void showNameActionBar(String NameActionBar){
        tvNameActionbar.setVisibility(View.VISIBLE);
        tvNameActionbar.setText(NameActionBar);
    }

    protected void setClickBackGenerateAcc() { }

}
