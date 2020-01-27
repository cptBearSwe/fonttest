package com.apps.bjorn.fonttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                EditText text2 = (EditText)findViewById(R.id.editText1);
                String text = text2.getText().toString();

                writeToFile(text);
                readText();
        }
    }

    static void writeToFile(String text) {
        try {
                //File external = Environment.getExternalStorageDirectory();
                //String sdcardPath = external.getPath();
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file = new File(path, "/" + "myfile.txt");
                file.createNewFile();
                FileWriter filewriter = new FileWriter(file, true);
                BufferedWriter out = new BufferedWriter(filewriter);

                out.write(text );
                out.newLine();
                out.close();
                filewriter.close();
        } catch (Exception e) {
            android.util.Log.d("failed to save file", e.toString());
        }
    }



    public void readText(){
        List<String> lines = new ArrayList<>();
        String rader = null;
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            FileReader reader = new FileReader(path + "/" + "myfile.txt");
            BufferedReader bufferedreader = new BufferedReader(reader);
            while ((rader = bufferedreader.readLine()) != null){
                lines.add(rader);
                }
            Toast.makeText(this,"AntaL rader: " + lines.size(),Toast.LENGTH_LONG).show();
        }
        catch(FileNotFoundException e){
            //Not done yet
            Toast.makeText(this, "FileNotFoundException",Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            //Not done yet
            Toast.makeText(this, "IOException",Toast.LENGTH_LONG).show();
        }
    }
}
