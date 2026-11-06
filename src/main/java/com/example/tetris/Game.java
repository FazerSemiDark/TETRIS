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
        //координаты точек в поле
        int xCoords[] = new int[4];
        int yCoords[] = new int[4];
        int lastxCoords[] = new int[4];
        int lastyCoords[] = new int[4];
        int res = 0;
        //очищает предыдущие точки
        int i = 0;
        for(i = 0;i<4;i++){//сначала проверяем все точки
            lastxCoords[i] = LastFigureCoords[i][1] + LastFigureBasePoint[0];
            lastyCoords[i] = LastFigureCoords[i][0] + LastFigureBasePoint[1];
            if(lastyCoords[i]<0 || lastxCoords[i]<0 || lastxCoords[i] >= TetrisCellObj.cols) {
                break;
            }
            else if(lastyCoords[i] >= TetrisCellObj.rows-1){//если фигура дошла до конца, то запускаем новую
                System.out.println("НАЧАЛО");
                FigureBasePoint[0] = 3;
                FigureBasePoint[1] = -2;
                LastFigureBasePoint[0] = FigureBasePoint[0];
                LastFigureBasePoint[1] = FigureBasePoint[1];
                break;
            }
            //TetrisCellObj.setEmpty(lastxCoords,lastyCoords);
        }
        for(int j = 0;j<i;j++){//потом очищаем
            TetrisCellObj.setEmpty(lastxCoords[j],lastyCoords[j]);
        }
        //рисует новые
        for(i=0;i<4;i++){//сначала проверяем все точки
            xCoords[i] = FigureCoords[i][1] + FigureBasePoint[0];//координаты по x
            yCoords[i] = FigureCoords[i][0] + FigureBasePoint[1];//коордлинаты по y

            if(yCoords[i] < 0) break;
            else if(xCoords[i] < 0) {//если фигура вышла за границу слева, то двигаем её вправо, возвращая 1
                res = 1;
                return res;
            }
            else if(xCoords[i] >= TetrisCellObj.cols){//если фигура вышла за границу справа, то двигаем её влево, возвращая -1
                res=-1;
                return res;
            }
            if(TetrisCellObj.isFull(xCoords[i],yCoords[i])){//если здесь уже есть точка, то запускает новую фигуру
                i=-1;
                break;
            }
        }
        if(i==-1){
            for(int j =0;j<4;j++){//запуск новой фигуры, если эта фигура столкнулась с уже имеющимися
                TetrisCellObj.setFill(lastxCoords[j],lastyCoords[j]);
                System.out.println("НАЧАЛО2");
                FigureBasePoint[0] = 3;
                FigureBasePoint[1] = -2;
                LastFigureBasePoint[0] = FigureBasePoint[0];
                LastFigureBasePoint[1] = FigureBasePoint[1];
            }
        }
        else{
            for(int j =0;j<i;j++){//потом рисуем
                TetrisCellObj.setFill(xCoords[j],yCoords[j]);
            }
        }
        return res;
    }
}
