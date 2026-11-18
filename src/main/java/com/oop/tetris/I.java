package com.oop.tetris;

import javafx.scene.paint.Color;

public class I extends Figure {
    public I() {
        super(CellState.LIGHT_BLUE, // Colour
                new int[][] {{1,0}, {1,1}, {1,2}, {1,3}}, // Index List
                new int[][] { // Index Rotate Matrix
                        {3,0}, {2,0}, {1,0}, {0,0},
                        {3,1}, {2,1}, {1,1}, {0,1},
                        {3,2}, {2,2}, {1,2}, {0,2},
                        {3,3}, {2,3}, {1,3}, {0,3},},
                true // canRotate
        );
    }
}
