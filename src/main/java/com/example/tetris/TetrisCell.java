package com.example.tetris;

public class TetrisCell {
    public RenderCell rc;

    public int rows;
    public int cols;
    public final int cell = 30;

    private boolean[][] grid;

    // Задаём количество строк и столбоцов (по дефолту)
    public TetrisCell() {
        rows = 20;
        cols = 10;
        createGrid(rows, cols);
    }

    //  Задаём количество строк и столбоцов
    public TetrisCell(int rws, int clmns) {
        rows = rws;
        cols = clmns;
        createGrid(rows, cols);
    }

    // Создаём сетку в виде двумерного массива и объект класса RenderCell для отрисовки
    private void createGrid(int r, int c) {
        grid = new boolean[r][c];
        rc = new RenderCell(r, c, cell);
    }

    // Заполняем ячейку и просим RenderCell отрисовать её
    public void setFill(int h, int v) {
        grid[h][v] = true;
        rc.setFillCell(h, v);
    }

    // Убирает ячейку и убирает визуальную отрисовку (не факт что правильно)
    public void setEmpty(int h, int v) {
        grid[h][v] = false;
        rc.setEmptyCell(h, v);
    }

    public boolean isEmpty(int h, int v) {
        return !grid[h][v];
    }
}
