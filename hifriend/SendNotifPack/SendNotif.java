package com.hifriend.SendNotifPack;

import android.app.AppComponentFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.hifriend.R;
import com.hifriend.parents.book_consultation;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;

public class SendNotif extends AppCompatActivity {
    DatabaseReference consult_slots;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notif_lay);


        updateToken();
    }
    private void updateToken(){

//
//        FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").
//                getReference("consulations");
//
//        String getName = consult_slots.getKey();
//


        FirebaseInstallations.getInstance().getToken(true).addOnSuccessListener(new OnSuccessListener<InstallationTokenResult>() {
            @Override
            public void onSuccess(InstallationTokenResult installationTokenResult) {

                String refreshToken = installationTokenResult.getToken();
                Token token = new Token(refreshToken);

                FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Tokens").child(new consult_class().getParent_id()).setValue(token);


            }
        });



//        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener{
//            @Override
//            public void onSuccess(@NonNull @NotNull Task<InstallationTokenResult> task) {
//
//                String refreshToken = task.get
//            }
//        });
    }
}
