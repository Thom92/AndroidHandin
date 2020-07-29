package com.example.android_week14_facebookLogin_onCustomLayout.Model;

//Model class
public class Fighter {

    int image; //They have unique ID's which are ints.
    String name, sport;

    public Fighter(int image, String name, String sport) { //Constructor
        this.image = image;
        this.name = name;
        this.sport = sport;
    }

    //Getters
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSport() {
        return sport;
    }
}
