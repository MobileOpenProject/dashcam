package edu.fsu.cs.mobile.dashcam;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.net.URI;


/**
 * Created by Anjali on 7/29/2017.
 */

public class VideoPlayer extends Fragment

{
    String uri;
    //Activity activity = this.getActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /// activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View rootView = inflater.inflate(R.layout.video_player, container, false);
        VideoView vidView = (VideoView) rootView.findViewById(R.id.myVideo);

        //Uri vidUri = Uri.parse(uri);
        //String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        String vidPath = "file:///storage/emulated/0/DCIM/MapCam/VID_MapCam_Recording.mp4";
        File videoFile = new File(URI.create(vidPath).getPath());

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
