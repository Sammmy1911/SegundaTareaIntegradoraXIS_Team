package com.example.ti2.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Villager {
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private ArrayList<Image> idle;
    private ArrayList<Image> walk;
    private int frame = 0;
    private boolean facingRight = true;

    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;

    private Position position;
    private int state;

    private double playerWidth;
    private double playerHeight;

    private List<Rectangle> obstacles; // Lista de obstáculos para detección de colisiones

    public Villager(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.state = 0;
        this.idle = new ArrayList<>();
        this.walk = new ArrayList<>();

        this.position = new Position(250, 250);

        // Inicializar obstáculos vacíos
        this.obstacles = new ArrayList<>();

        // Carga de imágenes para animaciones
        for (int i = 0; i <= 8; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/IDLE/v-idle-" + i + ".png"));
            this.idle.add(image);
        }
        for (int i = 0; i <= 6; i++) {
            Image image = new Image(getClass().getResourceAsStream("/AssetsPerson/Villager/WALK/v-walk-" + i + ".png"));
            this.walk.add(image);
        }

        // Establecer el tamaño inicial del jugador
        this.playerWidth = this.idle.get(0).getWidth();
        this.playerHeight = this.idle.get(0).getHeight();
    }

    public void paint() {
        onMove();

        // Incrementar frame para la animación
        frame++;

        // Guardar contexto gráfico para manipular orientación
        graphicsContext.save();
        if (!facingRight) {
            graphicsContext.translate(position.getX() * 2 + playerWidth, 0);
            graphicsContext.scale(-1, 1);
        }

        // Dibujar el personaje según el estado
        switch (state) {
            case 0 -> graphicsContext.drawImage(idle.get(frame % idle.size()), position.getX(), position.getY(), playerWidth, playerHeight);
            case 1 -> graphicsContext.drawImage(walk.get(frame % walk.size()), position.getX(), position.getY(), playerWidth, playerHeight);
        }

        graphicsContext.restore();
    }

    public void onMove() {
        double newX = position.getX();
        double newY = position.getY();

        if (upPressed) newY -= 5; // Próxima posición hacia arriba
        if (downPressed) newY += 5; // Próxima posición hacia abajo
        if (rightPressed) {
            newX += 5; // Próxima posición hacia la derecha
            facingRight = true;
        }
        if (leftPressed) {
            newX -= 5; // Próxima posición hacia la izquierda
            facingRight = false;
        }

        // Comprobar colisiones con los obstáculos
        Rectangle newBounds = new Rectangle(newX, newY, playerWidth, playerHeight);
        boolean collisionDetected = false;

        for (Rectangle obstacle : obstacles) {
            if (newBounds.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                collisionDetected = true;
                break;
            }
        }

        // Actualizar la posición solo si no hay colisión
        if (!collisionDetected) {
            position.setX(newX);
            position.setY(newY);
        }

        // Verificar si el personaje alcanza las coordenadas para cambiar de pantalla
        if (newX == 165 && newY == 215) {
            System.out.println("Posición alcanzada. Cambiando a pantalla 2.");
            // Aquí llamaríamos a un método para cambiar de pantalla
            // Por ejemplo, utilizando un evento o una bandera en la clase ScreenManager
        }
    }


    public void OnKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 1; // Estado caminando
                upPressed = true;
            }
            case DOWN -> {
                state = 1;
                downPressed = true;
            }
            case RIGHT -> {
                state = 1;
                rightPressed = true;
            }
            case LEFT -> {
                state = 1;
                leftPressed = true;
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                state = 0; // Estado en reposo
                upPressed = false;
            }
            case DOWN -> {
                state = 0;
                downPressed = false;
            }
            case RIGHT -> {
                state = 0;
                rightPressed = false;
            }
            case LEFT -> {
                state = 0;
                leftPressed = false;
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), playerWidth, playerHeight);
    }

    public void setObstacles(List<Rectangle> obstacles) {
        this.obstacles = obstacles;
    }

    public void resize(double scaleFactor) {
        this.playerWidth = this.idle.get(0).getWidth() * scaleFactor;
        this.playerHeight = this.idle.get(0).getHeight() * scaleFactor;
    }

    // Método para obtener la posición exacta como una cadena
    public String getPositionAsString() {
        return "Posición del personaje: X = " + position.getX() + ", Y = " + position.getY();
    }
}
