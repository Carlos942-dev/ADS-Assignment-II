package com.ninja.fruitclient;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewCost extends AppCompatActivity  {
   private TextView cost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cost);
        cost = findViewById(R.id.cost);
        getCost();
    }



    public void getCost() {
        Call<CostResponse> responseCall = ApiClient.getVegService().cost();
        responseCall.enqueue(new Callback<CostResponse>() {
            @Override
            public void onResponse(Call<CostResponse> call, Response<CostResponse> response) {
                if (response.isSuccessful()) {
                    if(response != null) {
                      String c = response.body().cost;
                      cost.setText(c);
                    }
                } else {
                }

            }

            @Override
            public void onFailure(Call<CostResponse> call, Throwable t) {
                Toast.makeText(ViewCost.this, "failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}