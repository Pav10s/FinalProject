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

public class Camera extends AppCompatActivity {

    private TextView photo;
    private TextView video;

    private String[] redmi_photo = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fphoto%2Fs1.gif?alt=media&token=a027a5dc-2fcf-4f44-986e-9dece30452ac",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fphoto%2Fs2.gif?alt=media&token=32b8d564-1eea-4000-821a-f9302aad59f7",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fphoto%2Fs3.gif?alt=media&token=7249e069-83a7-4e3f-97ca-6dc5c15342da",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fphoto%2Fs4.gif?alt=media&token=aa8d6fe1-db36-45a5-9107-85b77437c249"};

    private String[] redmi_photo_strings = {"Click camera icon",
            "Click the white button",
            "Left bottom button to preview",
            "Press back button to exit"};

    private String[] google_photo = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FPhoto%2F1.gif?alt=media&token=7ae760a6-8519-441f-bc5b-f30ef8f5ad2b",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FPhoto%2F2.gif?alt=media&token=514606d8-e33e-4459-b8a9-a441bdef0d23",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FPhoto%2F3.gif?alt=media&token=d1be6f66-0c4f-4eca-9bc1-ae38803b59ed",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FPhoto%2F4.gif?alt=media&token=b12d7dba-5f14-4769-95e7-63483e7d24b0"};

    private String[] google_photo_strings = {"open the camera app",
            "tap on the subject to focus",
            "click the white circle located at the bottom side",
            "the photo can be found in the gallery app"};

    private String[] redmi_video = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fvideo%2F1.gif?alt=media&token=e493aa19-d0ea-4a82-a1f2-03f70105a436",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fvideo%2F2.gif?alt=media&token=05e19da2-78d2-4d80-b37f-78abbc215a0c",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fvideo%2F3.gif?alt=media&token=58c6849e-b08f-4494-b9b5-452b84e10971",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fvideo%2F4.gif?alt=media&token=43195c03-4b33-4913-922d-f6ff6bb82649",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FCamera%2Fvideo%2F5.gif?alt=media&token=e048371e-37f2-4aa9-977b-ef58925b8437"};

    private String[] redmi_video_strings = {"Click camera icon","Click video text","Red button to start, Red square button to stop","Left bottom button to preview","Press back button to exit"};

    private String[] google_video =  {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FPhoto%2F4.gif?alt=media&token=b12d7dba-5f14-4769-95e7-63483e7d24b0",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FCamera%2FVideo%2F5.gif?alt=media&token=1f42410d-410a-457d-a06e-0ea309e40730"};

    private String[] google_video_strings = {"tap on the video icon at the bottom",
            "click on the white circle to start recording and red to stop"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID, PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
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

        video = findViewById(R.id.video);
        photo = findViewById(R.id.photo);

        video.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_video);
                intent.putExtra("Strings", google_video_strings);
            } else {
                intent.putExtra("URLS", redmi_video);
                intent.putExtra("Strings", redmi_video_strings);
            }
            startActivity(intent);

        });


        photo.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_photo);
                intent.putExtra("Strings", google_photo_strings);
            } else {
                intent.putExtra("URLS", redmi_photo);
                intent.putExtra("Strings", redmi_photo_strings);
            }
            startActivity(intent);

        });


    }
}