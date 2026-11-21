    package com.oop.tetris;

    import java.util.Arrays;

    public class TetrisGrid {
        public RenderGridAbstr render_grid;

        public int rows;
        public int cols;
        public final int cellSize = 30;

        private CellState[][] grid;

        // Задаём количество строк и столбоцов (по дефолту)
//        public TetrisGrid() {
//            this(20, 10);
//        }

        // Задаём количество строк и столбоцов
        public TetrisGrid(int rws, int clmns,RenderGridAbstr rendGr) {//задаём размер поля и реализацию RenderGrid
            this.rows = rws;
            this.cols = clmns;
            this.render_grid = rendGr;

            this.grid = new CellState[cols][rows];
            for (CellState[] row : grid) {
                Arrays.fill(row, CellState.EMPTY);
            }
        }
        protected void makeField(){
            render_grid.render();
        }

        // Создаём сетку в виде двумерного массива и объект класса RenderCell для отрисовки
//        private void createGrid(int r, int c) {
//            grid = new boolean[c][r]; // поменял r и c местами, потому что тут что-то не то работает
//            render_grid = new RenderGrid(r, c, cellSize);
//        }

        // Заполняем ячейку и просим RenderCell отрисовать её
        public void setFillCell(int x, int y, CellState color) {
            //System.out.println(color);
            grid[x][y] = color;
            render_grid.setFillCell(x, y, color);
        }

        // Убирает ячейку и убирает визуальную отрисовку (не факт что правильно)
        public void setEmptyCell(int x, int y) {
            grid[x][y] = CellState.EMPTY;
            render_grid.setEmptyCell(x, y);
        }

        // Проверяем заполнена ли ячейка
        public boolean isFull(int x, int y) {
            return !grid[x][y].isEmpty();
        }

        public boolean lineIsFull(int y) {
            for (int x = 0; x < cols; x++) {
                if (!isFull(x, y)) {
                    System.out.println(x + ", " + y);
                    System.out.println("line is not full");
                    return false;
                }
            }
            System.out.println("line is full");
            return true;
        }

        public void setEmptyLine(int y) {
            for (int x = 0; x < cols; x++) {
                setEmptyCell(x, y);
            }
        }

        public void replaceLine(int y, int newY) {
            setEmptyLine(newY);
            for (int x = 0; x < cols; x++) {
                if (!grid[x][y].isEmpty()) {
                    setFillCell(x, newY, grid[x][y]);
                }
            }
            setEmptyLine(y);
        }
    }
