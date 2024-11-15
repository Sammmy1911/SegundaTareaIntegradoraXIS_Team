package com.example.ti2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Villager {
    //Canvas en el que el personaje existe
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    //Arreglos de las animaciones del personaje
    private ArrayList<Image> idle;
    private ArrayList<Image> walk;
    private ArrayList<Image> runs;
    private ArrayList<Image> hammer;
    private ArrayList<Image> axe;
    private ArrayList<Image> dig;
    private ArrayList<Image> roll;
    private ArrayList<Image> mine;
    //posicion
    private Position position;
    //estado de las animaciones
    private int state;

    public Villager(Canvas canvas){
        this.canvas= canvas;
        this.graphicsContext= this.canvas.getGraphicsContext2D();
        this.state= 0;
        this.walk= new ArrayList<>();
        this.idle= new ArrayList<>();

        this.position= new Position(100,100);
        for (int i=0; i<=8; i++){
            Image image = new Image(getClass().getResourceAsStream("AssetsPerson/Villager/IDLE/v-idle-"+i+".png"));
            this.idle.add(image);
        }


    }


}
