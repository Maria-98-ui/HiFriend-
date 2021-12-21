package com.hifriend.parents;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hifriend.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class shadow_game extends AppCompatActivity {
    ImageView image1, image2, image3, image4, image_main;
    Button next;
    TextView status;

    //list of colored images
    Integer[] images = {
            R.drawable.animal_1,
            R.drawable.animal_2,
            R.drawable.animal_3,
            R.drawable.animal_4,
            R.drawable.animal_5,
            R.drawable.animal_6,
            R.drawable.animal_7,
            R.drawable.animal_8,
            R.drawable.animal_9,
            R.drawable.animal_10,
            R.drawable.animal_11,
            R.drawable.animal_12,
            R.drawable.animal_13,
            R.drawable.animal_14,
            R.drawable.animal_15,
            R.drawable.animal_16,
            R.drawable.animal_17,
            R.drawable.animal_18,
            R.drawable.animal_19,
            R.drawable.animal_20,
            R.drawable.animal_21,
            R.drawable.animal_22,


    };

    //list of shadow images
    Integer[] images_bw = {

            R.drawable.animal_bw_1,
            R.drawable.animal_bw_2,
            R.drawable.animal_bw_3,
            R.drawable.animal_bw_4,
            R.drawable.animal_bw_5,
            R.drawable.animal_bw_6,
            R.drawable.animal_bw_7,
            R.drawable.animal_bw_8,
            R.drawable.animal_bw_9,
            R.drawable.animal_bw_10,
            R.drawable.animal_bw_11,
            R.drawable.animal_bw_12,
            R.drawable.animal_bw_13,
            R.drawable.animal_bw_14,
            R.drawable.animal_bw_15,
            R.drawable.animal_bw_16,
            R.drawable.animal_bw_17,
            R.drawable.animal_bw_18,
            R.drawable.animal_bw_19,
            R.drawable.animal_bw_20,
            R.drawable.animal_bw_21,
            R.drawable.animal_bw_22,
    };

    //list of numbers for all the images / currently 22 imgs
    Integer[] images_numbers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 };

    int turn =1;
    int correctAns = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shadow_game_kids);

        image1 = (ImageView) findViewById(R.id.image_1);
        image2 = (ImageView) findViewById(R.id.image_2);
        image3 = (ImageView) findViewById(R.id.image_3);
        image4 = (ImageView) findViewById(R.id.image_4);
        image_main = (ImageView) findViewById(R.id.image_Main);

        next = (Button) findViewById(R.id.b_next);
        status = (TextView) findViewById(R.id.tv_status);

        //random shuffle the imgs
        Collections.shuffle(Arrays.asList(images_numbers));
        setImages();

        //clicks

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAns == 1){
                    score++;
                    status.setText("Correct!");
                    next.setVisibility(View.VISIBLE);
                }else{
                    status.setText("Wrong!");
                    next.setVisibility(View.VISIBLE);
                }

                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAns == 2){
                    score++;
                    status.setText("Correct!");
                    next.setVisibility(View.VISIBLE);
                }else{
                    //status.setTextColor(377);
                    status.setText("Wrong!");
                    next.setVisibility(View.VISIBLE);
                }

                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }


        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAns == 3){
                    score++;
                    status.setText("Correct!");
                    next.setVisibility(View.VISIBLE);
                }else{
                    status.setText("Wrong!");
                    next.setVisibility(View.VISIBLE);
                }

                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }


        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(correctAns == 4){
                    score++;
                    status.setText("Correct!");
                    next.setVisibility(View.VISIBLE);
                }else{
                    status.setText("Wrong!");
                    next.setVisibility(View.VISIBLE);
                }

                image1.setEnabled(false);
                image2.setEnabled(false);
                image3.setEnabled(false);
                image4.setEnabled(false);

            }


        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn++;
                if(turn==11){
                    checkEnd();

                }else{
                    setImages();
                }
            }
        });


    }
    private void setImages(){
        //determine which is the correct answer 1-4
        Random r = new Random();
        correctAns = r.nextInt(4) + 1;

        //generate random wrong answers
        int wrongAns1, wrongAns2, wrongAns3;

        do{
            wrongAns1 = r.nextInt(22);

        }while (wrongAns1 == images_numbers[turn]);

        do{
            wrongAns2 = r.nextInt(22);

        }while (wrongAns2 == images_numbers[turn] || wrongAns2 == wrongAns1);

        do{
            wrongAns3 = r.nextInt(22);

        }while (wrongAns3 == images_numbers[turn] || wrongAns3 == wrongAns2 || wrongAns3 == wrongAns1);

        //set imgs for all ans
        switch (correctAns){
            case 1:
                image1.setImageResource(images[images_numbers[turn]]);
                image2.setImageResource(images[wrongAns1]);
                image3.setImageResource(images[wrongAns2]);
                image4.setImageResource(images[wrongAns3]);
                break;

            case 2:
                image1.setImageResource(images[wrongAns1]);
                image2.setImageResource(images[images_numbers[turn]]);
                image3.setImageResource(images[wrongAns2]);
                image4.setImageResource(images[wrongAns3]);
                break;

            case 3:
                image1.setImageResource(images[wrongAns1]);
                image2.setImageResource(images[wrongAns2]);
                image3.setImageResource(images[images_numbers[turn]]);
                image4.setImageResource(images[wrongAns3]);
                break;

            case 4:
                image1.setImageResource(images[wrongAns1]);
                image2.setImageResource(images[wrongAns2]);
                image3.setImageResource(images[wrongAns3]);
                image4.setImageResource(images[images_numbers[turn]]);
                break;
        }

        //set images for the qs

        image_main.setImageResource(images_bw[images_numbers[turn]]);

        //null stuff

        status.setText("");
        next.setVisibility(View.INVISIBLE);

        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);


    }
    private void checkEnd(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Game Over! Score: " + score);
        alertDialog.setPositiveButton("QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
