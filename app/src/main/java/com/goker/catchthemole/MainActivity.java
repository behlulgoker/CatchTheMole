package com.goker.catchthemole;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView highScoreText;
    int highScore;
    int lastHighScore;
    Intent lastScore;
    SharedPreferences savedHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        highScoreText = findViewById(R.id.highScoreText);
        savedHighScore = this.getSharedPreferences("com.goker.catchthemole", Context.MODE_PRIVATE);
        highScore = savedHighScore.getInt("storedData",0);;
        highScoreText.setText("High Score : "+ highScore);


    }

    public void newGame(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}