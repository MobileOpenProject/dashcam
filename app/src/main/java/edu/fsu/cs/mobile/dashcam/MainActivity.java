package edu.fsu.cs.mobile.dashcam;

import android.content.ContentValues;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements VideoFragment.VideoFragmentListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onMain();
    }

    @Override
    public void onRecord(ContentValues values)
    {



    }

    public void onMain()
    {
        MainFragment fragment;
    }



}

