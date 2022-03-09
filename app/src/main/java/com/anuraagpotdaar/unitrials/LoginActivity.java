package com.anuraagpotdaar.unitrials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText edt_username,edt_password;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String doctor = getIntent().getStringExtra("id");

        edt_username = findViewById(R.id.etUsername);
        edt_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(view -> isUser());
    }

    private void isUser() {
        String userEnterdUsername,userEnteredPassword;

        userEnterdUsername = edt_username.getEditableText().toString();
        userEnteredPassword = edt_password.getEditableText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");
        Query checkUser = reference.orderByChild("username").equalTo(userEnterdUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    edt_username.setError(null);
                    String passwordFromDB = snapshot.child(userEnterdUsername).child("password").getValue(String.class).trim();
                    String nameFromDatabase = snapshot.child(userEnterdUsername).child("name").getValue(String.class).trim();
                    String retentionRateFromDB = snapshot.child(userEnterdUsername).child("retension").getValue(String.class).trim();

                    if (passwordFromDB.equals(userEnteredPassword)){
                        edt_password.setError(null);
                        String patients = snapshot.child(userEnterdUsername).child("patients").getValue(int.class).toString();

                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);

                        intent.putExtra("Doctor_Name",nameFromDatabase);
                        intent.putExtra("Retention_Rate",retentionRateFromDB);
                        intent.putExtra("patients",patients);
                        intent.putExtra("id",userEnterdUsername);

                        startActivity(intent);
                        finish();
                    }else {
                        edt_password.setError("Wrong Password");
                        edt_password.requestFocus();
                    }
                  }else{
                    edt_username.setError("No Such A User");
                    edt_username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}