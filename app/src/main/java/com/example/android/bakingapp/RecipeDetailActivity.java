package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.adapters.StepsAdapter;
import com.example.android.bakingapp.fragments.RecipeStepsFragment;

public class RecipeDetailActivity extends AppCompatActivity implements StepsAdapter.StepItemClickListener {

    int recipeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();


        if (intentThatStartedThisActivity.hasExtra("Clicked Recipe")) {

            Bundle recipe = intentThatStartedThisActivity.getBundleExtra("Clicked Recipe");
            RecipeStepsFragment fragment = new RecipeStepsFragment();
            fragment.setArguments(recipe);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.recipe_steps_list, fragment).commit();

            recipeId = recipe.getInt("Recipe Id");
            String recipeName = recipe.getString("Name");
            setTitle(recipeName);
        }
    }

    @Override
    public void onStepItemClick(int clickedPosition) {

        Bundle stepId = new Bundle();
        stepId.putInt("Step", clickedPosition);
        stepId.putInt("Recipe Id", recipeId);

        Intent intent = new Intent(RecipeDetailActivity.this, RecipeStepActivity.class);
        intent.putExtra("Step Id", stepId);

        startActivity(intent);
    }
}
