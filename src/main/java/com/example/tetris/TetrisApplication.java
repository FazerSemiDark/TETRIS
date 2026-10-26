package com.example.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TetrisApplication extends Application {

    //private final TetrisCell tc = new TetrisCell();

    @Override
    public void start(Stage stage) {
        Game GameObj = new Game();

        StackPane root = new StackPane(GameObj.GetCanvas()); // тут оно как-то его создаёт, я хз
        stage.setScene(new Scene(root));
        stage.show(); // вызов окна

        GameObj.render_field();
    }

}
