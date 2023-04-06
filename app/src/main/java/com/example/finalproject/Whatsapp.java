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

public class Whatsapp extends AppCompatActivity {

    private TextView whatsapp_call;
    private TextView whatsapp_message;
    private TextView whatsapp_video_call;

    private String[] google_whatsapp_message = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FWhatsapp%2FWhatsappMsg%2Fezgif-3-27adbbcf30.gif?alt=media&token=20730537-8e51-408c-8763-c4db9c8fa060",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FWhatsapp%2FWhatsappMsg%2Fezgif-3-b302004644.gif?alt=media&token=4a21285c-b642-40df-9eef-87ad33e7ff1f",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FWhatsapp%2FWhatsappMsg%2Fezgif-3-a3a2ecf909.gif?alt=media&token=5ae80a71-29e5-4bc5-93f7-b3a87e589d14"};

    private String[] google_whatsapp_message_strings = {"open whatsapp",
            "search for the contact to send the message from the bottom right corner",
            "type the message you want to send and exit the app"};

    private String[] redmi_whatsapp_message = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappMsg%2Fwhatsapp1.gif?alt=media&token=9f236e99-0604-477a-9188-385f8eb3a22e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappMsg%2Fwhatsapp2.gif?alt=media&token=9ee12ad9-e835-4b93-a986-075250e5e043",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappMsg%2Fwhatsapp3.gif?alt=media&token=b9161cd1-7ef5-4a30-ac03-3a914a6867a7",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappMsg%2Fwhatsapp4.gif?alt=media&token=ba69ecef-5fbd-4eb5-ba80-cd7f2f33a04e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappMsg%2Fwhatsapp5.gif?alt=media&token=1bbcb273-ecee-4ce3-b73c-54e429f95086"};

    private String[] redmi_whatsapp_message_strings = {"Click whatsapp icon","Click bottom right green button","Search contact using searchbar on top and tap on it","Type message and hit enter","Press back button to exit"};

    private String[] google_whatsapp_call = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F1.gif?alt=media&token=5f9fb261-9d31-4423-9b96-e529d5e61740",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F2.gif?alt=media&token=e2dcc4db-8908-4ea5-a317-35fcec209a34",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F3.gif?alt=media&token=78082a2c-beb0-4b96-82c1-b1d4c665290e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F4.gif?alt=media&token=ff1c2650-7a6d-4032-be09-3eeb2acabaa9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F5.gif?alt=media&token=e8fa6fb4-fab8-4deb-9a82-664fd50991b1"};

    private String[] google_whatsapp_call_strings = {"open whatsapp",
            "search for the contact you want to call",
            "tap the phone icon on top right corner to start call",
            "tap the red phone icon on bottom right corner to end call",
            "exit the app"};

    private String[] redmi_whatsapp_call = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F1.gif?alt=media&token=5f9fb261-9d31-4423-9b96-e529d5e61740",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F2.gif?alt=media&token=e2dcc4db-8908-4ea5-a317-35fcec209a34",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F3.gif?alt=media&token=78082a2c-beb0-4b96-82c1-b1d4c665290e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F4.gif?alt=media&token=ff1c2650-7a6d-4032-be09-3eeb2acabaa9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappCall%2F5.gif?alt=media&token=e8fa6fb4-fab8-4deb-9a82-664fd50991b1"};

    private String[] redmi_whatsapp_call_strings = {"Click whatsapp icon","Search contact using searchbar on top and tap on it","Click phone icon at the top","Red button to end call","Press back button to exit"};

    private String[] google_video_call = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F1.gif?alt=media&token=d03173ff-99d5-4d7c-b124-dd65f35d43ad",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F2.gif?alt=media&token=7ef2b888-6949-45cb-ae0e-ddf3815a4a7e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F3.gif?alt=media&token=a4eb8067-a752-45a9-bb48-8c9c4aa1a816",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F4.gif?alt=media&token=4ee0d183-872f-42db-a60c-9eb3d7ed2a49"};

    private String[] google_video_call_strings = {"open whatsapp",
            "search the contact you want to video call",
            "tap camera icon on top right corner to start video call,tap red phone icon on bottom right corner to end video call",
            "exit the app"};

    private String[] redmi_video_call = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F1.gif?alt=media&token=d03173ff-99d5-4d7c-b124-dd65f35d43ad",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F2.gif?alt=media&token=7ef2b888-6949-45cb-ae0e-ddf3815a4a7e",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F3.gif?alt=media&token=a4eb8067-a752-45a9-bb48-8c9c4aa1a816",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FWhatsapp%2FwhatsappVcall%2F4.gif?alt=media&token=4ee0d183-872f-42db-a60c-9eb3d7ed2a49"};

    private String[] redmi_video_call_strings = {"Click whatsapp icon","Search contact using searchbar on top and tap on it","Click camera icon at the top and Red button to end call","Press back button to exit"};


    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
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

        whatsapp_message = findViewById(R.id.whatsapp_message);
        whatsapp_call = findViewById(R.id.whatsapp_call);
        whatsapp_video_call = findViewById(R.id.whatsapp_video_call);


        whatsapp_message.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")){
                intent.putExtra("URLS", google_whatsapp_message);
                intent.putExtra("Strings", google_whatsapp_message_strings);
            }
            else {
                intent.putExtra("URLS", redmi_whatsapp_message);
                intent.putExtra("Strings", redmi_whatsapp_message_strings);
            }
            startActivity(intent);

        });


        whatsapp_call.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_whatsapp_call);
                intent.putExtra("Strings",google_whatsapp_call_strings);
            }
            else{
                intent.putExtra("URLS", redmi_whatsapp_call);
                intent.putExtra("Strings",redmi_whatsapp_call_strings);
            }
            startActivity(intent);

        });


        whatsapp_video_call.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_video_call);
                intent.putExtra("Strings", google_video_call_strings);
            }
            else {
                intent.putExtra("URLS", redmi_video_call);
                intent.putExtra("Strings",redmi_video_call_strings);
            }
            startActivity(intent);

        });
    }


}