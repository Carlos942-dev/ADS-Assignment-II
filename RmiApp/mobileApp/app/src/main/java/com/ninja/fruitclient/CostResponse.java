package com.ninja.fruitclient;

public class CostResponse {
    String msg;
    String code;
    String cost;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceipt() {
        return cost;
    }

    public void setReceipt(String receipt) {
        this.cost = receipt;
    }
}
