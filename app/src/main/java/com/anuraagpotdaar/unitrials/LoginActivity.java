package com.anuraagpotdaar.unitrials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText edt_username,edt_password;
    Button btn_login,btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_username = findViewById(R.id.etUsername);
        edt_password = findViewById(R.id.etPassword);
        btn_login = findViewById(R.id.btn_login);
        btn_cancel =findViewById(R.id.btn_cancel);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
            }
        });

    }

    private void isUser() {
        String userEnterdUsername,userEnteredPassword;

        userEnterdUsername = edt_username.getEditableText().toString();
        userEnteredPassword = edt_password.getEditableText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");




    }
}