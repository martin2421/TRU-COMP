package com.example.bulkszn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardFragment extends Fragment implements MyListener {

    ArrayList<Food_modal> arrayList = new ArrayList<>();
    RecyclerView recyclerView;

    private TextView textView;

    public DashboardFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = rootView.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);

        textView = rootView.findViewById(R.id.tv_total_quantity);

        // Fill arraylist with items
        arrayList.add(new Food_modal(R.drawable.chickenbreast, "Chicken Breast", "Protein: 8.8g", "0"));
        arrayList.add(new Food_modal(R.drawable.pork, "Pork", "Protein: 5.8g", "0"));
        arrayList.add(new Food_modal(R.drawable.beef, "Beef", "Protein: 5.8g", "0"));
        arrayList.add(new Food_modal(R.drawable.burger, "Burger (1 Patty)", "Protein: 25g", "0"));
        arrayList.add(new Food_modal(R.drawable.turkey, "Turkey Breast", "Protein: 9.6g", "0"));
        arrayList.add(new Food_modal(R.drawable.ham, "Ham (1 Slice)", "Protein: 7.5g", "0"));
        arrayList.add(new Food_modal(R.drawable.tuna, "Canned Tuna (1 Can)", "Protein: 18g", "0"));
        arrayList.add(new Food_modal(R.drawable.shrimp, "Shrimp", "Protein: 3.9g", "0"));
        arrayList.add(new Food_modal(R.drawable.fish, "Fish", "Protein: 6.5g", "0"));
        arrayList.add(new Food_modal(R.drawable.salmon, "Salmon", "Protein: 5.8g", "0"));
        arrayList.add(new Food_modal(R.drawable.egg, "Egg", "Protein: 6.3g", "0"));
        arrayList.add(new Food_modal(R.drawable.beans, "Beans", "Protein: 1.3g", "0"));
        arrayList.add(new Food_modal(R.drawable.lentils, "Lentils", "Protein: 2.2g", "0"));
        arrayList.add(new Food_modal(R.drawable.yogurt, "Protein Yogurt", "Protein: 20g", "0"));
        arrayList.add(new Food_modal(R.drawable.almonds, "Almonds", "Protein: 6g", "0"));
        arrayList.add(new Food_modal(R.drawable.peanuts, "Peanuts", "Protein: 7.3g", "0"));
        arrayList.add(new Food_modal(R.drawable.beefjerky, "Beef Jerky", "Protein: 15g", "0"));
        arrayList.add(new Food_modal(R.drawable.proteinshake, "Protein Shake", "Protein: 30g", "0"));

        // Set adapter on recycler view
        Food_Adapter adapter = new Food_Adapter(getContext(), arrayList);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        // Vertical Linear Layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onButtonClicked(String text) {
        textView.setText(text);
    }
}