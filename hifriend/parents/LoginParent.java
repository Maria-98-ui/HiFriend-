package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hifriend.R;
import com.hifriend.Therapist.notifPack.FirebaseMessagingService;


public class LoginParent extends AppCompatActivity {
    TextView signup;
    static EditText email, pass;
    TextView forgotpass;
    Button login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    DatabaseReference refparent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseApp.initializeApp(this);


        // super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        login = (Button) findViewById(R.id.Resetbutton);
        email = (EditText) findViewById(R.id.emailLogin);
        pass = (EditText) findViewById(R.id.passwordlogin);
        progressBar = (ProgressBar) findViewById(R.id.progressReg);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString().trim();
                String passUser = pass.getText().toString().trim();

                if(useremail.isEmpty()){
                    email.setError("Email is required!");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                    email.setError("Provide a valid email!");
                    email.requestFocus();
                    return;
                }
                if(passUser.isEmpty()){
                    pass.setError("Password is required!");
                    pass.requestFocus();
                    return;
                }
                if(passUser.length()<6){
                    pass.setError("Min password length is 6 characters!");
                    pass.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(useremail,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String id = mAuth.getCurrentUser().getUid();

                            refparent = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent").child(id);
                            refparent.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
                                        @Override
                                        public void onSuccess(String s) {
                                            //String token = FirebaseMessagingService.NE
//                                              refparent.child("parent_token").setValue(token);
                                            refparent.child("user_token").setValue(s);

                                        }
                                    });


                                    //String token = FirebaseMessagingService.NE
//                                    refparent.child("parent_token").setValue(token);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(LoginParent.this,"Successfully logged in!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(),parent_main_view.class));
                        }else{
                            Toast.makeText(LoginParent.this,"Failed to login!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        forgotpass = (TextView) findViewById(R.id.forgotPass);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginParent.this,forgotPass.class);
                startActivity(intent);
            }
        });

        signup = (TextView) findViewById(R.id.signUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signPage();

            }
        });

    }
    public void signPage(){
        Intent intent = new Intent(this,SignUpParent.class);
        startActivity(intent);
    }
}
