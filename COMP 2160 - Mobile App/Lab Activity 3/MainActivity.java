package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText from, to, value;
    Button convert;
    double celsius, fahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign objects to their element's id
        from = findViewById(R.id.et_from);
        to = findViewById(R.id.et_to);
        value = findViewById(R.id.et_value);
        convert = findViewById(R.id.btn_convert);
        result = findViewById(R.id.tv_result);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // both inputs are the same - celsius
                if(from.getText().toString().trim().toLowerCase().equals("celsius") &&
                to.getText().toString().trim().toLowerCase().equals("celsius")) {
                    from.setError("INPUTS ARE EQUAL");
                    to.setError("INPUTS ARE EQUAL");
                }

                // both inputs are the same - fahrenheit
                else if (from.getText().toString().trim().toLowerCase().equals("fahrenheit") &&
                to.getText().toString().trim().toLowerCase().equals("fahrenheit")) {
                    from.setError("INPUTS ARE EQUAL");
                    to.setError("INPUTS ARE EQUAL");
                }

                // from celsius to fahrenheit
                else if (from.getText().toString().trim().toLowerCase().equals("celsius") &&
                to.getText().toString().trim().toLowerCase().equals("fahrenheit")) {

                    celsius = Double.parseDouble(value.getText().toString().trim());

                    fahrenheit = (celsius * 1.8) + 32;
                    String f = String.format("%.1f", fahrenheit);
                    result.setText(f + " °F");
                    from.setText("");
                    to.setText("");
                    value.setText("");

                }

                // from fahrenheit to celsius
                else if (from.getText().toString().trim().toLowerCase().equals("fahrenheit") &&
                to.getText().toString().trim().toLowerCase().equals("celsius")) {

                    fahrenheit = Double.parseDouble(value.getText().toString().trim());

                    celsius = (5 * (fahrenheit - 32)) / 9;
                    String c = String.format("%.1f", celsius);
                    result.setText(c + " °C");
                    from.setText("");
                    to.setText("");
                    value.setText("");

                }

                // wrong inputs
                else {
                    from.setError("WRONG INPUT");
                    to.setError("WRONG INPUT");
                    value.setError("WRONG INPUT");
                }

            }
        });

    }



}