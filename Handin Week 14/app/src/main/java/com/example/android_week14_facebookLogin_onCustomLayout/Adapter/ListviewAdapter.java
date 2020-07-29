package com.example.android_week14_facebookLogin_onCustomLayout.Adapter;


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

import com.example.android_week14_facebookLogin_onCustomLayout.Model.Fighter;
import com.example.android_week14_facebookLogin_onCustomLayout.R;

import java.util.List;

public class ListviewAdapter extends ArrayAdapter<Fighter> { //Needs to extend arrayadapter, type fighter

    Context mCtx; //Context is needed to inflate
    int resource;
    List<Fighter> fighterList;

    //Constructor adapter
    public ListviewAdapter(Context mCtx, int resource, List<Fighter> fighterList){
        super(mCtx,resource,fighterList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.fighterList = fighterList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx); //Inflater

        View view = inflater.inflate(resource,null); //To inflate the view
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewSport = view.findViewById(R.id.textViewSport);
        ImageView imageView = view.findViewById(R.id.imageView);

        //All the data to be displayed
        Fighter fighter = fighterList.get(position); //Fighter object of specified position
        textViewName.setText(fighter.getName());
        textViewSport.setText(fighter.getSport());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(fighter.getImage())); //deprecated method but works

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
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Do you wish to delete the fighter?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Will delete the selected fighter.
                fighterList.remove(index);
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
