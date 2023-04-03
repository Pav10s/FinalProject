package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Message extends AppCompatActivity {

    private TextView send;

    private String[] google_send_strings = {"Blah","Blah","Blah","Blah","Blah","Blah"};

    private String[] google_send = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMessage%2FSend%20Message%2Fa.gif?alt=media&token=83d0f0d1-43c5-4e7d-9927-80f08d3feba9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMessage%2FSend%20Message%2Fb.gif?alt=media&token=f7380623-0521-4fbc-8a22-2a5f925cf7e3",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMessage%2FSend%20Message%2Fc.gif?alt=media&token=88c8a7cc-1fee-4f10-9f93-0c3ac0041ed5",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMessage%2FSend%20Message%2Fd.gif?alt=media&token=5a3c5f18-9ec1-4b1a-9005-4a80cf579277",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMessage%2FSend%20Message%2Fe.gif?alt=media&token=dd21ecdf-9c0a-462b-a316-84bffd38ff78"};

    private String[] redmi_send_strings = {"Blah","Blah","Blah","Blah","Blah","Blah","Blah","Blah","Blah"};

    private String[] redmi_send = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg1.gif?alt=media&token=98480629-b27c-4982-b6bb-e6231bc8d04b",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg2.gif?alt=media&token=4af3f169-51bf-4866-8595-bd11f22f7daa",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg3.gif?alt=media&token=96259500-d22b-4e33-903f-b30cee89b689",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg4.gif?alt=media&token=8f4d1e03-dead-4589-8778-4cdc9f1586b9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg5.gif?alt=media&token=496201ac-599f-42ae-829f-beba368c8e0c",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg6.gif?alt=media&token=a61e4090-0e81-4e59-8c55-d2edd8707161",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg7.gif?alt=media&token=a4d63cc5-d944-4eed-94bf-487015e940bf",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMessage%2FSend%20Message%2Fmsg8.gif?alt=media&token=e009688a-bb02-4582-8157-894d9605d2c3"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

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

        send = findViewById(R.id.send);

        send.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_send);
                intent.putExtra("Strings", google_send_strings);
            }
            else {
                intent.putExtra("URLS", redmi_send);
                intent.putExtra("Strings",redmi_send_strings);
            }
            startActivity(intent);

        });
    }
}