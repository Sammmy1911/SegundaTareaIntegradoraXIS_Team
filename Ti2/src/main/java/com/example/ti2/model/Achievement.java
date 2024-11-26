package com.example.ti2.model;

public class Achievement {
    private String name;
    private String description;
    private boolean achieved;
    private Achievement left;
    private Achievement right;

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        this.achieved = false;
        this.left = null;
        this.right = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public Achievement getLeft() {
        return left;
    }

    public void setLeft(Achievement left) {
        this.left = left;
    }

    public Achievement getRight() {
        return right;
    }

    public void setRight(Achievement right) {
        this.right = right;
    }
}
