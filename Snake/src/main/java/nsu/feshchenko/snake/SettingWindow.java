package nsu.feshchenko.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nsu.feshchenko.snake.models.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingWindow {
    private int mapSize = -1;
    private int countFoods = -1;
    private int countBarriers = -1;
    private int goal = -1;

    private Parameters parameters = new Parameters();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
            if (!Objects.equals(sizeField.getText(), "") || !Objects.equals(foodOnField.getText(), "") || !Objects.equals(amountOfBarriers.getText(), "") || !Objects.equals(foodGoal.getText(), "")) {
                mapSize = Integer.parseInt(sizeField.getText());
                countFoods = Integer.parseInt(foodOnField.getText());
                countBarriers = Integer.parseInt(amountOfBarriers.getText());
                goal = Integer.parseInt(foodGoal.getText());
                if (mapSize > 5 && countFoods > 0 && countBarriers > -1 && goal > 1) {
                    parameters = new Parameters(mapSize, countFoods, countBarriers, goal);
                    applyButton.getScene().getWindow().hide();
                    MainFX application = new MainFX(parameters);
                    Stage stage = new Stage();
                    try {
                        application.start(stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("wrong input");
                }
            } else {
                System.out.println("empty fields");
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
}
