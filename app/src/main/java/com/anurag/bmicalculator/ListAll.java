package com.anurag.bmicalculator;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListAll extends AppCompatActivity {

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_all);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        List<BMIRecord> records = dbHelper.getAllRecords();  // Get all records

        TableLayout tableLayout = findViewById(R.id.recordsTable);

        for (BMIRecord record : records) {
            TableRow row = new TableRow(this);

            TextView nameText = new TextView(this);
            nameText.setText(record.getName());
            nameText.setPadding(8, 8, 8, 8);

            TextView heightText = new TextView(this);
            heightText.setText(String.format("%.2f", record.getHeight()));
            heightText.setPadding(8, 8, 8, 8);

            TextView weightText = new TextView(this);
            weightText.setText(String.format("%.2f", record.getWeight()));
            weightText.setPadding(8, 8, 8, 8);

            TextView bmiText = new TextView(this);
            bmiText.setText(String.format("%.2f", record.getBmi()));
            bmiText.setPadding(8, 8, 8, 8);

            // Add TextViews to TableRow
            row.addView(nameText);
            row.addView(heightText);
            row.addView(weightText);
            row.addView(bmiText);

            // Add TableRow to TableLayout
            tableLayout.addView(row);
        }
    }
}