package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    private static String mainName;
    private static List<String> alsoKnownAs;
    private static String placeOfOrigin;
    private static String description;
    private static String image;
    private static List<String> ingredients;

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject name = baseJsonResponse.getJSONObject("name");
            //JSONArray alsoKnownAsArray = baseJsonResponse.getJSONArray("alsoKnownAs");
            //JSONArray ingredientsArray = baseJsonResponse.getJSONArray("ingredients");

            //Parse JSON string to String variables for sandwich object
            mainName = name.getString("mainName");
            placeOfOrigin = baseJsonResponse.getString("placeOfOrigin");
            description = baseJsonResponse.getString("description");
            image = baseJsonResponse.getString("image");

            // TODO Parse JSON Array to List<String>
            // alsoKnownAs = (List<String>) alsoKnownAsArray.getString("alsoKnownAs");
            // ingredients = (List<String>) response.getJSONObject("ingredients");

            // Check if JSON was parsed to String variables correctly
            Log.v(LOG_TAG,"mainName: " +mainName);
            Log.v(LOG_TAG,"alsoKnownAs: "+alsoKnownAs);
            Log.v(LOG_TAG,"placeOfOrigin: "+placeOfOrigin);
            Log.v(LOG_TAG,"image: "+image);
            Log.v(LOG_TAG,"ingredients: "+ingredients);

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results ", e);
        }
        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs, placeOfOrigin,description,image, ingredients);
        return sandwich;
    }
}
