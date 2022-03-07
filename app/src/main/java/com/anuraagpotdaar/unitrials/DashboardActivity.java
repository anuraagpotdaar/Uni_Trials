package com.anuraagpotdaar.unitrials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.anuraagpotdaar.unitrials.HelperClasses.DashPartiDispAdapter;
import com.anuraagpotdaar.unitrials.HelperClasses.ParticipantModel;
import com.anuraagpotdaar.unitrials.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    DashPartiDispAdapter dashPartiDispAdapter;
    ArrayList<ParticipantModel> list;

    ArrayList<ParticipantModel> searchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recyclerView = binding.rvParticipantList;
        databaseReference = FirebaseDatabase.getInstance().getReference("Patient List");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list= new ArrayList<>();
        dashPartiDispAdapter = new DashPartiDispAdapter(this, list);
        recyclerView.setAdapter(dashPartiDispAdapter);

        binding.etParticipantSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                searchList.clear();
                if (editable.toString().isEmpty()){
                    recyclerView.setAdapter(dashPartiDispAdapter);
                    dashPartiDispAdapter.notifyDataSetChanged();
                } else {
                    Search(editable.toString());
                }
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    ParticipantModel participantModel =dataSnapshot.getValue(ParticipantModel.class);
                    list.add(participantModel);
                }
                dashPartiDispAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Search(String searchText) {
        for(ParticipantModel currentList :list) {
            if(currentList.getName().startsWith(searchText)) {
                searchList.add(currentList);
            }
        }
        recyclerView.setAdapter(new DashPartiDispAdapter(this, searchList));
        dashPartiDispAdapter.notifyDataSetChanged();
    }
}