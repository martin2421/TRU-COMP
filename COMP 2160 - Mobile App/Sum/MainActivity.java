package com.example.sum;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2;
    Button add, subtract, divide, multiply;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.et_num1);
        num2 = findViewById(R.id.et_num2);
        add = findViewById(R.id.btn_add);
        subtract = findViewById(R.id.btn_subtract);
        divide = findViewById(R.id.btn_divide);
        multiply = findViewById(R.id.btn_multiply);
        result = findViewById(R.id.tv_result);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = num1.getText().toString();
                String s2 = num2.getText().toString();
                double a = Double.parseDouble(s1);
                double b = Double.parseDouble(s2);

                result.setText("Result: " + (a+b));
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = num1.getText().toString();
                String s2 = num2.getText().toString();
                double a = Double.parseDouble(s1);
                double b = Double.parseDouble(s2);

                result.setText("Result: " + (a-b));
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = num1.getText().toString();
                String s2 = num2.getText().toString();
                double a = Double.parseDouble(s1);
                double b = Double.parseDouble(s2);

                result.setText("Result: " + (a/b));
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = num1.getText().toString();
                String s2 = num2.getText().toString();
                double a = Double.parseDouble(s1);
                double b = Double.parseDouble(s2);

                result.setText("Result: " + (a*b));
            }
        });
    }
}