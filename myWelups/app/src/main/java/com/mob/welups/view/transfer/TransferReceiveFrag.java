package com.mob.welups.view.transfer;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mob.welups.R;
import com.mob.welups.base.BaseFragment;

import java.text.ParseException;

import butterknife.BindView;

public class TransferReceiveFrag extends BaseFragment {

    public String memoryText;

    @BindView(R.id.btnAssetReceive)
    RelativeLayout btnAssetReceive;
    @BindView(R.id.btnCopy)
    ImageView btnCopy;
    @BindView(R.id.tvAddressReceive)
    TextView tvAddressReceive;

    @Override
    protected void initView(View mView, Bundle savedInstanceState) throws ParseException {
        hideItemActionBar();
        hideLogoWelupsAndMenu();
        hideMenuWelups();

        registerListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_transfer_receive;
    }

    private void registerListener() {
        btnAssetReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogListToken();
            }
        });

        tvAddressReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddressToken();
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = (String) tvAddressReceive.getText();
                setBtnCopy(text);
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

    private void setBtnCopy(String text){
        memoryText = text;
    }

    private void openDialogAddressToken() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_address_receive);
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
