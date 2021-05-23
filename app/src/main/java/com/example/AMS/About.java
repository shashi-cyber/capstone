package com.example.AMS;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView about = findViewById(R.id.horti);
        about.setText("This app is made to Detect the  plant disease by scanning the diseased leaf ." +"\n\n"+
                "This is beneficial as it reduces a large work of monitoring in big farms of crops, and at very early stage itself it detects the symptoms of disease and also suggests the medicine and fertilizers for various crops ." +"\n\n"+
                "In additional to this ,this app can also facilitate to find the seasonal crops and best crops for soil and accordingly fertilizers to be used." +"\n\n"+
                " In this app we can see the weather conditions for selected areas.");
    }
}
