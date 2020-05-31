package com.example.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DetailFragment extends Fragment {
    public DetailFragment()
    {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        String token1 = "";
        String token2 = "";
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        InputStream file2 = this.getResources().openRawResource(R.raw.text2);
        InputStream file1 = this.getResources().openRawResource(R.raw.text);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(file1));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(file2));

        TextView textView = view.findViewById(R.id.detailTextview);
        Bundle bundle = this.getArguments();
        int data = bundle.getInt("key");

        if (data == 0)
        {

            if (file1 != null) {
                try {
                    while ((token1 = reader1.readLine()) != null) {
                        sb1.append(token1 + "\n");
                    }
                    textView.setText(sb1);
                    file1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (data == 1) {


            if (file2 != null) {
                try {
                    while ((token2 = reader2.readLine()) != null) {
                        sb2.append(token2 + "\n");
                    }
                    textView.setText(sb2);
                    file2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return view;
    }

}


