package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class Phone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        String url1 = "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FGIF1.gif?alt=media&token=3035986d-1f91-4d9d-a16f-d18d4e428d00";
        ImageView imageView1 = findViewById(R.id.imageView3);
        Glide.with(getApplicationContext()).load(url1).into(imageView1);

        String url2 = "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FGIF2.gif?alt=media&token=d206d19c-454a-48dd-9a3c-5b04a7d9e3d9";
        ImageView imageView2 = findViewById(R.id.imageView4);
        Glide.with(getApplicationContext()).load(url2).into(imageView2);

        String url3 = "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FGIF3.gif?alt=media&token=5af7e95b-6b1c-4c5a-8ba1-57e16dfad2dc";
        ImageView imageView3 = findViewById(R.id.imageView5);
        Glide.with(getApplicationContext()).load(url3).into(imageView3);

        String url4 = "https://firebasestorage.googleapis.com/v0/b/final-project-b573c.appspot.com/o/Samsung%2FPhone%2FGIF4.gif?alt=media&token=ea8e06f9-fe74-401c-93d6-4c53c210e98a";
        ImageView imageView4 = findViewById(R.id.imageView6);
        Glide.with(getApplicationContext()).load(url4).into(imageView4);








    }
}