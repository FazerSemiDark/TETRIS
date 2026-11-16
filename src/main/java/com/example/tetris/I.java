package com.example.tetris;

import javafx.scene.paint.Color;

public class I extends Figure {
    I() {
        colour = Color.LIGHTBLUE;
        indexList = new int[][]{{1,0}, {1,1}, {1,2}, {1,3}};
        IndexRotatMatr = new int[][] {
                {3,0}, {2,0}, {1,0}, {0,0},
                {3,1}, {2,1}, {1,1}, {0,1},
                {3,2}, {2,2}, {1,2}, {0,2},
                {3,3}, {2,3}, {1,3}, {0,3},};
        MatrSize = 4;
    }

}
