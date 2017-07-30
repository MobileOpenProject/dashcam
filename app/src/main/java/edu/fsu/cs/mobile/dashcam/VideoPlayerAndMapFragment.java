package edu.fsu.cs.mobile.dashcam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Garrett on 30/7/17.
 */

public class VideoPlayerAndMapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.videoplayer_and_map, container, false);
        VideoPlayer videoPlayer;
        MapFragment mapFragment;

        FragmentManager manager = getFragmentManager();

        videoPlayer = (VideoPlayer) manager.findFragmentById(R.id.video_player_frame);
        mapFragment = (MapFragment) manager.findFragmentById(R.id.map_frame);

        return rootView;
    }
}
