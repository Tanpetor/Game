package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button jump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jump = findViewById(R.id.jump);
        jump.setOnClickListener(v ->{
            if(Content.sun.y - 100 >= 0){
                Content.sun.y -= 100;
            }
        });
    }
}