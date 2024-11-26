package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Screen1 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;
    private Image backgroundImage;
    private List<Obstacle> obstacles;

    public Screen1(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.backgroundImage = new Image("file:/C:/Users/sgall/Desktop/vambi/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/inside.png");

        // Define some obstacles
        this.obstacles = new ArrayList<>();
        this.obstacles.add(new Obstacle(20, 20, 10, 800));
        this.obstacles.add(new Obstacle(30, 20, 500, 100));
        this.obstacles.add(new Obstacle(330, 120, 500, 50));
        this.obstacles.add(new Obstacle(550, 1700, 10, 800));

        villager.setObstacles(obstacles);
    }

    public void paint() {
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
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
