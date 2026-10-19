package com.example.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TetrisApplication extends Application {

    private final TetrisCell tc = new TetrisCell();

    @Override
    public void start(Stage stage) {
        RenderCell view = tc.rc; // <----- не уверен, что это вообще норм тема, в теории можно написать
        // отдельный метод возвращающий RenderCell созданный в TetrisCell, но тоже не уверен, что так правильно
        // возможно надо будет переписывать пол кода, чтобы это было нормально, а возможно и так сойдёт

        StackPane root = new StackPane(view.getCanvas()); // тут оно как-то его создаёт, я хз
        stage.setScene(new Scene(root));
        stage.show(); // вызов окна

        view.render(); // заливка, по сути подготовка поля
        view.setFillCell(5, 3); // можно отрисовать клетку напрямую
        new Game(tc); // можно при помощи других классов (что нам и нужно)
    }

}
