package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {
    private Button changePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        toFrontPage();
        toAboutPage();
    }
    public void toFrontPage()
    {
        changePage = findViewById(R.id.frontPageFromContact);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frontPage();
            }
        });
    }
    public void toAboutPage()
    {
        changePage = findViewById(R.id.aboutPageFromContact);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPage();
            }
        });
    }


    public void frontPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void aboutPage()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
