package com.example.firebase.Storage;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Model.Note;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.firebase.Model.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseRepo {
    public static List<Note> notes = new ArrayList<>();
    private final static String notesPath = "notes";
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static RecyclerView.Adapter adapter;
    public static Note getNote(int index){
        if(index >= notes.size()) return new Note("", "empty", "");
        return notes.get(index);
    }

    static { // make sure the listener starts as soon as possible
        startNoteListener();
    }

    private static void startNoteListener() {  // break until 15.20
        FirebaseRepo.db.collection(notesPath).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                FirebaseRepo.notes.clear();
                for (DocumentSnapshot snap: values.getDocuments()) {
                    Log.i("all", "read from FB " + snap.getId() + " " + snap.get("body").toString());
                    FirebaseRepo.notes.add(new Note(snap.getId(),
                            snap.get("head").toString(), snap.get("body").toString()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public static void editNote(int index, String head, String body) {// add parameters here: index(int), head(String), body(String)
        // Get a Firebase ref. to the current note object
        String id = notes.get(index).getId();
        DocumentReference docRef = FirebaseRepo.db.collection(notesPath).document(id);
        // Make a new map to store all the data (including previous data)
        Map<String,String> map = new HashMap<>();
        map.put("head", head);
        map.put("body", body);
        // overwrites the previous map with the current one.
        docRef.set(map);
    }

    public static void deleteNote(int index) {
        // delete:
        String key = notes.get(index).getId();
        DocumentReference docRef = FirebaseRepo.db.collection(notesPath).document(key);
        docRef.delete();
    }
}
