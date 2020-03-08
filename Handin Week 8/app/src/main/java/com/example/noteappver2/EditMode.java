package com.example.noteappver2;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.widget.EditText;

public class EditMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mode);

        EditText editText = (EditText) findViewById(R.id.editText2);

        Intent intent = getIntent();
        //-1 because that id is imposible to get
        final int noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1){
            editText.setText(MainActivity.list.get(noteId));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //MainActivity.list.set(noteId, String.valueOf(s));
                //MainActivity.adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity.list.set(noteId, String.valueOf(s));
            }
        });
    }

}
