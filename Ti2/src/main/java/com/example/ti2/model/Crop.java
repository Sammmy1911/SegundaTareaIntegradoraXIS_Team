package com.example.ti2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class Crop {
    private double x, y;
    private String type;
    private Image soilBeforePlanting;
    private Image soilAfterPlanting;
    private Image cropImage;
    private Image matureCropImage;
    private boolean isPlanted;
    private boolean isMature;

    public Crop(double x, double y, String type, String soilBeforePlantingPath, String soilAfterPlantingPath, String cropImagePath, String matureCropImagePath) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.soilBeforePlanting = new Image(soilBeforePlantingPath);
        this.soilAfterPlanting = new Image(soilAfterPlantingPath);
        this.cropImage = new Image(cropImagePath);
        this.matureCropImage = new Image(matureCropImagePath);
        this.isPlanted = false;
        this.isMature = false;
    }

    public void plant() {
        this.isPlanted = true;

        // Configurar un temporizador para cambiar la imagen después de 30 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Cambiar a la imagen del cultivo maduro
                Platform.runLater(() -> isMature = true);
            }
        }, 30000); // 30000 milisegundos = 30 segundos
    }

    public void paint(GraphicsContext graphicsContext) {
        if (isPlanted) {
            // Dibujar la imagen del suelo después de plantar y el cultivo
            graphicsContext.drawImage(soilAfterPlanting, x, y, 20, 20);
            if (isMature) {
                graphicsContext.drawImage(matureCropImage, x, y, 20, 20);
            } else {
                graphicsContext.drawImage(cropImage, x, y, 20, 20);
            }
        } else {
            // Dibujar la imagen del suelo antes de plantar
            graphicsContext.drawImage(soilBeforePlanting, x, y, 20, 20);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public boolean isPlanted() {
        return isPlanted;
    }
}
