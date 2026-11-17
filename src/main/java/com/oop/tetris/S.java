package com.oop.tetris;

import javafx.scene.paint.Color;

public class S extends Figure {
    public S() {
        super(Color.GREEN, // Colour
                new int[][] {{1,0}, {1,1}, {0,1}, {0,2}}, // Index List
                new int[][] { // Index Rotate Matrix
                {2,0}, {1,0}, {0,0},
                {2,1}, {1,1}, {0,1},
                {2,2}, {1,2}, {0,2}},
                true // canRotate
        );
    }
}

