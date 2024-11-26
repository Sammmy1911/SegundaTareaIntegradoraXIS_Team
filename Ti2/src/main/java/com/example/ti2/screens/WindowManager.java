package com.example.ti2.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class WindowManager {
    private Canvas canvas;
    private Screen1 screen1;
    private Screen2 screen2;
    private Object currentScreen;

    public WindowManager(Canvas canvas) {
        this.canvas = canvas;
        this.screen1 = new Screen1(canvas);
        this.screen2 = new Screen2(canvas);
        this.currentScreen = screen1; // Set initial screen

        // Establecer el cambio de pantalla en Screen1
        screen1.setOnChangeScreen(this::switchToScreen2);
    }

    public void switchToScreen1() {
        clearCurrentScreen();
        this.currentScreen = screen1;
    }

    public void switchToScreen2() {
        clearCurrentScreen();
        this.currentScreen = screen2;
    }

    private void clearCurrentScreen() {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).paint();
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).paint();
        }
    }

    public void paintCurrentScreen() {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).paint();
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).paint();
        }
    }

    public void onKeyPressed(KeyEvent event) {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).onKeyPressed(event);
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).onKeyPressed(event);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        if (currentScreen instanceof Screen1) {
            ((Screen1) currentScreen).onKeyReleased(event);
        } else if (currentScreen instanceof Screen2) {
            ((Screen2) currentScreen).onKeyReleased(event);
        }
    }
}
