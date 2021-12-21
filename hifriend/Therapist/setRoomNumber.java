package com.hifriend.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;
import com.hifriend.Therapist.notifPack.FcmNotificationsSender;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;

public class setRoomNumber extends AppCompatActivity {

    public static Button send;
    public static EditText roomName;
    DatabaseReference refConsult, refParent, refRoom, theraRef;
    FirebaseAuth auth;
    String _NAME;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif_lay);

        send = (Button) findViewById(R.id.notify);

        roomName = (EditText) findViewById(R.id.room_txt);

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!roomName.getText().toString().isEmpty()){
                    String room = roomName.getText().toString();
//                    Intent i = getIntent();
//                    String token = i.getStringExtra("TOKEN");


                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    refParent = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").
                            getReference("parent").child(id);
                    refParent.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                String token = snapshot.child("user_token").getValue().toString();

                                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token, "Room name", room, getApplicationContext(), setRoomNumber.this);
                                notificationsSender.SendNotifications();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });


//                    FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token,"Room name",room,getApplicationContext(),setRoomNumber.this);
//                    notificationsSender.SendNotifications();

                }

//                theraRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").child(id);
//                theraRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                        String _name = snapshot.child("full_name").getValue().toString();
//
//                        Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
//                                .orderByChild("therapist_Name").equalTo(_name);
//
//                        query.addListenerForSingleValueEvent(sessions.valueEventListener);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                    }
//                });
//
//            }
            }
        });





//        refRoom = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");
//
//        String key = refRoom.getKey();


//        Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
//                .orderByChild("therapist_Name").equalTo(_name);
//
//        query.addListenerForSingleValueEvent(valueEventListener);
//
//
//
//
//
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//
//                if(snapshot.exists()){
//                    String room_name = roomName.getText().toString();
//                    refConsult.child(key).child("room_name").setValue(room_name).toString();
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        };
//
//
//
//
//
//
//
//        auth = FirebaseAuth.getInstance();
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                refRoom = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");
//                String key = refRoom.push().getKey();
//
//                refRoom.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                        String room_name = roomName.getText().toString();
//                        String _Name_consult = snapshot.child(key).child("therapist_Name").getValue().toString();
//
//                        String id = auth.getCurrentUser().getUid();
//
//                        refConsult = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").child(id);
//                        refParent.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                                String name = snapshot.child("full_name").getValue().toString();
//
//                                if(_Name_consult.equals(name)){
//
//                                    refRoom.child("room_name").setValue(room_name).toString();
//
//
//                                }
//
//                                Toast.makeText(setRoomNumber.this,"Room name has been set",Toast.LENGTH_LONG).show();
//
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                            }
//                        });
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//            }
//        });
//
//
//
//    }

}

}
