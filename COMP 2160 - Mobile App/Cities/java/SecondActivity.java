package com.example.cities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView cityImage;
    TextView cityWeather, cityPopulation, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        cityImage = findViewById(R.id.img_city);
        cityWeather = findViewById(R.id.tv_weather);
        cityPopulation = findViewById(R.id.tv_population);
        title = findViewById(R.id.tv_title);

        String selectedCity = getIntent().getStringExtra("selectedCity");

        if (selectedCity != null) {

            switch (selectedCity) {

                case "Calgary":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.calgary);
                    cityWeather.setText(R.string.calgary_weather);
                    cityPopulation.setText(R.string.calgary_population);
                    break;
                case "Edmonton":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.edmonton);
                    cityWeather.setText(R.string.edmonton_weather);
                    cityPopulation.setText(R.string.edmonton_population);
                    break;
                case "Kamloops":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.kamloops);
                    cityWeather.setText(R.string.kamloops_weather);
                    cityPopulation.setText(R.string.kamloops_population);
                    break;
                case "Montreal":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.montreal);
                    cityWeather.setText(R.string.montreal_weather);
                    cityPopulation.setText(R.string.montreal_population);
                    break;
                case "Ottawa":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.ottawa);
                    cityWeather.setText(R.string.ottawa_weather);
                    cityPopulation.setText(R.string.ottawa_population);
                    break;
                case "Toronto":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.toronto);
                    cityWeather.setText(R.string.toronto_weather);
                    cityPopulation.setText(R.string.toronto_population);
                    break;
                case "Vancouver":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.vancouver);
                    cityWeather.setText(R.string.vancouver_weather);
                    cityPopulation.setText(R.string.vancouver_population);
                    break;
                case "Victoria":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.victoria);
                    cityWeather.setText(R.string.victoria_weather);
                    cityPopulation.setText(R.string.victoria_population);
                    break;
                case "Waterloo":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.waterloo);
                    cityWeather.setText(R.string.waterloo_weather);
                    cityPopulation.setText(R.string.waterloo_population);
                    break;
                case "Winnipeg":
                    title.setText(selectedCity);
                    cityImage.setImageResource(R.drawable.winnipeg);
                    cityWeather.setText(R.string.winnipeg_weather);
                    cityPopulation.setText(R.string.winnipeg_population);
                    break;

            }

        }

    }
}