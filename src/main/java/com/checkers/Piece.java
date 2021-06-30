package com.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static com.checkers.Board.tileSize;

public class Piece extends StackPane {

    private PieceType type;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public Piece(final PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        Ellipse bg = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(tileSize * 0.03);

        bg.setTranslateX((tileSize - tileSize * 0.3125 * 2) / 2);
        bg.setTranslateY((tileSize - tileSize * 0.26 * 2) / 2 + tileSize * 0.07);

        Ellipse ellipse = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        ellipse.setFill(type == PieceType.redDown ? Color.valueOf("c40003") : Color.valueOf("fff9f4"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(tileSize * 0.03);

        ellipse.setTranslateX((tileSize - tileSize * 0.3125 * 2) / 2);
        ellipse.setTranslateY((tileSize - tileSize * 0.26 * 2) / 2);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });

        getChildren().addAll(bg, ellipse);

    }

    public void move(int x, int y){
        oldX = x * tileSize;
        oldY = y * tileSize;
        relocate(oldX, oldY);
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type){
        this.type = type;
    }

    public void abortMove(){
        relocate(oldX, oldY);
    }
}
