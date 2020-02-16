package com.example.stringfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity
{

    private TextView textView;
    private final String file = "text.data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        saveToFile("File", file);
        Object object = readFromFile(file);
        String text = (String) object;
    }

    private void saveToFile(Object object, String filename)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(getFileObject(filename));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            Log.i("Okay", "Writing");

        }
        catch (Exception e)
        {
            System.out.println("Error during file initialization: ");
            Log.i("All", "error writing " + e.getMessage());
        }
    }

    private File getFileObject(String filename)
    {
        File path = getFilesDir();
        return new File(path, filename);
    }

    private Object readFromFile(String filename)
    {
        Object object = null;
        try
        {
            FileInputStream fileInputStream = new FileInputStream(getFileObject(filename));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception e)
        {
            Log.i("Error", "went wrong" + e.getMessage());
        }
        return object;
    }


}
