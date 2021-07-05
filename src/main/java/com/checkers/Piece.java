package com.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import static com.checkers.Board.tileSize;

public class Piece extends StackPane {

    private PieceType type;
    private double mouseX, mouseY;
    private double oldX, oldY;
    private Ellipse ellipse;
    private Ellipse bg;
    private Color color;

    public Piece(final PieceType type, Color color, int x, int y) {
        this.type = type;
        this.color = color;

        move(x, y);

        bg = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        bg.setFill(Color.BLACK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(tileSize * 0.03);

        bg.setTranslateX((tileSize - tileSize * 0.3125 * 2) / 2);
        bg.setTranslateY((tileSize - tileSize * 0.26 * 2) / 2 + tileSize * 0.07);

        ellipse = new Ellipse(tileSize * 0.3125, tileSize * 0.26);
        ellipse.setFill(color);
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

    public Paint getColor() {
        return color;
    }

    public void setQueen(Piece piece){
        Text text = new Text("Q");
        text.setFont(Font.font("Lato", FontWeight.BOLD, 22));
        text.setTranslateX(18);
        text.setTranslateY(20);

        piece.getChildren().add(text);

        if (piece.getColor().equals(Color.valueOf("fff9f4")))
            piece.setType(PieceType.queenWhite);
        else
            piece.setType(PieceType.queenRed);
    }
}
