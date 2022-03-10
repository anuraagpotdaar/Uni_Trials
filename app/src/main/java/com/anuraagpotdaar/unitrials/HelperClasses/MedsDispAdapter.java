package com.anuraagpotdaar.unitrials.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anuraagpotdaar.unitrials.ParticipantDataManagementActivity;
import com.anuraagpotdaar.unitrials.R;

import java.util.ArrayList;

public class MedsDispAdapter extends RecyclerView.Adapter<MedsDispAdapter.MedsViewHolder> {

    Context context;

    ArrayList<MedsModel> list;

    public MedsDispAdapter(Context context, ArrayList<MedsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_meds,parent,false);
        return new MedsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MedsViewHolder holder, int position) {
        MedsModel meds = list.get(position);
        holder.name.setText(String.format("%s", meds.getMedName()));

        if(meds.getMorning() != null ) {
            holder.morning.setText(String.format("%s-", meds.getMorning()));
        }
        if(meds.getAfternoon() != null ) {
            holder.afternoon.setText(String.format("%s-", meds.getAfternoon()));
        }
        if(meds.getEvening() != null ) {
            holder.evening.setText(String.format("%s-", meds.getEvening()));
        }
        if(meds.getNight() != null ) {
            holder.night.setText(String.format("%s", meds.getNight()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MedsViewHolder extends RecyclerView.ViewHolder {

        TextView name, morning, afternoon , evening, night;
        ImageView delete;

        public MedsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvMedName);
            morning = itemView.findViewById(R.id.tvMedTime1);
            afternoon = itemView.findViewById(R.id.tvMedTime2);
            evening = itemView.findViewById(R.id.tvMedTime3);
            night = itemView.findViewById(R.id.tvMedTime4);

            delete = itemView.findViewById(R.id.ivDelMed);

            delete.setOnClickListener(view -> {
                // TODO: 08/03/2022  
            });
        }
    }
}
