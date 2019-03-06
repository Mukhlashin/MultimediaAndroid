package com.example.multimediaandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioStreamingActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar_audiostream)
    ProgressBar progressBarAudiostream;
    @BindView(R.id.btn_play_audiostream)
    ImageButton btnPlayAudiostream;
    @BindView(R.id.btn_stop_audiostream)
    ImageButton btnStopAudiostream;

    MediaPlayer player;
    String url = "http://live2.radiorodja.com/;stream.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming);
        ButterKnife.bind(this);

        progressBarAudiostream.setIndeterminate(false);
        progressBarAudiostream.setVisibility(View.INVISIBLE);
        progressBarAudiostream.setMax(100);

        btnPlayAudiostream.setImageResource(android.R.drawable.ic_media_play);

        setPlaying();
    }

    private void setPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.start();
    }

    @OnClick({R.id.btn_play_audiostream, R.id.btn_stop_audiostream})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_play_audiostream:
                progressBarAudiostream.setVisibility(View.VISIBLE);
                progressBarAudiostream.setIndeterminate(true);
                setPlaying();
                try {
                    player.prepareAsync();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        progressBarAudiostream.setIndeterminate(false);
                    }
                });

                btnPlayAudiostream.setEnabled(false);
                btnStopAudiostream.setEnabled(true);
                break;
            case R.id.btn_stop_audiostream:
                if (player == null) return;
                if (player.isPlaying()) {
                    player.stop();
                    player.release();

                    progressBarAudiostream.setVisibility(View.INVISIBLE);
                    progressBarAudiostream.setIndeterminate(true);

                    btnStopAudiostream.setEnabled(false);
                    btnPlayAudiostream.setEnabled(true);
                }
                break;
        }
    }
}
