package com.example.tetris;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class TetrisApplication extends Application {

    //private final TetrisCell tc = new TetrisCell();

    @Override
    public void start(Stage stage) {
        Game GameObj = new Game();

        StackPane root = new StackPane(GameObj.GetCanvas()); // тут оно как-то его создаёт, я хз
        stage.setScene(new Scene(root));
        stage.show(); // вызов окна
        GameObj.render_field();//отрисовка поля
        //цикл имитирующий гравитацию(снижение фигур)
        Timeline FigureGravitation = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
            GameObj.figure_downer();
        }));
        FigureGravitation.setCycleCount(Timeline.INDEFINITE);
        FigureGravitation.play();

        //Взаимодействие с клавиатурой
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                long time = System.nanoTime();
                System.out.println(event.getCode());

                if(event.getCode() == KeyCode.SPACE) {//поворачивает фигуру на пробел
                    GameObj.figureRotation();
                    long time2 = System.nanoTime();
                    System.out.println(time2-time);
                    System.out.println();
                }
            }
        });

    }

}
