package com.gpf.myprojectysdq.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;


import com.gpf.myprojectysdq.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class IndexPlayActivity extends AppCompatActivity {

//    @ViewInject(R.id.vv)
    private VideoView videoView;
//    @ViewInject(R.id.wvShow)
//    private WebView webView;
    private Intent intent;
    private String url;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(!LibsChecker.checkVitamioLibs(this))
            return;*/
        setContentView(R.layout.activity_index_play);
        videoView = (VideoView) findViewById(R.id.vv);
        mediaController = new MediaController(this);
        url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/test.mp4";
        if(videoView!=null){
            videoView.setVideoPath(url);
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);
            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,1.5f);
            videoView.start();
        }
    }
}
