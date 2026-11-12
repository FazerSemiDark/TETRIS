package com.example.tetris;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderCell {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private final int ROWS;
    private final int COLS;
    private final int CELL;

    // Конструктор принимает количество строк, столбцов и размер клетки + создаёт canvas ужного размера
    // (позже для интерфейса изменим)
    public RenderCell(int rows, int cols, int cell) {
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
    public void render() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawGrid(); // рисуем сетку
    }

    // Метод для рисования сетки между клетками
    private void drawGrid() {
        gc.setStroke(Color.GRAY);
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
    public void setFillCell(int c, int r, Color color) {
        gc.setFill(color);
        gc.fillRect(c * CELL, r * CELL, CELL, CELL);
    }

    // Метод убирает оперделённой клетки на определённых координатах
    public void setEmptyCell(int r, int c) {
        gc.setFill(Color.BLACK);
        gc.fillRect(r * CELL, c * CELL, CELL, CELL);
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        gc.strokeLine(r*CELL,c*CELL,(r+1)*CELL,c*CELL);
        gc.strokeLine(r*CELL,c*CELL,r*CELL,(c+1)*CELL);
        gc.strokeLine((r+1)*CELL,c*CELL,(r+1)*CELL,(c+1)*CELL);
        gc.strokeLine(r*CELL,(c+1)*CELL,(r+1)*CELL,(c+1)*CELL);
    }
}