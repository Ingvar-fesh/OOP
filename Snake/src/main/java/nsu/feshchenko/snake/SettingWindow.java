package nsu.feshchenko.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nsu.feshchenko.snake.models.Data;

import java.io.IOException;
import java.util.Objects;

public class SettingWindow {
    private int mapSize;
    private int countFoods;
    private int countBarriers;
    private int goal;
    private int speed;
    private final Data parameters = Data.getINSTANCE();

    @FXML
    private TextField speedSnake;

    @FXML
    private TextField amountOfBarriers;

    @FXML
    private Button backToMainMenu;

    @FXML
    private TextField foodGoal;
    
    @FXML
    private Button defaultButton;

    @FXML
    private TextField foodOnField;

    @FXML
    private TextField sizeField;

    @FXML
    private Button applyButton;

    @FXML
    void initialize() {
        speedSnake.setText(Integer.toString(parameters.getSpeed()));
        sizeField.setText(Integer.toString(parameters.getFieldSize()));
        foodOnField.setText(Integer.toString(parameters.getCountFoods()));
        foodGoal.setText(Integer.toString(parameters.getGoal()));
        amountOfBarriers.setText(Integer.toString(parameters.getCountBarriers()));

        applyButton.setOnAction(event -> {
            if (!Objects.equals(sizeField.getText(), "") || !Objects.equals(foodOnField.getText(), "") || !Objects.equals(amountOfBarriers.getText(), "") || !Objects.equals(foodGoal.getText(), "") || !Objects.equals(speedSnake.getText(), "")) {
                try {
                    mapSize = Integer.parseInt(sizeField.getText());
                    countFoods = Integer.parseInt(foodOnField.getText());
                    countBarriers = Integer.parseInt(amountOfBarriers.getText());
                    goal = Integer.parseInt(foodGoal.getText());
                    speed = Integer.parseInt(speedSnake.getText());
                }
                catch (NumberFormatException e){
                    wrongInputData();
                    return;
                }
                if (mapSize > 2 && mapSize <= 100 && countFoods > 0 && countBarriers > -1 && goal > 1 && countBarriers <= mapSize * mapSize / 2 && countFoods < mapSize * mapSize - countBarriers && speed > 0 && speed <= 500) {
                    parameters.setFieldSize(mapSize);
                    parameters.setCountBarriers(countBarriers);
                    parameters.setCountFoods(countFoods);
                    parameters.setGoal(goal);
                    parameters.setSpeed(speed);
                    applyButton.getScene().getWindow().hide();
                    MainFX application = new MainFX(parameters);
                    Stage stage = new Stage();
                    try {
                        application.start(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    wrongInputData();
                }
            } else {
                wrongInputData();
            }

        });

        defaultButton.setOnAction(event -> {
            defaultButton.getScene().getWindow().hide();
            parameters.setFieldSize(20);
            parameters.setCountBarriers(13);
            parameters.setCountFoods(5);
            parameters.setGoal(20);
            parameters.setSpeed(150);
            MainFX application = new MainFX(parameters);
            Stage stage = new Stage();
            try {
                application.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        backToMainMenu.setOnAction(event -> {
            backToMainMenu.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.close();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void wrongInputData() {
        applyButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("wrong-data.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
