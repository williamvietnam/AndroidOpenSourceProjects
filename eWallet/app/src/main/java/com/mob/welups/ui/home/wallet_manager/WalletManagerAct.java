package com.mob.welups.ui.home.wallet_manager;

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

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mob.welups.R;
import com.mob.welups.ui.home.generate_account.GenerateAccountAct;
import com.mob.welups.ui.qr_scanner.ScannerQRCodeFragment;
import com.mob.welups.base.BaseActivity;

import butterknife.OnClick;

public class WalletManagerAct extends BaseActivity {
    private int REQUEST_CODE_CAM = 111;

    @OnClick(R.id.tvConnectWalletManager)
    void openDialogConnectWallet() {
        Dialog dialog = new Dialog(WalletManagerAct.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_connectwallet);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgCloseDialog = dialog.findViewById(R.id.imgClose);
        ImageView imgQRCode = dialog.findViewById(R.id.imgQRCode);
        EditText edtPrivateKey = dialog.findViewById(R.id.edtPrivateKey);

        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.removeExtra("resultQRCode");
                dialog.dismiss();
            }
        });

        imgQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissionCAM();
                dialog.dismiss();
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            edtPrivateKey.setText(intent.getStringExtra("resultQRCode"));
        }

        dialog.show();
    }

    private void requestPermissionCAM() {
        if (ContextCompat.checkSelfPermission(WalletManagerAct.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(WalletManagerAct.this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAM);
        } else {
            Intent intent = new Intent(WalletManagerAct.this, ScannerQRCodeFragment.class);
            intent.putExtra("testWallet", "");
            startActivity(intent);
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

    @OnClick(R.id.tvGenerateAcc)
    void openGenerateAcc() {
        startActivity(new Intent(WalletManagerAct.this, GenerateAccountAct.class));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        hideLogoWelupsAndMenu();
        showNameActionBar(getString(R.string.wallet_manager));
        setShowClickBackButton();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.wallet_manager;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (intent.getStringExtra("resultQRCode") != null) {
            openDialogConnectWallet();
        }
    }

}
