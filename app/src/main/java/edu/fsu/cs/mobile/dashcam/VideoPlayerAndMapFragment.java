package edu.fsu.cs.mobile.dashcam;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/****************************************************************/
/* -------DashCam App---------                                  */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: CameraPreview                                          */
/* Description:                                                 */
/* This file allows the user to view the camera live on their   */
/* screen by utilizing a surface view                           */
/*                                                              */
/****************************************************************/

public class VideoPlayerAndMapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.videoplayer_and_map, container, false);
        VideoPlayer videoPlayer;
        MapFragment mapFragment;
        rootView.setBackgroundColor(Color.BLACK);
        FragmentManager manager = getFragmentManager();
        videoPlayer = (VideoPlayer) manager.findFragmentById(R.id.video_player_frame);
        mapFragment = (MapFragment) manager.findFragmentById(R.id.map_frame);

        return rootView;
    }
}
