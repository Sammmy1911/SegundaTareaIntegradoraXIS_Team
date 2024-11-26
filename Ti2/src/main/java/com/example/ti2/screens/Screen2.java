package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import com.example.ti2.model.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
    private List<Tool> tools;
    private Random random;
    private Image toolImage; // Imagen de la herramienta

    public Screen2(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.random = new Random();
        this.obstacles = new ArrayList<>();
        this.tools = new ArrayList<>();
        generateRandomObstacles();
        generateInitialTools();
        villager.setObstacles(obstacles);
        villager.setTools(tools);
        this.toolImage = new Image("file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/descarga.jpg"); // Cargar la imagen de la herramienta
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

    private void generateInitialTools() {
        tools.add(new Tool(150, 150, "pickaxe"));
        tools.add(new Tool(300, 300, "axe"));
    }

    public void paint() {
        clearCanvas();
        drawBackground();
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
        drawTools();
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

    private void drawTools() {
        for (Tool tool : tools) {
            graphicsContext.drawImage(toolImage, tool.getX(), tool.getY(), 20, 20); // Ajustar tamaño de las herramientas
        }
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
