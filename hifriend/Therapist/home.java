package com.hifriend.Therapist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class home extends Fragment {

    DatabaseReference databaseReference;
    FirebaseAuth auth;
    TextView wel;
    static CircleImageView profile;
    ImageView prof;
    public static String _NAME;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_thera, container, false);

        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        toolbar = (Toolbar) root.findViewById(R.id.app_bar);
        compatActivity.getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        String id = auth.getCurrentUser().getUid();


        wel = (TextView) root.findViewById(R.id.welcome_thera);
        profile = (CircleImageView) root.findViewById(R.id.profile_image_home);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                _NAME = Objects.requireNonNull(snapshot.child(id).child("full_name").getValue()).toString();
                wel.setText("Hello,\n" +_NAME);
                //wel.setTextSize(16);

                String img = snapshot.child(id).child("image").getValue(String.class);

                Glide.with(profile.getContext()).load(img).into(profile);
                //profile.setImageURI(Uri.parse(img));

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        prof = (ImageView) root.findViewById(R.id.prof_page_thera);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),profile_page.class));
            }
        });

//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(),profile_page.class));
//            }
//        });

        return root;
    }
}
