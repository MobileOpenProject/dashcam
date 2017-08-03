package edu.fsu.cs.mobile.dashcam;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.widget.Toast;
/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: PlugInControlReceiver                                  */
/* Description:                                                 */
/* This file is to detect and respond to the phone being plugged*/
/*  in to a power outlet                                        */
/*                                                              */
/****************************************************************/

public class PlugInControlReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context , Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Power Connected", Toast.LENGTH_LONG).show();
            //Will be used for future implementations
        }

        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power Disconnected", Toast.LENGTH_LONG).show();
            //Will be used for future implementations
        }
    }

}