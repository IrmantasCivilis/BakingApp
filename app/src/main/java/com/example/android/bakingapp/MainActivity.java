package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapp.adapters.RecipesAdapter;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.utils.JsonUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.RecipeItemClickListener {

    RecipesAdapter mRecipesAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String bakingJson = JsonUtils.getJSONString(this);
        List<Recipe> recipes = JsonUtils.extractFeaturesFromJson(bakingJson);

        mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecipesAdapter = new RecipesAdapter(this, recipes, this);
        mRecyclerView.setAdapter(mRecipesAdapter);
    }

    @Override
    public void onRecipeItemClick(Recipe clickedRecipe) {

    }
}
