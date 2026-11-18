package com.oop.tetris;

public abstract class Figure {
    private final int matrixSize;
    CellState colour;
    boolean canRotate;

    private final int[][] indexRotateMatrix; // список перехода из одной координаты в другую при вращении
    private final int[][] indexList; // координаты точек в фигуре

    protected Figure(CellState color, int[][] indList, int[][] indLMatrix, boolean canRot)
    {
        this.colour = color;
        this.indexList = indList;
        this.indexRotateMatrix = indLMatrix;
        this.matrixSize = (int) Math.sqrt(indexRotateMatrix.length);
        this.canRotate = canRot;
    }

    private void indexSwap(int[][] list, int idx1, int idx2) { // меняет индексы местами
        int[] indexNull = list[idx1];
        list[idx1] = list[idx2];
        list[idx2] = indexNull;
    }
    public int[][] retIdxL() { // возвращает координаты точек фигуры ввиде вложенного массива
        int[][] res = new int[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = indexList[i][j];
            }
        }
        return res;
    }
    public void figureRotate() { // вращение фигуры
        if (!canRotate) {
            System.out.println("Not Rotated");
            return;
        }

        for (int i = 0; i < 4; i++) { // меняем координаты
            int[] idx = indexList[i];
            indexList[i] = indexRotateMatrix[idx[0] * matrixSize + idx[1]];
        }
        // сортируем массив
        if (indexList[0][0] < indexList[1][0]) indexSwap(indexList,1,0);
        if (indexList[2][0] < indexList[3][0]) indexSwap(indexList,3,2);
        if (indexList[0][0] < indexList[2][0]) indexSwap(indexList,2,0);
        if (indexList[1][0] < indexList[3][0]) indexSwap(indexList,3,1);
        if (indexList[1][0] < indexList[3][0]) indexSwap(indexList,3,1);
        // консольный вывод
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(indexList[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    };

}
