package com.example.tetris;
import javafx.scene.canvas.Canvas;


// класс по сути затычка для теста, но в теории можно начать писать в нём логику игры



public class Game {
    private final TetrisCell TetrisCellObj = new TetrisCell();
    private final RenderCell RenderCellObj = TetrisCellObj.render_cell;
    public Canvas GetCanvas()
    {
        return TetrisCellObj.render_cell.getCanvas();
    }
    public void render_field(){
        RenderCellObj.render();
    }
    //private void setColor()
    //{
//        tc.setFill(2, 5);
//    }
}
