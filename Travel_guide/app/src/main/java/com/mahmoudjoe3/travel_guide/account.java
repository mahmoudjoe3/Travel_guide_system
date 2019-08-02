package com.mahmoudjoe3.travel_guide;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class account extends AppCompatActivity {


    private String Country,Interest;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    Intent switcher=new Intent(account.this,Main.class);
                    switcher.putExtra("country",Country);
                    switcher.putExtra("interest",Interest);
                    startActivity(switcher);
                    return true;

                case R.id.navigation_favorite:
                    Intent switcher1=new Intent(account.this,favorite.class);
                    switcher1.putExtra("country",Country);
                    switcher1.putExtra("interest",Interest);
                    startActivity(switcher1);
                    return true;

                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Country=getIntent().getStringExtra("country");
        Interest=getIntent().getStringExtra("interest");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu =navView.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
    }



}
