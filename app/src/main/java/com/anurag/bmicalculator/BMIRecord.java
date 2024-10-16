package com.anurag.bmicalculator;

public class BMIRecord {
    private int id;
    private String name;
    private double height;
    private double weight;
    private double bmi;

    public BMIRecord(int id, String name, double height, double weight, double bmi) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBmi() {
        return bmi;
    }
}

