package com.mob.welups.ui.home;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mob.welups.R;
import com.mob.welups.ScannerQRCodeAct;
import com.mob.welups.base.BaseFragment;
import com.mob.welups.ui.GenerateAccountAct;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFrag extends BaseFragment {
    private final int REQUEST_CODE_CAM = 111;

    private boolean isCheckedEye = false;

    @OnClick(R.id.layout_ConnectWallet)
    void openConnectWallet() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_connectwallet);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        ImageView imgQRCode = dialog.findViewById(R.id.imgQRCode);
        EditText edtPrivateKey = dialog.findViewById(R.id.edtPrivateKey);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getActivity().getIntent();
                intent.removeExtra("data");

                dialog.dismiss();

            }
        });

        imgQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissionCAM();
                dialog.dismiss();
                getActivity().finish();
            }
        });

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            edtPrivateKey.setText(intent.getStringExtra("data"));
        }

        dialog.show();
    }

    @OnClick(R.id.layoutSend)
    void OpenSendFrag() {
//        TransferFrag sendFrag = new TransferFrag();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .add(android.R.id.tabcontent, sendFrag).addToBackStack(null).commit();
    }

    @OnClick(R.id.layoutReceive)
    void OpenReceiveFrag() {

    }

    @BindView(R.id.tvMoney)
    TextView tvMoney;

    @BindView(R.id.imgEye)
    ImageView imgEye;

    @OnClick(R.id.imgEye)
    void clickIconEye(){
        if (isCheckedEye){
            imgEye.setImageResource(R.drawable.ic_eye_off);
            tvMoney.setText("******");
        }else {
            imgEye.setImageResource(R.drawable.ic_eye_active);
            tvMoney.setText("9.44000567");
        }
        isCheckedEye = !isCheckedEye;
    }

    @OnClick(R.id.layout_Generate_Acc)
    void clickOpenGenerateAcc() {
        startActivity(new Intent(getContext(), GenerateAccountAct.class));
    }

    @Override
    protected void initView(View mView, Bundle savedInstanceState) throws ParseException {
        setClickMenu();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home;
    }

    private void requestPermissionCAM() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAM);
        } else {
            Intent intent = new Intent(getContext(), ScannerQRCodeAct.class);
            intent.putExtra("testHomeFrag", "");
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = getActivity().getIntent();
        if (intent.getStringExtra("data") != null) {
            openConnectWallet();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAM) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestPermissionCAM();
            }
        }
    }
}
