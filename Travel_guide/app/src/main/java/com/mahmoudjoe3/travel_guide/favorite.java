package com.mahmoudjoe3.travel_guide;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class favorite extends AppCompatActivity {

    private String Country,Interest;
public ArrayAdapter<String> adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    Intent switcher=new Intent(favorite.this,Main.class);
                    switcher.putExtra("country",Country);
                    switcher.putExtra("interest",Interest);
                    startActivity(switcher);
                    overridePendingTransition(R.anim.fede_in2,R.anim.fede_out2);
                    finish();
                    return true;

                case R.id.navigation_favorite:
                    return true;

                case R.id.navigation_account:
                    Intent switcher2=new Intent(favorite.this,account.class);
                    switcher2.putExtra("country",Country);
                    switcher2.putExtra("interest",Interest);
                    startActivity(switcher2);
                    overridePendingTransition(R.anim.fede_in2,R.anim.fede_out2);
                    finish();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Country=getIntent().getStringExtra("country");
        Interest=getIntent().getStringExtra("interest");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu =navView.getMenu();
        MenuItem menuItem=menu.getItem(1);
        menuItem.setChecked(true);

        ListView l = (ListView) findViewById(R.id.list);
         SQLiteHelper s1 = new SQLiteHelper(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        l.setAdapter(adapter);
        adapter.clear();

        try {
            Cursor cursor = s1.ShowAllFavouriteHotels();


            while (!cursor.isAfterLast()) {
                adapter.add(cursor.getString(1));
                cursor.moveToNext();

            }
            cursor = s1.ShowAllFavouriteSites();

            while (!cursor.isAfterLast()) {
                adapter.add(cursor.getString(1));
                cursor.moveToNext();

            }

        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

}
