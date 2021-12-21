package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.hifriend.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class therapist_parent_consult extends Fragment {
    String name,  email,  expertise, phone, image,date,time;
    static Button consult;

    public therapist_parent_consult(){

    }
    public therapist_parent_consult(String name, String email, String expertise, String phone, String image,String date,String time){
        this.name=name;
        this.email=email;
        this.expertise=expertise;
        this.phone=phone;
        this.image=image;
        this.date=date;
        this.time=time;
//

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.consult,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();

        CircleImageView image_t = view.findViewById(R.id.img1);
        TextView name_t = view.findViewById(R.id.name_text);
        TextView email_t = view.findViewById(R.id.email_text);
        TextView expert_t = view.findViewById(R.id.expertise_txt);
        TextView phone_t = view.findViewById(R.id.phone_txt);
        consult = view.findViewById(R.id.consultbtn);

        name_t.setText(name);
        email_t.setText(email);
        expert_t.setText(expertise);
        phone_t.setText(phone);
        Glide.with(getContext()).load(image).into(image_t);

        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new book_consultation(name,  email,  expertise, phone, image, date,time))
                        .addToBackStack(null).commit();

            }
        });

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Therapist_parent()).addToBackStack(null).commit();
    }
}
