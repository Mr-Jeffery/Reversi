package model;

import java.awt.*;

public enum ChessPiece {
    BLACK(Color.BLACK), //黑色 1
    WHITE(Color.WHITE), //白色-1
    GRAY(Color.LIGHT_GRAY); //灰色 2

    private final Color color;


    ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
