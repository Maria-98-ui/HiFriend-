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

public class pagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private int[] imageArr = new int[]{R.drawable.reg,R.drawable.thera,R.drawable.consult,R.drawable.sched,R.drawable.attend};
    private String[] texts = new String[]{"Register","Choose a therapist","Consult with the therapist","View schedule once consulted","Attend sessions"};

    pagerAdapter(Context ctx){context=ctx;}
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
        View view = layoutInflater.inflate(R.layout.how_to_start_custompage, null);

        ImageView img;
        TextView text;

        img = (ImageView) view.findViewById(R.id.img_how_to);
        text = (TextView) view.findViewById(R.id.intruc);

        img.setImageResource(imageArr[position]);
        text.setText(texts[position]);

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
