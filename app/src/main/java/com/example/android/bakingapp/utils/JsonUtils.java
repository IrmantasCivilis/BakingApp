package com.example.android.bakingapp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_MEASURE = "measure";
    private static final String KEY_INGREDIENT = "ingredient";
    private static final String KEY_STEPS = "steps";
    private static final String KEY_STEP_ID = "id";
    private static final String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VIDEO_URL = "videoURL";
    private static final String KEY_THUMBNAIL_URL = "thumbnailURL";
    private static final String KEY_SERVINGS = "servings";
    private static final String KEY_IMAGE = "image";

    public static List<Recipe> extractFeaturesFromJson(String json) {

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        List<Recipe> recipes = new ArrayList<>();

        try {
            JSONArray recipesArray = new JSONArray(json);

            for (int i = 0; i < recipesArray.length(); i++) {

                JSONObject currentRecipe = recipesArray.getJSONObject(i);

                int recipeId = currentRecipe.getInt(KEY_ID);

                String recipeName = currentRecipe.getString(KEY_NAME);

                List<Ingredient> ingredients = new ArrayList<>();

                JSONArray ingredientsArray = currentRecipe.optJSONArray(KEY_INGREDIENTS);

                for (int j = 0; j < ingredientsArray.length(); j++ ) {

                    JSONObject currentIngredient = ingredientsArray.getJSONObject(j);

                    double quantity = currentIngredient.getDouble(KEY_QUANTITY);

                    String measure = currentIngredient.getString(KEY_MEASURE);

                    String ingredient = currentIngredient.getString(KEY_INGREDIENT);

                    Ingredient recipeIngredient = new Ingredient(quantity, measure, ingredient);

                    ingredients.add(recipeIngredient);

                    //TODO Finish Json parsing
                }
            }

        } catch (JSONException e) {
            Log.e("JsonUtils", "Problem parsing JSON");
        }

        return recipes;
    }

}
