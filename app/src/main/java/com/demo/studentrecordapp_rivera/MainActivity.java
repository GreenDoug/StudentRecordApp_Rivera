package com.demo.studentrecordapp_rivera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText id, name, course, address, contact;
    TextView output;
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
        output = findViewById(R.id.output);
    }

    public void save(View v) {
        try {
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write("Student ID: " + id.getText().toString() + "\n");
            outputWriter.write("Student Name: " + name.getText().toString() + "\n");
            outputWriter.write("Course and Section: " + course.getText().toString() + "\n");
            outputWriter.write("Residence Address: " + address.getText().toString() + "\n");
            outputWriter.write("Contact Number: " + contact.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieve(View v){
        try{
            String sID = id.getText().toString();
            String sName = name.getText().toString();
            String sCourseSection = course.getText().toString();
            String sAddress = address.getText().toString();
            String sContact = contact.getText().toString();
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "I am " + sName + " with " + sID + " taken up " + sCourseSection + " with " + sAddress + ", for any question you may contact me at " + sContact + "\n\n\n\n";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer))>0){
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }
            InputRead.close();
            output.setText(s);

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