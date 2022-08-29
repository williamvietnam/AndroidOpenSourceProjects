package com.mob.welups.ui.transfer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TransferAdapter extends FragmentStateAdapter {

    public TransferAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TransferSendFragment();
            case 1:
                return new TransferReceiveFragment();
            default:
                return new TransferSendFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
