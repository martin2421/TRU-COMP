package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    ImageView image;
    TextView name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        image = findViewById(R.id.imageView);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        email = findViewById(R.id.tv_email);

        image.setImageResource(getIntent().getIntExtra("image", 0));
        name.setText(getIntent().getStringExtra("name"));
        phone.setText(getIntent().getStringExtra("number"));
        email.setText(getIntent().getStringExtra("email"));
    }
}