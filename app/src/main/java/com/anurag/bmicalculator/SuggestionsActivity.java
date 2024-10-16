package com.anurag.bmicalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuggestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggestions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView suggestionsText = findViewById(R.id.suggestionsText);

        double bmi = getIntent().getDoubleExtra("bmi", 0);
        String category = determineCategory(bmi);
        String recommendations = getDietRecommendations(category);

        suggestionsText.setText(recommendations);
    }

    private String determineCategory(double bmi) {
        if (bmi < 18.5) {
            return "underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "normal";
        } else if (bmi >= 24.9 && bmi < 29.9) {
            return "overweight";
        } else {
            return "obese";
        }
    }

    private String getDietRecommendations(String bmiCategory) {
        switch (bmiCategory) {
            case "underweight":
                return "Gain healthy weight: Increase calorie intake, regular meals...";
            case "normal":
                return "Maintain healthy weight: Balanced diet, portion control...";
            case "overweight":
                return "Lose weight: Caloric deficit, nutrient-dense foods...";
            case "obese":
                return "Lose weight sustainably: Significant caloric deficit...";
            default:
                return "Invalid BMI category";
        }
    }
}