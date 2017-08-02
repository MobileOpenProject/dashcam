package edu.fsu.cs.mobile.dashcam;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;
import java.net.URI;


/****************************************************************/
/* -------DashCam App---------                                  */
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

public class VideoPlayer extends Fragment

{
    String uri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.video_player, container, false);
        VideoView vidView = (VideoView) rootView.findViewById(R.id.myVideo);
        String vidPath = "file:///storage/emulated/0/DCIM/MapCam/VID_MapCam_Recording.mp4";
        File videoFile = new File(URI.create(vidPath).getPath());

        //if video exists then play it otherwise toast will show
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
