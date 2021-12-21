package com.hifriend.parents;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.hifriend.R;

public class our_session extends AppCompatActivity {
    LinearLayout sliderDotspanel;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_session);

        ViewPager viewPager = findViewById(R.id.ViewPager);
        sliderDotspanel = findViewById(R.id.SliderDots);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pageAdapter_one adapter = new pageAdapter_one(this);
        viewPager.setAdapter(adapter);
        new sliderDots_three().dots(adapter,this,sliderDotspanel,viewPager);


    }
}
