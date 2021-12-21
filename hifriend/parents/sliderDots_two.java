package com.hifriend.parents;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.hifriend.R;

public class sliderDots_two {


    private int dotscount;
    private ImageView[] dots;
    ViewPager viewPager;


    public void dots(pagerAdapter adapt, Context act, LinearLayout sliderDotpanel, ViewPager viewPager){

            dotscount = adapt.getCount();
            dots = new ImageView[dotscount];

            for(int i = 0; i < dotscount;i++){
                dots[i] = new ImageView(act);
                dots[i].setImageDrawable(ContextCompat.getDrawable(act.getApplicationContext(), R.drawable.nonactive_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8,0,8,0);
                sliderDotpanel.addView(dots[i],params);

            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(act.getApplicationContext(),R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                        for(int i = 0;i<dotscount;i++){
                            dots[i].setImageDrawable(ContextCompat.getDrawable(act.getApplicationContext(),R.drawable.nonactive_dot));

                        }
                        dots[position].setImageDrawable(ContextCompat.getDrawable(act.getApplicationContext(),R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }


    }




