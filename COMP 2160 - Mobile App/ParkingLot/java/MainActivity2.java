package com.example.parkinglot;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    final Handler handler = new Handler();
    TextView tv, timer, finalTime;
    EditText plateNumber;
    TimePicker time;
    CountDownTimer countDownTimer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.textView);
        timer = findViewById(R.id.timer_count);
        finalTime = findViewById(R.id.final_time);
        plateNumber = findViewById(R.id.PlateET);
        time = findViewById(R.id.timePicker);

        tv.setText(getIntent().getStringExtra("name"));
        time.setIs24HourView(true);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Sets the time for the TimePicker
        time.setHour(hour);
        time.setMinute(minute);
        time.setOnTimeChangedListener((view, hourOfDay, minute1) -> {
            Calendar now = Calendar.getInstance();

            // Create a Calendar object for the selected time
            Calendar selectedTime = Calendar.getInstance();
            selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedTime.set(Calendar.MINUTE, minute1);

            // Calculate the difference between the two times in milliseconds
            long differenceInMillis = selectedTime.getTimeInMillis() - now.getTimeInMillis();

            handler.postDelayed(() -> {

                // Convert the total minutes to milliseconds and start the countdown timer
                startCountdownTimer(differenceInMillis);
                Toast.makeText(MainActivity2.this, "Parking lot booked!", Toast.LENGTH_SHORT).show();
            }, 4000);
        });

    }
    private void startCountdownTimer(long millisInFuture) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Create a new countdown timer that counts down from the specified time
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int hours = (int) (millisUntilFinished / (60 * 60 * 1000));
                int minutes = (int) ((millisUntilFinished / (60 * 1000)) % 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);

                // Create a Calendar object for the time when the parking will expire
                Calendar expireTime = Calendar.getInstance();
                expireTime.add(Calendar.HOUR_OF_DAY, hours);
                expireTime.add(Calendar.MINUTE, minutes);
                expireTime.add(Calendar.SECOND, seconds);

                // Format the remaining time as a string and display it in the countdown timer TextView
                String timeRemaining = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timer.setText(timeRemaining);

                // Format the expire time as a string and display it in the final time TextView
                String expireTimeString = String.format("%02d:%02d", expireTime.get(Calendar.HOUR_OF_DAY), expireTime.get(Calendar.MINUTE));
                finalTime.setText("Parking will expire at: " + expireTimeString);
                timer.setVisibility(View.VISIBLE);
                finalTime.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                Intent intent =new Intent( MainActivity2.this, ForegroundService.class);
                startForegroundService(new Intent(MainActivity2.this, ForegroundService.class));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                }
            }
        };

        // Start the countdown timer
        countDownTimer.start();
    }
}