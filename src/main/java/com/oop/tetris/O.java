package com.oop.tetris;

import javafx.scene.paint.Color;

public class O extends Figure {
    public O() {
        super(3, // Matrix Size
                Color.YELLOW, // Colour
                new int[][] {{1,0}, {1,1}, {0,0}, {0,1}}, // Index List
                new int[][] { // Index Rotate Matrix
                        {0,0}, {1,0}, {2,0},
                        {0,1}, {1,1}, {2,1},
                        {0,2}, {1,2}, {2,2}},
                false // canRotate
        );
    }
}
