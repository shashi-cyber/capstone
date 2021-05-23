package com.example.AMS;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroScreen extends AppCompatActivity {

    private static int TIME_OUT = 3000;
    private FirebaseAuth mAuth;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser[] user = {mAuth.getCurrentUser()};

        if (user[0] != null) {
             intent = new Intent(IntroScreen.this,MainActivity.class);
        }
        else {
             intent = new Intent(IntroScreen.this,SignIn.class);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }
}
