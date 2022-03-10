package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anuraagpotdaar.unitrials.databinding.FragmentAddMedicationBinding;
import com.anuraagpotdaar.unitrials.databinding.FragmentMedicationBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedicationFragment extends BottomSheetDialogFragment {

    private FragmentAddMedicationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMedicationBinding.inflate(inflater, container, false);

        String selected = getActivity().getIntent().getStringExtra("selected participant");

        DatabaseReference medsReference = FirebaseDatabase.getInstance().getReference("Patient List/"+selected+"/meds");

        binding.btnMedDone.setOnClickListener(
                view -> {
                    if (!binding.etMorning.getText().toString().isEmpty()) {
                        medsReference.child(binding.etMedName.getText().toString()).child("Morning").setValue(binding.etMorning.getText().toString());
                    }
                    if (!binding.etAfternoon.getText().toString().isEmpty()) {
                        medsReference.child(binding.etMedName.getText().toString()).child("Afternoon").setValue(binding.etAfternoon.getText().toString());
                    }
                    if (!binding.etEvening.getText().toString().isEmpty()) {
                        medsReference.child(binding.etMedName.getText().toString()).child("Evening").setValue(binding.etEvening.getText().toString());
                    }
                    if (!binding.etNight.getText().toString().isEmpty()) {
                        medsReference.child(binding.etMedName.getText().toString()).child("Night").setValue(binding.etNight.getText().toString());
                    }
                    dismiss();
                });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}