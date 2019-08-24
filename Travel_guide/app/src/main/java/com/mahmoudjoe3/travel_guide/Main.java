package com.mahmoudjoe3.travel_guide;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

public class Main extends AppCompatActivity {

    private String Country,Interest;
    boolean founded=false;
    boolean clicked=true;

     String city_name,sites_or_hotel;
     SQLiteHelper s1;
     Button b1,b2,b3,b4,b5,link1,link2,link3,link4,link5;

     ImageView I1,I2,I3,I4,I5;

     TextView t1,t2,t3,t4,t5;

     CardView c3,c4,c5;
     Intent browser;
     String searchkey;

    BottomNavigationView navView;
    Menu menu;
    MenuItem menuItem;

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
                    overridePendingTransition(R.anim.fede_in2,R.anim.fede_out2);
                    finish();

                    return true;

                case R.id.navigation_account:
                    browser=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.booking.com/index.ar.html?aid=356980&label=gog235jc-1FCAYY7wcoUDhtSDNYA2hDiAEBmAEBuAEZyAEM2AEB6AEB-AENiAIBqAIDuAKZwJfqBcACAQ&sid=bebdaca7e8f8766137b736474dc49f94&click_from_logo=1"));
                    startActivity(browser);
                    overridePendingTransition(R.anim.fede_in2,R.anim.fede_out2);

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu =navView.getMenu();
        menuItem=menu.getItem(0);
        menuItem.setChecked(true);

