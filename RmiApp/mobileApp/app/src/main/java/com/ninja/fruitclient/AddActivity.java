package com.ninja.fruitclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    Button add;
    TextInputEditText fruitName,qty,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btn_add);
        fruitName = findViewById(R.id.fruitName);
        price = findViewById(R.id.price);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVeg(vegData());
            }
        });
    }
    public FruitRequest vegData(){
        FruitRequest veg = new FruitRequest();
        veg.setVegName(fruitName.getText().toString());
        veg.setPrice(price.getText().toString());
        return veg;
    }

    public void addVeg(FruitRequest fruitRequest){
         Call<FruitResponse> responseCall = ApiClient.getVegService().addVeg(fruitRequest);
         responseCall.enqueue(new Callback<FruitResponse>() {
             @Override
             public void onResponse(Call<FruitResponse> call, Response<FruitResponse> response) {
                 if(response.isSuccessful()){
                     fruitName.setText("");
                     price.setText("");
                     Intent i = new Intent(getApplicationContext(), ViewFruit.class);
                     i.putExtra("msg","Added");
                     startActivity(i);
                     Toast.makeText(AddActivity.this,"Added",Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(AddActivity.this,"failed",Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<FruitResponse> call, Throwable t) {

             }
         });
    }
}