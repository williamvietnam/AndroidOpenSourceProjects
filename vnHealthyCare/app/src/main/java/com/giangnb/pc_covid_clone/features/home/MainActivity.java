package com.giangnb.pc_covid_clone.features.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.giangnb.pc_covid_clone.R;
import com.giangnb.pc_covid_clone.databinding.ActivityMainBinding;
import com.giangnb.pc_covid_clone.features.menu.MenuFragment;
import com.giangnb.pc_covid_clone.features.menu.featuresOfMenu.checkin.CheckInHistoryFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigateToUploadScreen();



//        NavHostFragment navHostFragment =
//                (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();

//        showHomeFragment();
    }

    private void navigateToUploadScreen(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
    }

    private void showHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, homeFragment, HomeFragment.class.getName())
                .show(homeFragment)
                .commit();
    }

    public void returnHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, homeFragment, MenuFragment.class.getName())
                .show(homeFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showMenuFragment() {
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, menuFragment, MenuFragment.class.getName())
                .show(menuFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showCheckInHistoryFragment() {
        CheckInHistoryFragment checkInHistoryFragment = new CheckInHistoryFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, checkInHistoryFragment, MenuFragment.class.getName())
                .show(checkInHistoryFragment)
                .addToBackStack(null)
                .commit();
    }

}
