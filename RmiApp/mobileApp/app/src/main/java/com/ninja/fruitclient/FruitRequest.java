package com.ninja.fruitclient;

public class FruitRequest {
    String fruitName;
    String price;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVegName() {
        return fruitName;
    }
    public void setVegName(String fruitName) {
        this.fruitName = fruitName;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

}
