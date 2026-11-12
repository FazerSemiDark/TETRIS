package com.example.tetris;
import javafx.scene.canvas.Canvas;

import java.util.Random;

public class Game {
    private int[] figureBasePoint = {3, -2}; // точка, от которой строим фигуру
    private int[] lastFigureBasePoint = {3, -2}; // предыдущее состояние этой точки

    private Figure figure;// = getFigure();

    private int[][] figureCoords;//координаты точек фигуры
    private int[][] lastFigureCoords; //их предыдущее состояние

    private final TetrisCell TetrisCellObj = new TetrisCell();
    private final RenderCell RenderCellObj = TetrisCellObj.render_cell;

    Game() {
        getFigure();
    }

    public Canvas GetCanvas() {
        return TetrisCellObj.render_cell.getCanvas();
    }
    private void getFigure() { //временно
        System.out.println("НОВАЯ ФИГУРА");
        Random random = new Random();
        int number = random.nextInt(6);
        switch (number) {
            case 0: figure = new T();
                break;
            case 1: figure = new S();
                break;
            case 2: figure = new S();
                break;
            case 3: figure = new S();
                break;
            case 4: figure = new T();
                break;
            case 5: figure = new T();
                break;

        }
        figureCoords = figure.retIdxL();
        saveLastPosition();
        figureBasePoint[0] = 3;//рандомные число
        figureBasePoint[1] = -2;//не рандомн
    }
    public void render_field() {
        RenderCellObj.render();
    }

    private void saveLastPosition() {
        lastFigureBasePoint[0] = figureBasePoint[0];
        lastFigureBasePoint[1] = figureBasePoint[1];
        lastFigureCoords = figure.retIdxL();
    }

    // вращает фигуру
    public void figureRotation() {
        saveLastPosition();
        figure.FigureRotate();
        figureCoords = figure.retIdxL();
        int res = field_update();

        if (res == 1) {
            figure_righter();
        }
        else if (res == -1) {
            figure_lefter();
        }
    }

    public void figure_lefter() {
        saveLastPosition();
        figureBasePoint[0]--;
        int res = field_update();
        if (res == 1) { // если фигура вышла за границу, то двигаем её вправо
            figure_righter();
        }
    }

    public void figure_righter(){
        saveLastPosition();
        figureBasePoint[0]++;
        int res = field_update();
        //если вышла за границу, то двигаем фигуру влево
        if (res == -1) {
            figure_lefter();
        }
    }

    // создает эффект гравитации, фигура постепенно снижается вниз
    public int figure_downer() {
        saveLastPosition();
        figureBasePoint[1]++;
        return field_update();
    }

    // обновляет отображение фигуры
    private int field_update() {
        // координаты точек в поле
        int[] xCoords = new int[4];
        int[] yCoords = new int[4];
        int[] lastXCoords = new int[4];
        int[] lastYCoords = new int[4];
        int res = 0;
        // очищает предыдущие точки
        int i = 0;

        for (i = 0; i < 4; i++) { // сначала проверяем все точки
            lastXCoords[i] = lastFigureCoords[i][1] + lastFigureBasePoint[0];
            lastYCoords[i] = lastFigureCoords[i][0] + lastFigureBasePoint[1];
            if(lastYCoords[i] < 0 || lastXCoords[i] < 0 || lastXCoords[i] >= TetrisCellObj.cols) {
                break;
            }
            else if (lastYCoords[i] >= TetrisCellObj.rows - 1) { // если фигура дошла до конца, то запускаем новую
                System.out.println("НАЧАЛО");
                res = 5;
                getFigure();
                break;
            }
            //TetrisCellObj.setEmpty(lastXCoords,lastYCoords);
        }
        for (int j = 0; j < i; j++) { // потом очищаем
            TetrisCellObj.setEmpty(lastXCoords[j], lastYCoords[j]);
        }
        // рисует новые
        for (i = 0; i < 4; i++) { // сначала проверяем все точки
            xCoords[i] = figureCoords[i][1] + figureBasePoint[0]; // координаты по x
            yCoords[i] = figureCoords[i][0] + figureBasePoint[1]; // коордлинаты по y

            if (yCoords[i] < 0) break;
            else if (xCoords[i] < 0) { // если фигура вышла за границу слева, то двигаем её вправо, возвращая 1
                res = 1;
                return res;
            }
            else if(xCoords[i] >= TetrisCellObj.cols) { // если фигура вышла за границу справа, то двигаем её влево, возвращая -1
                res = -1;
                return res;
            }
            if (TetrisCellObj.isFull(xCoords[i], yCoords[i])) {
                //если здесь уже есть точка
                if (yCoords[i] - 1 == lastYCoords[i]) { // то запускаем новую фигуру
                    i = -2;
                    break;
                }
                if (xCoords[i] < lastXCoords[i] && yCoords[i] == lastYCoords[i]) { // то двигаем её вправо
                    figureBasePoint[0] += 1;
                    i = -1;
                    break;
                }
                else if (xCoords[i] > lastXCoords[i] && yCoords[i] == lastYCoords[i]) { // то двигаем её влево
                    figureBasePoint[0] -= 1;
                    i = -1;
                    break;
                }
                break;
            }
        }
        if (i == -1) {
            for (int j = 0; j < 4; j++) { // двигает фигуру вправо/влево, если она заехала на другую
                xCoords[j] = figureBasePoint[0] + figureCoords[j][1];
                yCoords[j] = figureBasePoint[1] + figureCoords[j][0];
                TetrisCellObj.setFill(xCoords[j], yCoords[j], figure.colour);
                System.out.println("ЗАЕХАЛО НА ФИГУРУ");
            }
            return res;
        }
        else if (i == -2) {
            for (int j = 0; j < 4; j++) { // запуск новой фигуры, если эта фигура столкнулась с уже имеющимися
                TetrisCellObj.setFill(lastXCoords[j], lastYCoords[j], figure.colour);
                System.out.println("НАЧАЛО2");
                res = 5;
            }
            getFigure();
        }
        else {
            for (int j = 0; j < i; j++) { // рисуем точки
                TetrisCellObj.setFill(xCoords[j], yCoords[j], figure.colour);
            }
        }
        return res;
    }
}
