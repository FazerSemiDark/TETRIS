package com.oop.tetris;

public abstract class RenderGridAbstr {
    protected abstract void render();
    protected abstract void setFillCell(int c, int r, CellState color);
    protected abstract void setEmptyCell(int r, int c);

}
