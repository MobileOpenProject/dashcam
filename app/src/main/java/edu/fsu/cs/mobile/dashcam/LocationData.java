package edu.fsu.cs.mobile.dashcam;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Garrett on 2/8/17.
 */

public class LocationData {
    public int TimeStamp;
    public LatLng aLocation;

    LocationData() {

    }

    LocationData(int t, LatLng l) {
        TimeStamp = t;
        aLocation = l;
    }
}
