package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView signup_redirect;
    private TextView forgot_password;
    private EditText l_email;
    private EditText l_pass;
    private Button log_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        l_email = findViewById(R.id.email);
        l_pass = findViewById(R.id.password);

        signup_redirect = findViewById(R.id.signupText);
        forgot_password = findViewById(R.id.forgotPassword);
        log_button = findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();

        log_button.setOnClickListener(v -> loginUser());
        forgot_password.setOnClickListener(v ->startActivity(new Intent(this,ForgotPassword.class)));

        signup_redirect.setOnClickListener(v -> startActivity(new Intent(this,SignUp.class)));

    }

    private void loginUser() {
        String email = l_email.getText().toString();
        String password = l_pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            l_email.setError("Email cannot be empty");
            l_email.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            l_pass.setError("Password cannot be empty");
            l_pass.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"User logged in successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }else{
                        Toast.makeText(Login.this,"Login Unsuccessful " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    public void onBackPressed(){

        Toast.makeText(getApplicationContext(), " Tata :) ", Toast.LENGTH_SHORT).show();
        finishAffinity();
        System.exit(0);

    }
}