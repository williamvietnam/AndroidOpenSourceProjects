package com.giangnb.pc_covid_clone.features.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

public class HomeViewModel implements HomeContract.ViewModel{

    private int REQUEST_CODE_CAM = 111;

    public void scannerQrCode(){

    }

//    @Override
//    public void requestPermissionCAM() {
//        if (ContextCompat.checkSelfPermission(new HomeFragment().requireContext(),
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            FragmentActivity.requestPermissions(,
//                    new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAM);
//        } else {
//            Intent intent = new Intent((), ScannerQRCodeAct.class);
//            intent.putExtra("testHomeFrag", "");
//            startActivity(intent);
//        }
//    }


}
