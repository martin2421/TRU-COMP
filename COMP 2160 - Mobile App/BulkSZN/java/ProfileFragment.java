package com.example.bulkszn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class ProfileFragment extends Fragment {

    Spinner spinner;
    private EditText weightInput;
    ImageView bodies_image;
    Button updateInfo;

    Bundle receivingBundle;
    double multiplier, weight, total, goal;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = new Bundle();
        receivingBundle = getArguments();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        spinner = rootView.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getContext(), R.array.type_of_bulk, android.R.layout.simple_spinner_item
        );

        // Deals with input weight
        weightInput = rootView.findViewById(R.id.et_weight);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedType = spinner.getSelectedItem().toString();

                if (!(selectedType.equals("Select a Type"))) {
                    switch (selectedType) {
                        case "Cut":
                            multiplier = 0.65;
                            break;
                        case "Regular":
                            multiplier = 1;
                            break;
                        case "Clean":
                            multiplier = 1.2;
                            break;
                        case "Dirty":
                            multiplier = 1.5;
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Makes image clickable
        bodies_image = rootView.findViewById(R.id.bodies_image);
        bodies_image.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TypesOfBody.class);
            startActivity(intent);
        });

        // Button to send info to other fragments
        updateInfo = rootView.findViewById(R.id.btn_update);
        updateInfo.setOnClickListener(view -> {
            weight = Integer.parseInt(weightInput.getText().toString());

            total = sharedPreferences.getFloat("total", 69);

            Log.d("TOTAL (Profile)", String.valueOf(total));
            goal = Math.round(weight * multiplier);
            bundle.putDouble("goalNumber", goal);
            Log.d("GOAL (Profile)", String.valueOf(goal));
            bundle.putString("goal", String.format("%.1f", total) + "g / " + goal + "g");


            FragmentManager fragmentManager = getFragmentManager();
            HomeFragment fragment = new HomeFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();


        });

        return rootView;
    }

}