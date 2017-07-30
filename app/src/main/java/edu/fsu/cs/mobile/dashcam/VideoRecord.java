package edu.fsu.cs.mobile.dashcam;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Garrett on 28/7/17.
 */

public class VideoRecord extends Fragment {

    Button recordButton;

    private Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private CameraPreview mPreview;
    private VideoRecordListener mListener;
    public static String URI="";
    private boolean isRecording = false;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public VideoRecord() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_record, container, false);

        mCamera = getCameraInstance();

        recordButton = (Button) rootView.findViewById(R.id.stop_record_button);
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout) rootView.findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        recordButton = (Button) rootView.findViewById(R.id.stop_record_button);
        //recordButton.setVisibility(View.VISIBLE);
        recordButton.setText("Start Recording");

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRecording) {
                    Toast.makeText(getContext(), "stopping", Toast.LENGTH_LONG).show();
                    mMediaRecorder.stop();
                    releaseMediaRecorder();
                    Log.d("dbtag", "notrecording");
                    isRecording = false;
                    recordButton.setText("Start Recording");
                } else {
                    Toast.makeText(getContext(), "starting", Toast.LENGTH_LONG).show();
                    prepareVideoRecorder();
                    isRecording = true;
                    recordButton.setText("Stop Recording");
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




}
