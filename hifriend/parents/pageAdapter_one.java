package com.hifriend.parents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hifriend.R;

import org.jetbrains.annotations.NotNull;

public class pageAdapter_one extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] imageArr = new int[]{R.drawable.aba,R.drawable.rdi,R.drawable.speech_therapy};
    private String desc[] = new String[]{"Applied behavioral analysis (ABA) is a type of therapy that can improve social, " +
            "communication, and learning skills through reinforcement strategies. " +
            "Many experts consider ABA to be the gold-standard treatment for children with autism spectrum disorder (ASD) " +
            "or other developmental conditions.","Relationship Development Intervention (RDI) is a family-based, " +
            "behavioral treatment which addresses the core symptoms of autism. " +
            "It focuses on building social and emotional skills. Parents are trained as the primary therapist in most RDI programs.",
            "Speech-language therapy addresses challenges with language and communication. " +
                    "It can help people with autism improve their verbal, nonverbal, and social communication. " +
                    "The overall goal is to help the person communicate in more useful and functional ways."};
    private String[] texts = new String[]{"ABA Therapy","RDI Therapy","Speech Therapy"};

    pageAdapter_one(Context ctx){context=ctx;}
    @Override
    public int getCount() {
        return imageArr.length;
    }

    @Override
    public boolean isViewFromObject(View view,  Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.our_session_custom, null);

        ImageView img;
        TextView text1, text2;

        img = (ImageView) view.findViewById(R.id.thera_images);
        text1 = (TextView) view.findViewById(R.id.name_of_thera);
        text2 = (TextView) view.findViewById(R.id.descrip);

        img.setImageResource(imageArr[position]);
        text1.setText(texts[position]);
        text2.setText(desc[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);


        return view;



    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        //container.removeView((view) object);
//        ImageView view = (ImageView) object;
//        container.removeView(view);

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

        //container.removeView((TextView) object);
    }


}


