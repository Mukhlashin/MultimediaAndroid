package com.example.multimediaandroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends AppCompatActivity {

    MediaPlayer player;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_resume)
    Button btnResume;
    @BindView(R.id.btn_pause)
    Button btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);

        player = new MediaPlayer();

        enable(btnStart);
        disable(btnPause);
        disable(btnResume);
        disable(btnStop);

    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_resume, R.id.btn_pause})
    public void onViewClicked(View view) {

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.almulk);

        switch (view.getId()) {
            case R.id.btn_start:

                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    player.setDataSource(AudioActivity.this, uri);
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

                disable(btnStart, btnResume);
                enable(btnPause, btnStop);

                break;
            case R.id.btn_stop:
                player.stop();
                player.reset();
                player.release();

                enable(btnStart);
                disable(btnResume, btnPause, btnStop);
                break;
            case R.id.btn_resume:
                player.start();
                enable(btnStop, btnPause);
                disable(btnStart, btnResume);
                break;
            case R.id.btn_pause:
                if (player.isPlaying() && player != null) {
                    player.pause();
                    enable(btnResume, btnStop);
                    disable(btnPause, btnStart);
                }
                break;
        }
    }

    private void enable(View... views) {
        for (View v : views) {
            v.setEnabled(true);
        }
    }

    private void disable(View... views) {
        for (View v : views) {
            v.setEnabled(false);
        }
    }
}