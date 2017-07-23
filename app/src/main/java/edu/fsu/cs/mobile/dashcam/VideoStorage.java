package edu.fsu.cs.mobile.dashcam;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by alex on 7/23/2017.
 * this currently stores the URIs for any videos recorded
 * last edited
 * asdfasdf
 */

public class VideoStorage extends ContentProvider {

    public final static String DBNAME = "DASHCAMSTORAGE";
    public final static String TABLE_NAMESTABLE = "VideoTable";
    public final static String PRIMARY_KEY = "_ID";
    public final static String URI = "video_uri";

    /*This table will store the Uri to the video*/
    private static final String SQL_CREATE_MAIN = "CREATE TABLE VideoTable ( " +
            "_ID INTEGER PRIMARY KEY, " +
            "video_uri TEXT )";


    public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.mobile.dashcam.provider");
    public DBHelper db_helper;

    @Override
    public boolean onCreate() {
        db_helper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db_helper.getWritableDatabase()
                .insert(TABLE_NAMESTABLE, null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //need to check for invalid values?
        return db_helper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db_helper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return db_helper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri){
        return null;
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
