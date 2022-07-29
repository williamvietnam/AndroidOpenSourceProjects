package com.giangnb.pc_covid_clone.features.notify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giangnb.pc_covid_clone.R;
import com.giangnb.pc_covid_clone.data.model.Notify;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ViewHolder> {

    private List<Notify> notifyList;

    public NotifyAdapter(List<Notify> notifyList) {
        this.notifyList = notifyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_notify, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notify notify = notifyList.get(position);

        holder.tvDateNotify.setText(notify.getDateNotify());
        holder.tvContent.setText(notify.getContent());
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDateNotify;
        private final TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateNotify = itemView.findViewById(R.id.tvDateNotify);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
