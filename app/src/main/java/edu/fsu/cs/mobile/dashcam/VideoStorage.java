package edu.fsu.cs.mobile.dashcam;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/****************************************************************/
/* -------DashCam App---------                                  */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: VideoStorage                                           */
/* Description:                                                 */
/* This file currently stores the URIs for any videos recorded, */
/* it also stores the location data points for GPS coordinates  */
/* Lat and Long                                                 */
/*                                                              */
/****************************************************************/

public class VideoStorage extends ContentProvider {

    /* declare some global variables */
    public final static String DBNAME = "DASHCAMSTORAGE";
    public final static String TABLE_NAMESTABLE = "VideoTable";
    public final static String PRIMARY_KEY = "_ID";
    public final static String URI = "video_uri";
    public final static String LATITUD = "lat";
    public final static String LONGITUDE = "long";
    public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.mobile.dashcam.provider");

    public DBHelper db_helper;

    /* create a table which will store the data */
    private static final String SQL_CREATE_MAIN = "CREATE TABLE VideoTable ( " +
            "_ID INTEGER PRIMARY KEY, " +
            "lat REAL, " +
            "long REAL, " +
            "video_uri TEXT )";

    /* when ViedoStoage class is called, create a new DBHelper object */
    @Override
    public boolean onCreate() {
        db_helper = new DBHelper(getContext());
        return true;
    }

    /* insert data into the table */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db_helper.getWritableDatabase()
                .insert(TABLE_NAMESTABLE, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    /* Update a entry in the table */
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return db_helper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }

    /* Delete data from the table */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db_helper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    /* Query data from a table, this utilizes the DBHelper */
    /* Many different types of queries can be performed */
    /* such as get all data, get a row, col */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return db_helper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    /* It is used by applications (either other third-party applications, */
    /* if your ContentProvider has been exported, or your own) to retrieve */
    /* the MIME type of the given content URL */
    @Override
    public String getType(Uri uri){
        return uri.toString();
    }

    /* helper class for opening the SQLite DB */
    protected static final class DBHelper extends SQLiteOpenHelper {
        DBHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }




}
