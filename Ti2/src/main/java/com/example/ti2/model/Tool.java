package com.example.ti2.model;

public class Tool {
    private double x, y;
    private String type;

    public Tool(double x, double y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public String getType() { return type; }
}
