package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.DashPartiDispAdapter;
import com.anuraagpotdaar.unitrials.HelperClasses.HealthDataModel;
import com.anuraagpotdaar.unitrials.HelperClasses.HealthDataReadingAdapter;
import com.anuraagpotdaar.unitrials.HelperClasses.MedsDispAdapter;
import com.anuraagpotdaar.unitrials.HelperClasses.MedsModel;
import com.anuraagpotdaar.unitrials.databinding.FragmentParticipantHelthInfoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParticipantHelthInfoFragment extends Fragment {

    private FragmentParticipantHelthInfoBinding binding;

    RecyclerView recyclerView ;
    DatabaseReference databaseReference;
    HealthDataReadingAdapter healthDataReadingAdapter;
    ArrayList<HealthDataModel> healthDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParticipantHelthInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        String selected = getActivity().getIntent().getStringExtra("selected participant");

        recyclerView = binding.rvReadingList;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        healthDataList= new ArrayList<>();


        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (binding.btnHeart.isChecked()) {
                displayData(selected,"Heart rate");
                healthDataReadingAdapter = new HealthDataReadingAdapter(getContext(),healthDataList);
                recyclerView.setAdapter(healthDataReadingAdapter);
            } else if (binding.btnOxy.isChecked()) {
                displayData(selected,"Oxygen");
                healthDataReadingAdapter = new HealthDataReadingAdapter(getContext(),healthDataList);
                recyclerView.setAdapter(healthDataReadingAdapter);
            } else if (binding.btnBP.isChecked()) {
                displayData(selected,"BP");
                healthDataReadingAdapter = new HealthDataReadingAdapter(getContext(),healthDataList);
                recyclerView.setAdapter(healthDataReadingAdapter);
            }
        });

        return binding.getRoot();
    }

    private void displayData(String selected, String selectedHealthData) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Patient List/"+selected+"/Health Data/"+ selectedHealthData);

        healthDataList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    HealthDataModel healthDataModel = new HealthDataModel();
                    healthDataModel.setDate(dataSnapshot.getKey());

                    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Patient List/"+ selected + "/Health Data/" +selectedHealthData);

                    dataRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                                healthDataModel.setValue(snapshot.child(healthDataModel.getDate()).getValue(String.class));
                            }
                            healthDataReadingAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    healthDataList.add(healthDataModel);
                }
                healthDataReadingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}