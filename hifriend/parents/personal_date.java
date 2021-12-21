package com.hifriend.parents;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hifriend.R;
import com.hifriend.Therapist.SignUp;
import com.hifriend.Therapist.personal_data_view;

import de.hdodenhof.circleimageview.CircleImageView;

public class personal_date extends AppCompatActivity {
    CircleImageView setImg;
    FloatingActionButton upload;
    EditText name, phone, email, childs_name, childs_age, childs_diagnosis, residency;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Button update_profile;
    ImageView back_to_profile;
    private Uri imageUri = null;
    FirebaseAuth auth;
    String _NAME, _PHONE, _EMAIL, _CHILDS_NAME, _CHILDS_AGE, _CHILDS_DIAG, _RESIDENDY, _IMAGE;
    //databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_personal_data);

        setImg = (CircleImageView) findViewById(R.id.profile_data_p);
        upload = (FloatingActionButton) findViewById(R.id.upload_image);
        //upload.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name_full_parent);
        phone = (EditText) findViewById(R.id.phone_parent);
        email = (EditText) findViewById(R.id.email_parent);
        childs_name = (EditText)findViewById(R.id.childs_name);
        childs_age = (EditText)findViewById(R.id.childs_age);
        childs_diagnosis = (EditText)findViewById(R.id.childs_diag);
        residency = (EditText)findViewById(R.id.res);
        back_to_profile = (ImageView) findViewById(R.id.back_to_profile);
       // back_to_profile.setOnClickListener(this);
        update_profile = (Button) findViewById(R.id.update);

        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("parent files");
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        String id = auth.getCurrentUser().getUid();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot){
                _NAME = snapshot.child(id).child("fullname").getValue().toString();
                name.setText(_NAME);
                name.setTextSize(16);

                _PHONE = snapshot.child(id).child("phone").getValue().toString();
                phone.setText(_PHONE);
                phone.setTextSize(16);

                _EMAIL = snapshot.child(id).child("email").getValue().toString();
                email.setText(_EMAIL);
                email.setTextSize(16);

                _CHILDS_NAME = snapshot.child(id).child("childname").getValue().toString();
                childs_name.setText(_CHILDS_NAME);
                childs_name.setTextSize(16);

                _CHILDS_AGE = snapshot.child(id).child("childage").getValue().toString();
                childs_age.setText(_CHILDS_AGE);
                childs_age.setTextSize(16);

                _CHILDS_DIAG = snapshot.child(id).child("diagnosisAge").getValue().toString();
                childs_diagnosis.setText(_CHILDS_DIAG);
                childs_diagnosis.setTextSize(16);

                _RESIDENDY = snapshot.child(id).child("residency").getValue().toString();
                residency.setText(_RESIDENDY);
                residency.setTextSize(16);


                if(snapshot.child(id).child("imageUri").exists()){
                    _IMAGE = snapshot.child(id).child("imageUri").getValue(String.class);
//                    setImg.setImageURI(Uri.parse(img));
                    Glide.with(setImg.getContext()).load(_IMAGE).into(setImg);
                }
              else{
                   setImg.setImageResource(R.drawable.iconregister);
               }

                if(!snapshot.child(id).child("imageUri").exists()){
                    setImg.setImageResource(R.drawable.iconregister);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        back_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //startActivity(new Intent(getApplicationContext(),profile_page.class));
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(personal_date.this)
                        .maxResultSize(1080,1080)
                        .start(25);
            }
        });

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(personal_date.this);
                alertDialogBuilder
                        .setMessage("Updating Profile\n\t\tDo you wish to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                startActivity(new Intent(getApplicationContext(), personal_data_view.class));
//                                finish();
                                  update();


                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==25 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            setImg.setImageURI(imageUri);

//            update_profile.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    update();
//                }
//            });
        }
    }

    private void update(){
        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent")
                .child(id);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    final StorageReference filepath = storageReference.child("images").child(imageUri.getLastPathSegment());
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            if (imageUri == null) {
                                Toast.makeText(getApplicationContext(), "Profile not set", Toast.LENGTH_LONG).show();

                            } else {
                                // databaseReference.child("imageUri").setValue(imageUri).toString();
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url = uri.toString();
                                        //databaseReference.child("imageUri").setValue(url).toString();
                                        if (!snapshot.child("imageUri").exists()) {
                                            databaseReference.child(id).child("imageUri").setValue(url).toString();
                                        }
                                        else if(!_IMAGE.equals(setImg.getContext().toString())){
                                            databaseReference.child(id).child("imageUri").setValue(url).toString();
                                        }
                                        //databaseReference.child("imageUri").setValue(url).toString();

                                        else{
                                            databaseReference.child("imageUri").setValue(url).toString();
                                        }
//                                        else if (!_IMAGE.equals(setImg.getContext().toString())) {
//
//                                            databaseReference.child("imageUri").setValue(url).toString();
//                                        }
//                                                else{
//                                                    databaseReference.child("imageUri").setValue(url).toString();
//                                                }
                                        Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG).show();
                                    }
                                });
                                //databaseReference.child("imageUri").setValue(imageUri.toString());

                            }
                        }
                    });
                }


                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

       if(isNameChanged() || isPhoneChanged() || isEmailChanged() || isChildNameChanged() || isChildAgeChanged()
        || isChildDiagChanged() || isResidencyChanged()){
            Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_LONG).show();

        }

}


    private boolean isNameChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_NAME.equals(name.getText().toString())){
            databaseReference.child(id).child("fullname").setValue(name.getText().toString());
            _NAME = name.getText().toString();
            return true;
        }else{
            return false;
        }

    }

    private boolean isPhoneChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_PHONE.equals(phone.getText().toString())){
            databaseReference.child(id).child("phone").setValue(phone.getText().toString());
            _PHONE = phone.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isEmailChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_EMAIL.equals(email.getText().toString())){
            databaseReference.child(id).child("email").setValue(email.getText().toString());
            _EMAIL = email.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isChildNameChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_CHILDS_NAME.equals(childs_name.getText().toString())){
            databaseReference.child(id).child("childname").setValue(childs_name.getText().toString());
            _CHILDS_NAME = childs_name.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isChildAgeChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_CHILDS_AGE.equals(childs_age.getText().toString())){
            databaseReference.child(id).child("childage").setValue(childs_age.getText().toString());
            _CHILDS_AGE = childs_age.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isChildDiagChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_CHILDS_DIAG.equals(childs_diagnosis.getText().toString())){
            databaseReference.child(id).child("diagnosisAge").setValue(childs_diagnosis.getText().toString());
            _CHILDS_DIAG = childs_diagnosis.getText().toString();
            return true;
        }else{
            return false;
        }

    }
    private boolean isResidencyChanged(){
        String id = auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent");
        if(!_RESIDENDY.equals(residency.getText().toString())){
            databaseReference.child(id).child("residency").setValue(residency.getText().toString());
            _RESIDENDY = residency.getText().toString();
            return true;
        }else{
            return false;
        }

    }
}
