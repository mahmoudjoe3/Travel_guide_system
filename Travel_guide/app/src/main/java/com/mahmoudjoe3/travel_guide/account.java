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

    Intent switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu =navView.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    switcher=new Intent(account.this,Main.class);
                    startActivity(switcher);
                    return true;

                case R.id.navigation_favorite:
                    switcher=new Intent(account.this,favorite.class);
                    startActivity(switcher);
                    return true;

                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };
}
