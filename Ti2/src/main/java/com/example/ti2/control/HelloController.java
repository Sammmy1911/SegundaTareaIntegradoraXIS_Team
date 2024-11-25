package com.example.ti2.control;

import com.example.ti2.screens.Screen1;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Canvas canvas;
    private Screen1 screen1;

    private GraphicsContext graphicsContext;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (canvas != null) {
            this.graphicsContext = canvas.getGraphicsContext2D();
            this.screen1 = new Screen1(this.canvas);

            new Thread(() -> {
                while (true) {
                    Platform.runLater(() -> {
                        screen1.paint();
                    });
                    try {
                        Thread.sleep(100);
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