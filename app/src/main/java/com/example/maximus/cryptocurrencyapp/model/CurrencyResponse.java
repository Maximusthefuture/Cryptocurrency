package com.example.maximus.cryptocurrencyapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrencyResponse {
    @SerializedName("data")
    @Expose
    private List<Model> data = null;

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }
}

