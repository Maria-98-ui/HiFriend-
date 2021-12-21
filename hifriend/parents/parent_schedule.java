package com.hifriend.parents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.hifriend.Therapist.schedule_adapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class parent_schedule extends Fragment {
    RecyclerView recyclerView;
    private sched_adapter adapter;
    private List<consult_class> consult_classList;
    public String _name;
    FirebaseAuth auth;

    DatabaseReference ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sched,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();

        recyclerView = view.findViewById(R.id.parent_recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        consult_classList = new ArrayList<>();
        adapter = new sched_adapter(getContext(),consult_classList);
        recyclerView.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();


        Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
                .orderByChild("parent_id").equalTo(id);

        query.addListenerForSingleValueEvent(valueEventListener);



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

}
