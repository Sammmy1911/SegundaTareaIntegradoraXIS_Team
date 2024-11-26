package com.example.ti2.screens;

import com.example.ti2.model.Animal;
import com.example.ti2.model.AnimalThread;
import com.example.ti2.model.Position;
import com.example.ti2.model.Villager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen1 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;
    private List<Animal> animals;
    private List<AnimalThread> animalThreads;
    private Random rand;
    private List<Rectangle> bushes;

    public Screen1(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
        this.rand = new Random();
        this.bushes = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.animalThreads = new ArrayList<>();

        initializeForest();
        initializeAnimals();
    }

    private void initializeForest() {
        // Inicializar arbustos en posiciones fijas
        for (int i = 0; i < 10; i++) {
            double x = rand.nextDouble() * canvas.getWidth();
            double y = rand.nextDouble() * canvas.getHeight();
            bushes.add(new Rectangle(x, y, 30, 20)); // Arbusto
        }
    }

    private void initializeAnimals() {
        // Inicializar animales en posiciones aleatorias con velocidades fijas y muy bajas
        for (int i = 0; i < 3; i++) {
            double sectorMinX = rand.nextDouble() * (canvas.getWidth() / 2);
            double sectorMaxX = sectorMinX + (canvas.getWidth() / 4);
            double sectorMinY = rand.nextDouble() * (canvas.getHeight() / 2);
            double sectorMaxY = sectorMinY + (canvas.getHeight() / 4);

            Position startPosition = new Position(
                    sectorMinX + rand.nextDouble() * (sectorMaxX - sectorMinX),
                    sectorMinY + rand.nextDouble() * (sectorMaxY - sectorMinY)
            );

            // Crear el animal sin movimiento
            Animal animal = new Animal(startPosition);
            animals.add(animal);

            // Crear el hilo para animar el animal, si es necesario
            AnimalThread animalThread = new AnimalThread(animal, graphicsContext);
            animalThreads.add(animalThread);
            animalThread.start(); // Iniciar el hilo del animal
        }
    }

    public void paint() {
        // Limpiar el canvas antes de redibujar
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar el fondo del bosque
        graphicsContext.setFill(Color.FORESTGREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar arbustos
        graphicsContext.setFill(Color.DARKGREEN);
        for (Rectangle bush : bushes) {
            graphicsContext.fillOval(bush.getX(), bush.getY(), bush.getWidth(), bush.getHeight()); // Arbusto
        }

        // Dibujar el personaje villager
        villager.paint();

        // Dibujar los animales
        for (Animal animal : animals) {
            animal.paint(graphicsContext);
        }
    }

    public void OnKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void OnKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
