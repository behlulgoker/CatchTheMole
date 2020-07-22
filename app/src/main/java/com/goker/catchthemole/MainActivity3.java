package com.goker.catchthemole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {


    TextView scoreText;
    TextView timerText;
    int score = 0;
    Intent hardness ;
    int delay ;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    SharedPreferences savedHighScore;
    int storedScore;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        hardness = getIntent();
        delay = hardness.getIntExtra("userSelection",500);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12};
        savedHighScore = this.getSharedPreferences("com.goker.catchthemole", Context.MODE_PRIVATE);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        scoreText.setText("Score : " + score);

        hideImages();

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                timerText.setText("Time : " + l/1000);
            }

            @Override
            public void onFinish() {
                timerText.setText("Time Off!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray)
                    image.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity3.this);
                alert.setTitle("Game Over!");
                alert.setMessage("Your Score : " + score);
                alert.setPositiveButton("Restart?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    saveScore();
                    //Restart game
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    }
                });

                alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveScore();
                        Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

                alert.show();
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score : " + score);
    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray)
                    image.setVisibility(View.INVISIBLE);
                // for(int i=0,i<12,i++) imageArray[i].setVisibility(View.INVISIBLE);

                Random random = new Random();
                int i = random.nextInt(12);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,delay);
            }
        };
        handler.post(runnable);


    }

    public void saveScore(){
        storedScore = savedHighScore.getInt("storedData",0);
        if (score>storedScore)
        savedHighScore.edit().putInt("storedData",score).apply();
    }
}