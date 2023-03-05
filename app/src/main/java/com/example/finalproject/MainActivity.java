package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID,PhName,fullName;

    ArrayList<RecModel> recModels = new ArrayList<>();

    int[] recImages = {R.drawable.mock,R.drawable.mock1,
            R.drawable.mock,R.drawable.mock1,
            R.drawable.mock,R.drawable.mock1,
            R.drawable.mock,R.drawable.mock1,
            R.drawable.mock,R.drawable.mock1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                PhName = value.getString("phName");
                fullName = value.getString("fullName");
            }
        });

        RecyclerView recyclerView = findViewById(R.id.mRecycler);
        setRecModels();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,recModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.about:
                startActivity(new Intent(MainActivity.this, About.class));
                break;

            case R.id.donate:
                startActivity(new Intent(MainActivity.this, Donation.class));
                break;

            case R.id.log_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(fullName+", Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mAuth.signOut();
                                startActivity(new Intent(MainActivity.this, Login.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
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

    public void onBackPressed(){

        Toast.makeText(getApplicationContext(), " Tata :) ", Toast.LENGTH_SHORT).show();
        finishAffinity();
        System.exit(0);

    }

    @Override
    public void onItemClick(int position) {
        if (PhName.equals("Samsung")) {
            if (position == 0)
                startActivity(new Intent(this, Phone.class));
            else if (position == 1)
                startActivity(new Intent(this, Message.class));
            else if (position == 2)
                startActivity(new Intent(this, Phone.class));
            else if (position == 3)
                startActivity(new Intent(this, Message.class));
            else if (position == 4)
                startActivity(new Intent(this, Phone.class));
            else if (position == 5)
                startActivity(new Intent(this, Message.class));
            else if (position == 6)
                startActivity(new Intent(this, Phone.class));
            else if (position == 7)
                startActivity(new Intent(this, Message.class));
            else if (position == 8)
                startActivity(new Intent(this, Phone.class));
            else if (position == 9)
                startActivity(new Intent(this, Message.class));
        }
        else {
            if (position == 0)
                startActivity(new Intent(this, Phone.class));
            else if (position == 1)
                startActivity(new Intent(this, GooglePay.class));
            else if (position == 2)
                startActivity(new Intent(this, Whatsapp.class));
            else if (position == 3)
                startActivity(new Intent(this, GooglePay.class));
            else if (position == 4)
                startActivity(new Intent(this, Whatsapp.class));
            else if (position == 5)
                startActivity(new Intent(this, GooglePay.class));
            else if (position == 6)
                startActivity(new Intent(this, Whatsapp.class));
            else if (position == 7)
                startActivity(new Intent(this, GooglePay.class));
            else if (position == 8)
                startActivity(new Intent(this, Whatsapp.class));
            else if (position == 9)
                startActivity(new Intent(this, GooglePay.class));
        }
    }
}