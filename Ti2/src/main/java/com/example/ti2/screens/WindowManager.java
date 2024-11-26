package com.example.ti2.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class WindowManager {
    private Canvas canvas;
    private Screen1 screen1;
    private Screen2 screen2;
    private Screen3 screen3;
    private Object currentScreen;

    public WindowManager(Canvas canvas) {
        this.canvas = canvas;
        this.screen1 = new Screen1(canvas);
        this.screen2 = new Screen2(canvas);
        this.screen3 = new Screen3(canvas);
        this.currentScreen = screen1; // Set initial screen

        // Establecer los cambios de pantalla en Screen1, Screen2 y Screen3
        screen1.setOnChangeScreenTo2(this::switchToScreen2);
        screen2.setOnChangeScreenTo1(this::switchToScreen1);
        screen2.setOnChangeScreenTo3(this::switchToScreen3);
        screen3.setOnChangeScreenTo1(this::switchToScreen1);
    }

    public void switchToScreen1() {
        clearCurrentScreen();
        this.currentScreen = screen1;
    }

    public void switchToScreen2() {
        clearCurrentScreen();
        this.currentScreen = screen2;
    }

    public void switchToScreen3() {
        clearCurrentScreen();
        this.currentScreen = screen3;
    }

    private void clearCurrentScreen() {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).clear();
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).clear();
        } else if (currentScreen instanceof Screen3) {
            ((Screen3) currentScreen).clear();
        }
    }

    public void paintCurrentScreen() {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).paint();
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).paint();
        } else if (currentScreen instanceof Screen3) {
            ((Screen3) currentScreen).paint();
        }
    }

    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case DIGIT1 -> switchToScreen1();
            case DIGIT2 -> switchToScreen2();
            case DIGIT3 -> switchToScreen3();
            default -> {
                if (currentScreen instanceof Screen1) {
                    ((Screen1) currentScreen).onKeyPressed(event);
                } else if (currentScreen instanceof Screen2) {
                    ((Screen2) currentScreen).onKeyPressed(event);
                } else if (currentScreen instanceof Screen3) {
                    ((Screen3) currentScreen).onKeyPressed(event);
                }
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).onKeyReleased(event);
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).onKeyReleased(event);
        } else if (currentScreen instanceof Screen3) {
            ((Screen3) currentScreen).onKeyReleased(event);
        }
    }
}

