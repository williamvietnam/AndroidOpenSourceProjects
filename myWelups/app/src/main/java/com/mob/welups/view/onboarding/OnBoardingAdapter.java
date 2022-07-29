package com.mob.welups.view.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mob.welups.R;
import com.mob.welups.model.OnBoarding;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {


    private final List<OnBoarding> onBoardingList;

    public OnBoardingAdapter(List<OnBoarding> onBoardingList) {
        this.onBoardingList = onBoardingList;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_on_boarding, parent, false);
        return new OnBoardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        OnBoarding onBoarding = onBoardingList.get(position);
        if (onBoarding == null) {
            return;
        }
        holder.imgLogoOnBoarding.setImageResource(onBoarding.getDrawableOnBoarding());
        holder.titleOnBoarding.setText(onBoarding.getTitleOnBoarding());
        holder.contentOnBoarding.setText(onBoarding.getDescriptionOnBoarding());

    }

    @Override
    public int getItemCount() {
        if (onBoardingList != null) {
            return onBoardingList.size();
        }
        return 0;
    }

    public static class OnBoardingViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgLogoOnBoarding;
        private final TextView titleOnBoarding;
        private final TextView contentOnBoarding;

        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogoOnBoarding = itemView.findViewById(R.id.imgLogoOnBoarding);
            titleOnBoarding = itemView.findViewById(R.id.tvTitleOnBoarding);
            contentOnBoarding = itemView.findViewById(R.id.tvContentOnBoarding);
        }
    }
}