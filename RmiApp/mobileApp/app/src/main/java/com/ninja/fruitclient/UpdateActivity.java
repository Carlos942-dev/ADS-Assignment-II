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

public class UpdateActivity extends AppCompatActivity {

    Button update,delete;
    TextInputEditText fruitName,veId,vePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update = findViewById(R.id.btn_add);
        delete = findViewById(R.id.btn_delete);
        fruitName = findViewById(R.id.fruitName);
        vePrice = findViewById(R.id.vePrice);
        veId = findViewById(R.id.veId);

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String id = getIntent().getStringExtra("id");
        fruitName.setText(name);
        vePrice.setText(price);
        veId.setText(id);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVeg(vegData());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVeg(vegData());
            }
        });
    }
    public FruitRequest vegData(){
        FruitRequest veg = new FruitRequest();
        veg.setVegName(fruitName.getText().toString());
        veg.setPrice(vePrice.getText().toString());
        veg.setId(veId.getText().toString());
        return veg;
    }

    public void updateVeg(FruitRequest fruitRequest){
         Call<FruitResponse> responseCall = ApiClient.getVegService().updateVeg(fruitRequest);
         responseCall.enqueue(new Callback<FruitResponse>() {
             @Override
             public void onResponse(Call<FruitResponse> call, Response<FruitResponse> response) {
                 if(response.isSuccessful()){
                     fruitName.setText("");
                     vePrice.setText("");
                     veId.setText("");
                     Intent i = new Intent(getApplicationContext(), ViewFruit.class);
                     i.putExtra("msg","Updated");
                     startActivity(i);
                     Toast.makeText(UpdateActivity.this,"updated",Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(UpdateActivity.this,"failed",Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<FruitResponse> call, Throwable t) {

             }
         });
    }

    public void deleteVeg(FruitRequest fruitRequest){
        Call<FruitResponse> responseCall = ApiClient.getVegService().deleteVeg(fruitRequest);
        responseCall.enqueue(new Callback<FruitResponse>() {
            @Override
            public void onResponse(Call<FruitResponse> call, Response<FruitResponse> response) {
                if(response.isSuccessful()){
                    fruitName.setText("");
                    vePrice.setText("");
                    veId.setText("");
                    Intent i = new Intent(getApplicationContext(), ViewFruit.class);
                    i.putExtra("msg","Deleted");
                    startActivity(i);
                    Toast.makeText(UpdateActivity.this,"deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateActivity.this,"failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FruitResponse> call, Throwable t) {

            }
        });
    }
}