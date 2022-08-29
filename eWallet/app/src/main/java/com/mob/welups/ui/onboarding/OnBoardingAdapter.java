package com.mob.welups.ui.onboarding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mob.welups.data.models.OnBoarding;
import com.mob.welups.databinding.WidgetOnBoardingBinding;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {
    private final List<OnBoarding> onBoardingList;

    public OnBoardingAdapter(List<OnBoarding> onBoardingList) {
        this.onBoardingList = onBoardingList;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        WidgetOnBoardingBinding binding = WidgetOnBoardingBinding.inflate(inflater, parent, false);
        return new OnBoardingAdapter.OnBoardingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.onBindData(position);
    }

    @Override
    public int getItemCount() {
        if (onBoardingList != null) {
            return onBoardingList.size();
        }
        return 0;
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder {
        private final WidgetOnBoardingBinding binding;

        public OnBoardingViewHolder(@NonNull WidgetOnBoardingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBindData(int position) {
            OnBoarding item = onBoardingList.get(position);
            this.binding.imgLogoOnBoarding.setImageResource(item.getDrawableOnBoarding());
            this.binding.tvTitleOnBoarding.setText(item.getTitleOnBoarding());
            this.binding.tvContentOnBoarding.setText(item.getDescriptionOnBoarding());
        }
    }
}