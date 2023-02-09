package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button button1;

    EditText l_email;
    EditText l_pass;
    Button log_button;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_email = findViewById(R.id.email);
        l_pass = findViewById(R.id.Password);

        button1 = findViewById(R.id.register);
        log_button = findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();

        log_button.setOnClickListener(v -> loginUser());

        button1.setOnClickListener(v -> Register());

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
                        Toast.makeText(Login.this,"User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }else{
                        Toast.makeText(Login.this,"Login Unsuccessful" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }



    public void Register() {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
}