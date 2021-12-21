package com.hifriend.Therapist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class schedule extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private schedule_adapter adapter;
    private List<consult_class> consult_classList;
    public String _name;
    FirebaseAuth auth;

    DatabaseReference refConsult,theraRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_thera,container,false);
        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        toolbar = (Toolbar) root.findViewById(R.id.app_bar);
        // compatActivity.getActionBar().setTitle("Your Schedule");
         compatActivity.getSupportActionBar().show();

        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        consult_classList = new ArrayList<>();
        adapter = new schedule_adapter(getContext(),consult_classList);
        recyclerView.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();

        theraRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").child(id);
        theraRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                _name = snapshot.child("full_name").getValue().toString();


                Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
                        .orderByChild("therapist_Name").equalTo(_name);

                query.addListenerForSingleValueEvent(valueEventListener);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return view;
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            consult_classList.clear();

            if(snapshot.exists()){
                for(DataSnapshot ds: snapshot.getChildren()){
                    consult_class consultClass = ds.getValue(consult_class.class);
                    consult_classList.add(consultClass);

                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater = getActivity().getMenuInflater();
//        inflater.inflate(R.menu.schedule_nav,menu);
//
//    }
}

