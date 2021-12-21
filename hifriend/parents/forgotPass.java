package com.hifriend.parents;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.hifriend.R;


public class forgotPass extends AppCompatActivity {
    private EditText email;
    private Button reset;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        email = (EditText) findViewById(R.id.emailLogin);
        reset = (Button) findViewById(R.id.Resetbutton);
        progressBar = (ProgressBar) findViewById(R.id.progressReg);

        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPass();
            }
        });

    }
    private void resetPass(){
        String resetEmail = email.getText().toString().trim();

        if(resetEmail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()){
            email.setError("Provide a valid email!");
            email.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotPass.this,"Check your email to reset your password!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    startActivity(new Intent(getApplicationContext(),LoginParent.class));
                    LoginParent.email.setText("");
                }
                else{
                    Toast.makeText(forgotPass.this,"Something went wrong, try again!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}