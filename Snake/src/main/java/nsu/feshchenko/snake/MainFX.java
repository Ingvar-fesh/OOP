package nsu.feshchenko.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javafx.util.Duration;
import nsu.feshchenko.snake.graphics.Graphics;
import nsu.feshchenko.snake.models.*;
import nsu.feshchenko.snake.*;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainFX extends Application {
    private Direction currentDirection = Direction.STOP;
    private nsu.feshchenko.snake.models.Parameters parameters = new SettingWindow().tellParameters();
    private FoodGenerator foodGenerator = new FoodGenerator(parameters.getCountFoods(), parameters.getGoal());
    private Snake snake = new Snake();
    private Field field = new Field(parameters.getFieldSize());
    private Graphics graphicsWork = new Graphics();
    private BarrierGenerator barrierGenerator = new BarrierGenerator(parameters.getBarriersAmount());
    private Timeline timeline;

    public MainFX() {

    }
    public MainFX(nsu.feshchenko.snake.models.Parameters parameters) {
        this.foodGenerator = new FoodGenerator(parameters.getCountFoods(), parameters.getGoal());
        this.barrierGenerator = new BarrierGenerator(parameters.getBarriersAmount());
        this.snake = new Snake();
        this.field = new Field(parameters.getFieldSize());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Canvas canvas = new Canvas(600, 600);
        stage.setTitle("Snake game");
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT || code == KeyCode.D) {
                if (currentDirection != Direction.LEFT) {
                    currentDirection = Direction.RIGHT;
                }
            } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                if (currentDirection != Direction.RIGHT) {
                    currentDirection = Direction.LEFT;
                }
            } else if (code == KeyCode.UP || code == KeyCode.W) {
                if (currentDirection != Direction.DOWN) {
                    currentDirection = Direction.UP;
                }
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                if (currentDirection != Direction.UP) {
                    currentDirection = Direction.DOWN;
                }
            }
        });
        barrierGenerator.generateBarriers(field.getROWS(), field.getCOLUMNS());
        foodGenerator.generatorFoods(snake.getBody(), field.getROWS(), field.getCOLUMNS(), barrierGenerator.getBarriers());
        snake.initSnake(field.getROWS(), field.getCOLUMNS());
        timeline = new Timeline(new KeyFrame(Duration.millis(120), e -> {
            try {
                run(gc, stage);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc, Stage stageStart) throws InterruptedException {
        if (snake.isGameOver(field.getSQUARE_SIZE(), field.getWIDTH(), field.getHEIGHT(), barrierGenerator.getBarriers())) {
            timeline.stop();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("lose.fxml"));
            transitionToNewFxml(stageStart, loader);
        }
        if (isVictory()) {
            timeline.stop();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("win.fxml"));
            transitionToNewFxml(stageStart, loader);
        }
        graphicsWork.drawBackground(gc, field.getROWS(), field.getCOLUMNS(), field.getSQUARE_SIZE());
        graphicsWork.drawBarriers(gc, field.getSQUARE_SIZE(), barrierGenerator.getBarriers());
        graphicsWork.drawFood(gc, field.getSQUARE_SIZE(), foodGenerator.getFoods());
        graphicsWork.drawScore(gc, foodGenerator.getCurrentFoods());
        graphicsWork.drawGoal(gc, foodGenerator.getGoal());
        eatFood();
        graphicsWork.drawSnake(gc, snake.getHead(), snake.getBody(), field.getSQUARE_SIZE());

        if (snake.getBody().size() > 1) {
            Point newElem = snake.getBody().get(snake.getBody().size() - 1);
            newElem.x = snake.getHead().x;
            newElem.y = snake.getHead().y;
            snake.getBody().add(1, newElem);
            snake.removeBody(snake.getBody());
        }

        switch (currentDirection) {
            case RIGHT -> snake.moveRight();
            case LEFT -> snake.moveLeft();
            case UP -> snake.moveUp();
            case DOWN -> snake.moveDown();
        }

    }

    private void transitionToNewFxml(Stage stageStart, FXMLLoader loader) throws InterruptedException {
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        TimeUnit.SECONDS.sleep(1);
        stageStart.close();
    }

    public void eatFood() {
        for (int i = 0; i < foodGenerator.getFoodsOnField(); i++) {
            if (snake.getHead().getX() == foodGenerator.getFoods().get(i).getX() && snake.getHead().getY() == foodGenerator.getFoods().get(i).getY()) {
                snake.getBody().add(new Point(-1, -1));
                foodGenerator.removeFromFoodList(i);
                foodGenerator.generatorFoods(snake.getBody(), field.getROWS(), field.getCOLUMNS(), barrierGenerator.getBarriers());
            }
        }
    }

    public boolean isVictory() {
        return foodGenerator.getCurrentFoods() == foodGenerator.getGoal();
    }
}
