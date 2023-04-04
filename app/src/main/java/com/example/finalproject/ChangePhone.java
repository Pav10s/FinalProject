package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.collect.ObjectArrays;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChangePhone extends AppCompatActivity {

    private AutoCompleteTextView changeAuto;
    private Button changeButton;

    private String fullName;

    private String[] redmi = {"Redmi 7A", "Redmi 7", "Redmi 8",
            "Redmi 8A", "Redmi 8A Dual", "Redmi Note 8", "Redmi Note 8 Pro", "Redmi Note 7",
            "Redmi Note 7 Pro", "Redmi 9A", "Redmi 9AT", "Redmi 9i", "Redmi 9",
            "Redmi 9 Active", "Redmi K20", "Redmi K20 pro", "Poco F1", "Poco X2",
            "Poco C3"};

    private String[] google = {"Google Pixel 7 Pro","Google Pixel 7","Google Pixel 6 Pro","Google Pixel 6",
            "Google Pixel 6a","Google Pixel 5","Google Pixel 5a","Google Pixel 4","Google Pixel 4XL",
            "Google Pixel 4a","Google Pixel 3","Google Pixel 3XL","Google Pixel 3a","Google Pixel 2XL",
            "Google Pixel 2","Google Pixel XL","MOTOROLA Edge 20 Fusion","Moto G60","Moto G51 5G",
            "Motorola One Fusion+","Micromax IN Note 1B","Micromax IN Note 1B","Micromax IN Note 2"};

    private String[] phones = ObjectArrays.concat(redmi, google, String.class);

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        getSupportActionBar().hide();

        changeAuto = findViewById(R.id.ChangeAuto);
        changeButton = findViewById(R.id.ChangeButton);

        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.select_dialog_item, phones);

        changeAuto.setThreshold(2);
        changeAuto.setAdapter(ad);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        String userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                fullName = value.getString("fullName");
            }
        });

        changeButton.setOnClickListener(v -> ChangeData(documentReference,fullName));
    }



    private void ChangeData(DocumentReference documentReference, String fullName) {
        String verifyItem = changeAuto.getText().toString();

        if(TextUtils.isEmpty(verifyItem)){
            changeAuto.setError("Phone must be selected");
        }

        else if(!check(phones, verifyItem)){
            changeAuto.setError("Phone not available");
        }

        else {
            Map<String, Object> change = new HashMap<>();
            change.put("phName",selectBrand());
            change.put("fullName",fullName);
            documentReference.set(change).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ChangePhone.this, "Phone Changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangePhone.this,MainActivity.class));
                }
            });
            }

    }//ChangeData() ends here

    private String selectBrand() {
        String selectedItem = changeAuto.getText().toString();
        String brand;
        if(check(redmi,selectedItem))
            brand="Redmi";
        else
            brand="Google";
        return brand;
    }

    private static boolean check(String[] arr, String toCheckValue)
    {
        // sort given array
        Arrays.sort(arr);
        // return the boolean value whether the string is same or not
        return Arrays.binarySearch(arr, toCheckValue) >= 0;
    }



}
