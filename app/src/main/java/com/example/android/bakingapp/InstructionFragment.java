package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InstructionFragment extends Fragment {

    public InstructionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_instruction, container, false);

        Bundle instruction = getArguments();
        if (instruction != null) {
            String instructionString = instruction.getString("Instruction");
            TextView textView = rootView.findViewById(R.id.instruction_text_view);
            textView.setText(instructionString);
        }

        return rootView;
    }
}
