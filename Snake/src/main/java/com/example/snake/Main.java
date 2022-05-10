package com.example.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private final int WEIGHT = 600;
    private final int HEIGHT = 600;
    private final int ROWS = 20;
    private final int COLUMNS = 20;
    private final int SQUARE_SIZE = WEIGHT / ROWS;

    private String[] FOODS = new String[]{"/images/banana.png", "/images/cherry.png", "/images/apple.png", "/images/plum.png"};

    private final List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;

    private final int RIGHT = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int DOWN = 3;

    private GraphicsContext gc;
    private final List<Image> foodImage = new ArrayList<>();
    private final List<Point> foodLocation = new ArrayList<>();
    private final int COUNT_FOOD = 3;

    private int currentDirection;
    private boolean gameOver;

    private int score = 0;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake game");
        Group root = new Group();
        Canvas canvas = new Canvas(WEIGHT, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D)
                    currentDirection = RIGHT;
                else if (code == KeyCode.LEFT || code == KeyCode.A)
                    currentDirection = LEFT;
                else if (code == KeyCode.UP || code == KeyCode.W)
                    currentDirection = UP;
                else if (code == KeyCode.DOWN || code == KeyCode.S)
                    currentDirection = DOWN;
            }
        });

        snakeHead = new Point(ROWS / 2, COLUMNS / 2);
        snakeBody.add(snakeHead);
        generateFood();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e->run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digiral-7", 60));
            gc.fillText("Game Over", WEIGHT / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        drawFood(gc);
        drawSnake(gc);
        drawScore(gc);

        for (int i = snakeBody.size() - 1; i >= 1; --i) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

        switch (currentDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUP();
                break;
            case DOWN:
                moveDown();
                break;
        }
        gameOver();
        eatFood();
    }


    private void drawBackground(GraphicsContext gc) {
        for (int i = 0;i < ROWS; ++i) {
            for (int j = 0;j < COLUMNS; ++j) {
                if ((i + j) % 2 == 0)
                    gc.setFill(Color.web("00CB1C"));
                else gc.setFill(Color.web("00C21A"));

                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }


    private void drawFood(GraphicsContext gc) {
        for (int i = 0; i < COUNT_FOOD; ++i)
            gc.drawImage(foodImage.get(i), foodLocation.get(i).x * SQUARE_SIZE, foodLocation.get(i).y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }


    private void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.web("F2C97C"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); ++i)
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, 20, 20);
    }

    private void generateFood() {
        start:
        while (true) {
            while(foodLocation.size() != COUNT_FOOD) {
                int foodX = (int)(Math.random() * ROWS);
                int foodY = (int)(Math.random() * COLUMNS);
                boolean flag = false;
                for (Point point : foodLocation) {
                    if (point.x == foodX && point.y == foodY) {
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                    foodLocation.add(new Point(foodX, foodY));
            }

            for (Point snake: snakeBody) {
                for (int i = 0;i < COUNT_FOOD; ++i) {
                    if (snake.x == foodLocation.get(i).x && snake.y == foodLocation.get(i).x)
                        continue start;
                }
            }

            while(foodImage.size() != COUNT_FOOD)
                foodImage.add(new Image(FOODS[(int)(Math.random() * FOODS.length)]));

            break;
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUP() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    private void gameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * SQUARE_SIZE >= WEIGHT || snakeHead.y * SQUARE_SIZE >= HEIGHT)
            gameOver = true;
        for (int i = 1; i < snakeBody.size(); ++i) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood() {
        for (int i = 0; i < COUNT_FOOD; ++i) {
            if (snakeHead.x == foodLocation.get(i).x && snakeHead.y == foodLocation.get(i).y) {
                snakeBody.add(new Point(-1, -1));
                foodLocation.remove(i);
                foodImage.remove(i);
                generateFood();
                score++;
            }
        }
    }

    private void drawScore(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score:" + score, 10, 35);
    }
}
