package com.example.tetris;

import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class TetrisApplication extends Application {

    private static final double MOVE_SPEED = 1;

    Game GameObj = new Game();

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane(GameObj.GetCanvas()); // тут оно как-то его создаёт, я хз
        stage.setScene(new Scene(root));
        stage.show(); // вызов окна
        GameObj.render_field(); // отрисовка поля
        // цикл имитирующий гравитацию(снижение фигур)
        Timeline FigureGravitationCycle = new Timeline(new KeyFrame(Duration.seconds(MOVE_SPEED), e ->{
            GameObj.figure_downer();
        }));
        FigureGravitationCycle.setCycleCount(Timeline.INDEFINITE);
        FigureGravitationCycle.play();

        // Взаимодействие с клавиатурой
        stage.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                long time = System.nanoTime();
                System.out.println(event.getCode());

                if (event.getCode() == KeyCode.SPACE) { // поворачивает фигуру на пробел
                    GameObj.figureRotation();
                    long time2 = System.nanoTime();
                    System.out.println(time2-time);
                    System.out.println();
                }
                if (event.getCode() == KeyCode.A) { // двигает фигуру влево на клавишу A
                    GameObj.figure_lefter();
                }
                if (event.getCode() == KeyCode.D) { //двигает фигуру вправо на клавишу D
                    GameObj.figure_righter();
                }
                if (event.getCode() == KeyCode.ENTER) {
                    int CycleExit = 0;
                    while (CycleExit != 5) {
                        CycleExit = GameObj.figure_downer();
                    }
                }
            }
        });

        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                if (event.getCode() == KeyCode.S) { //поворачивает фигуру на пробел
                    GameObj.figure_downer();
                }
            }
        });

    }

}
