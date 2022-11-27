package com.hifriend.parents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hifriend.R;
import com.hifriend.Therapist.Login;

public class MainActivity extends AppCompatActivity {
    Button parentB;
    Button therapist;
    LinearLayout sliderDotspanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentB = (Button) findViewById(R.id.parentbutton);
        parentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginP();
            }
        });

        therapist = (Button) findViewById(R.id.therapistbutton2);
        therapist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }

    public void loginP(){
        Intent intent = new Intent(this,LoginParent.class);
        startActivity(intent);
    }
}
