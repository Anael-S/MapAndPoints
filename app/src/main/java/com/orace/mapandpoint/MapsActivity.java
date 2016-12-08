package com.orace.mapandpoint;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orace.mapandpoint.Utils.JsonUtil;
import com.orace.mapandpoint.model.CustomMarker;
import com.orace.mapandpoint.model.CustomMarkerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Our main activity used to display the map with our markers
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Our google map
    private GoogleMap mMap;
    //Hashmap containing all our marker/custom points associations
    HashMap<Marker, CustomMarker> mapMarker;
    //List of all our custom points
    ArrayList<CustomMarker> customMarkerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Load all the markers stocked in the JSon file
        customMarkerList = JsonUtil.getPointsFromJson(this);

        // Obtain the SupportMapFragment and get notified (via onMapReady) when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mapMarker = new HashMap<>();
        //We browse our list of point and add every marker on the map and in the hashmap
        for (CustomMarker lPoint : customMarkerList) {
            if (lPoint != null) {
                int drawableResourceId;
                try {
                    drawableResourceId = getResources().getIdentifier(lPoint.getLogo(), "drawable", getPackageName());
                } catch (Exception e) {
                    Log.e("MapAndPoint", "Logo not found", e);
                    drawableResourceId = getResources().getIdentifier("point", "drawable", getPackageName());
                }

                BitmapDescriptor lMarkerIcon = BitmapDescriptorFactory.fromResource(drawableResourceId);

                MarkerOptions lMarkerOption = new MarkerOptions().position(new LatLng(lPoint.getLatitude(), lPoint.getLongitude())).
                        title(lPoint.getTitle()).icon(lMarkerIcon);
                Marker lMarker = mMap.addMarker(lMarkerOption);
                mapMarker.put(lMarker, lPoint);
            }
        }

        //Set the map adapter, that's how the custom content is displayed
        mMap.setInfoWindowAdapter(new CustomMarkerViewAdapter(this, mapMarker));

    }
}
