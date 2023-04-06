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

    private String[] redmi_record_voice_strings = {"Open recorder application","Click on middle bottom red button to start recording","Click on right bottom Tick button to stop recording and press blue OK button to save","Click on right bottom three line button to see recorded voice","Press back button"};

    private String[] google_record_voice = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2F1.gif?alt=media&token=04dbc47d-8c8e-46a8-87d9-e053553e10e2",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2F2.gif?alt=media&token=512943c4-8bb1-49b4-bf68-161b8491e88d",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2F3.gif?alt=media&token=40dc688b-b8a4-4254-84fb-844d60bd4227",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FVoiceRecorder%2F4.gif?alt=media&token=89e29e36-457a-41e8-9cf7-d03f82f4f5a6",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FDelete%20Record%2F8.gif?alt=media&token=d6eae43b-a0c0-494e-a030-20183522078e"};

    private String[] google_record_voice_strings = {"open the voice recorder app",
            "tap red circle button at center bottom side of screen to start recording",
            "tap red square button at same position to end recording",
            "tap save button near resume button to save recording",
            "exit app"};

    private String[] redmi_delete_record = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F1.gif?alt=media&token=3bb19e96-dd37-46a1-a169-f78385fa8072",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F2.gif?alt=media&token=6001d8ff-39e4-4edd-86f0-a34e2411b0ed",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F3.gif?alt=media&token=ffc8e144-ed29-4c4a-b495-c976248c53a1",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FAudio%20Recorder%2FaudioDeleting%2F4.gif?alt=media&token=be76a050-c55c-4383-aee8-d26fc85c15f2"};

    private String[] redmi_delete_record_strings = {"Open recorder application","Click on right bottom three line button to see recorded voice","Press and hold the voice to delete then click on right bottom bin button and press blue OK button","Press back button"};

    private String[] google_delete_record =  {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FDelete%20Record%2F5.gif?alt=media&token=90067a60-3d78-4453-84c0-79bb527f1173",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FDelete%20Record%2F6.gif?alt=media&token=2c465d5b-73b1-463e-ac16-d0f78d033cf1",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FDelete%20Record%2F7.gif?alt=media&token=59c34fc7-3838-4026-a814-82d715833eef",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FRecord%2FDelete%20Record%2F8.gif?alt=media&token=d6eae43b-a0c0-494e-a030-20183522078e"};

    private String[] google_delete_record_strings = {"tap and hold the recording you want to delete",
            "press trash icon at top right corner of screen ",
            "tap delete on pop up screen",
            "record has been deleted , exit app"};

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