package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mSteps;
    final private StepItemClickListener mItemClickListener;

    public StepsAdapter(Context context, ArrayList<String> step, StepItemClickListener itemClickListener) {
        mContext = context;
        mSteps = step;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.recipe_step_item, parent, false);
        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {

        String stepDescription = mSteps.get(position);
        holder.recipeStepTextView.setText(stepDescription);

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public interface StepItemClickListener {
        void onStepItemClick(String clickedStep);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeStepTextView;

        ViewHolder(View itemView) {
            super(itemView);

            recipeStepTextView = itemView.findViewById(R.id.step_description_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String clickedStep = mSteps.get(clickedPosition);
            mItemClickListener.onStepItemClick(clickedStep);
        }
    }
}
