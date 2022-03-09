package com.anuraagpotdaar.unitrials;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.ParticipantModel;
import com.anuraagpotdaar.unitrials.databinding.FragmentParticipantDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParticipantDetailsFragment extends Fragment {

    private FragmentParticipantDetailsBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParticipantDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String selected = getActivity().getIntent().getStringExtra("selected participant");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Patient List/"+selected);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.tvPartiName.setText(snapshot.child("name").getValue(String.class));

                String quickInfo = snapshot.child("age").getValue(String.class) + " years old " + snapshot.child("gender").getValue(String.class);
                binding.tvPartiQuickInfo.setText(quickInfo);

                Integer priority = snapshot.child("priority").getValue(Integer.class);

                switch (priority) {
                    case 1:
                        binding.tvStatus.setText("Status: Needs quick attention");
                        break;
                    case 2:
                        binding.tvStatus.setText("Status: Attention may be required");
                        break;
                    case 3:
                        binding.tvStatus.setText("Status: All okay");
                        break;
                }

                String currentHealthDetails = snapshot.child("heart").child("current").getValue(String.class) + " BPH\n" +
                        snapshot.child("oxygen").child("current").getValue(String.class) + " %\n" +
                        snapshot.child("bp").child("current").getValue(String.class) + " mm Hg\n";

                binding.tvPartiHealthData2.setText(currentHealthDetails);

                String personalDetails = snapshot.child("phone").getValue(String.class) + "\n" +
                        snapshot.child("email").getValue(String.class) + "\n" +
                        snapshot.child("address").getValue(String.class);

                binding.tvPartiDetails.setText(personalDetails);

                binding.btnMedHistory.setOnClickListener(view1 -> {
                    Navigation.findNavController(view).navigate(R.id.action_open_medicalHistory);
                });
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnCall.setOnClickListener(view12 -> {
            if (ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + selected)));
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
            }
        });



        return view;
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}