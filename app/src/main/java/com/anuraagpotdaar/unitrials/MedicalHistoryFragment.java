package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anuraagpotdaar.unitrials.databinding.FragmentMedicalHistoryBinding;
import com.anuraagpotdaar.unitrials.databinding.FragmentParticipantDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedicalHistoryFragment extends Fragment {

    private FragmentMedicalHistoryBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMedicalHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        DatabaseReference partiCount = FirebaseDatabase.getInstance().getReference("Patient List/"+getActivity().getIntent().getStringExtra("selected participant"));

        partiCount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.tvHistoryContents.setText(snapshot.child("medicalHistory").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}