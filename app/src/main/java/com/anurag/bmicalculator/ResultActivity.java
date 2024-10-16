package com.anurag.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        TextView bmiText = findViewById(R.id.bmiText);
        ProgressBar bmiIndicator = findViewById(R.id.bmiIndicator);
        Button suggestionsBtn = findViewById(R.id.suggestionsBtn);

        double bmi = getIntent().getDoubleExtra("bmi", 0);

        bmiText.setText(String.format("Your BMI: %.1f", bmi));

        // Set ProgressBar max value (for example, max 40)
        bmiIndicator.setMax(40);

        // Set progress based on BMI
        int progress = ((int)bmi); // Example conversion for progress bar
        bmiIndicator.setProgress(progress);

        suggestionsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, SuggestionsActivity.class);
            intent.putExtra("bmi", bmi);
            startActivity(intent);
        });
    }
}