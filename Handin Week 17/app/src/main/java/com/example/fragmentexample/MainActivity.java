package com.example.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainMenuFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,mainMenuFragment).commit();


    }


}
