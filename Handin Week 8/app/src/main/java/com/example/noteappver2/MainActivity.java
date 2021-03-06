package com.example.noteappver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //File
    private static final String FILE = "NoteAppTextFile.txt";
    //Input field
    private EditText editText;
    //Output field
    private ListView listView;
    static ArrayList<String> list =  new ArrayList<>();


    private boolean inEditMode = false;
    private int currentRow = -1;
    static ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.enterHeadline);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, list);
        listView.setAdapter(adapter);

        //This method selects item on list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inEditMode = true;
                editText.setText(list.get(position));
                currentRow = position;
                Intent intent = new Intent(MainActivity.this, EditMode.class);
                intent.putExtra("NOTE_ID", currentRow);
                startActivity(intent);
            }
        });
    }

    public void ChangeColour(View view) {
        final String colourArray[] = {"#C8FE2E", "#A9E2F3", "#F5A9F2"};

        Button btn = (Button) findViewById(R.id.colour);
        final ConstraintLayout background = (ConstraintLayout) findViewById(R.id.activity);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();

                int colour = rd.nextInt(3-1) + 1;

                background.setBackgroundColor(Color.parseColor(colourArray[colour]));

            }
        });
    }
    public void save(View view){
        String s = editText.getText().toString();
        FileOutputStream fos = null;

        try{
            //FILE is filename, private means that only the file is allowed to access the txt file
            fos = openFileOutput(FILE, MODE_PRIVATE);
            fos.write(s.getBytes());
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(s.length() > 0){

            if(inEditMode){
                list.set(currentRow, s);
                inEditMode = false;
            }else{
                list.add(s);
            }



            adapter.notifyDataSetChanged();
            //Clears text
            editText.getText().clear();
            //Toast is a widget that pops up after a certain action
            //tells the user when the input is saved and where
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE, Toast.LENGTH_LONG).show();
        }

    }

}