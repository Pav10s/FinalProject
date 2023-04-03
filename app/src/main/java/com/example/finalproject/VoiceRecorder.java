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

public class VoiceRecorder extends AppCompatActivity {

    private TextView record_voice;
    private TextView delete_record;

    private String[] redmi_record_voice = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioRecording%2Fr1.gif?alt=media&token=8833c3f0-7b31-446d-b445-fe928cbacea8",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioRecording%2Fr2.gif?alt=media&token=b36b833c-5b5e-470a-a9d2-b49f2a863d09",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioRecording%2Fr3.gif?alt=media&token=55ef9509-01c4-4157-b635-7d87f6cf3a31",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioRecording%2Fr4.gif?alt=media&token=e90a3a63-b4f0-472e-9b55-13d4a3c91489",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioRecording%2Fr5.gif?alt=media&token=89dbfa99-63cd-4c6a-8e35-5272baafd974"};

    private String[] redmi_record_voice_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] google_record_voice = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-4c9c1967e0.gif?alt=media&token=06a2f0e2-358a-407d-bc35-fa15aea2b269",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-55f2793062.gif?alt=media&token=d34b71ab-6a5f-42d0-a03b-0b87ca53ae9e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-b60d5fb5fb.gif?alt=media&token=5937d7e6-77a9-4459-bfd5-a2d0fead33e0",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-de91f3833c.gif?alt=media&token=9b06c335-105d-464d-b438-d62510a3c3db"};

    private String[] google_record_voice_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] redmi_delete_record = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F1.gif?alt=media&token=3bb19e96-dd37-46a1-a169-f78385fa8072",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F2.gif?alt=media&token=6001d8ff-39e4-4edd-86f0-a34e2411b0ed",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F3.gif?alt=media&token=ffc8e144-ed29-4c4a-b495-c976248c53a1",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F4.gif?alt=media&token=be76a050-c55c-4383-aee8-d26fc85c15f2"};

    private String[] redmi_delete_record_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    private String[] google_delete_record =  {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-4c9c1967e0.gif?alt=media&token=06a2f0e2-358a-407d-bc35-fa15aea2b269",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-55f2793062.gif?alt=media&token=d34b71ab-6a5f-42d0-a03b-0b87ca53ae9e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-b60d5fb5fb.gif?alt=media&token=5937d7e6-77a9-4459-bfd5-a2d0fead33e0",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2Fezgif-3-de91f3833c.gif?alt=media&token=9b06c335-105d-464d-b438-d62510a3c3db"};

    private String[] google_delete_record_strings = {"Blah", "Blah", "Blah", "Blah", "Blah", "Blah"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID, PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recorder);
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

        delete_record = findViewById(R.id.delete_record);
        record_voice = findViewById(R.id.record_voice);

        delete_record.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_delete_record);
                intent.putExtra("Strings", google_delete_record_strings);
            } else {
                intent.putExtra("URLS", redmi_delete_record);
                intent.putExtra("Strings", redmi_delete_record_strings);
            }
            startActivity(intent);

        });


        record_voice.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_record_voice);
                intent.putExtra("Strings", google_record_voice_strings);
            } else {
                intent.putExtra("URLS", redmi_record_voice);
                intent.putExtra("Strings", redmi_record_voice_strings);
            }
            startActivity(intent);

        });


    }
}