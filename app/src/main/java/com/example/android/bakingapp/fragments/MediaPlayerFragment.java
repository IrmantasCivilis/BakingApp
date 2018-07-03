package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

public class MediaPlayerFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    public MediaPlayerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_media_player, container, false);

        Bundle videoBundle = getArguments();
        if (videoBundle != null) {
            String videoUrl = videoBundle.getString("Video URL");
            TextView textView = rootView.findViewById(R.id.empty_text_view);
            textView.setText(videoUrl);
        }

        return rootView;
    }

    //@Override
    //public void onAttach(Context context) {
     //   super.onAttach(context);
     //   if (context instanceof OnFragmentInteractionListener) {
     //       mListener = (OnFragmentInteractionListener) context;
     //   } else {
     //       throw new RuntimeException(context.toString()
     //               + " must implement OnFragmentInteractionListener");
      //  }
    //}

    //@Override
    //public void onDetach() {
    //    super.onDetach();
    //    mListener = null;
    //}

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    //public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
     //   void onFragmentInteraction(Uri uri);
    //}
}
