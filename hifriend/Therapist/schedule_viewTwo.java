package com.hifriend.Therapist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;
import com.hifriend.parents.adapter_thera;
import com.hifriend.parents.consult_class;
import com.hifriend.parents.modelClass_consult;

import org.jetbrains.annotations.NotNull;

public class schedule_viewTwo extends Fragment {
    static RecyclerView recyclerView;
    DatabaseReference consultRef, theraRef;
    private FirebaseAuth mAuth;
    static schedule_view_adapt myAdapter;
    String _NAME;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_thera,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().show();

        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        consultRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");
        theraRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist")
                .child(id);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        theraRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                _NAME = snapshot.child("full_name").getValue().toString();
                Query firebaseSearchQuery = consultRef.orderByChild("therapist_Name").equalTo(_NAME);
                FirebaseRecyclerOptions<consult_class> options =
                        new FirebaseRecyclerOptions.Builder<consult_class>()
                                .setQuery(firebaseSearchQuery,consult_class.class)
                                .build();


                myAdapter = new schedule_view_adapt(options);
                recyclerView.setAdapter(myAdapter);
                myAdapter.startListening();


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

}
