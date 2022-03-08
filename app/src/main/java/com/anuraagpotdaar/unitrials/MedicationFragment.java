package com.anuraagpotdaar.unitrials;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.anuraagpotdaar.unitrials.databinding.FragmentMedicationBinding;

public class MedicationFragment extends Fragment {

    private FragmentMedicationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMedicationBinding.inflate(inflater, container, false);

        binding.btnAddMeds.setOnClickListener(
                view -> {
                    Navigation.findNavController(view).navigate(R.id.action_add_medication);
                });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}