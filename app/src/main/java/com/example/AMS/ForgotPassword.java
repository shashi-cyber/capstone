package com.example.AMS;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    EditText mEmailAddress;
    Button mReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEmailAddress = findViewById(R.id.forgotEmail);
        mReset = findViewById(R.id.pswdResetBtn);
        mAuth = FirebaseAuth.getInstance();

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mEmailAddress.getText().toString().isEmpty()) mEmailAddress.setError("Please enter an email address");
                else {
                    mAuth.sendPasswordResetEmail(mEmailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Password reset email successfully sent",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPassword.this,SignIn.class);
                                startActivity(intent);
                            }
                            else {
                                if(task.getException().getMessage().contains("There is no user record")) mEmailAddress.setError("Please enter an existing email address");
                                if(task.getException().getMessage().contains("email address is badly formatted")) mEmailAddress.setError("Please enter a valid email address");

                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


    }
}