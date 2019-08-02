package com.mahmoudjoe3.travel_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class wellcome_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_page);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                findViewById(R.id.layer2).animate().translationY(0).setDuration(800).setStartDelay(600);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(wellcome_page.this,MainActivity.class);
                        startActivity(intent);
                    }
                },1500);
            }
        },6000);

    }
}
