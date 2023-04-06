package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Chrome extends AppCompatActivity {

    private TextView search;

    private String[] google_search_strings = {"open chrome app",
                    "type and anything you want to search in the search bar",
                    "enjoy, exit the app"};

    private String[] google_search = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FChrome%2FSearch%2FVID-20230308-WA0028.gif?alt=media&token=d469b257-c69a-4378-a933-75b7619a366d",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FChrome%2FSearch%2FVID-20230308-WA0029.gif?alt=media&token=f4c09393-9ce8-46b6-a868-2c5df8fa1b0e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FChrome%2FSearch%2FVID-20230308-WA0030.gif?alt=media&token=a97d53f4-a6e6-4518-90c1-615af20e2160"};

    private String[] redmi_search_strings = {"Click Chrome icon","Click on top text field and type the content to search and hit enter","Click on the first blue texts","Press back button"};

    private String[] redmi_search = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FChrome%2FchromeSearch%2Fc1.gif?alt=media&token=1bce288a-63f4-40f9-a280-eebc7624ed5e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FChrome%2FchromeSearch%2Fc2.gif?alt=media&token=d0dd70c0-77fc-4b24-8e1c-bd14e90269ed",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FChrome%2FchromeSearch%2Fc3.gif?alt=media&token=f97d898b-e30b-438f-9d11-2e32916a0a34",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FChrome%2FchromeSearch%2Fc4.gif?alt=media&token=8821450e-9e6d-4b20-b6a1-5f432c7f76d5"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                PhName = value.getString("phName");
            }
        });

        search = findViewById(R.id.search);

        search.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_search);
                intent.putExtra("Strings", google_search_strings);
            }
            else {
                intent.putExtra("URLS", redmi_search);
                intent.putExtra("Strings",redmi_search_strings);
            }
            startActivity(intent);

        });
    }
}