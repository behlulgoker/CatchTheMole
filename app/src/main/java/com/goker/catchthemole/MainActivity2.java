package com.goker.catchthemole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void backToMain (View View){
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }

    public void startNewGame (View view){
        AlertDialog.Builder startMenu = new AlertDialog.Builder(this);
        startMenu.setTitle("Select Game Level");
        startMenu.setCancelable(true);
        startMenu.setNegativeButton("Hard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("userSelection",300);
                startActivity(intent);
            }
        });

        startMenu.setPositiveButton("Medium", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("userSelection",600);
                startActivity(intent);
            }
        });

        startMenu.setNeutralButton("Easy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("userSelection",900);
                startActivity(intent);
            }
        });

        startMenu.show();
    }
}