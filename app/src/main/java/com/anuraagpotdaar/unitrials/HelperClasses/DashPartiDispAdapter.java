package com.anuraagpotdaar.unitrials.HelperClasses;

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
import android.content.Context;

import java.util.ArrayList;

public class DashPartiDispAdapter extends RecyclerView.Adapter<DashPartiDispAdapter.DpdViewHolder> {

    Context context;

    ArrayList<ParticipantModel> list;

    String phone;

    public DashPartiDispAdapter(Context context, ArrayList<ParticipantModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DpdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_participants,parent,false);
        return new DpdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DpdViewHolder holder, int position) {
        ParticipantModel parti = list.get(position);
        holder.name.setText(parti.name);
        holder.ageGender.setText(parti.gender);
        phone = parti.phone;
        switch (parti.priority) {
            case 1:
                holder.priority.setImageResource(R.drawable.ic_high);
                break;
            case 2:
                holder.priority.setImageResource(R.drawable.ic_medium);
                break;
            case 3:
                holder.priority.setImageResource(R.drawable.ic_all_ok);
                break;
        }

        boolean isExpanded = list.get(position).isExpanded();
        holder.expandable.setVisibility(isExpanded ? View.VISIBLE :View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DpdViewHolder extends RecyclerView.ViewHolder {

        TextView name, ageGender, details , btnMore;
        ImageView priority;
        ConstraintLayout expandable;
        CardView card;
        public DpdViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cvParti);

            name = itemView.findViewById(R.id.tvSearchParticipantName);
            ageGender = itemView.findViewById(R.id.tvSearchParticipantAgeGender);
            priority = itemView.findViewById(R.id.ivSatusSeverity);
            details = itemView.findViewById(R.id.tvPartiHealthData);
            btnMore = itemView.findViewById(R.id.btnSeeDetails);


            expandable = itemView.findViewById(R.id.expandable);

            card.setOnClickListener(view -> {
                ParticipantModel parti = list.get(getBindingAdapterPosition());
                parti.setExpanded(!parti.isExpanded());
                notifyItemChanged(getAbsoluteAdapterPosition());
            });

            btnMore.setOnClickListener(view -> {
                Intent myIntent = new Intent(context, ParticipantDataManagementActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("selected participant", phone);
                context.startActivity(myIntent);
            });
        }
    }
}
