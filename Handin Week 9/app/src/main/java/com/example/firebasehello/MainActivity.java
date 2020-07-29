package com.example.firebasehello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.firebasehello.adapter.MyRecycleViewAdapter;
import com.example.firebasehello.model.Note;
import com.example.firebasehello.storage.MemoryStorage;



public class MainActivity extends AppCompatActivity {
    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView editText1;
    TextView editText2;
    MyRecycleViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter();
        recyclerView.setAdapter(adapter);
        startNoteListener();
    }


    private void editNote() {
        //edit
        DocumentReference docRef = db.collection(notes).document("8K2cP9EnkTpZ5xNTlawm");
        Map<String, String> map = new HashMap<>();
        map.put("head", "changed head");
        map.put("body", "changed body");
        docRef.set(map);
    }

    private void deleteNote() {
        DocumentReference docRef = db.collection(notes).document("MMc3yCJqpl3ebmCELsFs");
        docRef.delete();
    }

    private void startNoteListener()
    {
        db.collection(notes).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                if (e != null) {

                } else {
                    MemoryStorage.notes.clear();
                    for (DocumentSnapshot snap : values.getDocuments()) {
                        Log.i("all", "read from FB " + snap.getId());
                        MemoryStorage.notes.add(new Note(snap.getId(),
                                snap.get("head").toString(), snap.get("body").toString()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void addNewNote(View view) {
        DocumentReference docRef = db.collection(notes).document();
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        String s = editText1.getText().toString();
        String k = editText2.getText().toString();
        Map<String, String> map = new HashMap<>();

        map.put("head", s);
        map.put("body", k);
        docRef.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("all", "added successfully");
            }
        });
    }
}
