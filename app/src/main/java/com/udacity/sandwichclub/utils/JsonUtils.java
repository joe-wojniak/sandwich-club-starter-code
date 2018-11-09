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
            JSONObject response = baseJsonResponse.getJSONObject("name");
            mainName = response.getString("mainName");
            alsoKnownAs = (List<String>) response.getJSONObject("alsoKnownAs");
            placeOfOrigin = response.getString("placeOfOrigin");
            description = response.getString("description");
            image = response.getString("image");
            ingredients = (List<String>) response.getJSONObject("ingredients");
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results ", e);
        }
        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs, placeOfOrigin,description,image, ingredients);
        return sandwich;
    }
}
