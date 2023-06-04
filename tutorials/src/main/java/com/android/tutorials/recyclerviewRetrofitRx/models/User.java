package com.android.tutorials.recyclerviewRetrofitRx.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String model;

    public String getModel() {
        return model;
    }
}
