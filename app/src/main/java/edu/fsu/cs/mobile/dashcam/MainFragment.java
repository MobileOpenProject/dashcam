package edu.fsu.cs.mobile.dashcam;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: MainFragment                                           */
/* Description:                                                 */
/* This file is a fragment which displays the home screen to    */
/* the user                                                     */
/*                                                              */
/****************************************************************/

public class MainFragment extends Fragment
{

    private MainFragmentListener mListener;
    public Button Record;
    Button Review;
    Button Stop;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // TODO: setup UI
        Record = (Button) rootView.findViewById(R.id.button_record);
        Review = (Button) rootView.findViewById(R.id.button_review);
        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.startRecord();
            }
        });


        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onReview();
            }
        });

        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentListener) {
            mListener = (MainFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface MainFragmentListener {
        void onReview();

        void startRecord();
        void stopRecord();
    }
}
