package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import com.example.ti2.model.Obstacle;
import com.example.ti2.model.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

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
    private Image floorImage; // Imagen del piso
    private Image backgroundImage; // Imagen de fondo
    private Image treeReplacementImage; // Imagen para reemplazar los árboles
    private Image rockReplacementImage; // Imagen para reemplazar las rocas
    private Runnable onChangeScreenTo1; // Agregar un Runnable para cambiar a Screen1
    private Runnable onChangeScreenTo3; // Agregar un Runnable para cambiar a Screen3

    private double referencePoint1X = 50;  // Coordenada X del primer punto de referencia (para volver a Screen1)
    private double referencePoint1Y = 50;  // Coordenada Y del primer punto de referencia (para volver a Screen1)
    private double referencePoint3X = 550; // Coordenada X del segundo punto de referencia (para avanzar a Screen3)
    private double referencePoint3Y = 130; // Coordenada Y del segundo punto de referencia (para avanzar a Screen3)

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
        this.backgroundImage = new Image("file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/Grass_07-512x512.png"); // Cargar la nueva imagen de fondo
        this.floorImage = new Image("file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/plasma/floor.png"); // Cargar la nueva imagen del piso
        this.treeReplacementImage = new Image("file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/plasma/0.png"); // Cargar la nueva imagen para los árboles
        this.rockReplacementImage = new Image("file:/C:/Users/sgall/Desktop/Mongo/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/PAINT/plasma/1.png"); // Cargar la nueva imagen para las rocas
    }

    public void setOnChangeScreenTo1(Runnable onChangeScreenTo1) {
        this.onChangeScreenTo1 = onChangeScreenTo1;
    }

    public void setOnChangeScreenTo3(Runnable onChangeScreenTo3) {
        this.onChangeScreenTo3 = onChangeScreenTo3;
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
        drawFloor();
        drawBackground();
        villager.paint();
        showPlayerPosition();
        printPlayerPosition();
        drawObstacles();
        drawTools();
        drawReferencePoints(); // Dibujar los puntos de referencia
        checkPositionForScreenChange(); // Verificar cambio de pantalla
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawBackground() {
        graphicsContext.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight()); // Dibujar la nueva imagen de fondo
    }

    private void drawFloor() {
        graphicsContext.drawImage(floorImage, 0, 0, canvas.getWidth(), canvas.getHeight()); // Dibujar la nueva imagen del piso
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
                graphicsContext.drawImage(rockReplacementImage, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            } else if (obstacle.getType().equals("tree")) {
                graphicsContext.drawImage(treeReplacementImage, obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            }
        }
    }

    private void drawTools() {
        for (Tool tool : tools) {
            graphicsContext.drawImage(toolImage, tool.getX(), tool.getY(), 20, 20); // Ajustar tamaño de las herramientas
        }
    }

    private void drawReferencePoints() {
        graphicsContext.setFill(Color.RED); // Color del primer punto de referencia (para volver a Screen1)
        graphicsContext.fillOval(referencePoint1X, referencePoint1Y, 10, 10); // Dibujar un pequeño círculo

        graphicsContext.setFill(Color.BLUE); // Color del segundo punto de referencia (para avanzar a Screen3)
        graphicsContext.fillOval(referencePoint3X, referencePoint3Y, 10, 10); // Dibujar un pequeño círculo azul
    }

    private void checkPositionForScreenChange() {
        double villagerX = villager.getPosition().getX();
        double villagerY = villager.getPosition().getY();

        // Verificar si el Villager está en el primer punto de referencia (volver a Screen1)
        if (Math.abs(villagerX - referencePoint1X) < 10 && Math.abs(villagerY - referencePoint1Y) < 10) {
            if (onChangeScreenTo1 != null) {
                onChangeScreenTo1.run(); // Notificamos a WindowManager que debe cambiar a Screen1
            }
        }

        // Verificar si el Villager está en el segundo punto de referencia (avanzar a Screen3)
        if (Math.abs(villagerX - referencePoint3X) < 10 && Math.abs(villagerY - referencePoint3Y) < 10) {
            if (onChangeScreenTo3 != null) {
                onChangeScreenTo3.run(); // Notificamos a WindowManager que debe cambiar a Screen3
            }
        }
    }

    public void clear() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void onKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
