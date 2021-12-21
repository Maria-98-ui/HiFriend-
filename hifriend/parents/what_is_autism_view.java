package com.hifriend.parents;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hifriend.R;

public class what_is_autism_view extends AppCompatActivity {
    Toolbar toolbar;
    private VideoView videoView;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_is_autism_layout);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        videoView = findViewById(R.id.videoView);
        String vidPath = "android.resource://"+getPackageName()+"/"+R.raw.what_is_autism_vid;
        Uri uri = Uri.parse(vidPath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        if(mediaController==null){
                            mediaController = new MediaController(what_is_autism_view.this);
                            videoView.setMediaController(mediaController);
                            mediaController.setAnchorView(videoView);
                        }
                    }
                });
            }
        });


    }
}
