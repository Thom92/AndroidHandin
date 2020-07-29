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
        toContactPage();
        toAboutPage();
        toEmployeePage();

    }
    public void toAboutPage()
    {
        changePage = (Button) findViewById(R.id.aboutPageFromFront);

        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPage();
            }
        });
    }
    public void toContactPage()
    {
        changePage = (Button) findViewById(R.id.contactPageFromFront);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactPage();
            }
        });
    }
    public void toEmployeePage()
    {
        changePage = findViewById(R.id.employeepageFromFront);
        changePage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                employeePage();
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
    public void employeePage()
    {
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }





}
