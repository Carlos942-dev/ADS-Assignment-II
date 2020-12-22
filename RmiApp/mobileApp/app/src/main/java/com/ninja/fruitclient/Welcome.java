package com.ninja.fruitclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    private Button addFruit, viewFruit, viewCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        addFruit = findViewById(R.id.addFruit);
        viewFruit = findViewById(R.id.viewFruit);
        viewCost = findViewById(R.id.viewCost);

        addFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });
        viewFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewFruit.class);
                startActivity(i);
            }
        });

        viewCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewCost.class);
                startActivity(i);
            }
        });


    }
}