package edu.fsu.cs.mobile.dashcam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ListView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: MainActivity                                           */
/* Description:                                                 */
/* This is the only activity in our application. It is the      */
/* central location where all the other classes are called and  */
/* used(directly or indirectly), and displays all the fragments */
/* for displaying the recorded video, recording the video,      */
/* maps, and a home screen for the user                         */
/*                                                              */
/****************************************************************/




public class MainActivity extends AppCompatActivity
        implements VideoFragment.VideoFragmentListener,
                    MainFragment.MainFragmentListener {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private List<String> DrawerArrayList;
    private ArrayAdapter<String> drawer_adapter ;
    private ActionBarDrawerToggle mDrawerToggle;
    private String URI;
    final private int REQUEST_ALL_PERMISSION = 1;
    final private String[] DrawerList = new String[] {"Past Videos", "Settings"};

    /********************************************************************/
    /* Method to run anything that is neccessary to this activity at the*/
    /* time of creation                                                 */
    /********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        requestPermission1();

        // Drawer Variables
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        DrawerArrayList = new ArrayList<String>(Arrays.asList(DrawerList));
        drawer_adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, DrawerArrayList);
        mDrawerList.setAdapter(drawer_adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        onMain();

    }

    /********************************************************************/
    /* Method to request Camera, Audio, and external storage permission */
    /********************************************************************/
    private void requestPermission1() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            //Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECORD_AUDIO},
                    REQUEST_ALL_PERMISSION);
        }
    }


    /*************************************************************/
    /* Method to overide onRequestPermissionResult               */
    /* This Method is used for any actions needed to be taken    */
    /* based on the users response to the request for permissions*/
    /*************************************************************/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ALL_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Application Will Not Function Correctly Without Permissions", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /*************************************************************/
    /* Helpter method for action taken when different            */
    /* items are selected in the drawer                          */
    /*************************************************************/
    private void selectItem(int position){
        switch (position){
            case 0:
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                //To be used for future implementations
                mDrawerLayout.closeDrawers();
                break;
            case 2:
                //To be used for future implementations
                break;
            default:
                Toast.makeText(this, "This is the Default in Drawer", Toast.LENGTH_SHORT).show();
        }
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
                //Toast.makeText(this, "Auto Clicked", Toast.LENGTH_LONG).show();
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
    public void onRecord(ContentValues values) {

    }

    @Override
    public void onReview() {

        VideoPlayerAndMapFragment fragment = new VideoPlayerAndMapFragment();

        String tag = VideoPlayerAndMapFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();


    }

    /* Calls main fragment */
    public void onMain() {
        MainFragment fragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    /*when user hits record button,VideoRecord fragment shows up */
    public void startRecord() {
        VideoRecord fragment = new VideoRecord();
        String tag = VideoRecord.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
        getSupportFragmentManager().popBackStack();
    }

    //WHEN USER CLICKS ON THE BACK BUTTON, MAIN SCREEN POPS UP.
    @Override
    public void onBackPressed()
    {
        onMain();
        return;
    }

    public void stopRecord() {

    }
}

