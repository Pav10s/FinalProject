package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";

    private TextView log_redirect;
    private EditText r_email;
    private EditText pass;
    private EditText re_pass;
    private EditText fullName;
    private Button reg;
    private AutoCompleteTextView phoneSelection;

    String[] phones = { "Select Phone", "Google", "Redmi", "Redmi 75", "Redmi 99"}; //To add the list of phones
    String userID;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        fullName = findViewById(R.id.full_name);
        r_email = findViewById(R.id.email);
        pass = findViewById(R.id.password1);
        re_pass = findViewById(R.id.password2);
        reg = findViewById(R.id.registerButton);
        log_redirect = findViewById(R.id.login);

        phoneSelection = findViewById(R.id.phone_selection);

        // Create the instance of ArrayAdapter having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.select_dialog_item, phones);

        phoneSelection.setThreshold(2);
        phoneSelection.setAdapter(ad);




        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        reg.setOnClickListener(view -> createUser(phoneSelection));
        log_redirect.setOnClickListener(v -> Login());

    }


    
    private void createUser(AutoCompleteTextView phoneSelection){
        String name = fullName.getText().toString();
        String email = r_email.getText().toString();
        String password = pass.getText().toString();
        String re_password = re_pass.getText().toString();
        String selectedItem = phoneSelection.getText().toString();

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

        else if(TextUtils.isEmpty(selectedItem)){
            phoneSelection.setError("Phone must be selected");
        }


        else if(!check(phones, selectedItem)){
                phoneSelection.setError("Phone not available");
        }



        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("users").document(userID);
                        Map<String, Object> user = new HashMap<>();
                        user.put("phName",selectedItem);
                        user.put("fullName",name);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG,"Created"+userID);
                                mAuth.signOut();
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

    private static boolean check(String[] arr, String toCheckValue)
    {
        // sort given array
        Arrays.sort(arr);
        // check if the specified element
        // is present in the array or not
        // using Binary Search method
        int res = Arrays.binarySearch(arr, toCheckValue);
        boolean test = res >= 0 ? true : false;
        return test;
    }

}