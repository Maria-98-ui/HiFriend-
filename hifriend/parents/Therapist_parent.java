package com.hifriend.parents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.hifriend.R;

import org.jetbrains.annotations.NotNull;

public class Therapist_parent extends Fragment {

    static RecyclerView recyclerView;
    static adapter_thera myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_therap,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().show();

        recyclerView = view.findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<modelClass_consult> options =
                new FirebaseRecyclerOptions.Builder<modelClass_consult>()
                        .setQuery(FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Therapist"),modelClass_consult.class)
                        .build();


        myAdapter = new adapter_thera(options);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
//
//        inflater.inflate(R.menu.search_menu,menu);
//        MenuItem item=menu.findItem(R.id.search);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                processSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                processSearch(newText);
//                return false;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    static void processSearch(String q){

        FirebaseRecyclerOptions<modelClass_consult> options =
                new FirebaseRecyclerOptions.Builder<modelClass_consult>()
                        .setQuery(FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Therapist").orderByChild("full_name").startAt(q).endAt(q+ "\uf8ff"),modelClass_consult.class)
                        .build();

        myAdapter = new adapter_thera(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);


    }
}
