package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {
    private Button button1;

    EditText r_email;
    EditText pass;
    Button log;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        r_email = findViewById(R.id.email1);
        pass = findViewById(R.id.Password1);
        log = findViewById(R.id.register1);

        button1 = findViewById(R.id.login);
        button1.setOnClickListener(v -> Login());

        mAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(view -> createUser());

    }
    
    private void createUser(){
        String email = r_email.getText().toString();
        String password = pass.getText().toString();

        if(TextUtils.isEmpty(email)){
            r_email.setError("Email cannot be empty");
            r_email.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            pass.setError("Password cannot be empty");
            pass.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this,"User Registered",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUp.this,Login.class));
                    }else{
                        Toast.makeText(SignUp.this,"Registration Unsuccessful" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }



    public void Login() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

}