package com.rootulp.rootulpjsona;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Geocoder;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback{

    private GoogleMap mMap = null; // Might be null if Google Play services APK is not available.
    private Geocoder geocoder;
    List<Address> addressList = null;
    double lat, lng;
    private LatLng latLng;

    ArrayList<String> vals;
    ArrayAdapter<String> adapter;

    ArrayList<artist> artistList;
    ArrayList<Integer> paramArray;
    StringBuffer locationName = new StringBuffer();

    @Override
    public void onMapReady(GoogleMap map) { //mapisloadedbutnotlaidoutyet
        map.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapartist);
        MapFragment mf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mf.getMapAsync(this);
        geocoder = new Geocoder(this);
        Intent myCallerIntentHandler = getIntent();
        Bundle myBundle = myCallerIntentHandler.getExtras();

        paramArray = myBundle.getIntegerArrayList("myIntArray1");
        artistList = (ArrayList<artist>) myBundle.getSerializable("artists");

        latlongCheck(paramArray,artistList);

        retrieveCities(paramArray,artistList);
    }

    public void latlongCheck(ArrayList<Integer> selected, ArrayList<artist> arts) {

        vals = new ArrayList<String>();

        for (int i = 0; i < selected.size(); i++) {
            String home = arts.get(selected.get(i)).getNationality();
            String person = arts.get(selected.get(i)).getName();
            locationName.replace(0, locationName.length(), home);

            try {
                addressList = geocoder.getFromLocationName(locationName.toString(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList != null && addressList.size() > 0) {
                lat = (double) (addressList.get(0).getLatitude());
                lng = (double) (addressList.get(0).getLongitude());
            }
        }
    }


    private void retrieveCities(ArrayList<Integer> selected, ArrayList<artist> arts) {

        for (int i = 0; i < selected.size(); i++) {
            String home = arts.get(selected.get(i)).getNationality();
            String person = arts.get(selected.get(i)).getName();
            String title = arts.get(selected.get(i)).getTitle();
            locationName.replace(0, locationName.length(), home);

            try {
                addressList = geocoder.getFromLocationName(locationName.toString(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList != null && addressList.size() > 0) {
                lat = (double) (addressList.get(0).getLatitude());
                lng = (double) (addressList.get(0).getLongitude());
            }
            if (mMap == null) {
                mMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
            }
            latLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(latLng).title(person).snippet(title));

        }
    }}

