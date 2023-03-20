package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    FirebaseAuth mAuth;                 //The entry point of the Firebase Authentication SDK.
    FirebaseFirestore fStore;           //The entry point for all Cloud Firestore operations.

    String userID,PhName,fullName;

    //To store complete data of Recycler view
    ArrayList<RecModel> recModels = new ArrayList<>();

    //Stores the images of the Recycler view
    int[] recImages = {R.drawable.phone,R.drawable.camera,
            R.drawable.whatsapp,R.drawable.playstore,
            R.drawable.youtube,R.drawable.gmail,
            R.drawable.mock,R.drawable.mock1,
            R.drawable.mock,R.drawable.mock1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Gets the Unique ID of the current user
        userID = mAuth.getCurrentUser().getUid();

        //Used to access the database
        DocumentReference documentReference = fStore.collection("users").document(userID);

        //Read data from database
        documentReference.addSnapshotListener(this, (value, error) -> {
            PhName = value.getString("phName");
            fullName = value.getString("fullName");
        });

        RecyclerView recyclerView = findViewById(R.id.mRecycler);
        setRecModels();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,recModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //Sets the array recModels with all of the required data
    private void setRecModels() {

        String[] recModelNames = getResources().getStringArray(R.array.recText);

        for (int i=0;i<recModelNames.length;i++) {
            recModels.add(new RecModel(recModelNames[i],recImages[i]));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //Implements the desired action for the item selected.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.request:
                startActivity(new Intent(MainActivity.this, ChangePhone.class));
                break;

            case R.id.about:
                startActivity(new Intent(MainActivity.this, About.class));
                break;

            case R.id.donate:
                startActivity(new Intent(MainActivity.this, Donation.class));
                break;

            case R.id.sent_request:
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                break;

            case R.id.log_out:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(fullName+", Are you sure you want to log out?")                  // message

                        .setCancelable(false)                                                       // false - cannot change the focus

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {       // when clicked yes
                            public void onClick(DialogInterface dialog, int id) {
                                mAuth.signOut();
                                startActivity(new Intent(MainActivity.this, Login.class));
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {        // when clicked no
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel logout action
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //To close the app
    @Override
    public void onBackPressed(){

        Toast.makeText(getApplicationContext(), " Tata :) ", Toast.LENGTH_SHORT).show();
        finishAffinity();
        System.exit(0);

    }

    //To allocate different activity for different recycler view
    @Override
    public void onItemClick(int position) {

            if (position == 0)
                startActivity(new Intent(this, Phone.class));
            else if (position == 1)
                startActivity(new Intent(this, Camera.class));
            else if (position == 2)
                startActivity(new Intent(this, Whatsapp.class));
            else if (position == 3)
                startActivity(new Intent(this, PlayStore.class));
            else if (position == 4)
                startActivity(new Intent(this, Youtube.class));
            else if (position == 5)
                startActivity(new Intent(this, Gmail.class));
            else if (position == 6)
                startActivity(new Intent(this, Phone.class));
            else if (position == 7)
                startActivity(new Intent(this, Phone.class));
            else if (position == 8)
                startActivity(new Intent(this, Phone.class));
            else if (position == 9)
                startActivity(new Intent(this, Phone.class));

    }

}