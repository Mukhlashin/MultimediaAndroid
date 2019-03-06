package com.example.multimediaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void audio(View view) {
        startActivity(new Intent(this, AudioActivity.class));
    }

    public void audioSederhana(View view) {
        startActivity(new Intent(this, AudioSederhanaActivity.class));
    }

    public void videoOffline(View view) {
        startActivity(new Intent(this, VideoActivity.class));
    }

    public void audioStreaming(View view) {
        startActivity(new Intent(this, AudioStreamingActivity.class));
    }

    public void videoStreaming(View view) {
        startActivity(new Intent(this, VideoStreamingActivity.class));
    }
}
