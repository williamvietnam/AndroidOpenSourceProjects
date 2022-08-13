package com.mob.welups.view.transfer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mob.welups.R;
import com.mob.welups.base.BaseFragment;

import java.text.ParseException;

import butterknife.BindView;

public class TransferSendFrag extends BaseFragment {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnPayWithSend)
    RelativeLayout btnPayWithSend;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnAddressSend)
    RelativeLayout btnAddressSend;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btnSendNow)
    TextView btnSendNow;

    @Override
    protected void initView(View mView, Bundle savedInstanceState) throws ParseException {
        hideItemActionBar();
        hideLogoWelupsAndMenu();
        hideMenuWelups();

        registerListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_transfer_send;
    }

    private void registerListener() {
        btnPayWithSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogListToken();
            }
        });

        btnAddressSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddressToken();
            }
        });

        btnSendNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    private void openDialogListToken() {
        Dialog dialog = new Dialog(getActivity());
        //Click outside dialog screen is close dialog
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_token);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        //Set dialog screen is rectangle shape
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //Close dialog by button close
        ImageView dialogButton = dialog.findViewById(R.id.btnCloseDialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openDialogAddressToken() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_address_send);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ImageView btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
