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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;
    EditText mFirstName;
    EditText mLastName;
    EditText mPhoneNumber;
    CheckBox mShowPassword;

    private FirebaseAuth mAuth;
    private String email = null;
    private String password = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.emailId);
        mPassword = (EditText) findViewById(R.id.password);
        mFirstName = (EditText) findViewById(R.id.firstName);
        mLastName = (EditText) findViewById(R.id.lastName);
        mPhoneNumber = (EditText) findViewById(R.id.phoneNo);
        mShowPassword = (CheckBox) findViewById(R.id.checkBox);

        mShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mShowPassword.isChecked()) mPassword.setTransformationMethod(new PasswordTransformationMethod());
                else mPassword.setTransformationMethod(null);
            }
        });

    }


    public void signup(View view){

        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if(!mFirstName.getText().toString().isEmpty())
        {
            if(!mLastName.getText().toString().isEmpty())
            {
                if(!mPhoneNumber.getText().toString().isEmpty())
                {
                    if(!mEmail.getText().toString().isEmpty())
                    {
                        if(!mPassword.getText().toString().isEmpty())
                        {
                            createUser();
                        }
                        else mPassword.setError("Please enter a password");
                    }
                    else mEmail.setError("Please enter your email address");
                }
                else mPhoneNumber.setError("Please enter a phone number");
            }
            else mLastName.setError("Please enter your last name");
        }
        else
        {
            mFirstName.setError("Please enter your first name");
            if(!mLastName.getText().toString().isEmpty())
            {
                if(!mPhoneNumber.getText().toString().isEmpty())
                {
                    if(!mEmail.getText().toString().isEmpty())
                    {
                        if(!mPassword.getText().toString().isEmpty())
                        {
                            createUser();
                        }
                        else mPassword.setError("Please enter a password");
                    }
                    else mEmail.setError("Please enter your email address");
                }
                else mPhoneNumber.setError("Please enter a phone number");
            }
            else
            {
                mLastName.setError("Please enter your last name");
                if(!mPhoneNumber.getText().toString().isEmpty())
                {
                    if(!mEmail.getText().toString().isEmpty())
                    {
                        if(!mPassword.getText().toString().isEmpty())
                        {
                            createUser();
                        }
                        else mPassword.setError("Please enter a password");
                    }
                    else mEmail.setError("Please enter your email address");
                }
                else{
                    mPhoneNumber.setError("Please enter a phone number");
                    if(!mEmail.getText().toString().isEmpty())
                    {
                        if(!mPassword.getText().toString().isEmpty())
                        {
                            createUser();
                        }
                        else mPassword.setError("Please enter a password");
                    }
                    else {
                        mEmail.setError("Please enter your email address");
                        if(!mPassword.getText().toString().isEmpty())
                        {
                            createUser();
                        }
                        else mPassword.setError("Please enter a password");
                    }
                }
            }
        }

    }

    public void createUser()
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SingUp", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(SignUp.this, "Authentication Successful.",
                                    Toast.LENGTH_SHORT).show();

                            //                            user.sendEmailVerification()
                            //                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                            //                                        @Override
                            //                                        public void onComplete(@NonNull Task<Void> task) {
                            //                                            if (task.isSuccessful()){
                            //                                                Log.d("log", "email sent");
                            //                                            }
                            //                                        }
                            //                                    });


                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignUp", "createUserWithEmail:failure", task.getException());
                            if (task.getException().getMessage().contains("email address is badly formatted"))
                            {
                                mEmail.setError("Please enter a valid email address");
                                Toast.makeText(SignUp.this, "Please enter a valid email address",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else if(task.getException().getMessage().contains("The given password is invalid"))
                            {
                                mPassword.setError("Password should be at least 6 characters");
                                Toast.makeText(SignUp.this, "Password should be at least 6 characters",
                                        Toast.LENGTH_SHORT).show();
                            }

                            //  updateUI(null);
                        }

                        // ...
                    }
                });
    }
//
//    public void setContentView(int contentView) {
//        this.contentView = contentView;
//    });


}
