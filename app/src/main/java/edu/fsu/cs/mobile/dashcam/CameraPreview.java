package edu.fsu.cs.mobile.dashcam;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import static edu.fsu.cs.mobile.dashcam.VideoRecord.getCameraInstance;
/****************************************************************/
/* -------MapCam App---------                                   */
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

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);

    }

    public void surfaceCreated(SurfaceHolder holder) {

            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
            } catch (IOException e) {

            }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if (mHolder.getSurface() == null) {
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e) {

        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {

        }
    }



}
