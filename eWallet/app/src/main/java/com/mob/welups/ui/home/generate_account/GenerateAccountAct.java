package com.mob.welups.ui.home.generate_account;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.config.CustomToast;

import butterknife.BindView;
import butterknife.OnClick;

public class GenerateAccountAct extends BaseActivity {
    @BindView(R.id.imgQRCodeAdd)
    ImageView imgQRCodeAdd;

    @BindView(R.id.imgQRCodePrivate)
    ImageView imgQRCodePrivate;

    @BindView(R.id.tvKeyAdd)
    TextView tvKeyAdd;

    @BindView(R.id.cbGenerateAcc)
    CheckBox cbGenerateAcc;

    @BindView(R.id.tvConnect)
    TextView tvConnect;

    @BindView(R.id.tvPrivateKey)
    TextView tvKeyPrivate;

    @OnClick(R.id.imgCopyKeyAdd)
    void copyKeyAddress() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("dataKeyAdd", tvKeyAdd.getText().toString());
        clipboard.setPrimaryClip(data);

        CustomToast toast = new CustomToast(GenerateAccountAct.this);
        toast.makeCopySuccess().show();
    }

    @OnClick(R.id.imgCopyKeyPrivate)
    void copyKeyPrivate() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("dataKeyPrivate", tvKeyPrivate.getText().toString());
        clipboard.setPrimaryClip(data);

        CustomToast toast = new CustomToast(GenerateAccountAct.this);
        toast.makeCopySuccess().show();
    }

    @OnClick(R.id.cbGenerateAcc)
    void isCheckConnect() {
        if (cbGenerateAcc.isChecked()) {
            tvConnect.setBackgroundResource(R.drawable.bg_color_yellow);
        } else {
            tvConnect.setBackgroundResource(R.drawable.bg_color_gray);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showBtnBackGenerateAcc();
        showNameActionBar(getString(R.string.generate_account));


    }

    @Override
    protected void setClickBackGenerateAcc() {
        super.setClickBackGenerateAcc();

        Dialog dialog = new Dialog(GenerateAccountAct.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_waring_back_generate_acc);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imgClose = dialog.findViewById(R.id.imgClose);
        Button btnGoIt = dialog.findViewById(R.id.btnGoIt);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnGoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void generateQRCodeKeyAdd() {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            // test nhớ thay
            BitMatrix matrix = writer.encode("test mã QR Code key add", BarcodeFormat.QR_CODE,
                    500, 500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imgQRCodeAdd.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            // test nhớ thay
            manager.hideSoftInputFromWindow(tvKeyAdd.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void generateQRCodeKeyPrivate() {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            // test nhớ thay
            BitMatrix matrix = writer.encode("test mã QR Code key private", BarcodeFormat.QR_CODE,
                    500, 500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imgQRCodePrivate.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            // test nhớ thay
            manager.hideSoftInputFromWindow(tvKeyPrivate.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_generate_account;
    }
}
