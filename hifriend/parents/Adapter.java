package com.hifriend.parents;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hifriend.R;

public class Adapter extends PagerAdapter {
    private Context ctx;
    private LayoutInflater layoutInflater;
    private int[] imageArray = new int[]{R.drawable.home_image33,R.drawable.home_image1,R.drawable.home_image2};
    private Button[] buttons = new Button[3];


   // private String[] texts = new String[]{"We provide education to Autistic children.","We care about autistic children.","We support autistic children and cater for."};

    Adapter(Context context) {ctx=context;}
    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject( View view,Object object ) {
        return view == object; //true if view is equal to object
    }

    //creates an img view which contains an image as well as the position of the image

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//        for(int i=0;i<=3;i++){
//            Button bt1 = new Button(ctx.getApplicationContext());
//
//        }

//        Button bt1 = new Button(ctx.getApplicationContext());
//        Button bt2 = new Button(ctx.getApplicationContext());
//        Button bt3 = new Button(ctx.getApplicationContext());

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom, null);
        ImageView img;
       // img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Button bt = view.findViewById(R.id.button);

        buttons[0] = bt;
        buttons[0].setTag(1);
        buttons[1] = bt;
        buttons[1].setTag(2);
        buttons[2] = bt;
        buttons[2].setTag(3);

        //ConstraintLayout l = (ConstraintLayout) layoutInflater.inflate(R.layout.custom,null);


//        view.getTag(1);
//        view.getTag(2);
//        view.getTag(3);

//         l.addView(bt1);
//         l.addView(bt2);
//         l.addView(bt3);


        img = (ImageView) view.findViewById(R.id.images);
        img.setImageResource(imageArray[position]);

        bt.findViewWithTag(buttons[position]);

        bt.setText("Learn more");
//        bt.setTextSize(12);
//
//        bt2.findViewWithTag(buttons[position]);
//
//        bt2.setText("Learn more");
//        bt2.setTextSize(12);
//
//        bt3.findViewWithTag(buttons[position]);
//
//        bt3.setText("Learn more");
//        bt3.setTextSize(12);

        //bt.setBackgroundColor(34);



        ViewPager vp = (ViewPager) container;

        vp.addView(view,0);


        View.OnClickListener [] listeners = new View.OnClickListener[4];

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  // bt.setOnClickListener(listeners[i]);
                    //buttons[0].setTag(1);
                    if(bt == buttons[0].findViewWithTag(1)){
                        ctx.startActivity(new Intent(ctx.getApplicationContext(),forgotPass.class));

//
       }

//                if(((Integer)v.getTag())==1){
//                    ctx.startActivity(new Intent(ctx.getApplicationContext(),forgotPass.class));
//                }
            }
        });

//        for(int i=0;i<=buttons.length;i++){
//            bt.setOnClickListener(listeners[i]);
//            if(((Integer)){
//                ctx.startActivity(new Intent(ctx.getApplicationContext(),forgotPass.class));
//            }
//
//        }


//        bt.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                if(buttons[position].equals(0)){
//                    ctx.startActivity(new Intent(ctx.getApplicationContext(),forgotPass.class));
//
//                }
//
//
//
//            }
////
////
//            });
//        buttons[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//            }
//        });

        //return super.instantiateItem(container, position);
//        layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.activity_main,null);
//        ImageView imgview = new ImageView(ctx);
//        //TextView txt = new TextView(ctx);
//        imgview.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        //TextView textView = findViewById(R.id.textone);
//        //TextView textView = txt.findViewById(R.id.textone);
//
//        imgview.setImageResource(imageArray[position]);
//        //textView.setText(texts[position]);
//        container.addView(imgview,0);
//
//
        return view;

    }
    //container will remove the imageview of the object

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
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
