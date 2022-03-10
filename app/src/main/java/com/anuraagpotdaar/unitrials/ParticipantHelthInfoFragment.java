package com.anuraagpotdaar.unitrials;

import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.HeartRate;
import com.anuraagpotdaar.unitrials.HelperClasses.HeartRateAdapter;
import com.anuraagpotdaar.unitrials.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParticipantHelthInfoFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    HeartRateAdapter heartRateAdapter;
    ArrayList<HeartRate> heartRates;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String selected = getActivity().getIntent().getStringExtra("selected participant");

        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_participant_helth_info, container, false);
    }
}