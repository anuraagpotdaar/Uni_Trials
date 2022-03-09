package com.anuraagpotdaar.unitrials.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anuraagpotdaar.unitrials.ParticipantDataManagementActivity;
import com.anuraagpotdaar.unitrials.R;

import java.util.ArrayList;

public class HeartRateAdapter extends RecyclerView.Adapter<HeartRateAdapter.DpdViewHolder> {

    Context context;

    ArrayList<HeartRate> list;

    public HeartRateAdapter(Context context, ArrayList<HeartRate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DpdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_heartrate,parent,false);
        return new DpdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DpdViewHolder holder, int position) {
        HeartRate heartRate = list.get(position);
        holder.date.setText(heartRate.date);
        holder.value.setText(heartRate.value);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DpdViewHolder extends RecyclerView.ViewHolder {

        TextView date,value;
        CardView card;
        public DpdViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cvHeart);

            value = itemView.findViewById(R.id.text_value);
            date = itemView.findViewById(R.id.text_date);

            card.setOnClickListener(view -> {
                HeartRate parti = list.get(getBindingAdapterPosition());
                notifyItemChanged(getAbsoluteAdapterPosition());
            });

        }
    }
}
