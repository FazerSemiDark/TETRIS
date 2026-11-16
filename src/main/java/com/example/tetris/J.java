package com.example.tetris;

import javafx.scene.paint.Color;

public class J extends Figure {
    J() {
        colour = Color.BLUE;
        indexList = new int[][]{{1,0}, {1,1}, {1,2}, {0,0}};
        IndexRotatMatr = new int[][] {
                {2,0}, {1,0}, {0,0},
                {2,1}, {1,1}, {0,1},
                {2,2}, {1,2}, {0,2}};
        MatrSize = 3;
    }

}
