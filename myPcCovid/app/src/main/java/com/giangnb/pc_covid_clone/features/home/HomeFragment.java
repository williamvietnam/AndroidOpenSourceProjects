package com.giangnb.pc_covid_clone.features.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.giangnb.pc_covid_clone.R;
import com.giangnb.pc_covid_clone.databinding.FragmentHomeBinding;
import com.giangnb.pc_covid_clone.features.qrcode.QrCodeScannerFragment;

public class HomeFragment extends Fragment
        implements HomeContract.View {

    private int REQUEST_CODE_CAM = 111;
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel = new HomeViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListenerOnClick();
    }

    @Override
    public void initListenerOnClick() {
        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.menuFragment);
            }
        });

        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.notifyFragment);
            }
        });

        binding.btnQrCodeManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.qrManagementFragment);
            }
        });

        binding.btnMedicalDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.medicalDeclarationFragment);
            }
        });

        binding.btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.reportFragment);
            }
        });

        binding.btnQrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissionCAM();
                Navigation.findNavController(view).navigate(R.id.qrCodeScannerFragment);
            }
        });
    }

    private void requestPermissionCAM() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAM);
        }
    }
}
