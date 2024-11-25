package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Screen1 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Villager villager;

    public Screen1(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.villager = new Villager(this.canvas);
    }

    public void paint() {
        graphicsContext.setFill(Color.ALICEBLUE);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        villager.paint();
    }

    public void OnKeyPressed(KeyEvent event) {
        this.villager.OnKeyPressed(event);
    }

    public void OnKeyReleased(KeyEvent event) {
        this.villager.onKeyReleased(event);
    }
}
