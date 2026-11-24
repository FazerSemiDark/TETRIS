package com.oop.consoletetris;

import com.oop.tetris.Game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleApplication {
    static Game GameObj;
    static ConsoleRenderGrid RenderGridObj;
    static String input = "";
    private static final long MOVE_SPEED = 1;
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        RenderGridObj = new ConsoleRenderGrid();//создаём реализацию RenderGrid
        GameObj = new Game(20,10,RenderGridObj);
        Thread inputThread = new Thread(() -> {//поток ввода
            while(true) {
                input = scan.next();
                switch(input){
                    case "a"://на клавишу a влево
                        GameObj.figure_lefter();
                        break;
                    case "d"://на клавишу d вправо
                        GameObj.figure_righter();
                        break;
                    case "r"://на клавишу r вращение фигуры
                        GameObj.figureRotation();
                        break;
                    case "s"://на клавишу s снижение фигуры вниз
                        GameObj.figure_downer();
                        break;
                    case "f"://на клавишу f хард-дроп
                        int CycleExit = 0;
                        while (CycleExit != 5) {
                            CycleExit = GameObj.figure_downer();
                        }
                        break;
                    default:
                        continue;
                }
                RenderGridObj.render();//отрисовка поля
            }
        });
        inputThread.start();
        while (true) {
            RenderGridObj.render();//отрисовка поля
            try {
                TimeUnit.SECONDS.sleep(MOVE_SPEED);//задержка отрисовки/скорость падения фигур
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
            GameObj.figure_downer();//падение фигуры


        }
    }
}
