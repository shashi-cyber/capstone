package com.example.AMS;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;
    TextView mSignUp,mForgotPassword;
    CheckBox mShowPassword;
    String email;
    String password;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mSignUp = findViewById(R.id.createAccount);
        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.emailId);
        mPassword = (EditText) findViewById(R.id.password);
        mForgotPassword = (TextView) findViewById(R.id.forgotpswd);
        mShowPassword = (CheckBox) findViewById(R.id.shwpswd);

        mShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mShowPassword.isChecked()) mPassword.setTransformationMethod(new PasswordTransformationMethod());
                else mPassword.setTransformationMethod(null);
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

    }

    public void signUp(View view){
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }


    public void signIn(View view){


         email = mEmail.getText().toString();
         password = mPassword.getText().toString();

        if(!mEmail.getText().toString().isEmpty())
        {
            if(!mPassword.getText().toString().isEmpty())
            {
                signInUser();
            }
            else mPassword.setError("Please enter a password");
        }
        else
        {
            mEmail.setError("Please enter your email address");
            if(mPassword.getText().toString().isEmpty())
            {
                mPassword.setError("Please enter a password");
            }

        }

    }

    private void signInUser()
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Signing in..",Toast.LENGTH_SHORT).show();
                            Log.d("SignIn", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user);
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignIn", "signInWithEmail:failure", task.getException());
                            if(task.getException().getMessage().contains("password"))
                            {
                                mPassword.setError("The password is invalid");
                            }
                            Toast.makeText(SignIn.this, "Authentication failed : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }

                        // ...
                    }
                });
    }
}


