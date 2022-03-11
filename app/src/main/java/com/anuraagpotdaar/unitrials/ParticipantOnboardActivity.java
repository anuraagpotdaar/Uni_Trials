package com.anuraagpotdaar.unitrials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.ParticipantModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParticipantOnboardActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference partiRef, docRef;
    EditText edt_name,edt_phone,edt_email,edt_address,edt_dob,edt_medicalHistory,edt_gender;
    TextView btn_cancel,btn_done;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_onboard);

        count = Integer.parseInt(getIntent().getStringExtra("participants"));

        edt_name=findViewById(R.id.text_name);
        edt_phone=findViewById(R.id.text_phone_number);
        edt_email=findViewById(R.id.text_email);
        edt_address=findViewById(R.id.text_address);
        edt_dob=findViewById(R.id.text_dob);
        edt_medicalHistory=findViewById(R.id.text_medical_history);
        edt_gender=findViewById(R.id.text_gender);

        btn_cancel = findViewById(R.id.txt_btn_cancel);
        btn_done = findViewById(R.id.txt_btn_done);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                partiRef = rootNode.getReference("Patient List");

                String name = edt_name.getEditableText().toString();
                String phone = edt_phone.getEditableText().toString();
                String email = edt_email.getEditableText().toString();
                String address = edt_address.getEditableText().toString();
                String dob = edt_dob.getEditableText().toString();
                String medicalHistory = edt_medicalHistory.getEditableText().toString();
                String gender = edt_gender.getEditableText().toString();

                if (name.matches("")){
                    edt_name.setError("Can't Empty");
                    edt_name.requestFocus();
                }else if (phone.matches("")){
                    edt_phone.setError("Can't Be Empty");
                    edt_phone.requestFocus();
                }else if (email.matches("")){
                    edt_email.setError("Can't Be Empty");
                    edt_email.requestFocus();
                }else if (address.matches("")){
                    edt_address.setError("Can't Be Empty");
                    edt_address.requestFocus();
                }else if (dob.matches("")){
                    edt_dob.setError("Can't Be Empty");
                    edt_dob.requestFocus();
                }else if (medicalHistory.matches("")){
                    edt_medicalHistory.setError("Can't Be Empty");
                    edt_medicalHistory.requestFocus();
                }else if (gender.matches("")){
                    edt_gender.setError("Can't Be Empty");
                    edt_gender.requestFocus();
                }else{
                    count = count+1;

                    docRef = FirebaseDatabase.getInstance().getReference("Doctors/"+getIntent().getStringExtra("id"));
                    docRef.child("patients").setValue(count);

                    ParticipantModel participantModel = new ParticipantModel(name,phone,email,address,gender, dob,medicalHistory,3);
                    partiRef.child(phone).setValue(participantModel);

                    Toast.makeText(ParticipantOnboardActivity.this, "Patient added successfully", Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        });
    }
}