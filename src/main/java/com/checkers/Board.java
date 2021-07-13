package com.checkers;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.border.AbstractBorder;


public class Board {
    private final Tile[][] board = new Tile[8][8];
    private final int row = 8;
    private final int column = 8;
    public static final int tileSize = 100;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Piece killedPiece;
    private boolean whiteTurn = true;
    private Color white = Color.valueOf("fff9f4");
    private Color red = Color.valueOf("c40003");
    private Label turnLabel;
    private int forcedX;
    private int forcedY;

    public void printBoard(Stage stage, TextField playerOne, TextField playerTwo){
        Pane pane = new Pane();
        pane.getChildren().addAll(tileGroup, pieceGroup);

        for (int x=0; x<row; x++){
            for (int y=0; y<column; y++){
                Tile tile = new Tile((x+y) % 2 == 0, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(board[x][y]);

                Piece piece = null;

                if (y < 3 && (x + y) % 2 != 0){
                    piece = placePiece(PieceType.DOWN, red, x, y, x+1);
                }

                if (y > 4 && (x + y) % 2 != 0){
                    piece = placePiece(PieceType.UP, white, x, y, x+1);
                }

                if (piece != null){
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                }

            }
        }

        Label computer = new Label(playerOne.getText());
        computer.setFont(new Font("Lato",20));
        computer.setTranslateX(5);
        computer.setTranslateY(100);

        Label player = new Label(playerTwo.getText());
        player.setFont(new Font("Lato", 20));
        player.setTranslateX(5);
        player.setTranslateY(700);

        turnLabel = new Label(whiteTurn ? "white's\nturn" : "red's\nturn");
        turnLabel.setFont(new Font("Lato",20));
        turnLabel.setTranslateX(15);
        turnLabel.setTranslateY(400);

        Pane sidePane = new Pane();
        sidePane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        sidePane.setTranslateX(800);
        sidePane.setTranslateY(0);
        sidePane.getChildren().addAll(computer,turnLabel,player);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(pane,sidePane);

        Scene scene = new Scene(stackPane, 900, 800);
        stage.setScene(scene);
        stage.show();
    }

    private Piece placePiece(PieceType type, Color color,int x, int y, int id){
        Piece piece = new Piece(type, color,x, y, id);

        piece.setOnMouseReleased(e -> {

            int newX = centeringPiece(piece.getLayoutX());
            int newY = centeringPiece(piece.getLayoutY());

            int oldX = centeringPiece(piece.getOldX());
            int oldY = centeringPiece(piece.getOldY());

            MoveType result;

            if (newX < 0 || newY < 0 || newX >= row || newY >= column){
                result = MoveType.NONE;
            }else{
                result = validateMove(piece, newX, newY, oldX, oldY);
            }


            switch (result){
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX,newY);
                    board[oldX][oldY].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
                case KILL:
                    piece.move(newX,newY);
                    board[oldX][oldY].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    board[centeringPiece(killedPiece.getOldX())][centeringPiece(killedPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(killedPiece);
                    break;
            }

        });

        return piece;
    }

    private MoveType validateMove(Piece piece, int newX, int newY, int oldX, int oldY){

        int x1 = oldX + (newX - oldX) /2;
        int y1 = oldY + (newY - oldY) /2;

        if (whiteTurn){
            if (piece.getColor().equals(white) && oldY == 0 || piece.getColor().equals(white) && newY == 0 && oldY == 1 || piece.getColor().equals(white) && newY == 0 && oldY == 2)
                piece.setQueen(piece);

            if (y1 < newY && board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor().equals(red) && piece.getColor().equals(white) && piece.getType() != PieceType.queenWhite)
                piece.setType(PieceType.UP);

            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0){
                return MoveType.NONE;
            }

            if (Math.abs(newX - oldX) == 1 && newY - oldY == piece.getType().moveDir && piece.getType() == PieceType.UP && piece.getColor().equals(white)) {
                if (!forcedKill(red,newX,newY,oldX,oldY)){
                    System.out.println("here normal white");
                    whiteTurn = false;
                    turnLabel.setText("red's\nturn");
                    return MoveType.NORMAL;
                }else{
                    System.out.println("here normal white");
                    whiteTurn = false;
                    turnLabel.setText("red's\nturn");
                    return MoveType.NORMAL;
                }
            }

            if (Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * 2 && piece.getColor().equals(white) || Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * -2 && piece.getColor().equals(white)){
                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    if (forcedKill(red, newX, newY, oldX, oldY)) {
                        killedPiece = board[x1][y1].getPiece();
                        whiteTurn = true;
                        turnLabel.setText("white's\nturn");
                        return MoveType.KILL;
                    }else{
                        killedPiece = board[x1][y1].getPiece();
                        whiteTurn = false;
                        turnLabel.setText("red's\nturn");
                        return MoveType.KILL;
                    }
                }
            }

            //queen:
            if (piece.getType() == PieceType.queenWhite){
                return validateQueen(piece, white, red, piece.getType(),oldX, oldY, newX, newY);
            }
        }

        else {
            if (piece.getColor().equals(red) && oldY == 7 || piece.getColor().equals(red) && newY == 7 && oldY == 6 || piece.getColor().equals(red) && newY == 7 && oldY == 5)
                piece.setQueen(piece);

            if (y1 < newY && board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor().equals(white) && piece.getColor().equals(red))
                piece.setType(PieceType.UP);

            if (piece.getColor().equals(red) && piece.getType() != PieceType.queenRed) {
                piece.setType(PieceType.DOWN);
            }

            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0)
                return MoveType.NONE;

            if (Math.abs(newX - oldX) == 1 && newY - oldY == piece.getType().moveDir && piece.getType() == PieceType.DOWN && piece.getColor().equals(red)) {
                if (!forcedKill(white,newX,newY,oldX,oldY)){
                    System.out.println("here normal red");
                    whiteTurn = true;
                    turnLabel.setText("white's\nturn");
                    return MoveType.NORMAL;
                }
                else{
                    System.out.println("here normal red if true");
                    whiteTurn = true;
                    turnLabel.setText("white's\nturn");
                    return MoveType.NORMAL;
                }
            }
            if (Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * 2 && piece.getColor().equals(red) || Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * -2 && piece.getColor().equals(red)) {

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    if (forcedKill(white,newX,newY,oldX,oldY)){
                        killedPiece = board[x1][y1].getPiece();
                        whiteTurn = false;
                        turnLabel.setText("red's\nturn");
                        return MoveType.KILL;
                    }else{
                        killedPiece = board[x1][y1].getPiece();
                        whiteTurn = true;
                        turnLabel.setText("white's\nturn");
                        return MoveType.KILL;
                    }
                }
            }
        }

        //queen:
        if (piece.getType() == PieceType.queenRed){
            return validateQueen(piece, red, white, piece.getType(),oldX, oldY, newX, newY);
        }
        System.out.println("validate main false");
        return MoveType.NONE;
    }

    private int centeringPiece(double pixel){
        return (int) (pixel + tileSize / 2) / tileSize;
    }

    private boolean forcedKill(Color rivalColor,int newX, int newY, int oldX, int oldY){


        if (newX > 1 && newY > 1 && board[newX-1][newY-1].hasPiece() && board[newX-1][newY-1].getPiece().getColor().equals(rivalColor)){
            if (!board[newX-2][newY-2].hasPiece()){
                forcedX = newX - 2;
                forcedY = newY - 2;
                System.out.println("1 returned true");
                return true;
            }
        }
        if (newX > 1 && newY < 6 && board[newX-1][newY+1].hasPiece() && board[newX-1][newY+1].getPiece().getColor().equals(rivalColor)){
            if (!board[newX-2][newY+2].hasPiece()){
                forcedX = newX - 2;
                forcedY = newY + 2;
                return true;
            }
        }
        if (newY > 1 && newX < 6 && board[newX+1][newY-1].hasPiece() && board[newX+1][newY-1].getPiece().getColor().equals(rivalColor)){
            System.out.println("+-here1");
            if (!board[newX+2][newY-2].hasPiece()){
                System.out.println("+-here2");
                forcedX = newX + 2;
                forcedY = newY - 2;
                return true;
            }
        }
        if (newX < 6 && newY < 6 && board[newX+1][newY+1].hasPiece() && board[newX+1][newY+1].getPiece().getColor().equals(rivalColor)){
            if (!board[newX+2][newY+2].hasPiece()){
                forcedX = newX + 2;
                forcedY = newY + 2;
                return true;
            }
        }

        System.out.println("main false");
        return false;
    }



    private MoveType validateQueen(Piece piece, Color color, Color rivalColor, PieceType type,int oldX, int oldY, int newX, int newY) {
        int i = oldX;
        int j = oldY;
        if (newX < oldX) {
            if (oldX - newX == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type) {
                while (i != newX) {
                    if (board[i][j].hasPiece() && board[i][j].getPiece().getColor().equals(rivalColor)) {
                        killedPiece = board[i][j].getPiece();
                        turnLabel.setText(whiteTurn ? "red's\nturn" : "white's\nturn");
                        return MoveType.KILL;
                    }
                    i--;
                    j++;
                }
            }
            if (newX - oldX == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type) {
                while (i != newX) {
                    if (board[i][j].hasPiece() && board[i][j].getPiece().getColor().equals(rivalColor)) {
                        killedPiece = board[i][j].getPiece();
                        turnLabel.setText(whiteTurn ? "red's\nturn" : "white's\nturn");
                        return MoveType.KILL;
                    }
                    i--;
                    j--;
                }
            }
        } else if (newX > oldX) {
            if (newX - oldX == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type) {
                while (i != newX) {
                    if (board[i][j].hasPiece() && board[i][j].getPiece().getColor().equals(rivalColor)) {
                        killedPiece = board[i][j].getPiece();
                        turnLabel.setText(whiteTurn ? "red's\nturn" : "white's\nturn");
                        return MoveType.KILL;
                    }
                    i++;
                    j++;
                }
            }
            if (oldX - newX == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type) {
                while (i != newX) {
                    if (board[i][j].hasPiece() && board[i][j].getPiece().getColor().equals(rivalColor)) {
                        System.out.println("Queen here");
                        killedPiece = board[i][j].getPiece();
                        turnLabel.setText(whiteTurn ? "red's\nturn" : "white's\nturn");
                        return MoveType.KILL;
                    }
                    i++;
                    j--;
                }
            }
        }

        if (oldX - newX == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type ||
            Math.abs(newX - oldX) == 1 * (newY - oldY) && piece.getColor().equals(color) && piece.getType() == type ||
            oldX - newX == 1 * (oldY - newY) && piece.getColor().equals(color) && piece.getType() == type
        ) {
            System.out.println("queen normal here");
            if (!forcedKill(rivalColor,newX, newY, oldX, oldY)){
                System.out.println("Queen here1");
                if (color == white){
                    whiteTurn = true;
                    turnLabel.setText("white's\nturn");
                }
                else{
                    whiteTurn = false;
                    turnLabel.setText("red's\nturn");
                }
            }
            else{
                if (color.equals(white)){
                    whiteTurn = false;
                    turnLabel.setText("red's\nturn");
                }
                else{
                    whiteTurn = true;
                    turnLabel.setText("white's\nturn");
                }
            }
            return MoveType.NORMAL;
        }

        return MoveType.NONE;
    }
}
