package com.example.multimediaandroid;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoStreamingActivity extends AppCompatActivity {

    @BindView(R.id.vv_videostream)
    VideoView vvVideostream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_streaming);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Mohon Tunggu");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        MediaController controller = new MediaController(this);
        controller.setAnchorView(vvVideostream);
        vvVideostream.setMediaController(controller);

        String url = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

        Uri uri_url = Uri.parse(url);
        vvVideostream.setVideoURI(uri_url);
        vvVideostream.requestFocus();

        vvVideostream.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                dialog.dismiss();
                mp.start();
            }
        });
    }
}
