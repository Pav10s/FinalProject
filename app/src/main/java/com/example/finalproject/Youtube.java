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

public class Youtube extends AppCompatActivity {

    private TextView video_search;
    private TextView video_download;


    private String[] google_video_search = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FPlayVdo%2F1.gif?alt=media&token=16191514-50d4-4f6d-b444-e6cecd910883",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FPlayVdo%2F2.gif?alt=media&token=226e9ca4-823a-49ac-ab40-108f33c5b1c1",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FPlayVdo%2F3.gif?alt=media&token=9c09b234-3684-4767-9292-6448a3a9b66f",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FPlayVdo%2F4.gif?alt=media&token=752bc9ff-e0ce-4feb-8f5d-68ee501ecb11"};

    private String[] google_video_search_strings = {"open youtube app ",
            "select any video from list",
            "skip add by taping skip add",
            "after watching it close the video",
            "tap shorts icon next to home icon on bottom left side, after watching exit app"};

    private String[] redmi_video_download = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeOffline%2F1.gif?alt=media&token=94793603-e690-458c-8021-77de204a2a48",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeOffline%2F2.gif?alt=media&token=90c3e08b-d6b4-4567-afe4-4a8297897540",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeOffline%2F3.gif?alt=media&token=c5c19c41-d0ad-4500-aac4-e43e696f1fbb",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeOffline%2F4.gif?alt=media&token=d81aeac1-7dca-4886-8237-1f89de66b989",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeOffline%2F5.gif?alt=media&token=8865ee91-d940-45a1-9be3-1599dfbb51c4"};

    private String[] redmi_video_download_strings = {"Click on Youtube icon","Click on three dots under video then click download video and click blue download button","Click on library button on right bottom","Click on downloads to see the downloaded video","Press back button"};

    private String[] google_video_download = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FVdoDwnld%2F5.gif?alt=media&token=213b2762-36cb-4d69-bed0-f4a98fbe31c1",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FVdoDwnld%2F6.gif?alt=media&token=585cd083-e8f7-437c-a090-d4d9281e55ac",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FVdoDwnld%2F7.gif?alt=media&token=1c4924f4-a586-4f98-ac7d-da30f8510810",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FVdoDwnld%2F8.gif?alt=media&token=cfda24d7-92fb-4b60-8430-99d863996655",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FYoutube%2FVdoDwnld%2F9.gif?alt=media&token=b9aba555-2916-4ddc-aa67-e89687158b51"};

    private String[] google_video_download_strings = {"select video you want to download, click download button under the video",
            "select resolution from pop up screen, video will be downloaded",
            "tap library icon at bottom right corner to see downloaded video",
            "play video when your offline",
            "exit app"};


    private String[] redmi_video_search = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeSearch%2Fyt1.gif?alt=media&token=c1c2b0ce-f243-4c2c-b7d4-941dd38bfc15",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeSearch%2Fyt2.gif?alt=media&token=07ef8122-4b7a-4477-b41e-3abb0c368beb",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeSearch%2Fyt3.gif?alt=media&token=d85f3149-59c7-4acc-99e6-48368ffb4437",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FYoutube%2FyoutubeSearch%2Fyt4.gif?alt=media&token=347d729d-79c0-448e-b473-566e5f78f2d2"};

    private String[] redmi_video_search_strings = {"Click on Youtube icon","Click on the hand glass icon on the top, then type the content and hit enter","Click on the right video you need","Press back button"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID, PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
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

        video_download = findViewById(R.id.video_download);
        video_search = findViewById(R.id.video_search);

        video_download.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_video_download);
                intent.putExtra("Strings", google_video_download_strings);
            } else {
                intent.putExtra("URLS", redmi_video_download);
                intent.putExtra("Strings", redmi_video_download_strings);
            }
            startActivity(intent);

        });


        video_search.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_video_search);
                intent.putExtra("Strings", google_video_search_strings);
            } else {
                intent.putExtra("URLS", redmi_video_search);
                intent.putExtra("Strings", redmi_video_search_strings);
            }
            startActivity(intent);

        });


    }
}