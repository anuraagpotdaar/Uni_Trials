package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.HeartRateModel;
import com.anuraagpotdaar.unitrials.HelperClasses.HeartRateAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ParticipantHelthInfoFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView;
        DatabaseReference databaseReference;
        HeartRateAdapter heartRateAdapter;
        ArrayList<HeartRateModel> heartRateModels;
        String selected = getActivity().getIntent().getStringExtra("selected participant");
        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patient List/"+selected+"/Data/Heart Rate");
        return inflater.inflate(R.layout.fragment_participant_helth_info, container, false);

    }
}