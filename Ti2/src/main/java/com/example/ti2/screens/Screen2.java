package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen2 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;
    private List<Obstacle> obstacles;
    private Random random;

    public Screen2(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.random = new Random();
        this.obstacles = new ArrayList<>();
        generateRandomObstacles();
        villager.setObstacles(obstacles);
    }

    private void generateRandomObstacles() {
        int numObstacles = 5; // Número de obstáculos que deseas generar
        String[] types = {"rock", "tree"}; // Tipos de obstáculos

        for (int i = 0; i < numObstacles; i++) {
            double x = random.nextDouble() * canvas.getWidth();
            double y = random.nextDouble() * canvas.getHeight();
            double width = 50; // Ajusta el tamaño según sea necesario
            double height = 50; // Ajusta el tamaño según sea necesario
            String type = types[random.nextInt(types.length)]; // Selecciona un tipo aleatorio
            obstacles.add(new Obstacle(x, y, width, height, type));
        }
    }

    public void paint() {
        clearCanvas();
        drawBackground();
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawBackground() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void showPlayerPosition() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Posición del jugador: (" + villager.getPosition().getX() + ", " + villager.getPosition().getY() + ")", 10, canvas.getHeight() - 10);
    }

    private void printPlayerPosition() {
        System.out.println("Posición del jugador: (" + villager.getPosition().getX() + ", " + villager.getPosition().getY() + ")");
    }

    private void drawObstacles() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getType().equals("rock")) {
                graphicsContext.setFill(Color.GRAY);
            } else if (obstacle.getType().equals("tree")) {
                graphicsContext.setFill(Color.GREEN);
            }
            graphicsContext.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
