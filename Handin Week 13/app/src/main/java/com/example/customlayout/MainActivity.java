package com.example.customlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.customlayout.model.ProgrammingLanguages;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ProgrammingLanguages> languagesList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        languagesList = new ArrayList<>();

        //Adds fighters to list.
        languagesList.add(new ProgrammingLanguages(R.drawable.android,"Android"));
        languagesList.add(new ProgrammingLanguages(R.drawable.python,"Python"));
        languagesList.add(new ProgrammingLanguages(R.drawable.nodejs,"Nodejs"));


        listView = findViewById(R.id.listView);

        //Adapter created, passing this as context, the list view as layout, and the languagelist
        ListviewAdapter adapter = new
                ListviewAdapter(this,R.layout.my_list_item,languagesList);

        listView.setAdapter(adapter);

    }
}
