package com.oop.tetris;

import javafx.scene.paint.Color;

public class T extends Figure {
    public T() {
        super(3, // matrixSize
                Color.VIOLET, // Colour
                new int[][] {{1,0}, {1,1}, {1,2}, {0,1}}, // indexList
                new int[][] { // indexRotateMatrix
                        {2,0}, {1,0}, {0,0},
                        {2,1}, {1,1}, {0,1},
                        {2,2}, {1,2}, {0,2}},
                true // canRotate
        );
    }
}
