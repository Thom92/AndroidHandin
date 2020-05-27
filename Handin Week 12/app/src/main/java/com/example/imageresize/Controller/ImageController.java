package com.example.imageresize.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageController {

    private com.example.imageresize.MainActivity mainActivity;

    public ImageController(com.example.imageresize.MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleImageReturn(int requestCode, @Nullable Intent intent) {
        if(requestCode == 0){
            // 2. get the url for the image
            Uri uri = intent.getData();
            try {
                // 3. Create an inputstream to read the file
                InputStream is = mainActivity.getContentResolver().openInputStream(uri);  // the other is ContentProvider
                // 4. Make Bitmap from stream
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                // 5. Set bitmap to imageView
                mainActivity.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == 1){ // it's the camera
            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");  // here, the data itself was provided
            // with the intent

            mainActivity.imageView.setImageBitmap(bitmap);  //assign bitmap to imageView
        }
    }

    public void resizeImage(Bitmap bitmap)
    {
        //Resizes the recieved bitmap and sets the image
        Bitmap resized = Bitmap.createScaledBitmap ( bitmap , 200 , 200 , true );
        mainActivity.imageView.setImageBitmap(resized);
    }
}