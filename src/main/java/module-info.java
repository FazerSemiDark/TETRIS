module com.example.tetris {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.tetris to javafx.fxml;
    exports com.oop.tetris;
}