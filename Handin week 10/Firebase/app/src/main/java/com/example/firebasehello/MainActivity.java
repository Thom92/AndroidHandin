package com.example.firebasehello;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.firebasehello.storage.Repo;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Repo.uploadFile(this);
        imageView = findViewById(R.id.imageView);
        Repo.downloadImage("programmer.png",imageView); //calls download method,
        // imgview as param so it can be updated when file arrives
    }

    public void galleryBtnPressed(View view){
        Intent intent = new Intent(Intent.ACTION_PICK); //Implicit intent,
        // allows user to choose among different services to do this task
        intent.setType("image/*"); //Sets type of content
        startActivityForResult(intent,1); //starts activity, we expect an answer
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) { //-1 is OK
                Bundle extras = data.getExtras(); // asks for data from incomming intent
                Bitmap bitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bitmap);
            } else {
                System.out.println("failed to get img");
            }
        } if (requestCode == 2){
            if (resultCode == -1){ //then it wprls

            }
        }
    }

    public void cameraBtnPressed(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // asks for some app to handle camera
        startActivityForResult(intent,2); // new requestcode to identify
    }

}