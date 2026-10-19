package com.example.tetris;



// класс по сути затычка для теста, но в теории можно начать писать в нём логику игры



public class Game {
    public TetrisCell tc;
    public Game(TetrisCell tetcell)
    {
        tc = tetcell;
        setColor();
    }

    private void setColor()
    {
        tc.setFill(2, 5);
    }
}
