package edu.fsu.cs.mobile.dashcam;

/**
 * Created by sap15e on 7/23/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class VideoFragment extends Fragment{

    private VideoFragmentListener mListener;
    private View mRootView;

    public VideoFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.video_layout, container, false);
        // TODO: Setup UI
        //ImageView background = mRootView.findViewById(R.id.imageView);
        //background.setScaleType(ImageView.ScaleType.FIT_XY);
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

