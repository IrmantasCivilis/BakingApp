package com.example.android.bakingapp.models;

import java.util.List;

public class Recipe {

    private int mId;
    private String mName;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;
    private int mServings;
    private String mImage;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, int serving, String image) {
        mId = id;
        mName = name;
        mIngredients = ingredients;
        mSteps = steps;
        mServings = serving;
        mImage = image;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public int getServing() {
        return mServings;
    }

    public String getImage() {
        return mImage;
    }
}
