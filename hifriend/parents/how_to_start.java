package com.hifriend.parents;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hifriend.R;

public class how_to_start extends AppCompatActivity {

    LinearLayout sliderDotspanel;
    FloatingActionButton home;
    Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_start_page);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ViewPager viewPager = findViewById(R.id.ViewPager);
        sliderDotspanel = findViewById(R.id.SliderDots);

        pagerAdapter adapter = new pagerAdapter(this);
        viewPager.setAdapter(adapter);
        new sliderDots_two().dots(adapter,this,sliderDotspanel,viewPager);



//        home = (FloatingActionButton) findViewById(R.id.backhome);
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



    }
}
