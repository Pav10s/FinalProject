package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class Phone extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName;
    String[] samsung_call_urls = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FMake%20A%20Call%2FGIF1.gif?alt=media&token=46705049-914a-459c-8840-ae15cc8cfb0c",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FMake%20A%20Call%2FGIF2.gif?alt=media&token=d72d0bc1-e8fb-458d-84f6-99342baf7b76",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FMake%20A%20Call%2FGIF3.gif?alt=media&token=cb9f2caf-479a-4043-a236-8a39097f8b91",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FMake%20A%20Call%2FGIF4.gif?alt=media&token=1baf00f4-f35c-4d07-8e85-d9a5951edfdc"};
    String[] redmi_call_urls = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall1.gif?alt=media&token=b3a5e463-c070-423d-b8cf-38a8f8e6afba",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall2.gif?alt=media&token=00397bff-b064-46ac-b08d-b0f4a3aee774",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall3.gif?alt=media&token=f578f17f-635c-4f35-b525-00c6f148965b",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall4.gif?alt=media&token=800e2f96-ca2e-403f-91d3-0120478d8390"};


    private Button call_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

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

        call_button = findViewById(R.id.button);
        String[] strings = {"Click Phone","Click Recents","Slide the contact","Red Button: End Call"};


        call_button.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Samsung"))
                intent.putExtra("URLS", samsung_call_urls);
            else
                intent.putExtra("URLS", redmi_call_urls);

            intent.putExtra("Strings",strings);
            startActivity(intent);
        });
    }



}
