package com.giangnb.pc_covid_clone.features.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.giangnb.pc_covid_clone.R;
import com.giangnb.pc_covid_clone.databinding.FragmentMenuBinding;
import com.giangnb.pc_covid_clone.features.home.MainActivity;

public class MenuFragment extends Fragment implements MenuContract.View{

    private FragmentMenuBinding binding;
    private MenuViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new MenuViewModel();
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListenerOnClick();
    }

    @Override
    public void initListenerOnClick() {
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.homeFragment);
            }
        });

        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.notifyFragment);
            }
        });

        binding.btnAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.aboutAppFragment);
            }
        });
        binding.tvAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.aboutAppFragment);
            }
        });

        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.settingsFragment);
            }
        });
        binding.tvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.settingsFragment);
            }
        });

        binding.btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.userGuideFragment);
            }
        });
        binding.tvTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.userGuideFragment);
            }
        });

        binding.btnCheckInHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.checkInHistoryFragment);
            }
        });
        binding.tvCheckInHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.checkInHistoryFragment);
            }
        });

        binding.btnDataCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.dataCovid19Fragment);
            }
        });
        binding.tvDataCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.dataCovid19Fragment);
            }
        });

        binding.btnHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               viewModel.showDialogHotline();
            }
        });
        binding.tvHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.showDialogHotline();
            }
        });
    }


}

//  binding.btnHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity) getActivity()).returnHomeFragment();
//            }
//        });
//
//        binding.btnCheckInHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity)getActivity()).showCheckInHistoryFragment();
//            }
//        });