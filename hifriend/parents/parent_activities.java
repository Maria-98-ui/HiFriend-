package com.hifriend.parents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hifriend.R;

public class parent_activities extends Fragment {

    Button play;
    Button start;
    Button memo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_activ,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();

        play = (Button) root.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),kids_vid.class));
            }
        });
        start = (Button) root.findViewById(R.id.start_game);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(getContext(),shadow_game.class));
            }
        });

        memo = (Button) root.findViewById(R.id.b_memory_game);
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(getContext(),memory_game.class));
            }
        });
        return root;
    }
}
