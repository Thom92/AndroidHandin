package com.example.customlayout;
import com.example.customlayout.model.ProgrammingLanguages;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customlayout.model.ProgrammingLanguages;

import java.util.List;

public class ListviewAdapter extends ArrayAdapter<ProgrammingLanguages> { //Needs to extend arrayadapter, type fighter

    Context context; //Context is needed to inflate
    int resource;
    List<ProgrammingLanguages> languagesList;

    //Constructor adapter
    public ListviewAdapter(Context context, int resource, List<ProgrammingLanguages> languagesListn){
        super(context,resource,languagesListn);
        this.context = context;
        this.resource = resource;
        this.languagesList = languagesListn;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context); //Inflater

        View view = inflater.inflate(resource,null); //To inflate the view
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewSport = view.findViewById(R.id.textViewSport);
        ImageView imageView = view.findViewById(R.id.imageView);

        //All the data to be displayed
        ProgrammingLanguages languages = languagesList.get(position);
        textViewName.setText(languages.getName());
        imageView.setImageDrawable(context.getResources().getDrawable(languages.getImage()));

        //Anonymous class for onclick listener
        view.findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);

            }
        });
        //Returns the view
        return view;
    }
    private void removeItem(final int index){ //delete method, final cause its an innerclass
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Have you had enough of a programming language?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Will delete the selected fighter.
                languagesList.remove(index);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dont do anything, just close the dialog in case of no.
            }
        });

        //Create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
