package com.mahmoudjoe3.travel_guide;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    return true;

                case R.id.navigation_favorite:
                    Intent switcher1=new Intent(Main.this,favorite.class);
                    startActivity(switcher1);
                    return true;

                case R.id.navigation_account:
                    Intent switcher2=new Intent(Main.this,account.class);
                    startActivity(switcher2);
                    return true;
            }
            return false;
        }
    };


    private String Country,Interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu =navView.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);

        Country=getIntent().getStringExtra("country");
        Interest=getIntent().getStringExtra("interest");

        ((TextView) findViewById(R.id.textView1)).setText(Country);
        ((TextView) findViewById(R.id.textView2)).setText(Interest);



    }





}
