package com.hifriend.parents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hifriend.R;

import org.jetbrains.annotations.NotNull;

public class parent_main_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_homepage);


//        getSupportActionBar().setTitle("Therapist");
//        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Home_Page()).commit();

    }

//    @Override
//    public boolean onCreateOptionsMenu(@NonNull @NotNull Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search_menu,menu);
//        MenuItem item=menu.findItem(R.id.search);
//
//        SearchView searchView = (SearchView) item.getActionView();
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Therapist_parent.processSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Therapist_parent.processSearch(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment = new Home_Page();
                            break;

                        case R.id.therapist:
                            selectedFragment = new Therapist_parent();
                            break;

                        case R.id.session:
                            selectedFragment = new parent_sessions();
                            break;

                        case R.id.activ:
                            selectedFragment = new parent_activities();
                            break;

                        case R.id.schedule:
                            selectedFragment = new schedule_view_parent();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}