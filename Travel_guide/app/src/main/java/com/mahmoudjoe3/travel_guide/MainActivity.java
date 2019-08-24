package com.mahmoudjoe3.travel_guide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity {

    Button login;
    ImageView layer;
    GifImageButton louding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.angry_btn);
        louding=findViewById(R.id.gifImageButton);
        layer=findViewById(R.id.layer2);


        findViewById(R.id.clover).animate().alpha(0).setDuration(800).setStartDelay(1600);
        layer.animate().translationY(-2000).setDuration(800).setStartDelay(1600);
        findViewById(R.id.morning).animate().alpha(0).translationY(-800).setDuration(800).setStartDelay(1600);

        Animation head = AnimationUtils.loadAnimation(this,R.anim.apphead_anim);
        findViewById(R.id.head).startAnimation(head);

        findViewById(R.id.about).setVisibility(View.INVISIBLE);






        login.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                louding.setVisibility(View.VISIBLE);


                //for delay
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                            layer.animate().translationY(0).setDuration(800).setStartDelay(600);
                            findViewById(R.id.head).setVisibility(View.INVISIBLE);

                            /////////////////////
                            new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Intent i=new Intent(MainActivity.this,ListOne.class);
                                    startActivity(i);
                                    /*
                                    Intent show = new Intent(MainActivity.this, Main2Activity.class);
                                    startActivity(show);*/
                                    louding.setVisibility(View.INVISIBLE);
                                    layer.animate().translationY(-2000).setDuration(800).setStartDelay(600);
                                    overridePendingTransition(R.anim.fede_in,R.anim.fede_out);
                                    //findViewById(R.id.head).setVisibility(View.VISIBLE);
                                    finish();

                                }
                            }, 2000);
                            //////////////////////


                    }

                },1000);}

        });
    }
}
