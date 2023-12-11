package com.example.bulkszn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    ImageView progressImage, proteinImage;
    TextView progressTextView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        progressImage = rootView.findViewById(R.id.progress_image);
        progressTextView = rootView.findViewById(R.id.tv_progress);
        proteinImage = rootView.findViewById(R.id.protein_image);
        proteinImage.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ProteinInfo.class);
            startActivity(intent);
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            String goal = bundle.getString("goal");
            Log.d("GOAL (Home)", String.valueOf(goal));
            progressTextView.setText(String.valueOf(goal));

            double goalDouble = bundle.getDouble("goalNumber");
            double total = sharedPreferences.getFloat("total", 69);
            double percentage = (total / goalDouble) * 100;
            percentage = (int) percentage;


            if (percentage >= 10 && percentage < 20) {
                    progressImage.setImageResource(R.drawable.pc_10);
            } else  if (percentage >= 20 && percentage < 30) {
                progressImage.setImageResource(R.drawable.pc_20);
            } else  if (percentage >= 30 && percentage < 40) {
                progressImage.setImageResource(R.drawable.pc_30);
            } else  if (percentage >= 40 && percentage < 50) {
                progressImage.setImageResource(R.drawable.pc_40);
            } else  if (percentage >= 50 && percentage < 60) {
                progressImage.setImageResource(R.drawable.pc_50);
            } else  if (percentage >= 60 && percentage < 70) {
                progressImage.setImageResource(R.drawable.pc_60);
            } else  if (percentage >= 70 && percentage < 80) {
                progressImage.setImageResource(R.drawable.pc_70);
            } else  if (percentage >= 80 && percentage < 90) {
                progressImage.setImageResource(R.drawable.pc_80);
            } else  if (percentage >= 90 && percentage < 100) {
                progressImage.setImageResource(R.drawable.pc_90);
            } else  if (percentage >= 100) {
                progressImage.setImageResource(R.drawable.pc_100);
            }


        } else {
            progressImage.setImageResource(R.drawable.pc_0);
        }

        // Inflate the layout for this fragment
        return rootView;
    }
}