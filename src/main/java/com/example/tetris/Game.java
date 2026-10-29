package com.example.tetris;
import javafx.scene.canvas.Canvas;


// класс по сути затычка для теста, но в теории можно начать писать в нём логику игры



public class Game {
    private int[] FigureBasePoint = {3,-2};//точка, от которой строим фигуру
    private int[] LastFigureBasePoint={3,-2};//предыдущее состояние этой точки
    private Figure figure = new Figure();
    private int[][] FigureCoords = figure.indexList;//координаты точек фигуры
    private int[][] LastFigureCoords = figure.retIdxL();//их предыдущее состояние
    private final TetrisCell TetrisCellObj = new TetrisCell();
    private final RenderCell RenderCellObj = TetrisCellObj.render_cell;
    public Canvas GetCanvas()
    {
        return TetrisCellObj.render_cell.getCanvas();
    }
    public void render_field(){
        RenderCellObj.render();
    }
    private void saveLastPosition(){
        LastFigureBasePoint[0] = FigureBasePoint[0];
        LastFigureBasePoint[1] = FigureBasePoint[1];
        LastFigureCoords = figure.retIdxL();
    }
    //вращает фигуру
    public void figureRotation(){
        saveLastPosition();
        figure.FigureRotate();
        field_update();
    }
    //создает эффект гравитации, фигура постепенно снижается вниз
    public void figure_downer(){
        saveLastPosition();
        FigureBasePoint[1]++;
        field_update();
        for(int i = 0;i<4;i++){
            for(int j =0;j<2;j++){
                System.out.print(LastFigureCoords[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //обновляет отображение фигуры
    private void field_update(){
        int xCoords;
        int yCoords;
        //очищает предыдущие точки
        for(int i = 0;i<4;i++){
            int lastxCoords = LastFigureCoords[i][1] + LastFigureBasePoint[0];
            int lastyCoords = LastFigureCoords[i][0] + LastFigureBasePoint[1];
            if(lastyCoords<0 || lastxCoords<0) break;
            TetrisCellObj.setEmpty(lastxCoords,lastyCoords);
        }
        //рисует новые
        for(int i = 0;i<4;i++){
            xCoords = FigureCoords[i][1] + FigureBasePoint[0];//координаты по x
            yCoords = FigureCoords[i][0] + FigureBasePoint[1];//коордлинаты по y
            if(xCoords<0 || yCoords<0) break;
            TetrisCellObj.setFill(xCoords,yCoords);

        }
    }
}
