package edu.fsu.cs.mobile.dashcam;
import com.google.android.gms.maps.model.LatLng;

/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: LocationData                                           */
/* Description:                                                 */
/* This file is a helper class to store lat, long, and timestamp*/
/*                                                              */
/****************************************************************/

public class LocationData {
    public int TimeStamp;
    public LatLng aLocation;

    LocationData() { }

    LocationData(int t, LatLng l) {
        TimeStamp = t;
        aLocation = l;
    }
}
