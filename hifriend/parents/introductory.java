package com.hifriend.parents;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.hifriend.R;



public class introductory extends AppCompatActivity {
    ImageView logo,img;
    LottieAnimationView lottieAnimationView;

    private static final int Num_pages =3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introductory);
       // getSupportActionBar().hide();


        img = findViewById(R.id.bg);
        logo = findViewById(R.id.logo);
        lottieAnimationView= findViewById(R.id.lottie);


        viewPager = findViewById(R.id.liquid);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        anim = AnimationUtils.loadAnimation(this,R.anim.o_b_anim);
        viewPager.startAnimation(anim);


        img.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {



        public ScreenSlidePagerAdapter(FragmentManager fm) {
           super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    OnboardingFragment1 tab1 = new OnboardingFragment1();
                    return tab1;
                case 1:
                    OnboardingFragment2 tab2 = new OnboardingFragment2();
                    return tab2;
                case 2:
                    OnboardingFragment3 tab3 = new OnboardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return Num_pages;
        }
    }
}