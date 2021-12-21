package com.hifriend.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hifriend.R;


public class Login extends AppCompatActivity {

    TextView signUp;
    EditText email, pass;
    TextView forgotPass;
    Button login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.therapist_login);


        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.Therapist_login_email);
        pass = (EditText) findViewById(R.id.therapist_login_pass);
        login = (Button) findViewById(R.id.login_button);
        forgotPass = (TextView) findViewById(R.id.forgotPass);
        progressBar = (ProgressBar) findViewById(R.id.progressReg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tEmail = email.getText().toString().trim();
                String tPass = pass.getText().toString().trim();

                if(tEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(tEmail).matches()){
                    email.setError("Please provide correct email address!");
                    email.requestFocus();
                    return;

                }
                if(tPass.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }
                if(tPass.length()<6){
                    pass.setError("Min password length is 6 characters");
                    pass.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(tEmail,tPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Successfully logged in!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(),therapist_main_view.class));
                        }else{
                            Toast.makeText(Login.this,"Failed to login, email or password is incorrect",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,forgot_pass.class);
                startActivity(intent);
                
            }
        });

        signUp = (TextView) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));

            }
        });


    }
}
