package com.mahmoudjoe3.travel_guide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    Button findOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.layer2).animate().translationY(-2000).setDuration(800).setStartDelay(600);
        //findViewById(R.id.container).animate().translationY(-1800).setDuration(800).setStartDelay(600);
        Animation back_anim= AnimationUtils.loadAnimation(this,R.anim.back_alpha);
        findViewById(R.id.back).startAnimation(back_anim);

        findOut=findViewById(R.id.find);
        findOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                findOut.setBackground(getDrawable(R.drawable.active_btn));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent show=new Intent(Main2Activity.this,Main3Activity.class);
                        startActivity(show);
                    }
                },1000);

            }
        });
    }


}
