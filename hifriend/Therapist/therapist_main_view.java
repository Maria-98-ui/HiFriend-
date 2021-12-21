package com.hifriend.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hifriend.R;
import com.hifriend.parents.therapist_parent_consult;


public class therapist_main_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist);
        getSupportActionBar().setTitle("Your Schedule");
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_thera);
        bottomNavigationView.setOnNavigationItemSelectedListener(navLis);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_thera,new home()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.schedule_nav,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item2:
                startActivity(new Intent(getApplicationContext(),availability.class));
                return true;
            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_thera,
                        new view_availability())
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLis = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home_thera:
                    selectedFragment = new home();
                    break;
                case R.id.parent_thera:
                    selectedFragment = new parents();
                    break;
                case R.id.session_thera:
                    selectedFragment = new sessions();
                    break;
                case R.id.schedule_thera:
                    selectedFragment = new schedule_view();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_thera,
                    selectedFragment).commit();

            return true;
        }
    };
}
