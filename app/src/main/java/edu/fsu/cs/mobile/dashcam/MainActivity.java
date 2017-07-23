package edu.fsu.cs.mobile.dashcam;

import android.content.ContentValues;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements VideoFragment.VideoFragmentListener,MainFragment.MainFragmentListener
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ADD TOOLBAR
        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //onMain();
    }


    @Override
    public void onRecord(ContentValues values)
    {



    }

    @Override
    public void onReview()
    {


    }
    @Override
    public void onRecord()
    {


    }
    public void onMain()
    {
        //MainFragment fragment;
        MainFragment fragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
    }



}

