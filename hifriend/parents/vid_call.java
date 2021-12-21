package com.hifriend.parents;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hifriend.R;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class vid_call extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vid_call_parent);
        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(options);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onButtonClick(View v){
        EditText editText = findViewById(R.id.conferenceNameParent);
        String text = editText.getText().toString();


        if(text.length()>0){
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            JitsiMeetActivity.launch(this,options);
        }
    }
}
