package com.example.android_week14_facebookLogin_onCustomLayout;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_week14_facebookLogin_onCustomLayout.Adapter.ListviewAdapter;
import com.example.android_week14_facebookLogin_onCustomLayout.Model.Fighter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fighter> fighterList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        fighterList = new ArrayList<>();

        //Adds fighters to list.
        fighterList.add(new Fighter(R.drawable.andersonsilva,"Anderson Silva","MMA"));
        fighterList.add(new Fighter(R.drawable.mikethyshon,"Mike Tyson","Boxing"));
        fighterList.add(new Fighter(R.drawable.evanderholyfield,"Evander Holyfield","Boxing"));
        fighterList.add(new Fighter(R.drawable.kessler,"Mikkel Kessler","Boxing"));


        listView = findViewById(R.id.listView);

        //Adapter created, passing this as context, the list view as layout, and the fighterlist
        ListviewAdapter adapter = new
                ListviewAdapter(this,R.layout.my_list_item,fighterList);

        //Atttach the adapter to listview.
        listView.setAdapter(adapter);

    }
}