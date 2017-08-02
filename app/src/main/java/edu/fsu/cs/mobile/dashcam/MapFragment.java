package edu.fsu.cs.mobile.dashcam;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/****************************************************************/
/* -------MapCam App---------                                   */
/*                                                              */
/* Created By:  Alex Quesenberry, Katie Brodhead,               */
/*              Sree Paruchuri, Garrett Schmitt                 */
/*                                                              */
/* File: MapFragment                                            */
/* Description:                                                 */
/* This file is a fragment which displays the map view to       */
/* the user                                                     */
/*                                                              */
/****************************************************************/

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private static GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();

        fragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public static void drawLine(LatLng first, LatLng second) {
        PolylineOptions line = new PolylineOptions()
                .add(first, second)
                .width(5).color(Color.RED);
        mMap.addPolyline(line);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(second));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

    }
}
