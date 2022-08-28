package com.mob.welups;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.view.WalletManagerAct;

import java.io.Serializable;

import butterknife.BindView;

public class ScannerQRCodeAct extends BaseActivity implements Serializable {
    private CodeScanner codeScanner;

    @BindView(R.id.scanner_view)
    CodeScannerView scannerView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        codeScanner = new CodeScanner(ScannerQRCodeAct.this, scannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getDataFromIntent(result);
            }
        });
    }

    private void getDataFromIntent(Result result) {
        Intent intent = getIntent();
        if (intent != null) {
            // wallet manager screen
            if (intent.hasExtra("testWallet")) {
                intent = new Intent(ScannerQRCodeAct.this, WalletManagerAct.class);
                intent.putExtra("resultQRCode", result.getText());
                startActivity(intent);

                finish();
            }
            // address book frag screen
            if (intent.hasExtra("testAdd")) {

                finish();
            }
            // Home Frag screen
            if (intent.hasExtra("testHomeFrag")) {
                intent = new Intent(ScannerQRCodeAct.this, MainActivity.class);
                intent.putExtra("data", result.getText());
                startActivity(intent);

                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.qrcode_scranner;
    }

}
