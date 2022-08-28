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
                return new TransferSendFrag();
            case 1:
                return new TransferReceiveFrag();
            default:
                return new TransferSendFrag();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
