package com.example.ti2.screens;

import com.example.ti2.model.Villager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Screen1 {
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private Villager villager;

    public Screen1(Canvas canvas) {
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.canvas = canvas;
        this.villager = new Villager(this.canvas);
    }

    public void paint(){
        graphicsContext.setFill(Color.ALICEBLUE);
        graphicsContext.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        villager.paint();
    }
}
