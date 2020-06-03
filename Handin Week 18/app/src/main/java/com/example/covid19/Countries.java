package com.example.covid19;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Countries {

    @SerializedName("Countries")
    private ArrayList<Data> countries;

    public ArrayList<Data> getCountries() {
        return countries;
    }
}
