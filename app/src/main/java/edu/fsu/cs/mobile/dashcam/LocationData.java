package edu.fsu.cs.mobile.dashcam;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;


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
