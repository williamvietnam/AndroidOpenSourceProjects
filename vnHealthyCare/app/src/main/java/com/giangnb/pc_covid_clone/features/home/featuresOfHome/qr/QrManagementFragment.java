package com.giangnb.pc_covid_clone.features.home.featuresOfHome.qr;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.giangnb.pc_covid_clone.databinding.FragmentQrManagementBinding;

public class QrManagementFragment extends Fragment
        implements QrManagementContract {
    private FragmentQrManagementBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQrManagementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = binding.edtInput.getText().toString();
                if (data.isEmpty()) {
                    binding.edtInput.setError("Please enter a name");
                    Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
//                    QrManagementFragmentDirections.actionShowDataTest().setDataToShow(data);
                    Navigation.findNavController(view).navigate(QrManagementFragmentDirections.actionShowDataTest().setDataToShow(data));
                }
            }
        });
    }
}
