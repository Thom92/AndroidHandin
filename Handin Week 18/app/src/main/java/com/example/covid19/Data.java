package com.example.covid19;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data
{

    @SerializedName("Country")
    private String country;
    @SerializedName("TotalConfirmed")
    private int totalConfirmed;
    @SerializedName("TotalRecovered")
    private int recovered;

    public String getCountry() {
        return country;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getRecovered() {
        return recovered;
    }

}
