package edu.fsu.cs.mobile.dashcam;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

////////CODE 1-of-3 TO OBTAIN THE GPS Coordinates, as Video Records/////////////////////////////
import com.google.android.gms.maps.model.LatLng;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
///////////////////////////////////////////////////////////////////////////////////////////

/****************************************************************/
/* -------DashCam App---------                                  */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: CameraPreview                                          */
/* Description:                                                 */
/* This file allows the user to view the camera live on their   */
/* screen by utilizing a surface view                           */
/*                                                              */
/****************************************************************/

public class VideoRecord extends Fragment implements LocationListener {

    ImageButton recordButton;

    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private CameraPreview mPreview;
    private VideoRecordListener mListener;
    public static String URI="";
    private boolean isRecording = false;
    Context context = getContext();
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static ArrayList<LocationData> locations = new ArrayList<>();

    public int startTime;

    Location startingLocation;


    ////////CODE 2-of-3 TO OBTAIN THE GPS Coordinates, as Video Records/////////////////////////////
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onLocationChanged(Location location) {
        if (isRecording) {
            LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            //locations.add(newLatLng);
            LocationData temp = new LocationData(((int)System.currentTimeMillis() / 1000) - startTime, newLatLng);
            locations.add(temp);
            Toast.makeText(getContext(), "LatLng = " + newLatLng.toString(), Toast.LENGTH_LONG).show();
        }


    }
    ///////////////////////////////////////////////////////////////////////////////////////////



    public VideoRecord() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_record, container, false);

        ////////CODE 3-of-3 TO OBTAIN THE GPS Coordinates, as Video Records/////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////



        mCamera = getCameraInstance();

        recordButton = rootView.findViewById(R.id.stop_record_button);
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        //recordButton = (Button) rootView.findViewById(R.id.stop_record_button);
        //recordButton.setVisibility(View.VISIBLE);
        //recordButton.setText("Start Recording");

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        LocationManager lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        startingLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecording) {
                    Toast.makeText(getContext(), "stopping", Toast.LENGTH_LONG).show();
                    mMediaRecorder.stop();
                    releaseMediaRecorder();
                    Log.d("dbtag", "notrecording");
                    isRecording = false;
                    arrayContents();
                    recordButton.setImageResource(R.drawable.record);
                } else {
                    Toast.makeText(getContext(), "starting", Toast.LENGTH_LONG).show();
                    prepareVideoRecorder();
                    isRecording = true;
                    startTime = (int)System.currentTimeMillis() / 1000;
                    LatLng newLatLng = new LatLng(startingLocation.getLatitude(), startingLocation.getLongitude());
                    LocationData temp = new LocationData(0, newLatLng);
                    locations.add(temp);
                    recordButton.setImageResource(R.drawable.stop);

                }
            }
        });







        return rootView;
    }

    public interface VideoRecordListener {
        void onMain();
    }


    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {

        }
        return c;
    }

    public String returnURI()
    {
        return URI;
    }

    private static Uri getOutputMediaFileUri(int type) {
        URI = (Uri.fromFile(getOutputMediaFile(type))).toString();
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "MapCam");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MapCam", "failed to create directory");
                return null;
            }
        }

        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" +
                    "MapCam_Snapshot" + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + "MapCam_Recording" + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private boolean prepareVideoRecorder() {
        //mCamera = getCameraInstance();
        mMediaRecorder = new MediaRecorder();

        mCamera.lock();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());

        mMediaRecorder.setMaxDuration(120000);



        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d("dbtag", "illegalstateexception");
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d("dbtag", "IOException");
            Log.d("dbtag", e.toString());
            releaseMediaRecorder();
            return false;
        }

        mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mediaRecorder, int i, int i1) {
                if(i == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                    Toast.makeText(getContext(), "2 Minute Recording Reached", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext(), "stopping", Toast.LENGTH_LONG).show();
                    mMediaRecorder.stop();
                    releaseMediaRecorder();
                    Log.d("dbtag", "notrecording");
                    isRecording = false;
                    //Toast.makeText(getContext(), "starting", Toast.LENGTH_LONG).show();
                    prepareVideoRecorder();
                    isRecording = true;
                }
            }
        });

        mMediaRecorder.start();
        return true;
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();
            //mMediaRecorder.release();
            //mMediaRecorder = null;
            mCamera.lock();
        }
    }

    private void releaseCamera () {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    public void arrayContents() {
        for (int i = 0; i < locations.size(); i++) {
            Log.d("dbtag", "Location" + locations.get(i).TimeStamp);
            Log.d("dbtag", "Location" + locations.get(i).aLocation);
        }
    }


}
