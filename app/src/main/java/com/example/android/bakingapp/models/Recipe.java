package com.example.android.bakingapp.models;

import java.util.List;

public class Recipe {

    private int mId;
    private String mName;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;
    private int mServings;
    private String mImage;

    public Recipe(int id, String name, List<Ingredient> ingredient, List<Step> step, int serving, String image) {
        mId = id;
        mName = name;
        mIngredients = ingredient;
        mSteps = step;
        mServings = serving;
        mImage = image;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredient() {
        return mIngredients;
    }

    public List<Step> getStep() {
        return mSteps;
    }

    public int getServing() {
        return mServings;
    }

    public String getImage() {
        return mImage;
    }
}
