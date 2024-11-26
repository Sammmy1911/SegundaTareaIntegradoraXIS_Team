package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Screen2 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;
    private List<Obstacle> obstacles;

    public Screen2(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);

        // Define some obstacles
        this.obstacles = new ArrayList<>();
        this.obstacles.add(new Obstacle(50, 50, 20, 500));
        this.obstacles.add(new Obstacle(100, 100, 300, 50));
        this.obstacles.add(new Obstacle(200, 200, 400, 20));
        this.obstacles.add(new Obstacle(300, 300, 20, 600));

        villager.setObstacles(obstacles);
    }

    public void paint() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
    }

    private void showPlayerPosition() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Posición del jugador: (" + villager.getPosition().getX() + ", " + villager.getPosition().getY() + ")", 10, canvas.getHeight() - 10);
    }

    private void printPlayerPosition() {
        System.out.println("Posición del jugador: (" + villager.getPosition().getX() + ", " + villager.getPosition().getY() + ")");
    }

    private void drawObstacles() {
        graphicsContext.setFill(Color.RED);
        for (Obstacle obstacle : obstacles) {
            graphicsContext.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
    }

    public void OnKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void OnKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
