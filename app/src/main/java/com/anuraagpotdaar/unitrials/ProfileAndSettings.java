package com.anuraagpotdaar.unitrials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProfileAndSettings extends AppCompatActivity {

    TextView btn_back;
    Button btn_logOut;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_settings);

        btn_back = findViewById(R.id.btnSettingsBack);
        btn_logOut = findViewById(R.id.btn_logout);

        btn_back.setOnClickListener(view -> {
            finish();
        });

        btn_logOut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }
}