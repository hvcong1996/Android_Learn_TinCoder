package com.example.get_videos_from_gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hb.xvideoplayer.MxVideoPlayer;
import hb.xvideoplayer.MxVideoPlayerWidget;

public class PlayVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        String videoUrl = getIntent().getStringExtra("video_url");

        MxVideoPlayerWidget videoPlayerWidget = (MxVideoPlayerWidget) findViewById(R.id.mpw_video_player);
        videoPlayerWidget.startPlay(videoUrl, MxVideoPlayer.SCREEN_LAYOUT_NORMAL, "video name");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MxVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (MxVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}