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
        int res = field_update();
        if(res == 1){
            figure_righter();
        }
        else if(res == -1){
            figure_lefter();
        }
    }
    public void figure_lefter(){
        saveLastPosition();
        FigureBasePoint[0]--;
        int res = field_update();
        if(res == 1){//если фигура вышла за границу, то двигаем её вправо
            figure_righter();
        }
    }
    public void figure_righter(){
        saveLastPosition();
        FigureBasePoint[0]++;
        int res = field_update();
        if(res == -1){//если вышла за границу, то двигаем фигуру влево
            figure_lefter();
        }
    }
    //создает эффект гравитации, фигура постепенно снижается вниз
    public void figure_downer(){
        saveLastPosition();
        FigureBasePoint[1]++;
        field_update();
    }
    //обновляет отображение фигуры
    private int field_update(){
        //System.out.println("AAA");
        int xCoords;
        int yCoords;
        int res = 0;
        //очищает предыдущие точки
        for(int i = 0;i<4;i++){
            int lastxCoords = LastFigureCoords[i][1] + LastFigureBasePoint[0];
            int lastyCoords = LastFigureCoords[i][0] + LastFigureBasePoint[1];
            if(lastyCoords<0 || lastxCoords<0 || lastxCoords >= TetrisCellObj.cols) break;
            TetrisCellObj.setEmpty(lastxCoords,lastyCoords);
        }
        //рисует новые
        for(int i = 0;i<4;i++){
            xCoords = FigureCoords[i][1] + FigureBasePoint[0];//координаты по x
            yCoords = FigureCoords[i][0] + FigureBasePoint[1];//коордлинаты по y
            if(yCoords < 0) break;
            else if(xCoords < 0) {//если фигура вышла за границу слева, то двигаем её вправо, возвращая 1
                res = 1;
                break;
            }
            else if(xCoords >= TetrisCellObj.cols){//если фигура вышла за границу справа, то двигаем её влево, возвращая -1
                res=-1;
                break;
            }
            TetrisCellObj.setFill(xCoords,yCoords);

        }
        return res;
    }
}
