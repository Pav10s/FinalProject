package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        button1 = findViewById(R.id.phone);
        button2 = findViewById(R.id.message);
        button3 = findViewById(R.id.whatsapp);
        button4 = findViewById(R.id.gpay);

        button1.setOnClickListener(v -> startActivity(new Intent(this,Phone.class)));

        button2.setOnClickListener(v -> startActivity(new Intent(this,Message.class)));

        button3.setOnClickListener(v -> startActivity(new Intent(this,Whatsapp.class)));

        button4.setOnClickListener(v -> startActivity(new Intent(this,GooglePay.class)));



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
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}