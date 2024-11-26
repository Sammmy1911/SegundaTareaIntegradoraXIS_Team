package com.example.ti2.screens;

import com.example.ti2.model.*;
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
    private AchievementPopup achievementPopup; // Popup de logros
    private Runnable onChangeScreenTo1; // Agregar un Runnable para cambiar a Screen1

    // Coordenadas del punto de referencia para volver a Screen1
    private double referencePoint1X = 20;
    private double referencePoint1Y = 360;

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

        // Inicializar el popup de logros
        achievementPopup = new AchievementPopup(villager.getAchievementTree());
    }

    public void setOnChangeScreenTo1(Runnable onChangeScreenTo1) {
        this.onChangeScreenTo1 = onChangeScreenTo1;
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
        String matureCropPathCorn = "file:/path/to/mature_corn_image.png"; // Cambia esto según la ruta de la imagen de maíz maduro

        // Añadir una sola columna de cultivos a lo largo de la pared
        for (int i = 0; i < 10; i++) {
            crops.add(new Crop(startX, startY + i * gap, "wheat", soilBeforePlantingPath, soilAfterPlantingPath, soilAfterPlantingPath, matureCropPathWheat));
            crops.add(new Crop(startX + gap, startY + i * gap, "corn", soilBeforePlantingPath, soilAfterPlantingPath, soilAfterPlantingPath, matureCropPathCorn));
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
        drawResources();
        drawReferencePoint(); // Dibujar el punto de referencia para volver a Screen1
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

    private void drawResources() {
        graphicsContext.setFill(Color.BLACK);
        int yPosition = 40; // Coordenada Y inicial para los recursos

        // Mostrar recursos y sus cantidades
        graphicsContext.fillText("Trigo: " + villager.getWheatCount(), 10, yPosition);
        yPosition += 20;
        graphicsContext.fillText("Maíz: " + villager.getCornCount(), 10, yPosition);
        yPosition += 20;
        graphicsContext.fillText("Repollo: " + villager.getCabbageCount(), 10, yPosition);
    }

    private void drawReferencePoint() {
        graphicsContext.setFill(Color.GREEN); // Color del punto de referencia
        graphicsContext.fillOval(referencePoint1X, referencePoint1Y, 10, 10); // Dibujar un pequeño círculo
    }

    private void checkPositionForScreenChange() {
        double villagerX = villager.getPosition().getX();
        double villagerY = villager.getPosition().getY();

        // Verificar si el Villager está en el punto de referencia para volver a Screen1
        if (Math.abs(villagerX - referencePoint1X) < 10 && Math.abs(villagerY - referencePoint1Y) < 10) {
            if (onChangeScreenTo1 != null) {
                onChangeScreenTo1.run(); // Notificamos a WindowManager que debe cambiar a Screen1
            }
        }
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);

        // Sembrar un cultivo si el villager está en un recuadro despejado y se presiona la tecla "P" o "O"
        if (event.getCode() == KeyCode.P) {
            double villagerX = villager.getPosition().getX();
            double villagerY = villager.getPosition().getY();
            for (Crop crop : crops) {
                if (Math.abs(villagerX - crop.getX()) < 10 && Math.abs(villagerY - crop.getY()) < 10 && !crop.isPlanted()) {
                    crop.plant(); // Sembrar el cultivo
                    score += 10; // Añadir puntos a la puntuación (puedes ajustar este valor)

                    // Actualizar los recursos
                    if (crop.getType().equals("wheat")) {
                        villager.addWheat(1);
                    } else if (crop.getType().equals("corn")) {
                        villager.addCorn(1);
                    }

                    // Marcar logro como completado
                    Achievement achievement = villager.getAchievementTree().findAchievement("Planta el primer cultivo");
                    if (achievement != null && !achievement.isAchieved()) {
                        achievement.setAchieved(true);
                    }
                }
            }
        } else if (event.getCode() == KeyCode.O) {
            double villagerX = villager.getPosition().getX();
            double villagerY = villager.getPosition().getY();
            // Sembrar repollo si el villager está en un recuadro despejado y se presiona la tecla "O"
            String soilBeforePlantingPath = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/soil_01.png";
            String soilAfterPlantingPath = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/soil_00.png";
            String matureCropPathCabbage = "file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/SUNNYSIDE_WORLD_CROPS_V0.01/ASSETS/cabbage_03.png";

            Crop newCrop = new Crop(villagerX, villagerY, "cabbage", soilBeforePlantingPath, soilAfterPlantingPath, matureCropPathCabbage, matureCropPathCabbage);
            newCrop.plant(); // Sembrar el cultivo inmediatamente
            crops.add(newCrop);
            score += 10; // Añadir puntos a la puntuación (puedes ajustar este valor)
            villager.addCabbage(1); // Actualizar el recurso de repollo

            // Marcar logro como completado
            Achievement achievement = villager.getAchievementTree().findAchievement("Planta el primer cultivo");
            if (achievement != null && !achievement.isAchieved()) {
                achievement.setAchieved(true);
            }
        } else if (event.getCode() == KeyCode.L) {
            achievementPopup.showAchievements(); // Mostrar logros al presionar la tecla "L"
        }
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }

    public void onKeyTyped(KeyEvent event) {
        checkPositionForScreenChange(); // Verificar el cambio de pantalla en cada evento de tecla
    }
}
