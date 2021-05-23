package com.example.AMS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button agriculture;
    Button diseases;
    Button About;
    ImageButton farmMachine;
    ImageButton mrketPrice;
//    ImageButton storageArea;
    Button weather;
    Button login;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        final FirebaseUser[] user = {mAuth.getCurrentUser()};

        agriculture = findViewById(R.id.agri);
        diseases = findViewById(R.id.diseases);
        About = findViewById(R.id.about);
        weather = findViewById(R.id.weather);
        login = findViewById(R.id.login);


        if (user[0] != null) {
            // Name, email address, and profile photo Url
         //   String name = user.getDisplayName();
            String email = user[0].getEmail();

            login.setText("Log out");
            // Check if user's email is verified
            boolean emailVerified = user[0].isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user[0].getUid();
        }
        else {
            Intent intent = new Intent(MainActivity.this,SignIn.class);
            startActivity(intent);
        }


        diseases.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Diseases.class);
                        startActivity(intent);
                    }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user[0] != null){
                    mAuth.getInstance().signOut();
                    login.setText("Log in");
                    Toast.makeText(MainActivity.this, "Logged out",
                            Toast.LENGTH_LONG).show();
                    user[0] = null;

                    finish();


                }
                if (user[0] == null){
                    Intent intent = new Intent(MainActivity.this, SignIn.class);
                    startActivity(intent);
                }

            }
        });


        agriculture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agriculture.class);
                startActivity(intent);
            }
        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Weather.class);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.AMS.About.class);
                startActivity(intent);
            }
        });

    }

    public void signIn(View view) {
    }
}
