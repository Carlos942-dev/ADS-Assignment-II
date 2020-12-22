package com.ninja.fruitclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FruitService {

 @POST("RMIWebServer/Controller")
 Call<FruitResponse> addVeg(@Body FruitRequest fruitRequest);

 @POST("RMIWebServer/UpdateFruitPrice")
 Call<FruitResponse> updateVeg(@Body FruitRequest fruitRequest);

 @POST("RMIWebServer/DeleteFruitPrice")
 Call<FruitResponse> deleteVeg(@Body FruitRequest fruitRequest);

 @GET("RMIWebServer/CalculateCost")
 Call<ReceiptResponse> fetch();

 @GET("RMIWebServer/CalFruitCost")
 Call<CostResponse> cost();



}
