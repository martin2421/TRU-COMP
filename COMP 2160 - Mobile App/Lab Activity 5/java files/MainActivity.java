package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contact_modal> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recView);

        arrayList.add(new Contact_modal(R.drawable.img, "Andy B.", "+1 (987) 681-8909", "andy@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_1, "Maria I.", "+1 (987) 986-1089", "maria@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_2, "Ines A.", "+1 (616) 123-2421", "ines@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_3, "Fernando F.", "+1 (717) 456-3267", "fernando@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_4, "Hamza H.", "+1 (818) 777-6612", "hamza@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_5, "Francisco K.", "+1 (414) 778-9090", "francisco@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_6, "Ivi H.", "+1 (817) 121-8884", "ivi@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_7, "Riley H.", "+1 (313) 221-4567", "riley@email.com"));
        arrayList.add(new Contact_modal(R.drawable.img_8, "Martin", "+1 (211) 320-6521", "martin@email.com"));

        // Set adapter on recycler view
        Contact_Adapter adapter = new Contact_Adapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        // Vertical linear layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}