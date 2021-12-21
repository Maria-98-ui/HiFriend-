package com.hifriend.Therapist;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class personal_data_view extends AppCompatActivity {
    CircleImageView setImg;
    EditText name, phone, email, expertise,bio;
    DatabaseReference databaseReference;
    Button  update_profile;
    ImageView back_to_profile;
    FirebaseAuth auth;
   //ImageView logout,personal_data;

    String _NAME, _EMAIL, _PHONE, _EXPERTISE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thera_personal_data);


        name = (EditText) findViewById(R.id.name_full_thera);
        phone = (EditText) findViewById(R.id.phone_thera);
        email = (EditText) findViewById(R.id.email_thera);
        expertise = (EditText) findViewById(R.id.thera_expertise);
        setImg = (CircleImageView) findViewById(R.id.profile_data_t);
        back_to_profile = (ImageView) findViewById(R.id.back_to_profile);
        update_profile = (Button) findViewById(R.id.update);
        bio = (EditText) findViewById(R.id.bio);


        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        String id = auth.getCurrentUser().getUid();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                _NAME = Objects.requireNonNull(snapshot.child(id).child("full_name").getValue()).toString();
                name.setText(_NAME);
                name.setTextSize(16);
                //wel.setTextSize(16);

                _PHONE = Objects.requireNonNull(snapshot.child(id).child("phone").getValue()).toString();
                phone.setText(_PHONE);
                phone.setTextSize(16);

                _EMAIL = Objects.requireNonNull(snapshot.child(id).child("email").getValue()).toString();
                email.setText(_EMAIL);
                email.setTextSize(16);

                _EXPERTISE = Objects.requireNonNull(snapshot.child(id).child("area_of_expertise").getValue()).toString();
                expertise.setText(_EXPERTISE);
                expertise.setTextSize(16);

                String img = snapshot.child(id).child("image").getValue(String.class);
                setImg.setImageURI(Uri.parse(img));

                if(snapshot.child(id).child("bio").exists()){
                    String bioset = Objects.requireNonNull(snapshot.child(id).child("bio").getValue()).toString();
                    bio.setText(bioset);
                }
                else{
                    bio.setHint("bio");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        back_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //startActivity(new Intent(getApplicationContext(),profile_page.class));
            }
        });
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(personal_data_view.this);
                alertDialogBuilder
                        .setMessage("Updating Profile\n\t\tDo you wish to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                startActivity(new Intent(getApplicationContext(), personal_data_view.class));
//                                finish();
                                 updateProf();


                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

//        auth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
//        //String iid = auth.getCurrentUser().getUid();
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                String setbio = snapshot.child(id).child("bio").getValue().toString();
//                bio.setText(setbio);
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });

    }

    private void updateProf(){
        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist")
                .child(id);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String thera_bio = bio.getText().toString();
                    String bioo = snapshot.child(id).getKey();

                    // DatabaseReference ref = databaseReference.child(id).get().addOnCompleteListener();
                    //DatabaseReference refere = databaseReference.child("bio");


                    databaseReference.child("bio").setValue(thera_bio).toString();

                    if(isNameChanged() || isEmailChanged() || isArea0fExpertiseChanged() || isPhoneChanged()){
                        Toast.makeText(personal_data_view.this,"Profile updated successfully",Toast.LENGTH_LONG).show();
                    }

                    //bio.setText(thera_bio);

                    //refere.push().setValue(thera_bio).toString();

//                String bioSet = snapshot.child(id).child("bio").getValue().toString();
//                bio.setText(bioSet);
                    // bio.setText(bioo);

                    Toast.makeText(personal_data_view.this,"Profile updated successfully",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(personal_data_view.this,"Profile failed to update",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }


    private boolean isNameChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        if(!_NAME.equals(name.getText().toString())){
            databaseReference.child(id).child("full_name").setValue(name.getText().toString());
            _NAME = name.getText().toString();
            return true;
        }else{
            return false;
        }

    }

    private boolean isPhoneChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        if(!_PHONE.equals(phone.getText().toString())){
            databaseReference.child(id).child("phone").setValue(phone.getText().toString());
            _PHONE = phone.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isEmailChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        if(!_EMAIL.equals(email.getText().toString())){
            databaseReference.child(id).child("email").setValue(email.getText().toString());
            _EMAIL = email.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isArea0fExpertiseChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        if(!_EXPERTISE.equals(expertise.getText().toString())){
            databaseReference.child(id).child("area_of_expertise").setValue(expertise.getText().toString());
            _EXPERTISE = expertise.getText().toString();
            return true;
        }else{
            return false;
        }

    }
}
