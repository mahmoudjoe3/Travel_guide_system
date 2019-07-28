package com.mahmoudjoe3.travel_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListOne extends AppCompatActivity implements View.OnClickListener {

    TextView txt_eng,txt_egy,txt_fr,txt_ger,txt_it;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_one);

        txt_eng=findViewById(R.id.txt_ENG);
        txt_egy=findViewById(R.id.txt_EGY);
        txt_fr=findViewById(R.id.txt_FR);
        txt_ger=findViewById(R.id.txt_GER);
        txt_it=findViewById(R.id.txt_IT);

        txt_eng.setOnClickListener(this);
        txt_egy.setOnClickListener(this);
        txt_fr.setOnClickListener(this);
        txt_ger.setOnClickListener(this);
        txt_it.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.txt_ENG:
                intent=new Intent(ListOne.this,ListTwo.class);
                intent.putExtra("country",txt_eng.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_EGY:
                intent=new Intent(ListOne.this,ListTwo.class);
                intent.putExtra("country",txt_egy.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_FR:
                intent=new Intent(ListOne.this,ListTwo.class);
                intent.putExtra("country",txt_fr.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_GER:
                intent=new Intent(ListOne.this,ListTwo.class);
                intent.putExtra("country",txt_ger.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_IT:
                intent=new Intent(ListOne.this,ListTwo.class);
                intent.putExtra("country",txt_it.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
