package edu.fsu.cs.mobile.dashcam;

import android.content.ContentValues;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

        onMain();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //add actions to the menu selections
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_auto:
                Toast.makeText(this, "Auto Clicked", Toast.LENGTH_LONG).show();

                //TODO: put actions for click

                break;
            case R.id.menu_manual:
                Toast.makeText(this, "Manual Clicked", Toast.LENGTH_LONG).show();

                //TODO: put actions for click

                break;
        }
        return true;
    }


    @Override
    public void onRecord(ContentValues values)
    {



    }

    @Override
    public void onReview()
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

