package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;



import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Page extends Fragment {
    DatabaseReference databaseReference;
    static TextView welcome;
    FirebaseAuth mUath;
    LinearLayout sliderDotspanel;
    Button howbtn,session,our_thera,what_is_autism;
    CircleImageView prof;
    String _IMAGE;
    ImageView prof_page;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();


        mUath = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        String id = mUath.getCurrentUser().getUid();


        prof_page = (ImageView) root.findViewById(R.id.profPage);
        prof_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),profilePage.class));
            }
        });
        prof = (CircleImageView) root.findViewById(R.id.profile);
//        prof.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        welcome = (TextView) root.findViewById(R.id.welcome);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //databaseReference.child(id);
                String name = snapshot.child(id).child("fullname").getValue().toString();
                welcome.setText("Hello,\n" + name);
                welcome.setTextSize(24);

                if(snapshot.child(id).child("imageUri").exists()){
                    _IMAGE = snapshot.child(id).child("imageUri").getValue(String.class);
//                    setImg.setImageURI(Uri.parse(img));
                    Glide.with(prof.getContext()).load(_IMAGE).into(prof);
                }
                else{
                    prof.setImageResource(R.drawable.iconregister);
                }

                if(!snapshot.child(id).child("imageUri").exists()){
                    prof.setImageResource(R.drawable.iconregister);
                }


                String img = snapshot.child(id).child("imageUri").getValue(String.class);

//                Glide.with(prof.getContext()).load(img).into(prof);

            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

//        ViewPager viewPager = root.findViewById(R.id.ViewPager);
//        sliderDotspanel = root.findViewById(R.id.SliderDots);
//
//        Adapter adapter = new Adapter(this.getContext());
//        viewPager.setAdapter(adapter);
//        new sliderDots().dots(adapter,this.getContext(),sliderDotspanel,viewPager);
//        welcome.setText("Welcome, "+parent.fullname);
//        welcome.setTextSize(16);


        howbtn = (Button) root.findViewById(R.id.howTobtn);

        howbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),how_to_start.class));
            }
        });

        session = (Button) root.findViewById(R.id.sessionbtn);
        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),our_session.class));
            }
        });

        our_thera = (Button) root.findViewById(R.id.our_thera_btn);
        our_thera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),our_therapists_view.class));
            }
        });

        what_is_autism = (Button) root.findViewById(R.id.button2);
        what_is_autism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),what_is_autism_view.class));
            }
        });

       return root;
    }
}
