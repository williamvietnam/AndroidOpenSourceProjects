package com.giangnb.pc_covid_clone.features.menu.featuresOfMenu.checkin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giangnb.pc_covid_clone.R;
import com.giangnb.pc_covid_clone.data.model.CheckInHistory;

import java.util.List;

public class CheckInHistoryAdapter extends RecyclerView.Adapter<CheckInHistoryAdapter.ViewHolder> {
    private List<CheckInHistory> checkInHistoryList;

    public CheckInHistoryAdapter(List<CheckInHistory> checkInHistoryList) {
        this.checkInHistoryList = checkInHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_check_in_history, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckInHistory checkInHistory = checkInHistoryList.get(position);

        holder.tvDate.setText(checkInHistory.getDate());
        holder.tvTime.setText(checkInHistory.getTime());
        holder.tvPlace.setText(checkInHistory.getPlace());
        holder.tvAddressDetail.setText(checkInHistory.getAddress());
    }

    @Override
    public int getItemCount() {
        return checkInHistoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private  final TextView tvDate;
        private  final TextView tvTime;
        private  final TextView tvPlace;
        private  final TextView tvAddressDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvAddressDetail = itemView.findViewById(R.id.tvAddressDetail);
        }
    }
}
