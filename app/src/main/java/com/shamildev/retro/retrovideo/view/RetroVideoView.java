package com.shamildev.retro.retrovideo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.shamildev.retro.R;

/**
 * Created by Shamil Lazar on 08.05.2018.
 */

public class RetroVideoView extends RelativeLayout {


//    private YouTubePlayerView youtubeplayer;
//    private YouTubePlayer.OnInitializedListener mOnInitializedListener;

    public RetroVideoView(Context context) {
        super(context);
        init(null);
    }


    public RetroVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_videoplayer,this);

        if(attrs!= null){

        }

//        this.youtubeplayer =  (YouTubePlayerView)findViewById(R.id.youtubeplayer);
//        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        };


    }

//    public YouTubePlayerView getYoutubeplayer() {
//        return youtubeplayer;
//    }
}

