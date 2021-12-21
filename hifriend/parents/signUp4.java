 package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.hifriend.R;

 public class signUp4 extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    static EditText userName, user_phone,child_name,childs_age,childs_diagnosis,user_email,user_pass,retypePass;
    private ImageButton male,female;
    private Spinner residency;
    private AutoCompleteTextView auto;
    private ProgressBar progressBar;
    private static FirebaseAuth mAuth;


    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_4);
        //FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();


        register = (Button) findViewById(R.id.signButton);
        register.setOnClickListener(this);
        //final View view = getLayoutInflater().inflate(R.layout.signup);
        //final View view2 = getLayoutInflater().inflate(R.layout.signup2,null,false);


        userName = (EditText) SignUpParent.fullname;
        user_phone = (EditText) SignUpParent.phone;
        residency = (Spinner) SignUpParent.spinner;
        auto = (AutoCompleteTextView) SignUpParent.autoCompleteTextView;
        //auto.setOnItemClickListener(this);

        child_name = (EditText) signUp2.childsName;
        childs_age = (EditText) signUp2.age;
        childs_diagnosis = signUp2.diagnosis;
//        male = (ImageButton) findViewById(R.id.malebutton);
//        female = (ImageButton) findViewById(R.id.femalebutton);
        user_email = (EditText) findViewById(R.id.user_email);
        user_pass = (EditText) findViewById(R.id.user_pass);
        retypePass = (EditText) findViewById(R.id.retype_pass);


        progressBar = (ProgressBar) findViewById(R.id.progressReg);

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.signButton:
                registerUser();
                break;
        }

    }
    private void registerUser() {

        String parenName = userName.getText().toString().trim();
        String phoneNumber = user_phone.getText().toString().trim();

        //String res = residency.getSelectedItem().toString().trim();
        String reside = auto.getEditableText().toString().trim();

        String childName = child_name.getText().toString().trim();
        String childsAge = childs_age.getText().toString().trim();
        String childsDiagnosis = childs_diagnosis.getText().toString().trim();
        String user_Email = user_email.getText().toString().trim();
        String passUser = user_pass.getText().toString().trim();
        String retype = retypePass.getText().toString().trim();

        if (user_Email.isEmpty()) {
            user_email.setError("Email is required!");
            user_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user_Email).matches()) {
            user_email.setError("Please provide a valid email!");
            user_email.requestFocus();
            return;

        }
        if (passUser.isEmpty()) {
            user_pass.setError("Password is required!");
            user_pass.requestFocus();
            return;
        }
        if (passUser.length() < 6) {
            user_pass.setError("Min password length should be 6 characters!");
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(user_Email, passUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            parent user = new parent(parenName, phoneNumber, reside,
                                    childName, childsAge, childsDiagnosis, user_Email);

                            FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete (@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(signUp4.this, "You have been successfully registered", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to login page
//                                                Intent intent = new Intent(signUp4.this,LoginParent.class);
//                                                startActivity(intent);
                                        startActivity(new Intent(getApplicationContext(), LoginParent.class));

                                    } else {
                                        Toast.makeText(signUp4.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(signUp4.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });










        }



    }

