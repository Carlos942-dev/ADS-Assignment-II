package com.ninja.fruitclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewFruit extends AppCompatActivity implements RecyclerAdapter.OnVegListener {

    private RecyclerView recyclerView;
    private ArrayList<FruitModel> fruitModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fruit);
        recyclerView = findViewById(R.id.view_vegs);
        fruitModelArrayList = new ArrayList<>();
        getReceipt();
    }

    public void setAd() {
        RecyclerAdapter adapter = new RecyclerAdapter(fruitModelArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void getReceipt() {
        Call<ReceiptResponse> responseCall = ApiClient.getVegService().fetch();
        responseCall.enqueue(new Callback<ReceiptResponse>() {
            @Override
            public void onResponse(Call<ReceiptResponse> call, Response<ReceiptResponse> response) {
                if (response.isSuccessful()) {
                    if(response != null) {
                        String receiptData = response.body().getReceipt().substring(0, response.body().getReceipt().length() - 2);
                        if (!"".equalsIgnoreCase(receiptData)) {
                            String data[] = receiptData.split("EN");
                            for (String d : data) {
                                String smd[] = d.split("BR");
                                if (smd != null && smd.length > 0) {
                                    fruitModelArrayList.add(new FruitModel(smd[0].trim(), smd[1].trim(), smd[2].trim()));
                                }
                            }
                            setAd();
                        }
                    }
                } else {
                }

            }

            @Override
            public void onFailure(Call<ReceiptResponse> call, Throwable t) {
                Toast.makeText(ViewFruit.this, "failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onVegClick(int position) {
        fruitModelArrayList.get(position).getName();
        Intent i = new Intent(getApplicationContext(), UpdateActivity.class);
        i.putExtra("name", fruitModelArrayList.get(position).getName());
        i.putExtra("price", fruitModelArrayList.get(position).getPrice());
        i.putExtra("id", fruitModelArrayList.get(position).getId());
        startActivity(i);
    }
}