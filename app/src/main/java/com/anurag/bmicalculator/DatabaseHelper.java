package com.anurag.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Define database name and version
    private static final String DATABASE_NAME = "bmiData.db";
    private static final int DATABASE_VERSION = 1;

    // Define table name and columns
    public static final String TABLE_NAME = "bmi_records";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_BMI = "bmi";

    // SQL query to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_HEIGHT + " REAL, " +
                    COLUMN_WEIGHT + " REAL, " +
                    COLUMN_BMI + " REAL);";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Create table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a new BMI record into the database
    public void insertBMIRecord(String name, double height, double weight, double bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_HEIGHT, height);
        values.put(COLUMN_WEIGHT, weight);
        values.put(COLUMN_BMI, bmi);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to fetch all records from the database
    public List<BMIRecord> getAllRecords() {
        List<BMIRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double height = cursor.getDouble(2);
                double weight = cursor.getDouble(3);
                double bmi = cursor.getDouble(4);

                BMIRecord record = new BMIRecord(id, name, height, weight, bmi);
                records.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return records;
    }
}
