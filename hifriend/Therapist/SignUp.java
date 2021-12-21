package com.hifriend.Therapist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hifriend.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText full_name, email, phone, pass, expertise, uploadText;
    Button upload, register;
    TextView go_back;
    FloatingActionButton changeProfile;
    CircleImageView profile;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    ProgressBar progressBar;
    private Uri imageUri = null;

    FirebaseAuth muAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.therapist_sign_up);


        muAth = FirebaseAuth.getInstance();

        full_name = (EditText) findViewById(R.id.therapist_name);
        phone = (EditText) findViewById(R.id.therapist_phone);
        email = (EditText) findViewById(R.id.therapist_email);
        pass = (EditText) findViewById(R.id.therapist_pass);
        expertise = (EditText) findViewById(R.id.expertise_area);
        uploadText = (EditText) findViewById(R.id.select);
        upload = (Button) findViewById(R.id.upload_btn);
        register = (Button) findViewById(R.id.register_btn);
        register.setOnClickListener(this);
        go_back = (TextView) findViewById(R.id.goBck);
        go_back.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressReg);
        profile = (CircleImageView) findViewById(R.id.profile_image_home);
        changeProfile = (FloatingActionButton) findViewById(R.id.upload_image);
        changeProfile.setOnClickListener(this);

        storageReference = FirebaseStorage.getInstance().getReference("Therapist files");
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
        upload.setEnabled(false);

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(SignUp.this)
                        .maxResultSize(1080,1080)
                        .start(20);
            }
        });

        uploadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
            }
        });

        }
        private void selectPDF(){
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            upload.setEnabled(true);
            uploadText.setText(data.getDataString()
            .substring(data.getDataString().lastIndexOf("/")+1));

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadFiletoStorage(data.getData());
                }
            });
        }
        else{
            imageUri = data.getData();
            profile.setImageURI(imageUri);
        }
    }
    private void uploadFiletoStorage(Uri data){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File is loading...");
        progressDialog.show();

        StorageReference ref = storageReference.child("uploadPDF" + System.currentTimeMillis() + ".pdf");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri = uriTask.getResult();

                        Upload_pdf pdf = new Upload_pdf(uploadText.getText().toString(),uri.toString());
                        databaseReference.child("pdf").setValue(pdf);
                        Toast.makeText(SignUp.this,"File uploaded",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File Uploaded..." +(int) progress+"%");

            }
        });
    }
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.register_btn:
                register_therapist();
                break;
            case R.id.goBck:
                loginPage();
                break;
        }
    }

    public void register_therapist(){
        String fullName = full_name.getText().toString().trim();
        String Tphone = phone.getText().toString().trim();
        String Temail = email.getText().toString().trim();
        String Texpertise = expertise.getText().toString().trim();
        String Tpass = pass.getText().toString().trim();

        if(fullName.isEmpty()){
            full_name.setError("Full name is required!");
            full_name.requestFocus();
            return;
        }
        if(Tphone.isEmpty()){
            phone.setError("Phone is required!");
            phone.requestFocus();
            return;
        }
        if(Temail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Temail).matches()){
            email.setError("Please provide correct email address!");
            email.requestFocus();
            return;
        }
        if(Texpertise.isEmpty()){
            expertise.setError("Area of expertise is required!");
            expertise.requestFocus();
            return;
        }
        if(Tpass.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }
        if(Tpass.length() < 6){
            pass.setError("Min password length should be 6 characters!");
            pass.requestFocus();
            return;
        }
//        if(!upload.isSelected()){
//            upload.setError("resume is required!");
//            return;
//        }
        progressBar.setVisibility(View.VISIBLE);
        muAth.createUserWithEmailAndPassword(Temail,Tpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            final StorageReference filepath = storageReference.child("Images").child(imageUri.getLastPathSegment());

                            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    if(imageUri == null){
                                        Toast.makeText(getApplicationContext(),"Please upload an image", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Therapist t = new Therapist(fullName,Tphone,Temail,Texpertise,imageUri.toString());



                             databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if(task.isSuccessful()){
//                                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                            @Override
//                                            public void onSuccess(Uri uri) {
//                                                String url = uri.toString();
//                                                databaseReference.child("image").setValue(url);
//                                            }
//                                        });
                                        Toast.makeText(SignUp.this,"You have been successfully registered",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getApplicationContext(),Login.class));

                                    }else{
                                        Toast.makeText(SignUp.this,"Failed to register, try again!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                    }
                    });
                }
                        else{
                            Toast.makeText(SignUp.this, "Something is wrong, please try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
    public void loginPage(){
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}
