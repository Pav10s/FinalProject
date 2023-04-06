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

public class Gmail extends AppCompatActivity {

    private TextView send_mail;
    private TextView attach_to_mail;

    private String[] redmi_send_mail = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FcomposeMail%2FMail1.gif?alt=media&token=17983ee9-b979-4a4c-a744-9ddac1ba0f53",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FcomposeMail%2FMail2.gif?alt=media&token=62ee0eb7-c4fe-4cd9-9c07-3752b579eb17",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FcomposeMail%2FMail3.gif?alt=media&token=af8009ca-38d9-4c0c-a881-bc3cd43dbbbd",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FcomposeMail%2FMail4.gif?alt=media&token=12ceb107-55bd-4ea0-99d3-8e9a4c2d6c4f",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FcomposeMail%2FMail5.gif?alt=media&token=81f9155e-4a5a-49ef-8b1f-d1c02c025580"};

    private String[] redmi_send_mail_strings = {"Click on Gmail icon","Click on right bottom compose button","Type the recipient mail address","Click under subject then type the message and hit top right triangle button","Press back button"};

    private String[] google_send_mail = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMail%2FSendMail%2FVID-20230308-WA0025.gif?alt=media&token=0e129ff8-4be9-4936-92e4-0ab02a748e4d",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMail%2FSendMail%2FVID-20230308-WA0026.gif?alt=media&token=161c76b1-df13-4d5f-823d-ca2ca831a39c",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMail%2FSendMail%2FVID-20230308-WA0027.gif?alt=media&token=3a4d8bfb-fc29-4242-828c-69a78ddb459a"};

    private String[] google_send_mail_strings = {"open gmail app",
            "tap compompose button , type email address in to address",
            "type message you want to send in compose email , tap send icon at top right corner , exit app"};

    private String[] redmi_attach_to_mail = {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F1.gif?alt=media&token=8e7ac937-2e00-41a1-a055-1204be71e422",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F2.gif?alt=media&token=95c6e377-7c35-4406-970f-47fc37d474f9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F3.gif?alt=media&token=6f30de6d-c7e2-43ee-84c5-b80b9ee1a6ef",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F4.gif?alt=media&token=b23ba40e-75e9-4ea8-a646-2c253281eb27",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F5.gif?alt=media&token=cda64fc7-06e8-461f-93be-259d77117284",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F6.gif?alt=media&token=d2dc6693-2b2d-47e1-9d24-af46b94c3d26"};

    private String[] redmi_attach_to_mail_strings = {"Click on Gmail icon","Click on right bottom compose button","Type the recipient mail address","Click on attach pin icon on the top","Click on the attach file option and select the file","Click on top right triangle button","Press back button"};

    private String[] google_attach_to_mail =  {"https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Google%2FMail%2FSendMail%2FVID-20230308-WA0025.gif?alt=media&token=0e129ff8-4be9-4936-92e4-0ab02a748e4d",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F2.gif?alt=media&token=95c6e377-7c35-4406-970f-47fc37d474f9",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F3.gif?alt=media&token=6f30de6d-c7e2-43ee-84c5-b80b9ee1a6ef",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F4.gif?alt=media&token=b23ba40e-75e9-4ea8-a646-2c253281eb27",
            "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Redmi%2FMail%2FattachFile%2F5.gif?alt=media&token=cda64fc7-06e8-461f-93be-259d77117284"};

    private String[] google_attach_to_mail_strings = {"open gmail app",
            "type email address to send the attachment in To Address",
            "select pin icon near send mail icon , select add attachment ",
            "select file to send from files",
            "tap send icon at top right corner to send attachment , exit app"};

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID, PhName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);
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

        attach_to_mail = findViewById(R.id.attach);
        send_mail = findViewById(R.id.send_mail);

        attach_to_mail.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_attach_to_mail);
                intent.putExtra("Strings", google_attach_to_mail_strings);
            } else {
                intent.putExtra("URLS", redmi_attach_to_mail);
                intent.putExtra("Strings", redmi_attach_to_mail_strings);
            }
            startActivity(intent);

        });


        send_mail.setOnClickListener(v -> {

            Intent intent = new Intent(this, Display.class);
            if (PhName.equals("Google")) {
                intent.putExtra("URLS", google_send_mail);
                intent.putExtra("Strings", google_send_mail_strings);
            } else {
                intent.putExtra("URLS", redmi_send_mail);
                intent.putExtra("Strings", redmi_send_mail_strings);
            }
            startActivity(intent);

        });


    }
}