package com.example.ti2.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class AchievementPopup {
    private AchievementTree achievementTree;

    public AchievementPopup(AchievementTree achievementTree) {
        this.achievementTree = achievementTree;
    }

    public void showAchievements() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Logros");
        alert.setHeaderText("Tus Logros");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        StringBuilder achievements = new StringBuilder();
        getAchievements(achievementTree.getRoot(), achievements);

        textArea.setText(achievements.toString());

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    private void getAchievements(Achievement achievement, StringBuilder achievements) {
        if (achievement != null) {
            getAchievements(achievement.getLeft(), achievements);
            achievements.append(achievement.getName())
                    .append(": ")
                    .append(achievement.getDescription())
                    .append(" - ")
                    .append(achievement.isAchieved() ? "Logrado" : "No logrado")
                    .append("\n");
            getAchievements(achievement.getRight(), achievements);
        }
    }
}
