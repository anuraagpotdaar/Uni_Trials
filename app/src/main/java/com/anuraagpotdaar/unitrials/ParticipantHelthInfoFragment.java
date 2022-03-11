package com.anuraagpotdaar.unitrials;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anuraagpotdaar.unitrials.HelperClasses.HeartRateModel;
import com.anuraagpotdaar.unitrials.HelperClasses.HeartRateAdapter;
import com.anuraagpotdaar.unitrials.databinding.FragmentParticipantDetailsBinding;
import com.anuraagpotdaar.unitrials.databinding.FragmentParticipantHelthInfoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParticipantHelthInfoFragment extends Fragment {
    private FragmentParticipantHelthInfoBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParticipantHelthInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        RecyclerView recyclerView ;
        DatabaseReference databaseReference;
        HeartRateAdapter heartRateAdapter;
        ArrayList<HeartRateModel> list;



        String selected = getActivity().getIntent().getStringExtra("selected participant");

        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patient List/"+selected+"/Heart Rate");
        binding.tvHRUserName.setText("Ali");
       // recyclerView=binding.rvHRList;
       //  recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list= new ArrayList<>();
        heartRateAdapter = new HeartRateAdapter(getContext(),list);
        //recyclerView.setAdapter(heartRateAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    HeartRateModel heartRateModel = new HeartRateModel();
                    String  demo = snapshot.child("Date").toString();
                    Toast.makeText(getContext(), demo, Toast.LENGTH_SHORT).show();//list.add(heartRateModel);
                }
                heartRateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return inflater.inflate(R.layout.fragment_participant_helth_info, container, false);

    }

}