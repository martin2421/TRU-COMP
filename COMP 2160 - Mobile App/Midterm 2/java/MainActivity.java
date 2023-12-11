package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie_modal> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayBackgroundSound();

        RecyclerView recyclerView = findViewById(R.id.recView);

        arrayList.add(new Movie_modal(R.drawable.img1, "War Room", "November 22"));
        arrayList.add(new Movie_modal(R.drawable.img2, "Fireproof", "November 23"));
        arrayList.add(new Movie_modal(R.drawable.img3, "Facing The Giants", "November 24"));
        arrayList.add(new Movie_modal(R.drawable.img5, "FlyWheel Bible Study", "November 25"));
        arrayList.add(new Movie_modal(R.drawable.img6, "Overcomer Battante", "November 26"));
        arrayList.add(new Movie_modal(R.drawable.img7, "Indiana Jones", "November 27"));
        arrayList.add(new Movie_modal(R.drawable.img6, "Star Wars", "November 28"));
        arrayList.add(new Movie_modal(R.drawable.img6, "Avengers Endgame", "November 29"));


        // Set adapter on recycler view
        Movie_Adapter adapter = new Movie_Adapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        // Vertical linear layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    public void PlayBackgroundSound() {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        startService(intent);
    }


}