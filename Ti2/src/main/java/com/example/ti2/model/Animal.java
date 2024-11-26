package com.example.ti2.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Animal {
    private List<Image> idleImages;
    private Position position;
    private int frame; // Aunque no se use en este caso, si no tienes animación, puedes eliminar esta línea.

    // Constructor que recibe la posición inicial
    public Animal(Position startPosition) {
        // Cargar las imágenes del animal desde el directorio
        this.idleImages = loadImages("C:/Users/sgall/Desktop/n/SegundaTareaIntegradoraXIS_Team/Ti2/src/main/resources/AssetsPerson/animal/animal idle/idelAnimal");
        this.position = startPosition; // Mantiene la posición estática del animal
        this.frame = 0; // Inicia el contador de animación (aunque no se use)
    }

    // Método para cargar las imágenes de la carpeta (aseguramos que no haya duplicados)
    private List<Image> loadImages(String directoryPath) {
        List<Image> images = new ArrayList<>();
        File dir = new File(directoryPath);

        // Verifica si el directorio contiene archivos .png y los agrega a la lista
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".png")) {
                images.add(new Image(file.toURI().toString()));
            }
        }
        return images;
    }

    // Método para pintar el animal en la posición específica
    public void paint(GraphicsContext gc) {
        if (!idleImages.isEmpty()) {
            // Dibuja la primera imagen del animal en la posición inicial
            gc.drawImage(idleImages.get(0), position.getX(), position.getY());
        }
    }

    // Método para obtener los límites del animal (su área de colisión)
    public Rectangle getBounds() {
        if (!idleImages.isEmpty()) {
            return new Rectangle(position.getX(), position.getY(), idleImages.get(0).getWidth(), idleImages.get(0).getHeight());
        }
        return null; // Retorna null si no hay imágenes cargadas
    }
}
