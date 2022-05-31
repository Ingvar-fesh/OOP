package nsu.feshchenko.snake.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graphics {
    private final String foodImages = "/images/banana.png";
    private Image image;

    public void drawBarriers(GraphicsContext gc, int squareSize, List<Point> barriers) {
        gc.setFill(Color.GRAY);
        for (int i = 0; i < barriers.size(); i++) {
            gc.fillRoundRect(barriers.get(i).getX() * squareSize, barriers.get(i).getY() * squareSize, squareSize - 1, squareSize - 1, 10, 10);
        }
    }

    public void drawBackground(GraphicsContext gc, int rows, int columns, int squareSize) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("00CB1C"));
                } else {
                    gc.setFill(Color.web("00C21A"));
                }
                gc.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
    }

    public void drawFood(GraphicsContext gc, int squareSize, List<Point> foods) {
        image = new Image(foodImages);
        for (int i = 0; i < foods.size(); ++i) {
            gc.drawImage(image, foods.get(i).x * squareSize, foods.get(i).y * squareSize, squareSize, squareSize);
        }
    }

    public void drawSnake(GraphicsContext gc, Point snakeHead, List<Point> snakeBody, int squareSize) {
        gc.setFill(Color.RED);
        gc.fillRoundRect(snakeHead.getX() * squareSize, snakeHead.getY() * squareSize, squareSize - 1, squareSize - 1, 45, 45);
        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * squareSize, snakeBody.get(i).getY() * squareSize, squareSize - 1,
                    squareSize - 1, 20, 20);
        }
    }

    public void drawScore(GraphicsContext gc, int score) {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Digital-7", 20));
        gc.fillText("Score: " + score, 10, 20);
    }

    public void drawGoal(GraphicsContext gc, int goal) {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Digital-7", 20));
        gc.fillText("Goal: " + goal, 10, 40);
    }
}
