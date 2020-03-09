package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button changePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toFrontPage();
        toContactPage();
    }
    public void toFrontPage()
    {
        changePage = (Button) findViewById(R.id.frontPageFromAbout);

        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frontPage();
            }
        });
    }
    public void toContactPage()
    {
        changePage = (Button) findViewById(R.id.contactPageFromAbout);

        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactPage();
            }
        });
    }

    public void frontPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void contactPage()
    {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
