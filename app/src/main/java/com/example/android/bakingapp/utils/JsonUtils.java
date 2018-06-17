package com.example.android.bakingapp.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.bakingapp.models.Ingredient;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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

                for (int j = 0; j < ingredientsArray.length(); j++) {

                    JSONObject currentIngredient = ingredientsArray.getJSONObject(j);

                    double quantity = currentIngredient.getDouble(KEY_QUANTITY);

                    String measure = currentIngredient.getString(KEY_MEASURE);

                    String ingredient = currentIngredient.getString(KEY_INGREDIENT);

                    Ingredient recipeIngredient = new Ingredient(quantity, measure, ingredient);

                    ingredients.add(recipeIngredient);
                }

                List<Step> steps = new ArrayList<>();

                JSONArray stepsArray = currentRecipe.optJSONArray(KEY_STEPS);

                for (int k = 0; k < stepsArray.length(); k++) {

                    JSONObject currentStep = stepsArray.getJSONObject(k);

                    int stepId = currentStep.getInt(KEY_STEP_ID);

                    String shortDescription = currentStep.getString(KEY_SHORT_DESCRIPTION);

                    String description = currentStep.getString(KEY_DESCRIPTION);

                    String videoURL = currentStep.getString(KEY_VIDEO_URL);

                    String thumbnailURL = currentStep.getString(KEY_THUMBNAIL_URL);

                    Step recipeStep = new Step(stepId, shortDescription, description, videoURL, thumbnailURL);

                    steps.add(recipeStep);
                }

                int servings = currentRecipe.getInt(KEY_SERVINGS);

                String image = currentRecipe.getString(KEY_IMAGE);

                Recipe recipe = new Recipe(recipeId, recipeName, ingredients, steps, servings, image);

                recipes.add(recipe);
            }

        } catch (JSONException e) {
            Log.e("JsonUtils", "Problem parsing JSON");
        }

        return recipes;
    }

    public static String getJSONString (Context context) {

        StringBuilder output = new StringBuilder();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("baking_json.txt");


            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }

        } catch (IOException e) {
            Log.e("JsonUtils", "Problem converting file to string");

        }
        return output.toString();
    }

}
