package com.anuraagpotdaar.unitrials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.databinding.ActivityDashboardBinding;
import com.anuraagpotdaar.unitrials.databinding.ActivityProfileAndSettingsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProfileAndSettings extends AppCompatActivity {

    private ActivityProfileAndSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileAndSettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.tvDoctorName.setText(getIntent().getStringExtra("Doctor_Name"));
        binding.tvPartiCount.setText(String.format("%s", getIntent().getStringExtra("parti_count")));


        binding.btnSettingsBack.setOnClickListener(view1 -> {
            finish();
        });

        binding.btnLogout.setOnClickListener(view2 -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}