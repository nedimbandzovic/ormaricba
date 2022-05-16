package com.example.ormaricba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final Handler handler = new Handler();


        final Runnable r = new Runnable() {
            public void run() {
                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
                overridePendingTransition(0, 0);
            }
        };

        handler.postDelayed(r, 4000);


    }
}