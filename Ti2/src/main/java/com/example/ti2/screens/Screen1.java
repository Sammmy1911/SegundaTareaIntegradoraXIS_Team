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
    private Runnable onChangeScreen;

    public Screen1(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.backgroundImage = new Image("file:/C:/Users/sgall/Desktop/vambi/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/inside.png");

        this.obstacles = new ArrayList<>();
        this.obstacles.add(new Obstacle(20, 20, 10, 800, "wall"));
        this.obstacles.add(new Obstacle(30, 20, 500, 100, "wall"));
        this.obstacles.add(new Obstacle(330, 120, 500, 50, "wall"));
        this.obstacles.add(new Obstacle(550, 1700, 10, 800, "wall"));

        villager.setObstacles(obstacles);
    }

    public void setOnChangeScreen(Runnable onChangeScreen) {
        this.onChangeScreen = onChangeScreen;
    }

    public void paint() {
        clearCanvas();
        drawBackground();
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
        checkPositionForScreenChange();
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawBackground() {
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
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

    private void checkPositionForScreenChange() {
        if (villager.getPosition().getX() == 120 && villager.getPosition().getY() == 350) { // Cambio de coordenadas
            if (onChangeScreen != null) {
                onChangeScreen.run(); // Notificamos a WindowManager que debe cambiar de pantalla
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
