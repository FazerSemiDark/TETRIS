package com.oop.consoletetris;

import com.oop.tetris.CellState;
import com.oop.tetris.RenderGridAbstr;

import java.util.Arrays;

public class ConsoleRenderGrid extends RenderGridAbstr {
    private final int ROWS;
    private final int COLS;
    private final int CELL;
    private CellState colourMatrix[][];//матрица цветов клеток
    public ConsoleRenderGrid(){
        this(20,10,1);
    }
    public ConsoleRenderGrid(int rows,int cols,int cell){
        this.ROWS = rows;
        this.COLS = cols;
        this.CELL = cell;
        this.colourMatrix = new CellState[ROWS][COLS];
        for (CellState[] row : colourMatrix) {
            Arrays.fill(row, CellState.EMPTY);
        }
    }
    @Override
    protected void render() {//отрисовка поля
        for (int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                switch (colourMatrix[i][j]) {//определяет цвет клетки и рисует её
                    case RED:
                        System.out.print("\u001B[1;41m"+"\u001B[1;31m"+1+"\u001B[0m");
                        break;
                    case ORANGE:
                        System.out.print("\u001B[1;47m"+"\u001B[1;37m"+1+"\u001B[0m");
                        break;
                    case YELLOW:
                        System.out.print("\u001B[1;43m"+"\u001B[1;33m"+1+"\u001B[0m");
                        break;
                    case GREEN:
                        System.out.print("\u001B[1;42m"+"\u001B[1;32m"+1+"\u001B[0m");
                        break;
                    case LIGHT_BLUE:
                        System.out.print("\u001B[1;46m"+"\u001B[1;36m"+1+"\u001B[0m");
                        break;
                    case BLUE:
                        System.out.print("\u001B[1;44m"+"\u001B[1;34m"+1+"\u001B[0m");
                        break;
                    case VIOLET:
                        System.out.print("\u001B[1;45m"+"\u001B[1;35m"+1+"\u001B[0m");
                        break;
                    default:
                        System.out.print(0);
                        break;
                }
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    };
    @Override
    protected void setFillCell(int c, int r, CellState color) {//помечает клетку как цветную
        colourMatrix[r][c] = color;
    };
    @Override
    protected void setEmptyCell(int r, int c) {//очищает клетку
        colourMatrix[c][r] = CellState.EMPTY;

    };
}
