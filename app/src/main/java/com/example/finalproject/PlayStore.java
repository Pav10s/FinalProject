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

public class PlayStore extends AppCompatActivity {

    private TextView install;
    private TextView uninstall;

    private String[] redmi_install = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FinstallApp%2Fps1.gif?alt=media&token=afd7a632-ea7e-4660-8374-8b9c378213e0",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FinstallApp%2Fps2.gif?alt=media&token=a1de5285-7cdb-40b8-a883-859d0eb18135",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FinstallApp%2Fps3.gif?alt=media&token=1ef02969-6c78-46b7-accd-e9b01becc448",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FinstallApp%2Fps4.gif?alt=media&token=426d5f52-348d-48ad-a19c-10938ebedb56",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FinstallApp%2Fps5.gif?alt=media&token=fbe10ac3-8b1c-4b80-9f3e-6aa2b3c88355"};

    private String[] redmi_install_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] google_install = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPlayStore%2FInstll%2FVID-20230308-WA0021.gif?alt=media&token=b917d94e-2399-4e7c-a793-95e42f4c58d5",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPlayStore%2FInstll%2FVID-20230308-WA0022.gif?alt=media&token=9fe70aae-9a17-49d5-996e-41025ea4a1cd",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPlayStore%2FInstll%2FVID-20230308-WA0023.gif?alt=media&token=6afac2f7-2455-40a8-a487-ecd811d336f2",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPlayStore%2FInstll%2FVID-20230308-WA0024.gif?alt=media&token=297494b8-c00f-4cad-bb51-17a25cfe852e"};

    private String[] google_install_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] redmi_uninstall = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall1.gif?alt=media&token=36112e31-a918-4698-8327-4ca1fcd16521",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall2.gif?alt=media&token=3d25ddbf-182a-4f9e-bb76-12f4e404ccad",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall3.gif?alt=media&token=6b55de87-2d6c-49ef-9fb5-bd7cab466de5",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall4.gif?alt=media&token=a57f53b6-1d5b-4069-85be-85165bf6ceff"};

    private String[] redmi_uninstall_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] google_uninstall = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPlayStore%2FInstll%2FVID-20230308-WA0021.gif?alt=media&token=b917d94e-2399-4e7c-a793-95e42f4c58d5",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall2.gif?alt=media&token=3d25ddbf-182a-4f9e-bb76-12f4e404ccad",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPlaystore%2FuninstallApp%2Funinstall3.gif?alt=media&token=6b55de87-2d6c-49ef-9fb5-bd7cab466de5"};

    private String[] google_uninstall_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID, PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_store);
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

        uninstall = findViewById(R.id.uninstall);
        install = findViewById(R.id.install);

        uninstall.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_uninstall);
                intent.putExtra("Strings", google_uninstall_strings);
            } else {
                intent.putExtra("URLS", redmi_uninstall);
                intent.putExtra("Strings", redmi_uninstall_strings);
            }
            startActivity(intent);

        });


        install.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_install);
                intent.putExtra("Strings", google_install_strings);
            } else {
                intent.putExtra("URLS", redmi_install);
                intent.putExtra("Strings", redmi_install_strings);
            }
            startActivity(intent);

        });


    }
}