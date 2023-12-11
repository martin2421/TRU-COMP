package com.example.bulkszn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(view -> {

            final String USERNAME = "martin";
            final String PASSWORD = "password";

            String str_username = username.getText().toString().trim();
            String str_password = password.getText().toString().trim();

            // check username for errors
            if (str_username.equals("") || str_username.contains(" ")) {
                username.setError("Username is required!");
            }

            // check password for errors
            if (str_password.equals("") || str_password.contains(" ")) {
                password.setError("Password is required!");
            }

            // username & password match
            if (str_username.equals(USERNAME) && str_password.equals(PASSWORD)) {
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);

                username.requestFocus();
                username.setText("");
                password.setText("");

            } else {
                Toast.makeText(loginActivity.this,
                        "Invalid username or password!",
                        Toast.LENGTH_LONG).show();
            }

        });

    }
}