package edu.fsu.cs.mobile.dashcam;

import android.support.v7.app.ActionBar;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity
        implements VideoFragment.VideoFragmentListener,
                    MainFragment.MainFragmentListener {

    ListView mDrawerList;
    DrawerLayout mDrawerLayout;
    String[] DrawerList = new String[] {"Past Videos", "Settings"};
    List<String> DrawerArrayList;
    ArrayAdapter<String> drawer_adapter ;
    ActionBarDrawerToggle mDrawerToggle;
    String URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

//        //ADD TOOLBAR
//        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


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

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);





        onMain();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position){
        switch (position){
            case 0:
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                /*
                Cursor mCursor = getContentResolver().query(RunProvider.CONTENT_URI, null, null, null, null);
                SimpleCursorAdapter mAdapter;
                String[] mListColumns;

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Past Runs");

                if(metric == true) {
                    mListColumns = new String[]{RunProvider.RUN_MINUTES, RunProvider.RUN_SECONDS, RunProvider.RUN_MILISECONDS, RunProvider.RUN_STEPS, RunProvider.RUN_DISTANCE};

                    mAdapter = new SimpleCursorAdapter(this, R.layout.list_km, mCursor, mListColumns, new int[]{R.id.minute, R.id.second, R.id.milisecond, R.id.steps, R.id.distance}, 1);
                    alert.setAdapter(mAdapter, null);

                    alert.show();
                }else if(metric == false){
                    mListColumns = new String[]{RunProvider.RUN_MINUTES, RunProvider.RUN_SECONDS, RunProvider.RUN_MILISECONDS, RunProvider.RUN_STEPS, RunProvider.RUN_DISTANCE};

                    mAdapter = new SimpleCursorAdapter(this, R.layout.list_mi, mCursor, mListColumns, new int[]{R.id.minute, R.id.second, R.id.milisecond, R.id.steps, R.id.distance}, 1);
                    alert.setAdapter(mAdapter, null);

                    alert.show();
                }
                */
                mDrawerLayout.closeDrawers();
                break;
            case 2:
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Settings");
                builder.setMessage("Standard or Metric system:");

                builder.setPositiveButton("KM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!metric) {
                            metric = true;
                            milesToKilometers();
                            distance = menu.findItem(R.id.distance);
                            distance.setTitle(DIST_STR + distanceNum + KM);
                        }
                        mDrawerLayout.closeDrawers();
                    }
                });

                builder.setNegativeButton("Miles", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (metric) {
                            metric = false;
                            kilometersToMiles();
                            distance = menu.findItem(R.id.distance);
                            distance.setTitle(DIST_STR + distanceNum + Miles);
                        }
                        mDrawerLayout.closeDrawers();
                    }
                });

                builder.show();
                */
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
        VideoPlayer fragment = new VideoPlayer();
        //fragment.uri=URI;
        String tag = VideoPlayer.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();


    }

    public void onMain() {
        //MainFragment fragment;
        MainFragment fragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }


    public void startRecord() {
        VideoRecord fragment = new VideoRecord();
        String tag = VideoRecord.class.getCanonicalName();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment, tag).commit();
        URI = fragment.returnURI();
    }


    public void stopRecord() {

    }
}

