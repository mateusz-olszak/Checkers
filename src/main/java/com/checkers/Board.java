package com.checkers;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Board {
    private final Tile[][] board = new Tile[8][8];
    private final int row = 8;
    private final int column = 8;
    public static final int tileSize = 100;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Piece killedPiece;
    private boolean whiteTurn = true;
    private boolean redTurn = false;

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
                    piece = placePiece(PieceType.redDown, x, y);
                }

                if (y > 4 && (x + y) % 2 != 0){
                    piece = placePiece(PieceType.whiteUp, x, y);
                }

                if (piece != null){
                    pieceGroup.getChildren().add(piece);
                    tile.setPiece(piece);
                }

            }
        }

        Label computer = new Label(playerTwo.getText());
        computer.setFont(new Font("Lato",20));
        computer.setTranslateX(5);
        computer.setTranslateY(100);

        Label player = new Label(playerOne.getText());
        player.setFont(new Font("Lato", 20));
        player.setTranslateX(5);
        player.setTranslateY(700);

        Pane sidePane = new Pane();
        sidePane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        sidePane.setTranslateX(800);
        sidePane.setTranslateY(0);
        sidePane.getChildren().addAll(computer,player);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(pane,sidePane);

        Scene scene = new Scene(stackPane, 900, 800);
        stage.setScene(scene);
        stage.show();
    }

    private Piece placePiece(PieceType type, int x, int y){
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {

            int newX = centeringPiece(piece.getLayoutX());
            int newY = centeringPiece(piece.getLayoutY());

            MoveType result;

            if (newX < 0 || newY < 0 || newX >= row || newY >= column){
                result = MoveType.NONE;
            }else{
                result = validateMove(piece, newX, newY);
            }

            int oldX = centeringPiece(piece.getOldX());
            int oldY = centeringPiece(piece.getOldY());

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

    private MoveType validateMove(Piece piece, int newX, int newY){

        if (whiteTurn){
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0 && piece.getType() == PieceType.whiteUp)
                return MoveType.NONE;

            int oldX = centeringPiece(piece.getOldX());
            int oldY = centeringPiece(piece.getOldY());

            if (Math.abs(newX - oldX) == 1 && newY - oldY == piece.getType().moveDir && piece.getType() == PieceType.whiteUp){
                whiteTurn = false;
                redTurn = true;
                return MoveType.NORMAL;
            }
            else if (Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * 2){

                boolean forcedKill = forcedKill(piece, newX, oldX, newY, oldY);

                if (forcedKill) {
                    whiteTurn = false;
                    redTurn = true;
                    return MoveType.KILL;
                }
            }
        }

        else if (redTurn){
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0 && piece.getType() == PieceType.redDown)
                return MoveType.NONE;

            int oldX = centeringPiece(piece.getOldX());
            int oldY = centeringPiece(piece.getOldY());

            if (Math.abs(newX - oldX) == 1 && newY - oldY == piece.getType().moveDir && piece.getType() == PieceType.redDown){
                redTurn = false;
                whiteTurn = true;
                return MoveType.NORMAL;
            }
            else if (Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * 2 && piece.getType() == PieceType.redDown){

                boolean forcedKill = forcedKill(piece, newX, oldX, newY, oldY);

                if (forcedKill) {
                    redTurn = false;
                    whiteTurn = true;
                    return MoveType.KILL;
                }
            }
        }




        return MoveType.NONE;
    }

    private int centeringPiece(double pixel){
        return (int) (pixel + tileSize / 2) / tileSize;
    }

    private boolean forcedKill(Piece piece, int newX, int oldX, int newY, int oldY){

        int x1 = oldX + (newX - oldX) /2;
        int y1 = oldY + (newY - oldY) /2;
        System.out.println("herre 1");
        if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
            if (newY > oldY){
                System.out.println("here 2");
                piece.setType(PieceType.whiteDown);
                killedPiece = board[x1][y1].getPiece();
                return true;
            }
            else return true;
        }


        return false;
    }

}
