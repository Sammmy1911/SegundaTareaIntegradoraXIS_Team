package com.example.ti2.model;

public class Obstacle {
    private double x;
    private double y;
    private double width;
    private double height;

    public Obstacle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean intersects(double x, double y, double width, double height) {
        return x < this.x + this.width &&
                x + width > this.x &&
                y < this.y + this.height &&
                y + height > this.y;
    }
}
