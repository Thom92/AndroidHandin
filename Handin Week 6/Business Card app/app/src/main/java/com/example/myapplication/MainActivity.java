package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button changePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void toAboutPage(View view)
    {
        changePage = (Button) findViewById(R.id.aboutPageFromFront);

        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPage();
            }
        });
    }
    public void toContactPage(View view)
    {
        changePage = (Button) findViewById(R.id.contactPageFromFront);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactPage();
            }
        });
    }
    public void aboutPage()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void contactPage()
    {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }




}
