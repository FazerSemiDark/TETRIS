package com.oop.tetris;

public enum CellState {
    EMPTY,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    LIGHT_BLUE,
    VIOLET;

    CellState() {

    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
