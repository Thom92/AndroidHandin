package com.example.firebase;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.Storage.FirebaseRepo;

public class DetailActivity extends AppCompatActivity {

    EditText editTextHead;
    EditText editTextBody;
    private int row = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextHead = findViewById(R.id.editTextHead);
        editTextBody = findViewById(R.id.editTextBody);
        // need to know row number
        row = getIntent().getIntExtra(MainActivity.INDEX_KEY, 0);
        editTextHead.setText(FirebaseRepo.getNote(row).getHead());
        editTextBody.setText(FirebaseRepo.getNote(row).getBody());
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseRepo.editNote(row, editTextHead.getText().toString(), editTextBody.getText().toString());
    }
}
