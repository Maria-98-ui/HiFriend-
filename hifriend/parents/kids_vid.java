package com.hifriend.parents;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.hifriend.R;


public class kids_vid extends AppCompatActivity {
    private VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kids_vid_fruits);

        videoView = findViewById(R.id.videoView);
        String vidPath = "android.resource://"+getPackageName()+"/"+R.raw.fruits_veg_kids;
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
                            mediaController = new MediaController(kids_vid.this);
                            videoView.setMediaController(mediaController);
                            mediaController.setAnchorView(videoView);
                        }
                    }
                });
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(kids_vid.this,"Sorry, something went wrong while trying to play the video",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
