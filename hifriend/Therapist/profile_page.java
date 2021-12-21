package com.hifriend.Therapist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;



import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_page extends AppCompatActivity {
    CircleImageView setImg;
    TextView name, email;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ImageView logout,personal_data;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.therapist_profile_page);

        setImg = (CircleImageView) findViewById(R.id.profile_image_t);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email_t_d);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        String id = auth.getCurrentUser().getUid();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String namee = Objects.requireNonNull(snapshot.child(id).child("full_name").getValue()).toString();
                name.setText(namee);
                //wel.setTextSize(16);

                String emaill = Objects.requireNonNull(snapshot.child(id).child("email").getValue()).toString();
                email.setText(emaill);

                String img = snapshot.child(id).child("image").getValue(String.class);
                setImg.setImageURI(Uri.parse(img));

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        logout = (ImageView) findViewById(R.id.log_out);
        personal_data = (ImageView) findViewById(R.id.personal_next);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        personal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),personal_data_view.class));
            }
        });

    }
}
