package com.anuraagpotdaar.unitrials.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anuraagpotdaar.unitrials.R;

import java.util.ArrayList;

public class HeartRateAdapter extends RecyclerView.Adapter<HeartRateAdapter.HRViewHolder> {

    Context context;

    ArrayList<HeartRateModel> list;

    public HeartRateAdapter(Context context, ArrayList<HeartRateModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.layout_heartrate,parent,false);
        return new HRViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HRViewHolder holder, int position) {
        HeartRateModel heartRateModel = list.get(position);
        holder.date.setText(heartRateModel.date);
        holder.value.setText(heartRateModel.value);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HRViewHolder extends RecyclerView.ViewHolder {

        TextView date,value;
        CardView card;
        public HRViewHolder (@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cvHeart);

            value = itemView.findViewById(R.id.text_value);
            date = itemView.findViewById(R.id.text_date);

            card.setOnClickListener(view -> {
                HeartRateModel parti = list.get(getBindingAdapterPosition());
                notifyItemChanged(getAbsoluteAdapterPosition());
            });

        }
    }
}
