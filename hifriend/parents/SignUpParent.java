 package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hifriend.R;

 public class SignUpParent extends AppCompatActivity {

    FloatingActionButton signUp2;
    static EditText fullname,phone;
    static Spinner spinner;
    static AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        //FirebaseApp.initializeApp(this);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCountries);
        String[] option = {"Malaysia","Singapore","Thailand","Indonesia","Philippines","Brunai"};


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.countries_option,option);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.countries, R.layout.countries_option);
        
        autoCompleteTextView.setText(adapter.getItem(0).toString(),false);
        autoCompleteTextView.setAdapter(adapter);
        //spinner = (Spinner)findViewById(R.id.spinnerCountries);
        fullname =(EditText) findViewById(R.id.user_name);
        phone = (EditText) findViewById(R.id.userphone);


//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        signUp2 = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        signUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parenfullName = fullname.getText().toString().trim();
                String parentPhone = phone.getText().toString().trim();
                if(parenfullName.isEmpty()){
                    fullname.setError("Full name is required!");
                    fullname.requestFocus();
                    return;
                }
                if(parentPhone.isEmpty()){
                    phone.setError("Phone is required!");
                    phone.requestFocus();
                    return;
                }
                else{
                    signUp2();
                }

            }
        });

    }
    public void signUp2(){
        Intent intent = new Intent(this, signUp2.class);
        startActivity(intent);
    }
}
