package com.example.tetris;

import javafx.scene.paint.Color;

public class O extends Figure {
    O() {
        colour = Color.YELLOW;
        indexList = new int[][]{{1,0}, {1,1}, {0,0}, {0,1}};
        IndexRotatMatr = new int[][] {
                {0,0}, {1,0}, {2,0},
                {0,1}, {1,1}, {2,1},
                {0,2}, {1,2}, {2,2}};
        MatrSize = 3;
    }

}
