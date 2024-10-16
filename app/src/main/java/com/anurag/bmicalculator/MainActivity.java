package com.anurag.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);  // Initialize the database helper

        EditText heightInput = findViewById(R.id.heightInput);
        EditText weightInput = findViewById(R.id.weightInput);
        EditText nameInput = findViewById(R.id.nameInput);
        Button calculateBtn = findViewById(R.id.calculateBtn);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = heightInput.getText().toString();
                String weightStr = weightInput.getText().toString();
                String nameStr = nameInput.getText().toString();

                if (!heightStr.isEmpty() && !weightStr.isEmpty() && !nameStr.isEmpty()) {
                    double height = Double.parseDouble(heightStr) / 100; // convert cm to meters
                    double weight = Double.parseDouble(weightStr);
                    double bmi = weight / (height * height);

                    dbHelper.insertBMIRecord(nameStr, height, weight, bmi);

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("bmi", bmi);
                    startActivity(intent);
                }
            }
        });
    }
}