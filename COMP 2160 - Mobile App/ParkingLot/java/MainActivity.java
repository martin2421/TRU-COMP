package com.example.parkinglot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ParkingLot_Model> arrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);

        arrayList.add(new ParkingLot_Model("Parking Lot A"));
        arrayList.add(new ParkingLot_Model("Parking Lot B"));
        arrayList.add(new ParkingLot_Model("Parking Lot C"));
        arrayList.add(new ParkingLot_Model("Parking Lot H"));
        arrayList.add(new ParkingLot_Model("Parking Lot N"));
        arrayList.add(new ParkingLot_Model("Parking Lot ET"));

        ParkingLot_Adapter adapter = new ParkingLot_Adapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}