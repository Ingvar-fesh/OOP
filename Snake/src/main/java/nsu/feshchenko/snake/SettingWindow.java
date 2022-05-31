package nsu.feshchenko.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nsu.feshchenko.snake.models.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingWindow {
    private int mapSize = 20;
    private int countFoods = 5;
    private int countBarriers = 13;
    private int goal = 20;
    private int speed = 150;
    private Parameters parameters = new Parameters();

    private Label error;
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
                    parameters = new Parameters(mapSize, countFoods, countBarriers, goal, speed);
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
            MainFX application = new MainFX();
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


    public Parameters tellParameters() {
        return parameters;
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
