package com.example.tetris;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderCell {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private final int rows;
    private final int cols;
    private final int cell;

    // Конструктор принимает количество строк, столбцов и размер клетки + создаёт canvas ужного размера
    // (позже для интерфейса изменим)
    public RenderCell(int rows, int cols, int cell) {
        this.rows = rows;
        this.cols = cols;
        this.cell = cell;
        this.canvas = new Canvas(cols * cell, rows * cell);
        this.gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    // Заливка фона
    public void render() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawGrid();
    }

    // Метод для рисования сетки между клетками
    private void drawGrid() {
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);

        for (int x = 0; x <= cols; x++) {
            double px = x * cell;
            gc.strokeLine(px, 0, px, rows * cell);
        }
        for (int y = 0; y <= rows; y++) {
            double py = y * cell;
            gc.strokeLine(0, py, cols * cell, py);
        }
    }

    // Метод для закраски оперделённой клетки на определённых координатах (пока только зелёным)
    public void setFillCell(int c, int r) {
        gc.setFill(Color.GREEN);
        gc.fillRect(c * cell, r * cell, cell, cell);
    }

    // Метод убирает оперделённой клетки на определённых координатах
    // (пока не тестил, засунул пока по фасту для галочки)
    public void setEmptyCell(int r, int c) {
        gc.clearRect(r * cell, c * cell, cell, cell);
    }
}