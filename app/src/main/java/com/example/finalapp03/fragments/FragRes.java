package com.example.finalapp03.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.finalapp03.R;

import java.util.Locale;

public class FragRes extends Fragment {

    private TextView resolutionTextView;

    public FragRes(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resolution, container, false);
        resolutionTextView = view.findViewById(R.id.resolution_text_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int dpi = (int) getResources().getDisplayMetrics().densityDpi;
        String resolution = String.format(Locale.getDefault(), "%dx%d (%d dpi)", width, height, dpi);
        resolutionTextView.setText(resolution);
    }
}