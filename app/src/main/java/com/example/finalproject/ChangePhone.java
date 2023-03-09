package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ChangePhone extends AppCompatActivity {

    private Spinner changeSpinner;
    private Button changeButton;

    private String fullName;

    String[] phones = { "Select Phone", "Google", "Redmi"}; //To add the list of phones

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        getSupportActionBar().hide();

        changeSpinner = findViewById(R.id.ChangeSpinner);
        changeButton = findViewById(R.id.ChangeButton);

        // Create the instance of ArrayAdapter having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phones);

        // set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        changeSpinner.setAdapter(ad);

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
        String selectedItem = changeSpinner.getSelectedItem().toString();

        if(selectedItem.equals("Select Phone")) {
            Toast.makeText(this,"Phone must be selected",Toast.LENGTH_LONG).show();
        }

        else {
            Map<String, Object> change = new HashMap<>();
            change.put("phName",selectedItem);
            change.put("fullName",fullName);
            documentReference.set(change).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ChangePhone.this, "Phone Changed", Toast.LENGTH_SHORT).show();
                }
            });
            }

    }//SendRequest() ends here



}
