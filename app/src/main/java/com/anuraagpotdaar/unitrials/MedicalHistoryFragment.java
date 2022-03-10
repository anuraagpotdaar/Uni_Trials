package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

import java.util.Calendar;

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
                String dob =  snapshot.child("dob").getValue(String.class);
                int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(dob.substring(dob.length() - 4));

                String quickInfo = "of " + age + " years old " + snapshot.child("name").getValue(String.class);
                binding.tvSubHeadingHistory.setText(quickInfo);

                binding.tvHistoryContents.setText(snapshot.child("medicalHistory").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnHistoryBack.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_medication_to_participantDetails);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}