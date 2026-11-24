package com.oop.javafx;

import com.oop.tetris.CellState;
import com.oop.tetris.RenderGridAbstr;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderGrid extends RenderGridAbstr {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private final int ROWS;
    private final int COLS;
    private final int CELL;

    private final Color BACKGROUND_COLOR = new Color(0, 0, 0, 1);
    private final Color GRID_COLOR = new Color(0.5, 0.5, 0.5, 1);

    public RenderGrid(){
        this(20,10,30);
    }
    // Конструктор принимает количество строк, столбцов и размер клетки + создаёт canvas ужного размера
    // (позже для интерфейса изменим)
    public RenderGrid(int rows, int cols, int cell) {
        this.ROWS = rows;
        this.COLS = cols;
        this.CELL = cell;
        this.canvas = new Canvas(COLS * CELL, ROWS * CELL);
        this.gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    // Заливка фона
    @Override
    protected void render() {
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawGrid(); // рисуем сетку
    }

    // Метод для рисования сетки между клетками
    private void drawGrid() {
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);

        for (int x = 0; x <= COLS; x++) {
            double px = x * CELL;
            gc.strokeLine(px, 0, px, ROWS * CELL);
        }
        for (int y = 0; y <= ROWS; y++) {
            double py = y * CELL;
            gc.strokeLine(0, py, COLS * CELL, py);
        }
    }

    // Метод для закраски оперделённой клетки на определённых координатах (пока только зелёным)
    @Override
    protected void setFillCell(int c, int r, CellState color) {
        switch (color) {
            case RED:
                gc.setFill(Color.RED);
                break;
            case ORANGE:
                gc.setFill(Color.ORANGE);
                break;
            case YELLOW:
                gc.setFill(Color.YELLOW);
                break;
            case GREEN:
                gc.setFill(Color.GREEN);
                break;
            case LIGHT_BLUE:
                gc.setFill(Color.LIGHTBLUE);
                break;
            case BLUE:
                gc.setFill(Color.BLUE);
                break;
            case VIOLET:
                gc.setFill(Color.VIOLET);
                break;
            default:
                gc.setFill(BACKGROUND_COLOR);
                break;
        }

        gc.fillRect(c * CELL, r * CELL, CELL, CELL);
    }

    // Метод убирает оперделённой клетки на определённых координатах
    @Override
    protected void setEmptyCell(int r, int c) {

        // ПЕРДЕЛАТЬ!

        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(r * CELL, c * CELL, CELL, CELL);
        gc.setStroke(BACKGROUND_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,c*CELL,(r+1)*CELL,c*CELL);
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,c*CELL,(r+1)*CELL,c*CELL);

        gc.setStroke(BACKGROUND_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,c*CELL,r*CELL,(c+1)*CELL);
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,c*CELL,r*CELL,(c+1)*CELL);

        gc.setStroke(BACKGROUND_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine((r+1)*CELL,c*CELL,(r+1)*CELL,(c+1)*CELL);
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine((r+1)*CELL,c*CELL,(r+1)*CELL,(c+1)*CELL);

        gc.setStroke(BACKGROUND_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,(c+1)*CELL,(r+1)*CELL,(c+1)*CELL);
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,(c+1)*CELL,(r+1)*CELL,(c+1)*CELL);
    }
}