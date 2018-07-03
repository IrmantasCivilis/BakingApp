package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakingapp.fragments.InstructionFragment;
import com.example.android.bakingapp.fragments.MediaPlayerFragment;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.utils.JsonUtils;

import java.util.List;

public class RecipeStepActivity extends AppCompatActivity {

    Button previousButton;
    Button nextButton;
    int id;
    int recipeId;
    InstructionFragment mInstructionFragment;
    MediaPlayerFragment mMediaPlayerFragment;
    FragmentManager mFragmentManager;
    TextView textView;
    List<Recipe> recipes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        textView = findViewById(R.id.step_id_text_view);
        previousButton = findViewById(R.id.button_previous);
        nextButton = findViewById(R.id.button_next);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra("Step Id")){
            Bundle bundle = intentThatStartedThisActivity.getBundleExtra("Step Id");
            id = bundle.getInt("Step");


            recipeId = bundle.getInt("Recipe Id");
            textView.setText(String.valueOf(id));

            String bakingJson = JsonUtils.getJSONString(this);
            recipes = JsonUtils.extractFeaturesFromJson(bakingJson);

            String title = recipes.get(recipeId).getName();
            setTitle(title);
            String instruction = recipes.get(recipeId).getSteps().get(id).getDescription();
            if(id == 0){
                previousButton.setVisibility(View.INVISIBLE);
            }
            if (id == recipes.get(recipeId).getSteps().size() - 1){
                nextButton.setVisibility(View.INVISIBLE);
            }

            mInstructionFragment = new InstructionFragment();
            Bundle instructionBundle = new Bundle();
            instructionBundle.putString("Instruction", instruction);
            mInstructionFragment.setArguments(instructionBundle);

            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.instruction_container, mInstructionFragment)
                    .commit();

            String videoURL = recipes.get(recipeId).getSteps().get(id).getVideoURL();
            mMediaPlayerFragment = new MediaPlayerFragment();
            Bundle videoBundle = new Bundle();
            videoBundle.putString("Video URL", videoURL);
            mMediaPlayerFragment.setArguments(videoBundle);

            mFragmentManager.beginTransaction()
                    .add(R.id.media_container, mMediaPlayerFragment)
                    .commit();
        }
    }

    public void clickPreviousStep(View view){

        nextButton.setVisibility(View.VISIBLE);
        id = id - 1;
        if (id == 0){
            previousButton.setVisibility(View.INVISIBLE);
        }

        textView.setText(String.valueOf(id));

        String bakingJson = JsonUtils.getJSONString(this);
        recipes = JsonUtils.extractFeaturesFromJson(bakingJson);
        String instruction = recipes.get(recipeId).getSteps().get(id).getDescription();

        mInstructionFragment = new InstructionFragment();
        Bundle instructionBundle = new Bundle();
        instructionBundle.putString("Instruction", instruction);
        mInstructionFragment.setArguments(instructionBundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.instruction_container, mInstructionFragment)
                .commit();

        String videoURL = recipes.get(recipeId).getSteps().get(id).getVideoURL();
        mMediaPlayerFragment = new MediaPlayerFragment();
        Bundle videoBundle = new Bundle();
        videoBundle.putString("Video URL",videoURL);
        mMediaPlayerFragment.setArguments(videoBundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.media_container, mMediaPlayerFragment)
                .commit();

    }

    public void clickNextStep(View view){
        previousButton.setVisibility(View.VISIBLE);

        id = id + 1;
        if (id == recipes.get(recipeId).getSteps().size() - 1){
            nextButton.setVisibility(View.INVISIBLE);
        }
        textView.setText(String.valueOf(id));
        String bakingJson = JsonUtils.getJSONString(this);
        List<Recipe> recipes = JsonUtils.extractFeaturesFromJson(bakingJson);
        String instruction = recipes.get(recipeId).getSteps().get(id).getDescription();
        mInstructionFragment = new InstructionFragment();
        Bundle instructionBundle = new Bundle();
        instructionBundle.putString("Instruction", instruction);
        mInstructionFragment.setArguments(instructionBundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.instruction_container,mInstructionFragment)
                .commit();

        String videoURL = recipes.get(recipeId).getSteps().get(id).getVideoURL();
        mMediaPlayerFragment = new MediaPlayerFragment();
        Bundle videoBundle = new Bundle();
        videoBundle.putString("Video URL",videoURL);
        mMediaPlayerFragment.setArguments(videoBundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.media_container, mMediaPlayerFragment)
                .commit();

    }
}
