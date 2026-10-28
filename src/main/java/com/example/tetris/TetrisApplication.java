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
        //цикл обновления поля
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0005),e ->{
            GameObj.field_update();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //цикл имитирующий гравитацию(снижение фигур)
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
            GameObj.figure_downer();
        }));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

        //Взаимодействие с клавиатурой
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());//LEFT RIGHT

                if(event.getCode() == KeyCode.SPACE) {//поворачивает фигуру на пробел
                    GameObj.figureRotation();
                }
            }
        });
    }

}
