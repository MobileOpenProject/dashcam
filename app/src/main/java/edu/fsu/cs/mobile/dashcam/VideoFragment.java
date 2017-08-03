package edu.fsu.cs.mobile.dashcam;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: VideoFragment                                          */
/* Description:                                                 */
/* This file is a fragment which displays the recorded video    */
/* to the user                                                  */
/*                                                              */
/****************************************************************/


public class VideoFragment extends Fragment {

    private VideoFragmentListener mListener;
    private View mRootView;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.video_layout, container, false);
        mRootView.setBackgroundResource(0);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VideoFragmentListener) {
            mListener = (VideoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ViewFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface VideoFragmentListener
    {
        void onRecord(ContentValues values);
    }

}