        init();
        setfav();
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
        link1=findViewById(R.id.link1);
        link2=findViewById(R.id.link2);
        link3=findViewById(R.id.link3);
        link4=findViewById(R.id.link4);
        link5=findViewById(R.id.link5);
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
    private void checkfav(Button b)
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
                b.setBackgroundResource(R.drawable.activelike);

            }
            else {
                s1.delete_site(s[0]);
                Toast.makeText(Main.this, "deleted ", Toast.LENGTH_SHORT).show();
                b.setBackgroundResource(R.drawable.unactivelike);

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
                b.setBackgroundResource(R.drawable.activelike);


            } else {
                s1.delete_hotel(s[0]);
                Toast.makeText(Main.this, "deleted ", Toast.LENGTH_SHORT).show();
                b.setBackgroundResource(R.drawable.unactivelike);

            }

        }
    }
    public void allproject (String city,String site_or_hotel)
    {

        Cursor cursor;
        sites_or_hotel=site_or_hotel;
        city_name=city;
        /*if(!sites_or_hotel.equals("sites"))
        {
            c3.setVisibility(View.INVISIBLE);
            c4.setVisibility(View.INVISIBLE);
            c5.setVisibility(View.INVISIBLE);

        }
*/
        if(sites_or_hotel.equals("sites"))
        {
            cursor= s1.GetSites(city_name);
        }
        else {
            cursor = s1.GetHotels(city_name);
            if (city_name == "Egypt") {
                I3.setImageResource(R.drawable.e3);
                t3.setText("Billionaire Gerald Chan’s luxury country house, only an hour from London and surrounded by 400 acres of grounds, is something rather magical. Rooms are homely yet stylish, Skye Gyngell is on-hand as culinary director and guests can swim in the lake");
                I4.setImageResource(R.drawable.e4);
                t4.setText("The best castles to stay in England, hand-picked by our experts. We personally stay in every castle we feature and love sharing our experiences to help others. If you'd like to discuss castle hotels in England");
                I5.setImageResource(R.drawable.e5);
                t5.setText("The MERCURE BRIGHTON SEAFRONT is a Tourist hotel. Located in Sea Front area. Shopping is within walking distance and the nightlife/restaurants are located in the hotel");
            }
            else if (city_name == "England") {
                I3.setImageResource(R.drawable.e4);
                t3.setText("The best castles to stay in England, hand-picked by our experts. We personally stay in every castle we feature and love sharing our experiences to help others. If you'd like to discuss castle hotels in England");

                I4.setImageResource(R.drawable.e3);
                t4.setText("Billionaire Gerald Chan’s luxury country house, only an hour from London and surrounded by 400 acres of grounds, is something rather magical. Rooms are homely yet stylish, Skye Gyngell is on-hand as culinary director and guests can swim in the lake");
                I5.setImageResource(R.drawable.e5);
                t5.setText("The MERCURE BRIGHTON SEAFRONT is a Tourist hotel. Located in Sea Front area. Shopping is within walking distance and the nightlife/restaurants are located in the hotel");
            }
            else
            {
                I3.setImageResource(R.drawable.e5);
                t3.setText("The MERCURE BRIGHTON SEAFRONT is a Tourist hotel. Located in Sea Front area. Shopping is within walking distance and the nightlife/restaurants are located in the hotel");

                I4.setImageResource(R.drawable.e3);
                t4.setText("Billionaire Gerald Chan’s luxury country house, only an hour from London and surrounded by 400 acres of grounds, is something rather magical. Rooms are homely yet stylish, Skye Gyngell is on-hand as culinary director and guests can swim in the lake");
                I5.setImageResource(R.drawable.e5);
                t5.setText("The MERCURE BRIGHTON SEAFRONT is a Tourist hotel. Located in Sea Front area. Shopping is within walking distance and the nightlife/restaurants are located in the hotel");

            }
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
                checkfav(b1);
                /*if(clicked==true&&!founded)
                {
                    b1.setBackgroundResource(R.drawable.activelike);
                    clicked=false;
                }
                else
                {
                    b1.setBackgroundResource(R.drawable.unactivelike);
                    clicked=true;
                }*/

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=t2.getText().toString().split(":");
                founded=false;
                checkfav(b2);


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                s=t3.getText().toString().split(":");
                founded=false;
                checkfav(b3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                s=t4.getText().toString().split(":");
                founded=false;
                checkfav(b4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                s=t5.getText().toString().split(":");
                founded=false;
                checkfav(b5);
            }
        });



  //for location
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t1.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(getlocation(searchkey)));
                startActivity(browser);
            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t2.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(getlocation(searchkey)));
                startActivity(browser);
            }
        });

        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t3.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(getlocation(searchkey)));
                startActivity(browser);
            }
        });
        link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t4.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(getlocation(searchkey)));
                startActivity(browser);
            }
        });
        link5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t5.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(getlocation(searchkey)));
                startActivity(browser);
            }
        });

    //for hotel website
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t1.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(gethotelwebsite(searchkey)));
                startActivity(browser);
            }
        });
        I2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t2.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(gethotelwebsite(searchkey)));
                startActivity(browser);
            }
        });

        I3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t3.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(gethotelwebsite(searchkey)));
                startActivity(browser);
            }
        });
        I4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t4.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(gethotelwebsite(searchkey)));
                startActivity(browser);
            }
        });
        I5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchkey=(t5.getText().toString().split(":"))[0];
                browser=new Intent(Intent.ACTION_VIEW, Uri.parse(gethotelwebsite(searchkey)));
                startActivity(browser);
            }
        });

    }

    private String getlocation(String searchKey)
    {
        String location="";



        if (searchKey.equals("The Great Pyramids")) {
            location="https://www.google.com/maps/place/The+Great+Pyramid+of+Giza/@29.9792391,31.1320132,17z/data=!3m1!4b1!4m5!3m4!1s0x14584587ac8f291b:0x810c2f3fa2a52424!8m2!3d29.9792345!4d31.1342019";

        } else if (searchKey.equals("Luxor's Karnak Temple")) {
            location="https://www.google.com/maps/place/Karnak/@25.7188346,32.6550816,17z/data=!3m1!4b1!4m5!3m4!1s0x1449159228fec0cd:0xc71ae8c008c259d8!8m2!3d25.7188346!4d32.6572703";

        } else if (searchKey.equals("St.Catherine Monastery")) {
            location="https://www.google.com/maps/place/Saint+Catherine's+Monastery/@28.5559484,33.9738593,17z/data=!3m1!4b1!4m5!3m4!1s0x1454901def4fa789:0xc91f754605c6dadf!8m2!3d28.5559484!4d33.976048";

        } else if (searchKey.equals("Siwa Oasis")) {
            location="https://www.google.com/maps/place/Siwa+Oasis,+Qesm+Siwah,+Matrouh+Governorate/@29.2057406,25.386751,11z/data=!3m1!4b1!4m5!3m4!1s0x147aaface8f3a523:0x6f335df8f19a074d!8m2!3d29.2031708!4d25.5195451";

        } else if (searchKey.equals("White Desert")) {
            location="https://www.google.com/maps/place/White+Desert/@27.3306764,28.1866693,17z/data=!3m1!4b1!4m5!3m4!1s0x1469919c247c8fa9:0x6298fc9f6ef78f7d!8m2!3d27.3306764!4d28.188858";

        } else if (searchKey.equals("Eiffel Tower")) {
            location="https://www.google.com/maps/place/Eiffel+Tower/@48.8583701,2.2922926,17z/data=!3m1!4b1!4m5!3m4!1s0x47e66e2964e34e2d:0x8ddca9ee380ef7e0!8m2!3d48.8583701!4d2.2944813";

        } else if (searchKey.equals("Louvre Museum")) {
            location="https://www.google.com/maps/place/Louvre+Museum/@48.8606111,2.3354553,17z/data=!3m1!4b1!4m5!3m4!1s0x47e671d877937b0f:0xb975fcfa192f84d4!8m2!3d48.8606111!4d2.337644";

        } else if (searchKey.equals("Palace of Versailles")) {
            location="https://www.google.com/maps/place/Palace+of+Versailles/@48.8048649,2.1181667,17z/data=!3m1!4b1!4m5!3m4!1s0x47e67d94d7b14c75:0x538fcc15f59ce8f!8m2!3d48.8048649!4d2.1203554";

        } else if (searchKey.equals("Mont Saint-Michel")) {
            location="https://www.google.com/maps/place/50170+Mont+Saint-Michel,+France/@48.6363484,-1.5987569,12z/data=!3m1!4b1!4m5!3m4!1s0x480ea8d67c9ceeb3:0xc5834ce47e0dc3fe!8m2!3d48.636063!4d-1.511457";

        } else if (searchKey.equals("Loire Valley Châteaux")) {
            location="https://www.google.com/maps/place/Ch%C3%A2teau+de+Chenonceau/@47.4080646,-4.0131978,6z/data=!4m8!1m2!2m1!1sLoire+Valley+Ch%C3%A2teaux!3m4!1s0x47fcb0d5d9a0a8a1:0x261259efc9ff1001!8m2!3d47.3248696!4d1.0703005";

        } else if (searchKey.equals("Stonehenge")) {
            location="https://www.google.com/maps/place/Stonehenge/@51.1323881,-1.9540918,11z/data=!4m8!1m2!2m1!1sStonehenge!3m4!1s0x0:0x979170e2bcd3d2dd!8m2!3d51.1788856!4d-1.8262196";

        } else if (searchKey.equals("Tower of London")) {
            location="https://www.google.com/maps/place/Tower+of+London/@51.5081124,-0.078138,17z/data=!3m1!4b1!4m5!3m4!1s0x48760349331f38dd:0xa8bf49dde1d56467!8m2!3d51.5081124!4d-0.0759493 ";

        } else if (searchKey.equals("The Roman Baths and Georgian City of Bath")) {
            location="https://www.google.com/maps/place/The+Roman+Baths/@51.381072,-2.3618077,17z/data=!3m1!4b1!4m5!3m4!1s0x48718113ded530b5:0xe46be6814f1224d!8m2!3d51.381072!4d-2.359619";

        } else if (searchKey.equals("Canterbury Cathedral")) {
            location="https://www.google.com/maps/place/Canterbury+Cathedral/@51.2797971,1.0806111,17z/data=!3m1!4b1!4m5!3m4!1s0x47deca4aa51185d9:0xd5cea13e62d94916!8m2!3d51.2797971!4d1.0827998 ";

        } else if (searchKey.equals("Eden Project")) {
            location="https://www.google.com/maps/place/Eden+Project/@50.3601344,-4.7469065,17z/data=!3m1!4b1!4m5!3m4!1s0x486b6f66daf3a1b5:0x5660ab505756c241!8m2!3d50.3601344!4d-4.7447178";

        } else if (searchKey.equals("Colosseum")) {
            location="https://www.google.com/maps/place/Colosseum/@41.8902102,12.4900422,17z/data=!3m1!4b1!4m5!3m4!1s0x132f61b6532013ad:0x28f1c82e908503c4!8m2!3d41.8902102!4d12.4922309 ";

        } else if (searchKey.equals("Venice Canals")) {
            location="https://www.google.com/maps/place/Venice+Canals/@33.9835027,-118.4698692,17z/data=!3m1!4b1!4m5!3m4!1s0x80c2baa2bf5a7289:0x96e7291be897f918!8m2!3d33.9835027!4d-118.4676805 ";

        } else if (searchKey.equals("Pompeii")) {
            location="https://www.google.com/maps/place/80045+Pompei,+Metropolitan+City+of+Naples,+Italy/@40.7465984,14.4236419,12z/data=!3m1!4b1!4m5!3m4!1s0x133bbc95914ba4ef:0xd2d18a72aeb414a4!8m2!3d40.7461572!4d14.4989344 ";

        } else if (searchKey.equals("Leaning Tower of Pisa")) {
            location="https://www.google.com/maps/place/Leaning+Tower+of+Pisa/@43.722952,10.3944083,17z/data=!3m1!4b1!4m5!3m4!1s0x12d591a6c44e88cd:0x32eca9b1d554fc03!8m2!3d43.722952!4d10.396597 ";

        } else if (searchKey.equals("Vatican City")) {
            location="https://www.google.com/maps/place/00120+Vatican+City/@41.903815,12.4345516,14z/data=!3m1!4b1!4m5!3m4!1s0x1325890a57d42d3d:0x94f9ab23a7eb0!8m2!3d41.902916!4d12.453389";

        } else if (searchKey.equals("Editor's PickBerlin's Brandenburg Gate")) {
            location="https://www.google.com/maps/place/Brandenburg+Gate/@52.5162746,13.3755154,17z/data=!3m1!4b1!4m5!3m4!1s0x47a851c655f20989:0x26bbfb4e84674c63!8m2!3d52.5162746!4d13.3777041";

        } else if (searchKey.equals("Cologne Cathedral (Kölner Dom)")) {
            location="https://www.google.com/maps/place/Cologne+Cathedral/@50.9412784,6.9560927,17z/data=!3m2!4b1!5s0x47bf25baabc20433:0x312b7d4db7d02b48!4m5!3m4!1s0x47bf25a5369c3d2f:0x29ec913896e3a9c6!8m2!3d50.9412784!4d6.9582814 ";

        } else if (searchKey.equals("The Black Forest")) {
            location="https://www.google.com/maps/place/Black+Forest/@48.1838482,3.7426629,6z/data=!3m1!4b1!4m5!3m4!1s0x4790ec232f1791bd:0xdbe2bec8ae87edf1!8m2!3d48.2774862!4d8.185997";

        } else if (searchKey.equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
            location="https://www.google.com/maps/place/Neuschwanstein+Castle/@47.557574,10.7476117,17z/data=!3m1!4b1!4m5!3m4!1s0x479cf7cac44ea35d:0xc8a6866bd39dbba3!8m2!3d47.557574!4d10.7498004 ";

        } else if (searchKey.equals("Berlin's Museum Island")) {
            location="https://www.google.com/maps/place/Museum+Island/@52.5168826,13.3846314,14z/data=!3m1!4b1!4m5!3m4!1s0x47a851df3c8bcac7:0x936c396c9e126807!8m2!3d52.5169328!4d13.4018997 ";

        } else if (searchKey.equals("Fairmont Nile City, Cairo")) {
            location="https://www.google.com/maps/place/Fairmont+Nile+City+Hotel,+Cairo/@30.0719936,31.2255448,17z/data=!3m1!4b1!4m8!3m7!1s0x145840fa057818c7:0x3f7e03a153cbeafc!5m2!4m1!1i2!8m2!3d30.0719936!4d31.2277335 ";

        } else if (searchKey.equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
            location="https://www.google.com/maps/place/Aqua+Hotel+Resort+and+Spa/@30.0719936,31.2255448,17z/data=!4m8!3m7!1s0x14534c3a3a06f63d:0xb4fcde3dbb49b5e7!5m2!4m1!1i2!8m2!3d28.0283036!4d34.4354546 ";

        } else if (searchKey.equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {
            location="https://www.google.com/maps/place/Disneyland+Hotel/@48.8708823,2.7787907,17z/data=!3m1!4b1!4m8!3m7!1s0x47e61d1971f9af8b:0x1a635ad691123731!5m2!4m1!1i2!8m2!3d48.8708823!4d2.7809794";

        } else if (searchKey.equals("Kanopée Village - Campground, Trévoux, Ain")) {
            location="https://www.google.com/maps/place/Camping+Sites+and+Landscapes+Kanopée+Village/@45.939277,4.7647113,17z/data=!3m1!4b1!4m8!3m7!1s0x47f4906718bfdde5:0x264e98b517681a9e!5m2!4m1!1i2!8m2!3d45.939277!4d4.7669 ";

        } else if (searchKey.equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
            location="https://www.google.com/maps/place/Karma+St.+Martin's/@49.9663221,-6.3057514,17z/data=!3m1!4b1!4m8!3m7!1s0x48400efdf04c1dfd:0x1e0a4d0edb910e90!5m2!4m1!1i2!8m2!3d49.9663221!4d-6.3035627 ";

        } else if (searchKey.equals("Village House Hotel - Inn, Findon")) {
            location="https://www.google.com/maps/place/Village+House+Coaching+Inn/@50.8684117,-0.4094929,17z/data=!3m1!4b1!4m8!3m7!1s0x4875a2a0fbeefd0f:0x3d6bcf0d5a117a5!5m2!4m1!1i2!8m2!3d50.8684117!4d-0.4073042";

        } else if (searchKey.equals("Strand Hotel Terme Delfini, Ischia")) {
            location="https://www.google.com/maps/place/Strand+Hotel+Delfini/@40.726144,13.9569273,17z/data=!3m1!4b1!4m8!3m7!1s0x133b6ae103562f53:0xffcb0581c33d6eee!5m2!4m1!1i2!8m2!3d40.726144!4d13.959116";

        } else if (searchKey.equals("Villa Casale")) {
            location="https://www.google.com/maps/place/Villa+Casale/@39.1760549,12.2434131,7z/data=!4m11!1m2!2m1!1sVilla+Casale!3m7!1s0x133b959ffc39e24d:0x604758b05b97f6c1!5m2!4m1!1i2!8m2!3d40.6483975!4d14.6119192";


        } else if (searchKey.equals("LEGOLAND Feriendorf, Günzburg")) {
            location="https://www.google.com/maps/place/Legoland+Deutschland+Resort/@48.4250473,10.3091277,17z/data=!3m1!4b1!4m8!3m7!1s0x47994557c354d26b:0x9b53ec35a480d7d5!5m2!4m1!1i2!8m2!3d48.4250473!4d10.3113164";

        } else if (searchKey.equals("Hotel Ling Bao, Walberberg, Bornheim")) {
            location="https://www.google.com/maps/place/Hotel+Ling+Bao+-+Phantasialand+Erlebnishotel/@50.7983562,6.8820975,17z/data=!3m1!4b1!4m8!3m7!1s0x47bf180de6a4e1f1:0xe632225abe67e1f0!5m2!4m1!1i2!8m2!3d50.7983562!4d6.8842862";

        }



        return location;
    }


    private String gethotelwebsite(String searchKey)
    {
        String websitelink="";



        if (searchKey.equals("The Great Pyramids")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A3%D9%87%D8%B1%D8%A7%D9%85_%D8%A7%D9%84%D8%AC%D9%8A%D8%B2%D8%A9";

        } else if (searchKey.equals("Luxor's Karnak Temple")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A7%D9%84%D9%83%D8%B1%D9%86%D9%83";

        } else if (searchKey.equals("St.Catherine Monastery")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%AF%D9%8A%D8%B1_%D8%B3%D8%A7%D9%86%D8%AA_%D9%83%D8%A7%D8%AA%D8%B1%D9%8A%D9%86";

        } else if (searchKey.equals("Siwa Oasis")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%B3%D9%8A%D9%88%D8%A9";

        } else if (searchKey.equals("White Desert")) {
            websitelink="https://en.wikipedia.org/wiki/White_Desert_National_Park";

        } else if (searchKey.equals("Eiffel Tower")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A8%D8%B1%D8%AC_%D8%A5%D9%8A%D9%81%D9%84";

        } else if (searchKey.equals("Louvre Museum")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%85%D8%AA%D8%AD%D9%81_%D8%A7%D9%84%D9%84%D9%88%D9%81%D8%B1";

        } else if (searchKey.equals("Palace of Versailles")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%82%D8%B5%D8%B1_%D9%81%D8%B1%D8%B3%D8%A7%D9%8A";

        } else if (searchKey.equals("Mont Saint-Michel")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%AC%D8%A8%D9%84_%D8%A7%D9%84%D9%82%D8%AF%D9%8A%D8%B3_%D9%85%D9%8A%D8%B4%D9%8A%D9%84";

        } else if (searchKey.equals("Loire Valley Châteaux")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%88%D8%A7%D8%AF%D9%8A_%D8%A7%D9%84%D9%84%D9%88%D8%A7%D8%B1";

        } else if (searchKey.equals("Stonehenge")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%B3%D8%AA%D9%88%D9%86%D9%87%D9%86%D8%AC";

        } else if (searchKey.equals("Tower of London")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A8%D8%B1%D8%AC_%D9%84%D9%86%D8%AF%D9%86";

        } else if (searchKey.equals("The Roman Baths and Georgian City of Bath")) {
            websitelink="https://en.wikipedia.org/wiki/Roman_Baths,_Strand_Lane";

        } else if (searchKey.equals("Canterbury Cathedral")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%83%D8%A7%D8%AA%D8%AF%D8%B1%D8%A7%D8%A6%D9%8A%D8%A9_%D9%83%D8%A7%D9%86%D8%AA%D8%B1%D8%A8%D8%B1%D9%8A";

        } else if (searchKey.equals("Eden Project")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%85%D8%B4%D8%B1%D9%88%D8%B9_%D8%B9%D8%AF%D9%86";

        } else if (searchKey.equals("Colosseum")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%83%D9%88%D9%84%D9%88%D8%B3%D9%8A%D9%88%D9%85";

        } else if (searchKey.equals("Venice Canals")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A7%D9%84%D9%82%D9%86%D8%A7%D9%84_%D8%A7%D9%84%D9%83%D8%A8%D9%8A%D8%B1_(%D9%81%D9%8A%D9%86%D9%8A%D8%B3%D9%8A%D8%A7)";

        } else if (searchKey.equals("Pompeii")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A8%D9%88%D9%85%D8%A8%D9%8A";

        } else if (searchKey.equals("Leaning Tower of Pisa")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A8%D8%B1%D8%AC_%D8%A8%D9%8A%D8%B2%D8%A7_%D8%A7%D9%84%D9%85%D8%A7%D8%A6%D9%84";

        } else if (searchKey.equals("Vatican City")) {
            websitelink="https://en.wikipedia.org/wiki/Vatican_City";

        } else if (searchKey.equals("Editor's PickBerlin's Brandenburg Gate")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A8%D9%88%D8%A7%D8%A8%D8%A9_%D8%A8%D8%B1%D8%A7%D9%86%D8%AF%D9%86%D8%A8%D9%88%D8%B1%D8%BA";

        } else if (searchKey.equals("Cologne Cathedral (K?lner Dom)")) {
            websitelink="https://ar.wikipedia.org/wiki/%D9%83%D8%A7%D8%AA%D8%AF%D8%B1%D8%A7%D8%A6%D9%8A%D8%A9_%D9%83%D9%88%D9%84%D9%88%D9%86%D9%8A%D8%A7";

        } else if (searchKey.equals("The Black Forest")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%A7%D9%84%D8%BA%D8%A7%D8%A8%D8%A9_%D8%A7%D9%84%D8%B3%D9%88%D8%AF%D8%A7%D8%A1";

        } else if (searchKey.equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
            websitelink="https://en.wikipedia.org/wiki/Neuschwanstein_Castle";

        } else if (searchKey.equals("Berlin's Museum Island")) {
            websitelink="https://ar.wikipedia.org/wiki/%D8%AC%D8%B2%D9%8A%D8%B1%D8%A9_%D8%A7%D9%84%D9%85%D8%AA%D8%A7%D8%AD%D9%81";

        } else if (searchKey.equals("Fairmont Nile City, Cairo")) {
            websitelink="https://www.fairmont.com/nile-city-cairo/?cmpid=google_cai_search-branded_me-branded-e-revsh&gclid=CjwKCAjw4ZTqBRBZEiwAHHxpfvlaxZW2NdcMl4lV5cmnkv7aBJBWjKHLpFkOkhqtb1ej5STIKGl4fRoCdZwQAvD_BwE";

        } else if (searchKey.equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
            websitelink="https://www.booking.com/hotel/eg/aqua-resort-and-spa-sharm-el-sheikh.ar.html?aid=356980;label=gog235jc-1DCA0oQ0IjYXF1YS1yZXNvcnQtYW5kLXNwYS1zaGFybS1lbC1zaGVpa2hIAVgDaEOIAQGYAQG4ARfIAQzYAQPoAQH4AQKIAgGoAgO4AuTZl-oFwAIB;sid=df1802cd0a8bb1da1842c3cd2c6be55e";

        } else if (searchKey.equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {
            websitelink="https://www.tripadvisor.com/Hotel_Review-g1182377-d235644-Reviews-Disneyland_Hotel-Chessy_Marne_la_Vallee_Seine_et_Marne_Ile_de_France.html";
        } else if (searchKey.equals("Kanopée Village - Campground, Trévoux, Ain")) {
            websitelink="https://www.kanopee-village.com/fr";

        } else if (searchKey.equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
            websitelink="https://www.britainsfinest.co.uk/hotels/karma-st-martins?gclid=CjwKCAjw4ZTqBRBZEiwAHHxpfiMRUUZP9C4w-MQL-IAd9aTjm2h_kmaMEF3sds6E_GGrEiO4Pj4oEBoCDiYQAvD_BwE";

        } else if (searchKey.equals("Village House Hotel - Inn, Findon")) {
            websitelink="https://www.expedia.com/Village-House-Hotel-Inn.h3856325.Hotel-Information?chkin=8%2F3%2F2019&chkout=8%2F4%2F2019&regionId=177868&destination=Worthing%2C+England%2C+United+Kingdom&swpToggleOn=true&selected=3856325&rm1=a2&x_pwa=1&sort=recommended&SEMCID=US.UB.GOOGLE.PT-c-EN.HOTEL&SEMDTL=a1390002428.b153781188625.r1d144f3cb7214a4110321a638a610ab815cfa6fab21d56ca542dbef7dbe819e41.g1kwd-304651860622.i1.d1280259506455.e1c.j121469.k11006725.f11t1.n1.l1g.h1e.m1&GCLID=CjwKCAjw4ZTqBRBZEiwAHHxpfu-D4TrWAVAXueUMVW4Bte6irdye44lqO-P0cr82OPQkGGSfs8sXzxoC3dYQAvD_BwE&rfrr=HSR&pwa_ts=1564864235413";

        } else if (searchKey.equals("Strand Hotel Terme Delfini, Ischia")) {
            websitelink="http://en.hoteldelfini.it/";

        } else if (searchKey.equals("Villa Casale")) {
            websitelink="https://www.tripadvisor.com/Hotel_Review-g194873-d1116167-Reviews-Villa_Casale-Ravello_Amalfi_Coast_Province_of_Salerno_Campania.html";


        } else if (searchKey.equals("LEGOLAND Feriendorf, Günzburg")) {
            websitelink="https://www.expedia.de/Guenzburg-Hotels-LEGOLAND-Feriendorf.h4532850.Hotel-Beschreibung?chkin=03.08.2019&chkout=04.08.2019&rm1=a2&hwrqCacheKey=c3694efc-591e-49e1-85a4-a323bbdcaca0HWRQ1564864706605&cancellable=false&regionId=181171&vip=false&c=66aa771b-7c85-4b62-a1e5-be4351b1b88e&";

        } else if (searchKey.equals("Hotel Ling Bao, Walberberg, Bornheim")) {
            websitelink="https://www.booking.com/hotel/de/ling-bao.ar.html?aid=325251;label=ling-bao-lTH7OjeOz%2AhWbkLQxqWOLAS7215780723%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap1t1%3Aneg%3Afi%3Atiaud-181873118643%3Akwd-6281043166%3Alp21469%3Ali%3Adec%3Adm;sid=df1802cd0a8bb1da1842c3cd2c6be55e;dest_id=-1753303;dest_type=city;dist=0;hapos=1;hpos=1;room1=A%2CA;sb_price_type=total;sr_order=popularity;srepoch=1564864820;srpvid=7653915a8e34023b;type=total;ucfs=1&#hotelTmpl";

        }



        return websitelink;
    }


    private void setfav()
    {
        Cursor cursor=s1.ShowAllFavouriteSites();

        while (!cursor.isAfterLast()) {
            //site
            //egyptCountry="France";Country="Egypt";Country="England";Country="Italy";Country="Germany";
            if(Interest=="site") {
                if (Country == "Egypt") {
                    if (cursor.getString(1).equals("The Great Pyramids")) {
                        b1.setBackgroundResource(R.drawable.activelike);
                    } else if (cursor.getString(1).equals("Luxor's Karnak Temple")) {
                        b2.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("St.Catherine Monastery")) {
                        b3.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Siwa Oasis")) {
                        b4.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("White Desert")) {
                        b5.setBackgroundResource(R.drawable.activelike);

                    }
                }
                else if(Country=="France") {
                    //france
                    if (cursor.getString(1).equals("Eiffel Tower")) {
                        b1.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Louvre Museum")) {
                        b2.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Palace of Versailles")) {
                        b3.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Mont Saint-Michel")) {
                        b4.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Loire Valley Châteaux")) {
                        b5.setBackgroundResource(R.drawable.activelike);

                    }
                }
                //england
                else if(Country=="England") {
                    if (cursor.getString(1).equals("Stonehenge")) {
                        b1.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Tower of London")) {
                        b2.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("The Roman Baths and Georgian City of Bath")) {
                        b3.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Canterbury Cathedral")) {
                        b4.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Eden Project")) {
                        b5.setBackgroundResource(R.drawable.activelike);

                    }
                }
                //italy
                else if(Country=="Italy") {
                    if (cursor.getString(1).equals("Colosseum")) {
                        b1.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Venice Canals")) {
                        b2.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Pompeii")) {
                        b3.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Leaning Tower of Pisa")) {
                        b4.setBackgroundResource(R.drawable.activelike);

                    } else if (cursor.getString(1).equals("Vatican City")) {
                        b5.setBackgroundResource(R.drawable.activelike);

                    }
                }
                //germany
                else if(Country=="Germany") {
                    if (cursor.getString(1).equals("Editor's PickBerlin's Brandenburg Gate")) {
                    b1.setBackgroundResource(R.drawable.activelike);

                } else if (cursor.getString(1).equals("Cologne Cathedral (Kölner Dom)")) {
                    b2.setBackgroundResource(R.drawable.activelike);

                } else if (cursor.getString(1).equals("The Black Forest")) {
                    b3.setBackgroundResource(R.drawable.activelike);

                } else if (cursor.getString(1).equals("The Ultimate Fairytale Castle: Neuschwanstein")) {
                    b4.setBackgroundResource(R.drawable.activelike);

                } else if (cursor.getString(1).equals("Berlin's Museum Island")) {
                    b5.setBackgroundResource(R.drawable.activelike);

                }
            }
            //hotel
                else {
                    //Egypt
                    if(Country=="Egypt") {
                        if (cursor.getString(1).equals("Fairmont Nile City, Cairo")) {
                            b1.setBackgroundResource(R.drawable.activelike);

                        } else if (cursor.getString(1).equals("Aqua Hotel Resort & Spa, Sharm El-Sheikh")) {
                            b2.setBackgroundResource(R.drawable.activelike);

                        }
                    }
                    //France
                    else if(Country=="France") {
                        if (cursor.getString(1).equals("Disneyland Hotel, Chessy, Seine-et-Marne")) {
                            b1.setBackgroundResource(R.drawable.activelike);

                        } else if (cursor.getString(1).equals("Kanopée Village - Campground, Trévoux, Ain")) {
                            b2.setBackgroundResource(R.drawable.activelike);

                        }
                    }
                    //England
                    else if(Country=="England") {
                        if (cursor.getString(1).equals("Karma St. Martin's Hotel, Lower Town, Isles of Scilly")) {
                            b1.setBackgroundResource(R.drawable.activelike);

                        } else if (cursor.getString(1).equals("Village House Hotel - Inn, Findon")) {
                            b2.setBackgroundResource(R.drawable.activelike);

                        }
                    }
                    //Italy
                    else if(Country=="Italy") {
                        if (cursor.getString(1).equals("Strand Hotel Terme Delfini, Ischia")) {
                            b1.setBackgroundResource(R.drawable.activelike);

                        } else if (cursor.getString(1).equals("Villa Casale")) {
                            b2.setBackgroundResource(R.drawable.activelike);

                        }
                    }
                    //Germany
                    else if(Country=="Germany") {
                        if (cursor.getString(1).equals("LEGOLAND Feriendorf, Günzburg")) {
                            b1.setBackgroundResource(R.drawable.activelike);

                        } else if (cursor.getString(1).equals("Hotel Ling Bao, Walberberg, Bornheim")) {
                            b2.setBackgroundResource(R.drawable.activelike);

                        }
                    }
                }
        }
            cursor.moveToNext();
    }

}
}
