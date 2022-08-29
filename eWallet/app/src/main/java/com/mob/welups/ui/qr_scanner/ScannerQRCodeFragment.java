package com.mob.welups.ui.qr_scanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.mob.welups.ui.container.MainActivity;
import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.ui.home.wallet_manager.WalletManagerAct;

import java.io.Serializable;

import butterknife.BindView;

public class ScannerQRCodeFragment extends BaseActivity implements Serializable {
    private CodeScanner codeScanner;

    @BindView(R.id.scanner_view)
    public CodeScannerView scannerView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        codeScanner = new CodeScanner(ScannerQRCodeFragment.this, scannerView);

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
                intent = new Intent(ScannerQRCodeFragment.this, WalletManagerAct.class);
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
                intent = new Intent(ScannerQRCodeFragment.this, MainActivity.class);
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
        return R.layout.fragment_scanner_q_r_code;
    }

}
