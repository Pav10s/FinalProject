package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button button1;

    EditText r_email;
    EditText pass;
    EditText re_pass;
    EditText fullName;
    Button log;

    String[] phones = { "Select Phone", "Samsung", "Redmi"}; //To add the list of phones
    String userID;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        fullName = findViewById(R.id.full_name);
        r_email = findViewById(R.id.email1);
        pass = findViewById(R.id.Password1);
        re_pass = findViewById(R.id.Password2);
        log = findViewById(R.id.register1);

        Spinner spin = findViewById(R.id.Spin);

        // Create the instance of ArrayAdapter having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phones);

        // set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        spin.setAdapter(ad);

        button1 = findViewById(R.id.login);
        button1.setOnClickListener(v -> Login());

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        log.setOnClickListener(view -> createUser(spin));

    }


    
    private void createUser(Spinner spin){
        String name = fullName.getText().toString();
        String email = r_email.getText().toString();
        String password = pass.getText().toString();
        String re_password = re_pass.getText().toString();
        String selectedItem = spin.getSelectedItem().toString();

        if(TextUtils.isEmpty(email)){
            r_email.setError("Email cannot be empty");
            r_email.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            pass.setError("Password cannot be empty");
            pass.requestFocus();
        }

        else if (!TextUtils.equals(password, re_password)){
            re_pass.setError("Password should be same");
            re_pass.requestFocus();
        }

        else if(selectedItem.equals("Select Phone")){
            Toast.makeText(this,"Phone must be selected",Toast.LENGTH_LONG).show();
        }

        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this,selectedItem,Toast.LENGTH_LONG).show();
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("phName",selectedItem);
                        user.put("fullName",name);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG,"Created"+userID);
                            }
                        });
                        startActivity(new Intent(SignUp.this,Login.class));
                    }else{
                        Toast.makeText(SignUp.this,"Registration Unsuccessful " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
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