package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipesList;
    final private RecipeItemClickListener mItemClickListener;

    public RecipesAdapter(Context context, List<Recipe> recipesList, RecipeItemClickListener itemClickListener) {
        mContext = context;
        mRecipesList = recipesList;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder holder, int position) {

        Recipe recipe = mRecipesList.get(position);
        holder.recipeNameTextView.setText(recipe.getName());

    }

    @Override
    public int getItemCount() {
        return mRecipesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeNameTextView;

        ViewHolder(View itemView) {
            super(itemView);

            recipeNameTextView = itemView.findViewById(R.id.recipe_name_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Recipe clickedRecipe = mRecipesList.get(clickedPosition);
            mItemClickListener.onRecipeItemClick(clickedRecipe);
        }
    }

    public interface RecipeItemClickListener {
        void onRecipeItemClick(Recipe clickedRecipe);
    }

    public void setRecipesData(List<Recipe> recipes) {
        mRecipesList = recipes;
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        mRecipesList.clear();
        notifyDataSetChanged();
    }

}
