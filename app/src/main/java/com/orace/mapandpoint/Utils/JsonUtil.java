package com.orace.mapandpoint.Utils;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orace.mapandpoint.model.CustomMarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to recovers our list of custom markers from the json file called "points.json"
 */
public class JsonUtil {

    //The name of our json file containing all our markers
    private static final String FILENAME = "points.json";

    /**
     * Recover all the custom marker from the local json file
     * In order to change the json file (for example adding and/or deleting properties), you need to also change the CustomMarker class
     * or you'll get an IO exception here on the deserialization
     * @param context
     * @return
     */
    public static ArrayList<CustomMarker> getPointsFromJson(Context context) {

        ArrayList<CustomMarker> lPointsList = new ArrayList<CustomMarker>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //We deserialize our json file with jackson lib in order to get an arraylist of our CustomMarker object
            CustomMarker[] lTabPoints = objectMapper.readValue(context.getAssets().open(FILENAME), CustomMarker[].class);
            lPointsList = new ArrayList<>(Arrays.asList(lTabPoints));
        } catch (IOException e) {
            Log.d("MapAndPoint", "An error has occured while reading the json file", e);
        }

        return lPointsList;
    }
}
