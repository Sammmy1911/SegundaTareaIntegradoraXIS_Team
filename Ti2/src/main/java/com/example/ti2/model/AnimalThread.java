package com.example.ti2.model;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

public class AnimalThread extends Thread {
    private Animal animal;
    private GraphicsContext gc;
    private boolean running;

    public AnimalThread(Animal animal, GraphicsContext gc) {
        this.animal = animal;
        this.gc = gc;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            Platform.runLater(() -> {
                animal.paint(gc);
            });

            try {
                Thread.sleep(500); // Velocidad de animación más lenta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {
        this.running = false;
    }
}
