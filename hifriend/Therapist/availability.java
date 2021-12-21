package com.hifriend.Therapist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class availability extends AppCompatActivity {
    private static final String TAG = "availability";
    private EditText time, date;
    int hour, min;
    ImageView setDatenTime, newslot;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    FirebaseAuth auth;
    public static String _ID;
    public static String get_ID;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thera_availability);

        time = (EditText) findViewById(R.id.selectTime);
        date = (EditText) findViewById(R.id.selectDate);
        setDatenTime = (ImageView) findViewById(R.id.setDateNTime);
        newslot = (ImageView) findViewById(R.id.newSlot);
        _ID = home._NAME;

        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("slots").child(_ID).push();
        get_ID = databaseReference.push().getKey();



//        auth = FirebaseAuth.getInstance();
//        _ID = auth.getCurrentUser().getUid();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        availability.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year);
                String dateSet = month + "/" + dayOfMonth + "/" + year;
                date.setText(dateSet);

            }
        };

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(availability.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay;
                        min = minute;
                        String timee = hour + ":" + min;
                        SimpleDateFormat f24Hours = new SimpleDateFormat(
                                "HH:mm"
                        );
                        try {
                            Date date = f24Hours.parse(timee);
                            SimpleDateFormat f12hours = new SimpleDateFormat(
                                    "hh:mm aa"
                            );
                            time.setText(f12hours.format(date));


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //display prev selected time
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();

            }

        });

        setDatenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(availability.this);
                alertDialogBuilder
                        .setMessage("Setting Slot \n\t\t Do you confirm your slot?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                startActivity(new Intent(getApplicationContext(), personal_data_view.class));
//                                finish();
                                addDatenTime();


                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void addDatenTime(){

        //databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

               // String nam = databaseReference.child("name").push().setValue(_ID).toString();


                String DATE = date.getText().toString();
                databaseReference.child("date").setValue(DATE).toString();

                String TIME = time.getText().toString();
                databaseReference.child("time").setValue(TIME).toString();

                auth = FirebaseAuth.getInstance();
                String id = auth.getCurrentUser().getUid();
                databaseReference.child("thera_id").setValue(id).toString();

                Toast.makeText(availability.this,"Slot added",Toast.LENGTH_LONG).show();




            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }



}
