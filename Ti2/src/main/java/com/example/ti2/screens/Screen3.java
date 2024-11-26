package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import com.example.ti2.model.Tool;
import com.example.ti2.model.Crop;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Screen3 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;
    private List<Obstacle> obstacles;
    private List<Tool> tools;
    private List<Crop> crops; // Lista de cultivos
    private int score; // Puntuación del jugador

    public Screen3(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.obstacles = new ArrayList<>();
        this.tools = new ArrayList<>();
        this.crops = new ArrayList<>();
        this.score = 0; // Inicializar la puntuación

        // Definir obstáculos y cultivos iniciales
        this.obstacles.add(new Obstacle(50, 50, 20, 800, "wall")); // Pared roja

        // Añadir cultivos pegados a la pared roja
        addCropsNearWall();

        villager.setObstacles(obstacles);
        villager.setTools(tools);
    }

    private void addCropsNearWall() {
        // Coordenadas iniciales para los cultivos pegados a la pared
        double startX = 70;
        double startY = 50;
        double gap = 30; // Espacio entre cultivos

        // Definir los cultivos y sus imágenes
        String soilBeforePlantingPath = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/soil_01.png";
        String soilAfterPlantingPath = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/soil_00.png";
        String matureCropPathWheat = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/wheat_04.png";

        // Añadir una sola columna de cultivos a lo largo de la pared
        for (int i = 0; i < 10; i++) {
            crops.add(new Crop(startX, startY + i * gap, "wheat", soilBeforePlantingPath, soilAfterPlantingPath, soilAfterPlantingPath, matureCropPathWheat));
        }
    }

    public void paint() {
        clearCanvas();
        drawBackground();
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
        drawTools();
        drawCrops();
        drawScore();
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawBackground() {
        graphicsContext.setFill(Color.BLUE); // Establecer el fondo azul
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
        graphicsContext.setFill(Color.RED);
        for (Obstacle obstacle : obstacles) {
            graphicsContext.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
    }

    private void drawTools() {
        graphicsContext.setFill(Color.BLUE);
        for (Tool tool : tools) {
            graphicsContext.fillRect(tool.getX(), tool.getY(), 10, 10); // Ajustar tamaño de las herramientas
        }
    }

    private void drawCrops() {
        for (Crop crop : crops) {
            crop.paint(graphicsContext);
        }
    }

    private void drawScore() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Puntuación: " + score, 10, 20);
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);

        // Sembrar un cultivo si el villager está en un recuadro despejado y se presiona la tecla "P"
        if (event.getCode() == KeyCode.P) {
            double villagerX = villager.getPosition().getX();
            double villagerY = villager.getPosition().getY();
            for (Crop crop : crops) {
                if (Math.abs(villagerX - crop.getX()) < 10 && Math.abs(villagerY - crop.getY()) < 10 && !crop.isPlanted()) {
                    crop.plant(); // Sembrar el cultivo
                    score += 10; // Añadir puntos a la puntuación (puedes ajustar este valor)
                }
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
