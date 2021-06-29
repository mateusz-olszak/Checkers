package com.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static com.checkers.Board.tileSize;

public class Tile extends Rectangle {

    private Piece piece;

    public Tile(boolean light, int x, int y) {
        setWidth(tileSize);
        setHeight(tileSize);

        relocate(x * tileSize, y * tileSize);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece(){
        return piece != null;
    }
}
