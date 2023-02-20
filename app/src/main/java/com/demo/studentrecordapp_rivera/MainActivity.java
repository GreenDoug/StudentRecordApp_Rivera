package com.demo.studentrecordapp_rivera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText id, name, course, address, contact;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.sID);
        name = findViewById(R.id.sName);
        course = findViewById(R.id.CourseSection);
        address = findViewById(R.id.Address);
        contact = findViewById(R.id.cNumber);
    }

    public void save(View v) {
        try {
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(id.getText().toString());
            outputWriter.write(name.getText().toString());
            outputWriter.write(course.getText().toString());
            outputWriter.write(address.getText().toString());
            outputWriter.write(contact.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieve(View v){
        try{
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer))>0){
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }
            InputRead.close();
            id.setText(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void clear(View v){
        id = findViewById(R.id.sID);
        name = findViewById(R.id.sName);
        course = findViewById(R.id.CourseSection);
        address = findViewById(R.id.Address);
        contact = findViewById(R.id.cNumber);

        id.setText("");
        name.setText("");
        course.setText("");
        address.setText("");
        contact.setText("");

    }
}