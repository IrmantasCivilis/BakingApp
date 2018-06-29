package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.adapters.StepsAdapter;

import java.util.ArrayList;

public class RecipeStepsFragment extends Fragment implements StepsAdapter.StepItemClickListener {

    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);

        Bundle recipe = getArguments();
        if (recipe != null) {
            ArrayList<String> steps = recipe.getStringArrayList("Steps");
            RecyclerView mRecyclerView = rootView.findViewById(R.id.steps_recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setHasFixedSize(true);
            StepsAdapter mStepsAdapter = new StepsAdapter(getContext(), steps, this);
            mRecyclerView.setAdapter(mStepsAdapter);
        }

        return rootView;
    }

    @Override
    public void onStepItemClick(String clickedStep) {

    }

}
