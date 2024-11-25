package com.example.ti2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;

import java.awt.*;
import java.security.UnrecoverableEntryException;
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
    private int frame;



    private boolean upPressed;
    private boolean down;
    private boolean right;
    private boolean left;

    private boolean sPressed;
    private boolean aPressed;
    //posicion
    private Position position;
    //estado de las animaciones
    private int state;

    public Villager(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.state = 0;
        this.walk = new ArrayList<>();
        this.idle = new ArrayList<>();
        this.runs = new ArrayList<>();
        this.mine = new ArrayList<>();
        this.axe = new ArrayList<>();
        this.dig = new ArrayList<>();
        this.roll = new ArrayList<>();

        this.position = new Position(100, 100);
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/IDLE/v-idle-" + i + ".png"));
            this.idle.add(image);
            frame++;
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/RUN/v-run-" + i + ".png"));
            this.runs.add(image);
            frame++;
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/WALK/v-walk-" + i + ".png"));
            this.walk.add(image);
            frame++;
        }
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/MINE/v-mine-" + i + ".png"));
            this.mine.add(image);
            frame++;
        }
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/AXE/v-axe-" + i + ".png"));
            this.axe.add(image);
            frame++;
        }
        for (int i = 0; i <= 11; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/DIG/v-dig-" + i + ".png"));
            this.dig.add(image);
            frame++;
        }
    }

    public void paint() {
        if (state == 0) {
            graphicsContext.drawImage(idle.get(frame % 8), position.getX(), position.getY());
        } else if (state == 1) {
            graphicsContext.drawImage(walk.get(frame % 6), position.getX(), position.getY());
        } else if (state == 2) {
            graphicsContext.drawImage(runs.get(frame % 6), position.getX(), position.getY());
        } else if (state == 3) {
            graphicsContext.drawImage(mine.get(frame % 8), position.getX(), position.getY());
        } else if (state == 4) {
            graphicsContext.drawImage(dig.get(frame % 11), position.getX(), position.getY());
        } else if (state == 5) {
            graphicsContext.drawImage(axe.get(frame % 8), position.getX(), position.getY());
        }
    }



    public void onMove(){
        if (upPressed){
            position.setY(position.getY() - 10);
        }
        if (down){
            position.setY(position.getY() + 10);
        }
        if (right){
            position.setX(position.getX() + 10);
        }
        if (left){
            position.setX(position.getX() - 10);
        }
    }

    public void setOnKeyPressed(KeyEvent event){
        switch (event.getCode()){
            case UP -> {state = 1; upPressed = true; }
            case DOWN -> {state = 1; down = true;}
            case RIGHT -> {state= 1; right = true;}
            case LEFT -> {state = 1; left = true;}
            case A -> {state = 1; aPressed = true;}
            case S -> {state = 1; sPressed = true;}
        }

        if (aPressed && sPressed){
            System.out.println("Here");
        }
    }

    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()){
            case A -> System.out.println("Tecla A");
            case UP -> {state= 0; upPressed = false;}
            case DOWN -> {state = 0; down = false;}
            case RIGHT -> {state = 0; right = false;}
            case LEFT -> {state= 0; left = false;}
        }
    }

    public Position getPosition() {
        return position;
    }


}
