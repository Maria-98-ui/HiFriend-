package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hifriend.R;

public class signUp3 extends AppCompatActivity {
    FloatingActionButton SIGNUP_4;
    ImageButton male;
    ImageButton female;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup3);
        //FirebaseApp.initializeApp(this);

        male = findViewById(R.id.malebutton);
        female = findViewById(R.id.femalebutton);


        male.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                male.setPressed(true);

                return true;
            }
        });

        female.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                female.setPressed(true);

                return true;
            }
        });
        SIGNUP_4 = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        SIGNUP_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp_4();


            }
        });

//        female.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                female.setPressed(true);
//                return true;
//            }
//        });

    }
    public void signUp_4(){
        Intent intent = new Intent(this, signUp4.class);
        startActivity(intent);
    }

}

