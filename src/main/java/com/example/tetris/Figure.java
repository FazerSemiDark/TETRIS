package com.example.tetris;
import javafx.scene.paint.Color;
public class Figure {
    private int[][] IndexRotatMatr= {{2,0},{1,0},{0,0},{2,1},{1,1},{0,1},{2,2},{1,2},{0,2}};//список перехода из одной координаты в другую при вращении
    public int[][] indexList = {{1,0},{1,1},{1,2},{0,1}};//координаты точек в фигуре
    private Color colour;
    private void indexSwap(int[][] list,int idx1,int idx2){//меняет индексы местами
        int [] indexNull = list[idx1];
        list[idx1] = list[idx2];
        list[idx2] = indexNull;
    }
    public int[][] retIdxL(){//возвращает координаты точек фигуры ввиде вложенного массива
        int[][] res = new int[4][2];
        for(int i = 0;i<4;i++){
            for(int j = 0;j<2;j++) {
                res[i][j] = indexList[i][j];
            }
        }
        return res;
    }
    public void FigureRotate(){//вращение фигуры
        for(int i = 0;i<4;i++){//меняем координаты
            int[] idx = indexList[i];
            indexList[i] = IndexRotatMatr[idx[0]*3+idx[1]];
        }
        //сортируем массив
        if(indexList[0][0] < indexList[1][0]) indexSwap(indexList,1,0);
        if(indexList[2][0] < indexList[3][0]) indexSwap(indexList,3,2);
        if(indexList[0][0] < indexList[2][0]) indexSwap(indexList,2,0);
        if(indexList[1][0] < indexList[3][0]) indexSwap(indexList,3,1);
        if(indexList[1][0] < indexList[3][0]) indexSwap(indexList,3,1);
        //консольный вывод
        for(int i = 0;i<4;i++){
            for (int j =0;j<2;j++){
                System.out.print(indexList[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    };

}
