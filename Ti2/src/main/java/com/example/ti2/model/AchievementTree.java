package com.example.ti2.model;

public class AchievementTree {
    private Achievement root;

    public AchievementTree() {
        this.root = null;
    }

    public Achievement getRoot() {
        return root;
    }

    public void addAchievement(Achievement achievement) {
        if (root == null) {
            root = achievement;
        } else {
            addAchievementRec(root, achievement);
        }
    }

    private void addAchievementRec(Achievement current, Achievement achievement) {
        if (achievement.getName().compareTo(current.getName()) < 0) {
            if (current.getLeft() == null) {
                current.setLeft(achievement);
            } else {
                addAchievementRec(current.getLeft(), achievement);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(achievement);
            } else {
                addAchievementRec(current.getRight(), achievement);
            }
        }
    }

    public Achievement findAchievement(String name) {
        return findAchievementRec(root, name);
    }

    private Achievement findAchievementRec(Achievement current, String name) {
        if (current == null) {
            return null;
        }
        if (name.equals(current.getName())) {
            return current;
        }
        return name.compareTo(current.getName()) < 0
                ? findAchievementRec(current.getLeft(), name)
                : findAchievementRec(current.getRight(), name);
    }

    public void displayAchievements() {
        displayAchievementsRec(root);
    }

    private void displayAchievementsRec(Achievement current) {
        if (current != null) {
            displayAchievementsRec(current.getLeft());
            System.out.println(current.getName() + ": " + current.getDescription() + " - " + (current.isAchieved() ? "Logrado" : "No logrado"));
            displayAchievementsRec(current.getRight());
        }
    }
}
