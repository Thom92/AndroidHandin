package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);
        //Retrofit is used to create a Json Placeholder APi

        //addConverter is used to pass the JSON file to the response
        retrofit = new Retrofit.Builder().baseUrl("https://api.covid19api.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //Retrofit creates the implementation for this method
        getCountries();
    }

    private void getCountries()
    {
        Call<List<Countries>> call = jsonPlaceHolderApi.getCountries();
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if(!response.isSuccessful())
                {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
               List<Countries> countries = response.body();


                for(Countries data : countries)
                {
                    String content = "";
                    content += "Country: " + data.getCountries() + "\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
