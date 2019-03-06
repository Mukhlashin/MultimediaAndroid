package com.example.multimediaandroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioSederhanaActivity extends AppCompatActivity {

    MediaPlayer player;

    @BindView(R.id.imgbtn_play)
    ImageButton imgbtnPlay;
    @BindView(R.id.imgbtn_stop)
    ImageButton imgbtnStop;

    private int mLastStateButton;
    String mStatePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sederhana);
        ButterKnife.bind(this);
        mLastStateButton = android.R.drawable.ic_media_play;
        mStatePlayer = "PLAY";
    }

    @OnClick({R.id.imgbtn_play, R.id.imgbtn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_play:
                if (mLastStateButton == android.R.drawable.ic_media_play && mStatePlayer.equals("PLAY")) {
                    mLastStateButton = android.R.drawable.ic_media_pause;
                    mStatePlayer = "PAUSE";
                    imgbtnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.almulk);
                    player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        player.setDataSource(AudioSederhanaActivity.this, uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    player.start();
                } else if (mLastStateButton == android.R.drawable.ic_media_pause && mStatePlayer.equals("PAUSE")) {
                    imgbtnPlay.setImageResource(android.R.drawable.ic_media_play);
                    mStatePlayer = "RESUME";
                    mLastStateButton = android.R.drawable.ic_media_play;
                    if (player.isPlaying()) {
                        player.pause();
                    }
                } else if(mLastStateButton == android.R.drawable.ic_media_play && mStatePlayer.equals("RESUME")) {
                    mLastStateButton = android.R.drawable.ic_media_pause;
                    imgbtnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    mStatePlayer = "PAUSE";
                        player.start();
                }
                break;
            case R.id.imgbtn_stop:
                mLastStateButton = android.R.drawable.ic_media_play;
                mStatePlayer = "PLAY";
                imgbtnPlay.setImageResource(android.R.drawable.ic_media_play);
                player.stop();
                break;
        }
    }
}
