package com.anuraagpotdaar.unitrials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.anuraagpotdaar.unitrials.HelperClasses.DashPartiDispAdapter;
import com.anuraagpotdaar.unitrials.HelperClasses.ParticipantModel;
import com.anuraagpotdaar.unitrials.databinding.ActivityDashboardBinding;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    DashPartiDispAdapter dashPartiDispAdapter;
    ArrayList<ParticipantModel> list;

    ArrayList<ParticipantModel> searchList = new ArrayList<>();
    ArrayList<ParticipantModel> priorityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.tvDashboardUsername.setText(getIntent().getStringExtra("Doctor_Name"));

        DatabaseReference partiCount = FirebaseDatabase.getInstance().getReference("Doctors/"+getIntent().getStringExtra("id"));

        partiCount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.tvParticipantCount.setText(String.format("%s participants are under your observation", snapshot.child("patients").getValue(int.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.ibSettings.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),ProfileAndSettings.class);
            startActivity(intent);
        });

        binding.btnAddParti.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(),ParticipantOnboardActivity.class);
            intent.putExtra("id",getIntent().getStringExtra("id"));
            intent.putExtra("participants",getIntent().getStringExtra("patients"));
            startActivity(intent);
        });

        recyclerView = binding.rvParticipantList;
        databaseReference = FirebaseDatabase.getInstance().getReference("Patient List");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();

        binding.etParticipantSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()){
                    if (binding.btnPriority.isChecked()) {
                        loadPriorityData();
                    }else if(binding.btnAll.isChecked()) {
                        dashPartiDispAdapter = new DashPartiDispAdapter(getApplicationContext(), list);
                        recyclerView.setAdapter(dashPartiDispAdapter);
                        dashPartiDispAdapter.notifyDataSetChanged();
                    }
                } else {
                    Search(editable.toString());
                }
            }
        });

        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (binding.btnPriority.isChecked()) {
                loadPriorityData();
            } else if(binding.btnAll.isChecked()) {
                dashPartiDispAdapter = new DashPartiDispAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(dashPartiDispAdapter);
                dashPartiDispAdapter.notifyDataSetChanged();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    ParticipantModel participantModel =dataSnapshot.getValue(ParticipantModel.class);
                    list.add(participantModel);
                    loadPriorityData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadPriorityData() {
        priorityList.clear();
        for(ParticipantModel currentList :list) {
            if(currentList.getPriority() == 1 || currentList.getPriority() == 2) {
                priorityList.add(currentList);
            }
        }

        priorityList.sort(Comparator.comparingInt(ParticipantModel::getPriority));

        recyclerView.setAdapter(new DashPartiDispAdapter(this, priorityList));
    }

    private void Search(String searchText) {
        searchList.clear();
        if (binding.btnPriority.isChecked()) {
            for(ParticipantModel currentList :priorityList) {
                if(currentList.getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                    searchList.add(currentList);
                }
            }
        } else if(binding.btnAll.isChecked()) {
            for(ParticipantModel currentList :list) {
                if(currentList.getName().toLowerCase().startsWith(searchText.toLowerCase())) {
                    searchList.add(currentList);
                }
            }
        }

        recyclerView.setAdapter(new DashPartiDispAdapter(this, searchList));
    }
}