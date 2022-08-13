package com.mob.welups.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mob.welups.R;
import com.mob.welups.view.SettingAct;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private View mView;

    private Unbinder mUnbinder;

    @BindView(R.id.img_back_actionbar)
    ImageView imgBackActionBar;

    @BindView(R.id.img_menu_actionbar)
    ImageView imgMenuActionbar;

    @BindView(R.id.tv_name_actionbar)
    TextView tvNameActionbar;

    @BindView(R.id.img_logo_Welups)
    ImageView imgLogoWelups;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        try {
            initView(mView, savedInstanceState);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return mView;
    }

    protected abstract void initView(View mView, Bundle savedInstanceState) throws ParseException;

    protected abstract int getLayoutId();

    protected void setShowClickBackButton(){
        imgBackActionBar.setVisibility(View.VISIBLE);
        imgBackActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishBaseActivity();
            }
        });
    }

    protected void finishBaseActivity() {
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    protected void setClickMenu(){
        imgMenuActionbar.setVisibility(View.VISIBLE);
        imgMenuActionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingAct.class));
            }
        });
    }

    protected void hideLogoWelupsAndMenu(){
        imgLogoWelups.setVisibility(View.GONE);
        imgMenuActionbar.setVisibility(View.GONE);
    }

    protected void hideMenuWelups(){
        imgLogoWelups.setVisibility(View.GONE);
    }

    protected void hideItemActionBar(){
        ViewGroup viewGroup =  getActivity().findViewById(R.id.action_bar_isShow);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.GONE);
        }
    }

    protected void showNameActionBar(String NameActionBar){
        tvNameActionbar.setVisibility(View.VISIBLE);
        tvNameActionbar.setText(NameActionBar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }
}
