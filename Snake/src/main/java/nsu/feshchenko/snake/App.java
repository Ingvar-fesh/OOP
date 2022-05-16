package nsu.feshchenko.snake;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class App {
    @FXML
    private Button exitButton;

    @FXML
    private Button playButton;

    @FXML
    private Button settingButton;

    @FXML
    void initialize() {
        playButton.setOnAction(event -> {
            playButton.getScene().getWindow().hide();
            MainFX application = new MainFX();
            Stage stage = new Stage();
            try {
                application.start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        settingButton.setOnAction(event -> {
            settingButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("setting-window.fxml"));

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
        exitButton.setOnAction(event -> {
            exitButton.getScene().getWindow().hide();
        });

    }
}
