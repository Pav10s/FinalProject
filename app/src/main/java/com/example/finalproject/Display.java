package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Locale;

public class Display extends AppCompatActivity implements TextToSpeech.OnInitListener {
    int i = 0;
    String[] urls,desc;
    private Button next;
    private Button previous;
    private ProgressBar progressBar;
    TextView description;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().hide();

        tts = new TextToSpeech(this, this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speakOut(desc[0]);  //speak after 1000ms
            }
        }, 1000);


        progressBar = findViewById(R.id.progress_bar);

        next = findViewById(R.id.next_btn);
        previous = findViewById(R.id.previous_btn);
        description = findViewById(R.id.display_text);

        Intent intent = getIntent();
        urls = intent.getStringArrayExtra("URLS");
        desc = intent.getStringArrayExtra("Strings");

        imageRetrieve();
        textRetrieve(i);
        previous.setVisibility(View.INVISIBLE); //Initially set previous button disabled

        next.setOnClickListener(v -> {

            i++;
            if(i == urls.length - 1) {
                next.setVisibility(View.INVISIBLE);
            }

            if (i > 0) {
                previous.setVisibility(View.VISIBLE);
                imageRetrieve();
                textRetrieve(i);
                speakOut(desc[i]);
            }

        });

        previous.setOnClickListener(v -> {
            i--;
            if (i < urls.length - 1) {
                next.setVisibility(View.VISIBLE);
                imageRetrieve();
                textRetrieve(i);
                speakOut(desc[i]);
            }

            if (i == 0) {
                previous.setVisibility(View.INVISIBLE);
            }
        });


        /*
        description.setOnClickListener(v -> {
        speakOut(desc[i]);
        }) ;
         */

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Speak the text when the screen is touched
                speakOut(desc[i]);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    public void imageRetrieve() {
        progressBar.setVisibility(View.VISIBLE);
        ImageView display_image = findViewById(R.id.display_image);
        Glide.with(getApplicationContext()).load(urls[i]).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                display_image.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(display_image);
    }

     public void textRetrieve(int i) {
        description.setText(desc[i]);
    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"TextToSpeech");
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setSpeechRate(0.75f);
            tts.setLanguage(new Locale("en", "IND"));
            // TTS initialization succeeded
        } else {
            // TTS initialization failed
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

}