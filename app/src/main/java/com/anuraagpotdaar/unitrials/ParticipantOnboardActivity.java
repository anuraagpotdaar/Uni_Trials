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
    DatabaseReference reference;
    EditText edt_name,edt_phone,edt_email,edt_address,edt_dob,edt_medicalHistory,edt_gender;
    TextView btn_cancel,btn_done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_onboard);

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
                reference = rootNode.getReference("Patient List");

                String name = edt_name.getEditableText().toString();
                String phone = edt_phone.getEditableText().toString();
                String email = edt_email.getEditableText().toString();
                String address = edt_address.getEditableText().toString();
                String dob = edt_dob.getEditableText().toString();
                String medicalHistory = edt_medicalHistory.getEditableText().toString();
                String gender = edt_gender.getEditableText().toString();


                ParticipantModel participantModel = new ParticipantModel(name,phone,email,address,gender, dob,medicalHistory,3);
                reference.child(phone).setValue(participantModel);

                Toast.makeText(ParticipantOnboardActivity.this, "Patient added successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(intent);

            }
        });




    }
}