package com.udacity.sandwichclub.utils;

/* Portions of code modified starting from udacity/sandwich-club-starter-code
   and the following sources:

   Parsing JSON Array code modified from an example at
   https://www.tutorialspoint.com/android/android_json_parser.htm

   Working with List<String> variables adapted from
   https://stackoverflow.com/questions/13395114/how-to-initialize-liststring-object-in-java*/

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

            //Parse JSON string to String variables for sandwich object
            mainName = name.getString("mainName");
            placeOfOrigin = baseJsonResponse.getString("placeOfOrigin");
            description = baseJsonResponse.getString("description");
            image = baseJsonResponse.getString("image");

            // COMPLETE Parse JSON Array to List<String>
            // Instantiate the List<String> variables
            alsoKnownAs = new ArrayList<>();
            ingredients = new ArrayList<>();

            // Parse JSON Array to List<String> variables: alsoKnownAs and ingredients
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            JSONArray ingredientsArray = baseJsonResponse.getJSONArray("ingredients");

            // looping through all alsoKnownAsArray sandwich names
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }
            // looping through all ingredientsArray sandwich ingredients
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results ", e);
        }
        Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        return sandwich;
    }
}