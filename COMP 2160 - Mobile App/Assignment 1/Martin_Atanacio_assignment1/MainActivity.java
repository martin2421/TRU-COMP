package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText baseView;
    TextView percentageView, tipView, totalView, ratingView;
    SeekBar seekbar;

    double base, tip, total;
    int percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseView = findViewById(R.id.et_base);
        percentageView = findViewById(R.id.tv_percentage);
        tipView = findViewById(R.id.tv_tip);
        totalView = findViewById(R.id.tv_total);
        seekbar = findViewById(R.id.seekBar);
        ratingView = findViewById(R.id.rating);

        seekbar.setProgress(15);
        percentageView.setText("15%");

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                try {
                    base = Double.parseDouble(baseView.getText().toString());
//                    System.out.println("Base " + base);
                } catch (Exception e) {
                    baseView.setError("Please enter a number!");
                }

                percentage = seekbar.getProgress();
//                System.out.println("Progress " + seekbar.getProgress());
//                System.out.println("Percentage " + percentage);
                percentageView.setText(percentage + "%");

                tip = base * (percentage / 100.0);
//                System.out.println("Tip " + tip);
                total = base + tip;
//                System.out.println("Total " + total);

                if (percentage <= 10) {
                    ratingView.setText("Poor");
                    ratingView.setTextColor(getColor(R.color.red));
                    tipView.setText(String.format("$ %.2f", tip));
                    totalView.setText(String.format("$ %.2f", total));
                } else if (percentage > 10 && percentage <= 15) {
                    ratingView.setText("Acceptable");
                    ratingView.setTextColor(getColor(R.color.lightorange));
                    tipView.setText(String.format("$ %.2f", tip));
                    totalView.setText(String.format("$ %.2f", total));
                } else if (percentage > 15 && percentage <= 20) {
                    ratingView.setText("Good");
                    ratingView.setTextColor(getColor(R.color.lightgreen));
                    tipView.setText(String.format("$ %.2f", tip));
                    totalView.setText(String.format("$ %.2f", total));
                } else if (percentage > 20) {
                    ratingView.setText("Amazing");
                    ratingView.setTextColor(getColor(R.color.darkgreen));
                    tipView.setText(String.format("$ %.2f", tip));
                    totalView.setText(String.format("$ %.2f", total));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}