package com.mahmoudjoe3.travel_guide;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

public class Main extends AppCompatActivity {

    private String Country,Interest;
    boolean founded=false;

     String city_name,sites_or_hotel;
     SQLiteHelper s1;
     Button b1,b2,b3,b4,b5;

     ImageView I1,I2,I3,I4,I5;

     TextView t1,t2,t3,t4,t5;

     CardView c3,c4,c5;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    return true;

                case R.id.navigation_favorite:
                    Intent switcher1=new Intent(Main.this,favorite.class);
                    switcher1.putExtra("country",Country);
                    switcher1.putExtra("interest",Interest);
                    startActivity(switcher1);
                    return true;

                case R.id.navigation_account:
                    Intent switcher2=new Intent(Main.this,account.class);
                    switcher2.putExtra("country",Country);
                    switcher2.putExtra("interest",Interest);
                    startActivity(switcher2);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu =navView.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);

        init();

        allproject(Country,Interest);


    }

    private void init()
    {
        Country=getIntent().getStringExtra("country");
        Interest=getIntent().getStringExtra("interest");
        if(Country.equals("EGYPT"))
            Country="Egypt";
        else if(Country.equals("FRANCE"))
            Country="France";
        else if(Country.equals("ENGLAND"))
            Country="England";
        else if(Country.equals("ITALY"))
            Country="Italy";
        else if(Country.equals("GERMANY"))
            Country="Germany";
        if(Interest.equals("Famous Recommended Hotels"))
        {
            Interest="hotels";
        }
        else if(Interest.equals("Main Historical Site"))
        {
            Interest="sites";
        }

        s1=new SQLiteHelper(this);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);
        I1=(ImageView)findViewById(R.id.imageView1);
        I2=(ImageView)findViewById(R.id.imageView2);
        I3=(ImageView)findViewById(R.id.imageView3);
        I4=(ImageView)findViewById(R.id.imageView4);
        I5=(ImageView)findViewById(R.id.imageView5);
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t4=(TextView)findViewById(R.id.textView4);
        t5=(TextView)findViewById(R.id.textView5);
        c3=(CardView)findViewById(R.id.card3);
        c4=(CardView)findViewById(R.id.card4);
        c5=(CardView)findViewById(R.id.card5);
    }
    String[]s;
    private void checkfav()
    {
        if(sites_or_hotel.equals("sites"))
        {
            Cursor cursor=s1.ShowAllFavouriteSites();


            while(!cursor.isAfterLast())
            {
                if(s[0].equals(cursor.getString(1))){founded=true; break;}
                cursor.moveToNext();

            }
            if(!founded){s1.AddFavouriteSite(s[0]);
                Toast.makeText(Main.this, "Succfully added to favourites ", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(Main.this, "it's already in favourites ", Toast.LENGTH_SHORT).show();

            }

        }
        else {
            Cursor cursor = s1.ShowAllFavouriteHotels();
            while (!cursor.isAfterLast()) {
                if (s[0].equals(cursor.getString(1))) {
                    founded = true;
                    break;
                }
                cursor.moveToNext();

            }
            if (!founded) {
                s1.AddFavouriteHotel(s[0]);
                Toast.makeText(Main.this, "Succfully added to favourites ", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Main.this, "it's already in favourites ", Toast.LENGTH_SHORT).show();

            }

        }
    }
    public void allproject (String city,String site_or_hotel)
    {

        Cursor cursor;
        sites_or_hotel=site_or_hotel;
        city_name=city;
        if(!sites_or_hotel.equals("sites"))
        {
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
            c5.setVisibility(View.INVISIBLE);

        }

        if(sites_or_hotel.equals("sites"))
        {
            cursor=  s1.GetSites(city_name);
        }
        else {
            cursor=  s1.GetHotels(city_name);
        }

        int x=0;

        while(!cursor.isAfterLast()) {
            if(x==0)
            {

                if (cursor.getString(1).toString().equals("The Great Pyramids")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.p);


                } else if (cursor.getString(1).toString().equals("Luxor's Karnak Temple")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.karnak);
                } else if (cursor.getString(1).toString().equals("St.Catherine Monastery")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.cm);

                } else if (cursor.getString(1).toString().equals("Siwa Oasis")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.o);
                } else if (cursor.getString(1).toString().equals("White Desert")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.wd);

                } else if (cursor.getString(1).toString().equals("Eiffel Tower")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.efeel);

                } else if (cursor.getString(1).toString().equals("Louvre Museum")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.lm);

                } else if (cursor.getString(1).toString().equals("Palace of Versailles")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.pv);

                } else if (cursor.getString(1).toString().equals("Mont Saint-Michel")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.msm);

                } else if (cursor.getString(1).toString().equals("Loire Valley Châteaux")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.lv);

                } else if (cursor.getString(1).toString().equals("Stonehenge")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.koko);


                } else if (cursor.getString(1).toString().equals("Tower of London")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.tol);
                } else if (cursor.getString(1).toString().equals("The Roman Baths and Georgian City of Bath")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.bor);
                } else if (cursor.getString(1).toString().equals("Canterbury Cathedral")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.cc);

                } else if (cursor.getString(1).toString().equals("Eden Project")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.ep);

                } else if (cursor.getString(1).toString().equals("Colosseum")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.colo);


                } else if (cursor.getString(1).toString().equals("Venice Canals")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.vcanal);

                } else if (cursor.getString(1).toString().equals("Pompeii")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.pom);

                } else if (cursor.getString(1).toString().equals("Leaning Tower of Pisa")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.pisa);

                } else if (cursor.getString(1).toString().equals("Vatican City")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.vac_city);

                } else if (cursor.getString(1).toString().equals("Editor's PickBerlin's Brandenburg Gate")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.ed);

                } else if (cursor.getString(1).toString().equals("Cologne Cathedral (Kölner Dom)")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.ggg);
                } else if (cursor.getString(1).toString().equals("The Black Forest")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.bf);

                } else if (cursor.getString(1).toString().equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.astle);

                } else if (cursor.getString(1).toString().equals("Berlin's Museum Island")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.bmi);

                } else if (cursor.getString(1).toString().equals("Fairmont Nile City, Cairo")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.nilecity);

                } else if (cursor.getString(1).toString().equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.aquahotel);

                } else if (cursor.getString(1).toString().equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {

                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.disney);

                } else if (cursor.getString(1).toString().equals("Kanopée Village - Campground, Trévoux, Ain")) {

                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.kv);
                } else if (cursor.getString(1).toString().equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.karmahotel);

                } else if (cursor.getString(1).toString().equals("Village House Hotel - Inn, Findon")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.gi);

                } else if (cursor.getString(1).toString().equals("Strand Hotel Terme Delfini, Ischia")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.standhotel);

                } else if (cursor.getString(1).toString().equals("Villa Casale")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.villahotel);


                } else if (cursor.getString(1).toString().equals("LEGOLAND Feriendorf, Günzburg")) {
                    t1.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I1.setImageResource(R.drawable.lfg);

                } else if (cursor.getString(1).toString().equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                    t1.setText(cursor.getString(2).toString());
                    I1.setImageResource(R.drawable.las);


                }

            }
            else  if(x==1)
            {
                if (cursor.getString(1).toString().equals("The Great Pyramids")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.p);

                } else if (cursor.getString(1).toString().equals("Luxor's Karnak Temple")) {

                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.kkkk);
                } else if (cursor.getString(1).toString().equals("St.Catherine Monastery")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.cm);

                } else if (cursor.getString(1).toString().equals("Siwa Oasis")) {

                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.o);
                } else if (cursor.getString(1).toString().equals("White Desert")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.wd);

                } else if (cursor.getString(1).toString().equals("Eiffel Tower")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.efeel);

                } else if (cursor.getString(1).toString().equals("Louvre Museum")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.lm);

                } else if (cursor.getString(1).toString().equals("Palace of Versailles")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.pv);

                } else if (cursor.getString(1).toString().equals("Mont Saint-Michel")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.msm);

                } else if (cursor.getString(1).toString().equals("Loire Valley Châteaux")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.lv);

                } else if (cursor.getString(1).toString().equals("Stonehenge")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.sto);

                } else if (cursor.getString(1).toString().equals("Tower of London")) {

                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.tol);
                } else if (cursor.getString(1).toString().equals("The Roman Baths and Georgian City of Bath")) {

                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.bor);
                } else if (cursor.getString(1).toString().equals("Canterbury Cathedral")) {

                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.cc);

                } else if (cursor.getString(1).toString().equals("Eden Project")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.ep);

                } else if (cursor.getString(1).toString().equals("Colosseum")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.colo);

                } else if (cursor.getString(1).toString().equals("Venice Canals")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.vcvc);

                } else if (cursor.getString(1).toString().equals("Pompeii")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.pom);

                } else if (cursor.getString(1).toString().equals("Leaning Tower of Pisa")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.pisa);

                } else if (cursor.getString(1).toString().equals("Vatican City")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.vac_city);

                } else if (cursor.getString(1).toString().equals("Editor's PickBerlin's Brandenburg Gate")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.ed);

                } else if (cursor.getString(1).toString().equals("Cologne Cathedral (Kölner Dom)")) {

                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.ggg);
                } else if (cursor.getString(1).toString().equals("The Black Forest")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.bf);

                } else if (cursor.getString(1).toString().equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.astle);

                } else if (cursor.getString(1).toString().equals("Berlin's Museum Island")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.bmi);

                } else if (cursor.getString(1).toString().equals("Fairmont Nile City, Cairo")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.nilecity);

                } else if (cursor.getString(1).toString().equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.aquahotel);

                } else if (cursor.getString(1).toString().equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {

                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.disney);
                } else if (cursor.getString(1).toString().equals("Kanopée Village - Campground, Trévoux, Ain")) {

                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.kv);
                } else if (cursor.getString(1).toString().equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.karmahotel);

                } else if (cursor.getString(1).toString().equals("Village House Hotel - Inn, Findon")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.gi);


                } else if (cursor.getString(1).toString().equals("Strand Hotel Terme Delfini, Ischia")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.standhotel);

                } else if (cursor.getString(1).toString().equals("Villa Casale")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2));
                    I2.setImageResource(R.drawable.villahotel);


                } else if (cursor.getString(1).toString().equals("LEGOLAND Feriendorf, Günzburg")) {
                    t2.setText(cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.lfg);

                } else if (cursor.getString(1).toString().equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                    t2.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I2.setImageResource(R.drawable.las);

                }
            }
            else  if(x==2)
            {
                if (cursor.getString(1).toString().equals("The Great Pyramids")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.p);

                } else if (cursor.getString(1).toString().equals("Luxor's Karnak Temple")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.karnak);
                } else if (cursor.getString(1).toString().equals("St.Catherine Monastery")) {
                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.cm);

                } else if (cursor.getString(1).toString().equals("Siwa Oasis")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.o);
                } else if (cursor.getString(1).toString().equals("White Desert")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.wd);

                } else if (cursor.getString(1).toString().equals("Eiffel Tower")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.efeel);

                } else if (cursor.getString(1).toString().equals("Louvre Museum")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.lm);

                } else if (cursor.getString(1).toString().equals("Palace of Versailles")) {
                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.pv);

                } else if (cursor.getString(1).toString().equals("Mont Saint-Michel")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.msm);

                } else if (cursor.getString(1).toString().equals("Loire Valley Châteaux")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.lv);

                } else if (cursor.getString(1).toString().equals("Stonehenge")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.sto);

                } else if (cursor.getString(1).toString().equals("Tower of London")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.tol);
                } else if (cursor.getString(1).toString().equals("The Roman Baths and Georgian City of Bath")) {

                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.bor);
                } else if (cursor.getString(1).toString().equals("Canterbury Cathedral")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.cc);

                } else if (cursor.getString(1).toString().equals("Eden Project")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.ep);

                } else if (cursor.getString(1).toString().equals("Colosseum")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.colo);

                } else if (cursor.getString(1).toString().equals("Venice Canals")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.vcanal);

                } else if (cursor.getString(1).toString().equals("Pompeii")) {
                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.pom);

                } else if (cursor.getString(1).toString().equals("Leaning Tower of Pisa")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.pisa);

                } else if (cursor.getString(1).toString().equals("Vatican City")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.vac_city);

                } else if (cursor.getString(1).toString().equals("Editor's PickBerlin's Brandenburg Gate")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.ed);

                } else if (cursor.getString(1).toString().equals("Cologne Cathedral (Kölner Dom)")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.ggg);

                } else if (cursor.getString(1).toString().equals("The Black Forest")) {
                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.blackf);
                    I3.setMaxWidth(300);
                    I3.setMaxWidth(400);

                } else if (cursor.getString(1).toString().equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.astle);

                } else if (cursor.getString(1).toString().equals("Berlin's Museum Island")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.bmi);

                } else if (cursor.getString(1).toString().equals("Fairmont Nile City, Cairo")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.nilecity);

                } else if (cursor.getString(1).toString().equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.aquahotel);

                } else if (cursor.getString(1).toString().equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {

                    t3.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.disney);
                } else if (cursor.getString(1).toString().equals("Kanopée Village - Campground, Trévoux, Ain")) {

                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.kv);
                } else if (cursor.getString(1).toString().equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.karmahotel);

                } else if (cursor.getString(1).toString().equals("Village House Hotel - Inn, Findon")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.gi);

                } else if (cursor.getString(1).toString().equals("Strand Hotel Terme Delfini, Ischia")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.standhotel);

                } else if (cursor.getString(1).toString().equals("Villa Casale")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.villahotel);

                } else if (cursor.getString(1).toString().equals("LEGOLAND Feriendorf, Günzburg")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.lfg);

                } else if (cursor.getString(1).toString().equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                    t3.setText(cursor.getString(2).toString());
                    I3.setImageResource(R.drawable.las);

                }
            }
            else  if(x==3)
            {
                if (cursor.getString(1).toString().equals("The Great Pyramids")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.p);

                } else if (cursor.getString(1).toString().equals("Luxor's Karnak Temple")) {

                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.karnak);
                } else if (cursor.getString(1).toString().equals("St.Catherine Monastery")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.cm);

                } else if (cursor.getString(1).toString().equals("Siwa Oasis")) {

                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.o);
                } else if (cursor.getString(1).toString().equals("White Desert")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.wd);

                } else if (cursor.getString(1).toString().equals("Eiffel Tower")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.efeel);

                } else if (cursor.getString(1).toString().equals("Louvre Museum")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.lm);

                } else if (cursor.getString(1).toString().equals("Palace of Versailles")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.pv);

                } else if (cursor.getString(1).toString().equals("Mont Saint-Michel")) {
                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.msm);

                } else if (cursor.getString(1).toString().equals("Loire Valley Châteaux")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.lv);

                } else if (cursor.getString(1).toString().equals("Stonehenge")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.sto);

                } else if (cursor.getString(1).toString().equals("Tower of London")) {

                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.tol);
                } else if (cursor.getString(1).toString().equals("The Roman Baths and Georgian City of Bath")) {

                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.bor);
                } else if (cursor.getString(1).toString().equals("Canterbury Cathedral")) {

                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.cc);

                } else if (cursor.getString(1).toString().equals("Eden Project")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.ep);

                } else if (cursor.getString(1).toString().equals("Colosseum")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.colo);

                } else if (cursor.getString(1).toString().equals("Venice Canals")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.vcanal);

                } else if (cursor.getString(1).toString().equals("Pompeii")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.pom);

                } else if (cursor.getString(1).toString().equals("Leaning Tower of Pisa")) {
                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.pisa);

                } else if (cursor.getString(1).toString().equals("Vatican City")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.vac_city);

                } else if (cursor.getString(1).toString().equals("Editor's PickBerlin's Brandenburg Gate")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.ed);

                } else if (cursor.getString(1).toString().equals("Cologne Cathedral (Kölner Dom)")) {

                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.ggg);
                } else if (cursor.getString(1).toString().equals("The Black Forest")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.bf);

                } else if (cursor.getString(1).toString().equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.astle);

                } else if (cursor.getString(1).toString().equals("Berlin's Museum Island")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.bmi);

                } else if (cursor.getString(1).toString().equals("Fairmont Nile City, Cairo")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.nilecity);

                } else if (cursor.getString(1).toString().equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.aquahotel);

                } else if (cursor.getString(1).toString().equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {

                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.disney);
                } else if (cursor.getString(1).toString().equals("Kanopée Village - Campground, Trévoux, Ain")) {

                    t4.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.kv);
                } else if (cursor.getString(1).toString().equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.karmahotel);

                } else if (cursor.getString(1).toString().equals("Village House Hotel - Inn, Findon")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.gi);

                } else if (cursor.getString(1).toString().equals("Strand Hotel Terme Delfini, Ischia")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.standhotel);

                } else if (cursor.getString(1).toString().equals("Villa Casale")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.villahotel);

                } else if (cursor.getString(1).toString().equals("LEGOLAND Feriendorf, Günzburg")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.lfg);

                } else if (cursor.getString(1).toString().equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                    t4.setText(cursor.getString(2).toString());
                    I4.setImageResource(R.drawable.las);

                }
            }
            else  if(x==4)
            {
                if (cursor.getString(1).toString().equals("The Great Pyramids")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.p);

                } else if (cursor.getString(1).toString().equals("Luxor's Karnak Temple")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.karnak);
                } else if (cursor.getString(1).toString().equals("St.Catherine Monastery")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.cm);

                } else if (cursor.getString(1).toString().equals("Siwa Oasis")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.o);
                } else if (cursor.getString(1).toString().equals("White Desert")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.oswd);

                } else if (cursor.getString(1).toString().equals("Eiffel Tower")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.efeel);

                } else if (cursor.getString(1).toString().equals("Louvre Museum")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.lm);

                } else if (cursor.getString(1).toString().equals("Palace of Versailles")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.pv);

                } else if (cursor.getString(1).toString().equals("Mont Saint-Michel")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.msm);

                } else if (cursor.getString(1).toString().equals("Loire Valley Châteaux")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.lv);

                } else if (cursor.getString(1).toString().equals("Stonehenge")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.sto);

                } else if (cursor.getString(1).toString().equals("Tower of London")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.tol);
                } else if (cursor.getString(1).toString().equals("The Roman Baths and Georgian City of Bath")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.bor);
                } else if (cursor.getString(1).toString().equals("Canterbury Cathedral")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.cc);

                } else if (cursor.getString(1).toString().equals("Eden Project")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.ep);

                } else if (cursor.getString(1).toString().equals("Colosseum")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.colo);

                } else if (cursor.getString(1).toString().equals("Venice Canals")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.vcanal);

                } else if (cursor.getString(1).toString().equals("Pompeii")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.pom);

                } else if (cursor.getString(1).toString().equals("Leaning Tower of Pisa")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.pisa);

                } else if (cursor.getString(1).toString().equals("Vatican City")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.vac_city);

                } else if (cursor.getString(1).toString().equals("Editor's PickBerlin's Brandenburg Gate")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.ed);

                } else if (cursor.getString(1).toString().equals("Cologne Cathedral (Kölner Dom)")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.ggg);
                } else if (cursor.getString(1).toString().equals("The Black Forest")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.bf);

                } else if (cursor.getString(1).toString().equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.astle);

                } else if (cursor.getString(1).toString().equals("Berlin's Museum Island")) {
                    t5.setText(cursor.getString(1).toString()+": "+cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.bmi);

                } else if (cursor.getString(1).toString().equals("Fairmont Nile City, Cairo")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.nilecity);

                } else if (cursor.getString(1).toString().equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.aquahotel);

                } else if (cursor.getString(1).toString().equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.disney);
                } else if (cursor.getString(1).toString().equals("Kanopée Village - Campground, Trévoux, Ain")) {

                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.kv);
                } else if (cursor.getString(1).toString().equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.karmahotel);

                } else if (cursor.getString(1).toString().equals("Village House Hotel - Inn, Findon")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.gi);

                } else if (cursor.getString(1).toString().equals("Strand Hotel Terme Delfini, Ischia")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.standhotel);

                } else if (cursor.getString(1).toString().equals("Villa Casale")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.villahotel);

                } else if (cursor.getString(1).toString().equals("LEGOLAND Feriendorf, Günzburg")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.lfg);

                } else if (cursor.getString(1).toString().equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                    t5.setText(cursor.getString(2).toString());
                    I5.setImageResource(R.drawable.las);

                }
            }


            cursor.moveToNext();
            x++;
        }




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=t1.getText().toString().split(":");
                founded=false;
                checkfav();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s=t2.getText().toString().split(":");
                founded=false;
                checkfav();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=t3.getText().toString().split(":");
                founded=false;
                checkfav();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=t4.getText().toString().split(":");
                founded=false;
                checkfav();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[]s=t5.getText().toString().split(":");
                founded=false;
                checkfav();

            }
        });

    }



}
