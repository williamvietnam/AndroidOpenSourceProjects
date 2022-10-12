package com.mob.welups.ui.home.settings;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mob.welups.config.LocaleHelper;
import com.mob.welups.ui.home.wallet_manager.WalletManagerAct;
import com.mob.welups.ui.container.MainActivity;
import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;
import com.mob.welups.ui.pincode.PinCodeAct;

import java.util.Locale;

import butterknife.OnClick;

public class SettingAct extends BaseActivity {
    private static final int REQUEST_CODE_CALL = 123;

    private static final String VI_LANGUAGE = "vi";

    private static final String EN_LANGUAGE = "en";

    @OnClick(R.id.layoutWalletManager)
    void openLayoutWalletManager() {
        startActivity(new Intent(SettingAct.this, WalletManagerAct.class));
    }

    @OnClick(R.id.layoutFb)
    void openFbWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://www.facebook.com/Welups"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://www.facebook.com"));
        }
    }

    @OnClick(R.id.layoutTelegram)
    void openTelegramWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://t.me/welupsofficial"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://webz.telegram.org/"));
        }
    }

    @OnClick(R.id.layoutPinCode)
    void openPinCode(){
        startActivity(new Intent(SettingAct.this, PinCodeAct.class));
    }

    @OnClick(R.id.layoutTwitter)
    void openTwitterWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://mobile.twitter.com/WelupsOfficial"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://twitter.com/?lang=vi"));
        }
    }

    @OnClick(R.id.layoutInstagram)
    void openInstagramWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://www.instagram.com/welupsofficial/"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://www.instagram.com/"));
        }
    }

    @OnClick(R.id.layoutHelpCenter)
    void openHelpCenterWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://welups.com/contact"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://welups.com/"));
        }
    }

    @OnClick(R.id.layoutAbout)
    void openAboutWelups() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent == null) {
            return;
        }
        try {
            intent.setData(Uri.parse("https://welups.com/about"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            intent.setData(Uri.parse("https://welups.com/"));
        }
    }

    private void saveFlagChangeLanguage(boolean b) {
        if (b) {
            LocaleHelper.language = EN_LANGUAGE;
            saveLocale(EN_LANGUAGE);
            setLanguage();
        } else {
            LocaleHelper.language = VI_LANGUAGE;
            saveLocale(VI_LANGUAGE);
            setLanguage();
        }
    }

    private void setLanguage() {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        LocaleHelper.language = prefs.getString("Language", LocaleHelper.LOCALE_VI_VN);
        Locale locale = new Locale(LocaleHelper.language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    private void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public boolean getFlagChangeLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences("CommonPrefs", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("Language", LocaleHelper.language).equals(VI_LANGUAGE)) {
            return true;
        } else {
            return false;
        }

    }

    @OnClick(R.id.layoutLanguage)
    void openDialogChangeLanguage() {
        Dialog dialog = new Dialog(SettingAct.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_language);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView img_language_vi = dialog.findViewById(R.id.img_language_vi);
        ImageView img_language_en = dialog.findViewById(R.id.img_language_en);
        ImageView imgCloseDialog = dialog.findViewById(R.id.imgClose);

        if (getFlagChangeLanguage()) {
            img_language_en.setSelected(false);
            img_language_vi.setSelected(true);
        } else {
            img_language_vi.setSelected(false);
            img_language_en.setSelected(true);
        }

        imgCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.ln_language_vi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!img_language_vi.isSelected()) {
                    img_language_vi.setSelected(!img_language_vi.isSelected());
                    img_language_en.setSelected(false);

                    if (getFlagChangeLanguage() && img_language_vi.isSelected()) {
                        return;
                    } else if (!getFlagChangeLanguage() && img_language_en.isSelected()) {
                        return;
                    }

                    if (img_language_en.isSelected()) {
                        saveFlagChangeLanguage(true);
                    } else {
                        saveFlagChangeLanguage(false);
                    }
                    Intent intent = new Intent(SettingAct.this, MainActivity.class);
                    intent.putExtra("changeTheme", "success");
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    finish();
                } else {
                    return;
                }
            }
        });

        dialog.findViewById(R.id.ln_language_en).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!img_language_en.isSelected()) {
                    img_language_en.setSelected(!img_language_en.isSelected());
                    img_language_vi.setSelected(false);

                    if (getFlagChangeLanguage() && img_language_vi.isSelected()) {
                        return;
                    } else if (!getFlagChangeLanguage() && img_language_en.isSelected()) {
                        return;
                    }

                    if (img_language_en.isSelected()) {
                        saveFlagChangeLanguage(true);
                    } else {
                        saveFlagChangeLanguage(false);
                    }
                    Intent intent = new Intent(SettingAct.this, MainActivity.class);
                    intent.putExtra("changeTheme", "success");
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                    finish();
                } else {
                    return;
                }
            }
        });

        dialog.show();
    }

    @OnClick(R.id.tvLogout)
    void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingAct.this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.are_you_sure_you_want_to_logout);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    @OnClick(R.id.tvHotline)
    void openCallPhone() {
        if (ContextCompat.checkSelfPermission(SettingAct.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SettingAct.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);
        } else {
            callPhone();
        }
    }

    private void callPhone() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("tel: " + "+" + "971564082200"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCallPhone();
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        hideLogoWelupsAndMenu();
        showNameActionBar(getString(R.string.setting));
        setShowClickBackButton();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }
}