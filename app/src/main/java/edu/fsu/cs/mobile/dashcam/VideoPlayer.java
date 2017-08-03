package edu.fsu.cs.mobile.dashcam;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.net.URI;


/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: VideoPlayer                                            */
/* Description:                                                 */
/* This file is a fragment which displays the recorded video    */
/* to the user                                                  */
/*                                                              */
/****************************************************************/

public class VideoPlayer extends Fragment {
    String uri;
    VideoView vidView;

    LatLng first;

    public void getTime() {
        if (vidView.isPlaying()) {
            Log.d("dbtag", Integer.toString(vidView.getCurrentPosition()/1000));
            if (VideoRecord.locations.size() != 0) {
                for (int i = 1; i < VideoRecord.locations.size(); i++) {
                    if (vidView.getCurrentPosition() / 1000 == VideoRecord.locations.get(i).TimeStamp) {
                        MapFragment.drawLine(first, VideoRecord.locations.get(i).aLocation);
                        first = VideoRecord.locations.get(i).aLocation;
                        break;
                    }
                }
            }
        }

    }

    final Handler mHandler = new Handler();

    final Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            getTime();
            mHandler.postDelayed(mTicker, 1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (VideoRecord.locations.size() != 0) {
            first = VideoRecord.locations.get(0).aLocation;
        } else {
            Toast.makeText(getContext(), "No map data to show", Toast.LENGTH_LONG).show();
        }

        View rootView = inflater.inflate(R.layout.video_player, container, false);
        vidView = (VideoView) rootView.findViewById(R.id.myVideo);
        String vidPath = "file:///storage/emulated/0/DCIM/MapCam/VID_MapCam_Recording.mp4";
        File videoFile = new File(URI.create(vidPath).getPath());

        mTicker.run();

        if (videoFile.exists()) {

            vidView.setVideoURI(Uri.parse(vidPath));
            vidView.start();
            MediaController vidControl = new MediaController(this.getContext());
            vidControl.setAnchorView(vidView);
            vidView.setMediaController(vidControl);
        } else {
            Toast.makeText(getContext(), "Sorry no recording to show", Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

}
