package com.example.ti2.model;

public class Obstacle {
    private double x, y, width, height;
    private String type; // Añadimos el tipo de obstáculo

    public Obstacle(double x, double y, double width, double height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    // Getters y setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public String getType() { return type; }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        return x < otherX + otherWidth && x + width > otherX &&
                y < otherY + otherHeight && y + height > otherY;
    }
}
