package com.hifriend.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.hifriend.R;



public class forgot_pass extends AppCompatActivity {
    EditText email;
    Button reset;
    FirebaseAuth mUath;
    ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass_thera);

        mUath = FirebaseAuth.getInstance();
        
        email = (EditText) findViewById(R.id.Therapist_login_email);
        reset = (Button) findViewById(R.id.login_button);
        progressBar = (ProgressBar) findViewById(R.id.progressReg);
        
        
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgot_password();
            }
        });
          
    }
    
    public void forgot_password(){
        
        String email_v = email.getText().toString().trim();
        
        if(email_v.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_v).matches()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        
        progressBar.setVisibility(View.VISIBLE);
        mUath.sendPasswordResetEmail(email_v).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgot_pass.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    
                }else{
                    Toast.makeText(forgot_pass.this, "Failed to reset, please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
}
