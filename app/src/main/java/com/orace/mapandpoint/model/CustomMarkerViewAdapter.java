package com.orace.mapandpoint.model;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.orace.mapandpoint.R;

import java.util.HashMap;

/**
 * Custom adapter used to display your own Marker view on the map
 */
public class CustomMarkerViewAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    //Hashmap who match the Marker (used by Google Maps) with our own class CustomMarker, which will be used to display our custom data on the map
    HashMap<Marker, CustomMarker> mapMarker;

    /**
     * Constructor using fields, pass the hashmap containing all your markers here
     * @param pContext
     * @param pMapMarker
     */
    public CustomMarkerViewAdapter(Context pContext, HashMap<Marker, CustomMarker> pMapMarker) {
        context = pContext;
        mapMarker = pMapMarker;
    }

    /**
     * This method is called when the adapter view is rendered. On other terms it's called everytime the map wants to display a marker
     * @param pCurrentMarker
     * @return
     */
    @Override
    public View getInfoContents(Marker pCurrentMarker) {
        //Recovering the inflater from the context
        LayoutInflater inflater = LayoutInflater.from(context);
        // Getting view from the layout file info_window_layout
        View v = inflater.inflate(R.layout.custom_marker_view, null);

        // Getting the position from the marker
        LatLng latLng = pCurrentMarker.getPosition();

        // Getting reference to the TextView to set latitude
        TextView tvLat = (TextView) v.findViewById(R.id.TV_latitude);
        // Getting reference to the TextView to set longitude
        TextView tvLng = (TextView) v.findViewById(R.id.TV_longitude);
        // Getting reference to the TextView to set the description
        TextView tvDescription = (TextView) v.findViewById(R.id.TV_description);
        //Getting reference to the ImageView to set the logo
        ImageView ivLogo = (ImageView) v.findViewById(R.id.IV_Logo);
        TextView tvTitle = (TextView) v.findViewById(R.id.TV_Title);

        // Setting the latitude
        tvLat.setText("Latitude : " + latLng.latitude);

        // Setting the longitude
        tvLng.setText("Longitude : " + latLng.longitude);


        //We recover our custom CustomMarker in order to get the content displayed on our custom view
        CustomMarker lActualPoint = mapMarker.get(pCurrentMarker);
        if (lActualPoint != null) {
            //Setting the description
            tvDescription.setText(lActualPoint.getDescription());
            //Setting the title
            tvTitle.setText(lActualPoint.getTitle());
            //We try to recover the logo from the drawable package
            try {
                int drawableResourceId = context.getResources().getIdentifier(lActualPoint.getLogo(), "drawable", context.getPackageName());
                //And if we got it, we set it on our ImageView
                ivLogo.setImageResource(drawableResourceId);
            } catch (Exception e){
                Log.e("MapAndPoint","Logo not found",e);
                //If the logo isn't found, we'll set the default logo on our ImageView
                ivLogo.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.point));
            }
        }

        // Returning the view containing InfoWindow contents
        return v;
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

}