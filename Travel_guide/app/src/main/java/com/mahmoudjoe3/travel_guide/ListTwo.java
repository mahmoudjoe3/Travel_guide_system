package com.mahmoudjoe3.travel_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListTwo extends AppCompatActivity implements View.OnClickListener {
    String country;
    Intent intent;
    TextView txt_hotel,txt_historical, txt_point_of_interest,txt_Transportation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_two);

        country=getIntent().getStringExtra("country");

        txt_hotel=findViewById(R.id.txt_hotel);
        txt_historical=findViewById(R.id.txt_historical);


        txt_hotel.setOnClickListener(this);
        txt_historical.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.txt_hotel:
                intent=new Intent(ListTwo.this,Main.class);
                intent.putExtra("country",country);
                intent.putExtra("interest",txt_hotel.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_historical:
                intent=new Intent(ListTwo.this,Main.class);
                intent.putExtra("country",country);
                intent.putExtra("interest",txt_historical.getText().toString());
                startActivity(intent);
                break;


        }

    }
}
