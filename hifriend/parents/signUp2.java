package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hifriend.R;

public class signUp2 extends AppCompatActivity {
    FloatingActionButton signUp3;
    static EditText childsName,age,diagnosis;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);
        //FirebaseApp.initializeApp(this);

        signUp3 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        childsName = (EditText) findViewById(R.id.child_name);
        age = (EditText) findViewById(R.id.childage);
        diagnosis = (EditText) findViewById(R.id.diagnosisage);
        signUp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = childsName.getText().toString().trim();
                String agee = age.getText().toString().trim();
                String diagnosiss = diagnosis.getText().toString().trim();

                if(name.isEmpty()){
                    childsName.setError("Name is required!");
                    childsName.requestFocus();
                }
                if(agee.isEmpty()){
                    age.setError("Age is required!");
                    age.requestFocus();
                }
                if(diagnosiss.isEmpty()){
                    diagnosis.setError("Diagnosis age is required");
                    diagnosis.requestFocus();
                }
                else{
                    signUp3();
                }



            }
        });

    }
    public void signUp3(){
        Intent intent = new Intent(this, signUp3.class);
        startActivity(intent);
    }


}
