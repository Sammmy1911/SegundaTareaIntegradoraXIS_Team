package com.example.ti2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Villager {
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    // Arreglos de las animaciones del personaje
    private ArrayList<Image> idle;
    private ArrayList<Image> walk;
    private ArrayList<Image> runs;
    private ArrayList<Image> hammer;
    private ArrayList<Image> axe;
    private ArrayList<Image> dig;
    private ArrayList<Image> roll;
    private ArrayList<Image> mine;

    private int frame = 0;
    private boolean facingRight = true;

    private boolean upPressed;
    private boolean down;
    private boolean right;
    private boolean left;

    private Position position;
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
        this.hammer = new ArrayList<>();

        this.position = new Position(100, 100);

        // Carga de las imágenes de las animaciones
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/IDLE/v-idle-" + i + ".png"));
            this.idle.add(image);
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/RUN/v-run-" + i + ".png"));
            this.runs.add(image);
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/WALK/v-walk-" + i + ".png"));
            this.walk.add(image);
        }
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/MINE/v-mine-" + i + ".png"));
            this.mine.add(image);
        }
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/AXE/v-axe-" + i + ".png"));
            this.axe.add(image);
        }
        for (int i = 0; i <= 11; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/DIG/v-dig-" + i + ".png"));
            this.dig.add(image);
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/ROLL/v-roll-" + i + ".png"));
            this.roll.add(image);
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/HAMMER/v-hammer-" + i + ".png"));
            this.hammer.add(image);
        }
    }

    public void paint() {
        onMove();

        // Incremento del frame para la animación
        frame++;

        // Voltea el sprite si es necesario
        graphicsContext.save();
        if (!facingRight) {
            graphicsContext.translate(position.getX() * 2 + idle.get(0).getWidth(), 0);
            graphicsContext.scale(-1, 1);
        }

        // Dibuja el personaje según el estado
        switch (state) {
            case 0 -> graphicsContext.drawImage(idle.get(frame % idle.size()), position.getX(), position.getY());
            case 1 -> graphicsContext.drawImage(walk.get(frame % walk.size()), position.getX(), position.getY());
            case 2 -> graphicsContext.drawImage(runs.get(frame % runs.size()), position.getX(), position.getY());
            case 3 -> graphicsContext.drawImage(mine.get(frame % mine.size()), position.getX(), position.getY());
            case 4 -> graphicsContext.drawImage(axe.get(frame % axe.size()), position.getX(), position.getY());
            case 5 -> graphicsContext.drawImage(dig.get(frame % dig.size()), position.getX(), position.getY());
            case 6 -> graphicsContext.drawImage(roll.get(frame % roll.size()), position.getX(), position.getY());
            case 7 -> graphicsContext.drawImage(hammer.get(frame % hammer.size()), position.getX(), position.getY());
        }

        graphicsContext.restore();
    }

    public void onMove() {
        if (upPressed) position.setY(position.getY() - 10);
        if (down) position.setY(position.getY() + 10);
        if (right) {
            position.setX(position.getX() + 10);
            facingRight = true; // Cambia dirección a la derecha
        }
        if (left) {
            position.setX(position.getX() - 10);
            facingRight = false; // Cambia dirección a la izquierda
        }
    }

    public void OnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 1;
                upPressed = true;
            }
            case RIGHT -> {
                state = 1;
                right = true;
            }
            case LEFT -> {
                state = 1;
                left = true;
            }
            case DOWN -> {
                state = 1;
                down = true;
            }
            case S -> state = 2; // Correr
            case A -> state = 3; // Minar
            case D -> state = 4; // Usar el hacha
            case F -> state = 5; // Cavar
            case R -> state = 6; // Rodar
            case H -> state = 7; // Usar el martillo
        }
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 0;
                upPressed = false;
            }
            case DOWN -> {
                state = 0;
                down = false;
            }
            case RIGHT -> {
                state = 0;
                right = false;
            }
            case LEFT -> {
                state = 0;
                left = false;
            }
            case S, A, D, F, R, H -> state = 0;
        }
    }

    public Position getPosition() {
        return position;
    }
}
