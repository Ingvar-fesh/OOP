module nsu.feshchenko.snake {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;


    opens nsu.feshchenko.snake to javafx.fxml;
    exports nsu.feshchenko.snake;
}