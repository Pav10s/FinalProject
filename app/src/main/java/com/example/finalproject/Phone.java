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


public class Phone extends AppCompatActivity {

    private TextView call_by_number;
    private TextView call_by_contact;
    private TextView add_contact;

     private String[] google_call_urls = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F1.gif?alt=media&token=720a315c-2616-40f8-a592-54708691a701",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F2.gif?alt=media&token=6129cf31-4fae-43a1-8c30-e2bcf7f84894",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F3.gif?alt=media&token=742b67b7-d3fc-4452-9394-6727496d86f3"};

    private String[] google_call_urls_strings = {"Click Phone Icon","Type the number","Press red button to end the call"};

    private String[] redmi_call_urls = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall1.gif?alt=media&token=b3a5e463-c070-423d-b8cf-38a8f8e6afba",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall2.gif?alt=media&token=00397bff-b064-46ac-b08d-b0f4a3aee774",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall3.gif?alt=media&token=f578f17f-635c-4f35-b525-00c6f148965b",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FMake%20A%20Call%2Fcall4.gif?alt=media&token=800e2f96-ca2e-403f-91d3-0120478d8390"};

    private String[] redmi_call_urls_strings = {"Click Phone icon","Enter the number","Green button to call","Red button to end call"};

    private String[] google_add_contact = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F1.gif?alt=media&token=720a315c-2616-40f8-a592-54708691a701",
                "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FAdd%20Contact%2F2.gif?alt=media&token=3416dc8f-4e45-44b3-8896-ee1be25c2093",
                "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FAdd%20Contact%2F3.gif?alt=media&token=606992be-d592-4454-9afb-64c8e40b4d1b"};

    private String[] google_add_contact_strings = {"Click Phone Icon","Type the number","Fill the details and press save"};

    private String[] redmi_add_contact = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FAdd%20contact%2F1.gif?alt=media&token=a27d7b73-0ee7-48c1-b098-12fbae599063",
                "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FAdd%20contact%2F2.gif?alt=media&token=4627b448-c844-46d0-b891-106d772cc564",
                "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FAdd%20contact%2F3.gif?alt=media&token=a61dd125-948e-44bf-9e80-912bcfc0d6c7",
                "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FAdd%20contact%2F4.gif?alt=media&token=75829282-7655-492a-ac83-b10ea107a7be"};

    private String[] redmi_add_contact_strings = {"Click Phone icon","Type the Number","Click add the contact, Type name and hit tick sign at the top","Press back button"};

    private String[] google_call_contact = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F1.gif?alt=media&token=720a315c-2616-40f8-a592-54708691a701",
                                    "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FCall%20from%20contact%2F5.gif?alt=media&token=a505ba0f-7858-4fb6-874e-f90f0a135ee1",
                                    "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FPhone%2FMake%20a%20call%2F3.gif?alt=media&token=742b67b7-d3fc-4452-9394-6727496d86f3"};

    private String[] google_call_contact_strings = {"Click Phone Icon","Click the desired contact and press call","Press red button to end call"};


    private String[] redmi_call_contact = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2FAdd%20contact%2F1.gif?alt=media&token=a27d7b73-0ee7-48c1-b098-12fbae599063",
                                    "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2Fcall%20via%20contact%2F2.gif?alt=media&token=e204fd9e-c104-4bf3-89f9-e6ab39272266",
                                    "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2Fcall%20via%20contact%2F3.gif?alt=media&token=43e9b2fa-1cdd-4981-bb12-af416988280d",
                                    "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FPhone%2Fcall%20via%20contact%2F4.gif?alt=media&token=1ad7c459-2fcd-43e2-83c6-06d434bb8302"};

    private String[] redmi_call_contact_strings = {"Click phone icon","Searchbar to search contact, Click contact","Green button to call:Red button to end call","Press back button"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
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

        call_by_number = findViewById(R.id.callnumber);
        call_by_contact = findViewById(R.id.callcontact);
        add_contact = findViewById(R.id.addcontact);


        call_by_number.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")){
                intent.putExtra("URLS", google_call_urls);
                intent.putExtra("Strings", google_call_urls_strings);
            }
            else{
                intent.putExtra("URLS", redmi_call_urls);
                intent.putExtra("Strings", redmi_call_urls_strings);
            }
            startActivity(intent);

        });

        call_by_contact.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_call_contact);
                intent.putExtra("Strings", google_call_contact_strings);
            }
            else {
                intent.putExtra("URLS", redmi_call_contact);
                intent.putExtra("Strings", redmi_call_contact_strings);
            }
            startActivity(intent);

        });

        add_contact.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if(PhName.equals("Google")) {
                intent.putExtra("URLS", google_add_contact);
                intent.putExtra("Strings",google_add_contact_strings);
            }

            else {
                intent.putExtra("URLS", redmi_add_contact);
                intent.putExtra("Strings",redmi_add_contact_strings);
            }
            startActivity(intent);

        });
    }


}
