package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText email;
    private Button forgotPass;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.fg_email);
        forgotPass = findViewById(R.id.forgotButton);

        forgotPass.setOnClickListener(v -> {
            String f_email = email.getText().toString();
            if(f_email.isEmpty()) {
                email.setError("Email cannot be empty");
            }
            else {
                mAuth.sendPasswordResetEmail(f_email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Reset password email sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to send reset password email", Toast.LENGTH_SHORT).show();
                            }

                        });
                }
        });
    }
}