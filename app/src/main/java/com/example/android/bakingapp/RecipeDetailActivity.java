package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.adapters.StepsAdapter;

public class RecipeDetailActivity extends AppCompatActivity implements StepsAdapter.StepItemClickListener {

    RecyclerView mRecyclerView;
    StepsAdapter mStepsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();


        if (intentThatStartedThisActivity.hasExtra("Clicked Recipe")) {

            //Bundle recipe = intentThatStartedThisActivity.getBundleExtra("Clicked Recipe");
            Bundle recipe = getDataFromIntent(intentThatStartedThisActivity);
            RecipeStepsFragment fragment = new RecipeStepsFragment();
            fragment.setArguments(recipe);
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.recipe_steps_list, fragment).commit();

            String recipeName = recipe.getString("Name");
            setTitle(recipeName);

            //ArrayList<String> steps = recipe.getStringArrayList("Steps");
            //mRecyclerView = findViewById(R.id.steps_recycler_view);
            //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            //mRecyclerView.setLayoutManager(mLayoutManager);
            //mRecyclerView.setHasFixedSize(true);
            //mStepsAdapter = new StepsAdapter(this, steps, this);
            //mRecyclerView.setAdapter(mStepsAdapter);
        }

    }

    @Override
    public void onStepItemClick(String clickedStep) {

    }

    public Bundle getDataFromIntent (Intent intent){
        return intent.getBundleExtra("Clicked Recipe");
    }
}
