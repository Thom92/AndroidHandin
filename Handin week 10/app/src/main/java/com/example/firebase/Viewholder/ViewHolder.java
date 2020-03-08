package com.example.firebase.Viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.DetailActivity;
import com.example.firebase.MainActivity;
import com.example.firebase.R;
import com.example.firebase.Storage.FirebaseRepo;

public class ViewHolder extends RecyclerView.ViewHolder {

    int rowNumber = 0;
    TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textViewInList);
        handleLongPress();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new intent. Get context from the view passed as a param.
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                // Add the rownumber to the intent, so we can get it back
                // "on the other side" in DetailActivity
                intent.putExtra(MainActivity.INDEX_KEY, rowNumber);
                // again use the view to get the context, in order to start activity
                view.getContext().startActivity(intent);
            }
        });

    }

    private void handleLongPress() {
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i("all", "long click pressed");
                FirebaseRepo.deleteNote(rowNumber);
                return true; // should be true.
            }
        });
    }

    public void setPosition(int position) {
        rowNumber = position;
        textView.setText(FirebaseRepo.notes.get(position).getHead());
    }
}
