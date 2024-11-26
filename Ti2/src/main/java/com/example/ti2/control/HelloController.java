package com.example.ti2.control;

import com.example.ti2.screens.WindowManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Canvas canvas;
    private WindowManager windowManager;
    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (canvas != null) {
            this.graphicsContext = canvas.getGraphicsContext2D();
            this.windowManager = new WindowManager(this.canvas); // Crea el gestor de ventanas

            // Habilita la captura de eventos de teclado para el canvas
            this.canvas.setFocusTraversable(true);

            // Configura la escucha de las teclas presionadas
            this.canvas.setOnKeyPressed(event -> {
                windowManager.onKeyPressed(event);
            });

            // Configura la escucha de las teclas liberadas
            this.canvas.setOnKeyReleased(event -> {
                windowManager.onKeyReleased(event);
            });

            // Actualiza la pantalla en un hilo separado
            new Thread(() -> {
                while (true) {
                    Platform.runLater(() -> {
                        windowManager.paintCurrentScreen();  // Dibuja la pantalla actual en cada ciclo
                    });
                    try {
                        Thread.sleep(100);  // Controla el tiempo de actualización (100 ms)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            System.err.println("Canvas is not initialized!");
        }
    }
}
