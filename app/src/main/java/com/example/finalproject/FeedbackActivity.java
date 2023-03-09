package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class FeedbackActivity extends AppCompatActivity {

    private Button requestButton;
    private EditText requestContent;

    public String fullName;
    private int i = 0;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        requestContent = findViewById(R.id.RequestContent);
        requestButton = findViewById(R.id.RequestButton);
        fStore = FirebaseFirestore.getInstance();

        requestButton.setOnClickListener(v -> {

            String suggestion = requestContent.getText().toString();

            if (suggestion.isEmpty()) {
                requestContent.setError("This field cannot be empty");
            } else {
                Map<String, Object> suggestionData = new HashMap<>();
                suggestionData.put("suggestion", suggestion);

                fStore.collection("suggestions")
                        .add(suggestionData)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(FeedbackActivity.this, "Thank you for your suggestion", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(FeedbackActivity.this, "Failed to submit suggestion", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}