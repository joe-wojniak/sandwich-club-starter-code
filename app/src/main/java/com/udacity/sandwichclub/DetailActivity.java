package com.udacity.sandwichclub;

/* Portions of code modified from udacity/sandwich-club-starter-code*/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if (sandwich.getAlsoKnownAs() != null) {
            TextView textView = findViewById(R.id.textView);
            TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);

            List<String> alsoKnownAs = sandwich.getAlsoKnownAs();

            // looping through all alsoKnownAs sandwich names and update alsoKnownAsTextView
            for (int i = 0; i < alsoKnownAs.size(); i++) {
                alsoKnownAsTextView.append(alsoKnownAs.get(i) + "\r\n");
            }
        }
        if (sandwich.getIngredients() != null) {
            TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
            List<String> ingredients = sandwich.getIngredients();
            // looping through all sandwich ingredients and update ingredientsTextView
            for (int i = 0; i < ingredients.size(); i++) {
                ingredientsTextView.append(ingredients.get(i) + "\r\n");
            }
        }

        TextView originTextView = findViewById(R.id.origin_tv);
        String origin = sandwich.getPlaceOfOrigin();
        originTextView.setText(origin);

        TextView descriptionTextView = findViewById(R.id.description_tv);
        String description = sandwich.getDescription();
        descriptionTextView.setText(description);
    }
}
