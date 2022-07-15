package com.android.recyclerview.old.container;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.recyclerview.R;
import com.android.recyclerview.databinding.ActivityContainerBinding;
import com.android.recyclerview.old.views.concat.ConcatFragment;
import com.android.recyclerview.old.views.new_way.NewWayFragment;
import com.android.recyclerview.old.views.old_way.OldWayFragment;
import com.android.recyclerview.old.views.tab_game.GameFragment;
import com.google.android.material.navigation.NavigationBarView;

public class ContainerActivity extends AppCompatActivity {

    private ActivityContainerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showOldWayFragment();

        binding.bottomNav.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.navigationOldWay) {
                showOldWayFragment();
                Toast.makeText(ContainerActivity.this, "Sử dụng RecyclerView Adapter", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.navigationNewWay) {
                showNewWayFragment();
                Toast.makeText(ContainerActivity.this, "Sử dụng DiffUtil & ListAdapter", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.navigationGame) {
                showGameFragment();
                return true;
            }
            return false;
        }
    };

    private void showOldWayFragment() {
        OldWayFragment oldWayFragment = new OldWayFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, oldWayFragment, OldWayFragment.class.getName())
                .addToBackStack(OldWayFragment.class.getName())
                .show(oldWayFragment)
                .commit();
    }

    private void showNewWayFragment() {
        NewWayFragment newWayFragment = new NewWayFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, newWayFragment, NewWayFragment.class.getName())
                .addToBackStack(NewWayFragment.class.getName())
                .show(newWayFragment)
                .commit();
    }

    private void showGameFragment() {
        GameFragment gameFragment = new GameFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, gameFragment, GameFragment.class.getName())
                .addToBackStack(GameFragment.class.getName())
                .show(gameFragment)
                .commit();
    }

    private void showConcatFragment() {
        ConcatFragment concatFragment = new ConcatFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, concatFragment, ConcatFragment.class.getName())
                .addToBackStack(ConcatFragment.class.getName())
                .show(concatFragment)
                .commit();
    }
}
