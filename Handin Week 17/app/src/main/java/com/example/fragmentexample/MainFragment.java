package com.example.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the view first
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        final String[] menuItems = {"First Article", "Second Article",
        "Third Article",
        "Fourth Article"};

        ListView listView = view.findViewById(R.id.mainMenuListview);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,menuItems);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id)
           {
               DetailFragment detailFragment = new DetailFragment();
               FragmentManager fragmentManager = getFragmentManager();

               Bundle bundle = new Bundle();
               bundle.putInt("key",position);
               detailFragment.setArguments(bundle);
               fragmentManager.beginTransaction(    ).replace(R.id.container, detailFragment).commit(); //replace fragment
           }
       });

        return view;
    }

}
