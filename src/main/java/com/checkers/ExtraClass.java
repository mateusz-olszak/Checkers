package com.checkers;

public class ExtraClass {
//    private MoveType validateMove(Piece piece, int newX, int newY){
//        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0)
//            return MoveType.NONE;
//
//        int oldX = centeringPiece(piece.getOldX());
//        int oldY = centeringPiece(piece.getOldY());
//
//        if (Math.abs(newX - oldX) == 1 && newY - oldY == piece.getType().moveDir){
//            return MoveType.NORMAL;
//        }else if (Math.abs(newX - oldX) == 2 && newY - oldY == piece.getType().moveDir * 2){
//
//            int x1 = oldX + (newX - oldX) /2;
//            int y1 = oldY + (newY - oldY) /2;
//
//            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
//                killedPiece = board[x1][y1].getPiece();
//                return MoveType.KILL;
//            }
//        }
//
//        else if (Math.abs(newX - oldX) == 4 && newY - oldY == piece.getType().moveDir * 4 || Math.abs(newX - oldX) == 4 && (newY - oldY) + piece.getType().moveDir == piece.getType().moveDir || Math.abs(newY - oldY) == 4 && newX - oldX == piece.getType().moveDir * 0){
//
//            int x1, y1;
//
//            if (newY == oldY) {
//                x1 = oldX + (newX - oldX) / 4;
//                y1 = oldY + (newY - oldY - 4) / 4;
//            }
//            ///
//            else if (newX == oldX){
//                x1 = oldX + (newX - oldX + 4) / 4;
//                y1 = oldY + (newY - oldY) / 4;
//
//                if ((newX + newY+1) > x1) {
//                    x1 = oldX + (newX - oldX - 4) / 4;
//                    y1 = oldY + (newY - oldY) / 4;
//                }
//            }
//            //oldX=2, oldY=5, newX=2, newY=1, x1=3, y1=4, x2=3, y2=2
//            //oldX=4, oldY=5, newX=4, newY=1, x1=3, y1=4, x2=3, y2=2 // case2
//            /////
//            else {
//                x1 = oldX + (newX - oldX) / 4;
//                y1 = oldY + (newY - oldY) / 4;
//            }
//
//            int x2;
//            int y2;
//
//            if (newX - oldX > 0 && oldY != newY){ // diagonally right
//                x2 = x1 + 2;
//                y2 = y1 -2;
//            }
//
//            else if (newX - oldX < 0 && oldY != newY){ // diagonally left
//                x2 = x1 - 2;
//                y2 = y1 - 2;
//            }
//
//            else if (newY == oldY && newX - oldX > 0){ //horizontally right
//                x2 = x1 + 2;
//                y2 = y1;
//            }
//
//            else if (newY == oldY && newX - oldX < 0){//horizontally left
//                x2 = x1 - 2;
//                y2 = y1;
//            }
//            else if (newX == oldX){ // vertically right
//                x2 = x1;
//                y2 = y1 - 2;
//            }
//            else{
//                x2=0;
//                y2=0;
//            }
//
//            System.out.println("x1: " + x1 + " y1: "+ y1);
//            System.out.println("x2: " + x2 + " y2: "+ y2);
//
//            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType() && board[x2][y2].hasPiece() && board[x2][y2].getPiece().getType() != piece.getType()) {
//                killedPiece = board[x1][y1].getPiece();
//                secondKilledPiece = board[x2][y2].getPiece();
//                System.out.println("here");
//                return MoveType.DOUBLEKILL;
//            }
//            else if (newY == oldY && board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType() && board[x2][y2].hasPiece() && board[x2][y2].getPiece().getType() != piece.getType()) {
//                piece.setType(PieceType.white);
//                killedPiece = board[x1][y1].getPiece();
//                secondKilledPiece = board[x2][y2].getPiece();
//                return MoveType.DOUBLEKILL;
//            }
//            else if (newX == oldX) {
//                piece.setType(PieceType.whiteUp);
//                killedPiece = board[x1][y1].getPiece();
//                secondKilledPiece = board[x2][y2].getPiece();
//                return MoveType.DOUBLEKILL;
//            }
//
//        }
//
//
//
//        else if (Math.abs(newX - oldX) == 6 && newY - oldY == piece.getType().moveDir * 6){
//            return MoveType.TRIPLEKILL;
//        }
//
//        return MoveType.NONE;
//    }
}
//        else if (Math.abs(newX - oldX) == 4 && newY - oldY == piece.getType().moveDir * 4 || Math.abs(newX - oldX) == 4 && (newY - oldY) + piece.getType().moveDir == piece.getType().moveDir || Math.abs(newY - oldY) == 4 && newX - oldX == piece.getType().moveDir * 0){
//
//            int x1 = oldX + (newX - oldX) /2;
//            int y1 = oldY + (newY - oldY) /2;
//
//            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
//                killedPiece = board[x1][y1].getPiece();
//                return MoveType.DOUBLEKILL;
//            }
//
//        }
//
//        else if (Math.abs(newX - oldX) == 6 && newY - oldY == piece.getType().moveDir * 6){
//            return MoveType.TRIPLEKILL;
//        }

//            if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor().equals(red)){
//                    System.out.println("white kill queen");
//                    killedPiece = board[x1][y1].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[x1-1][y1+1].hasPiece() && board[x1-1][y1+1].getPiece().getColor().equals(red) && newX < x1-1 && x1 > 1){
//        System.out.println("white kill queen");
//        killedPiece = board[x1-1][y1+1].getPiece();
//        return MoveType.KILL;
//        }
//        if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[x1-2][y1+2].hasPiece() && board[x1-2][y1+2].getPiece().getColor().equals(red) && newX < x1-2 && x1 > 2){
//        System.out.println("white kill queen");
//        killedPiece = board[x1-2][y1+2].getPiece();
//        return MoveType.KILL;
//        }
//        if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[x1+1][y1-1].hasPiece() && board[x1+1][y1-1].getPiece().getColor().equals(red) && newX < x1+1){
//        System.out.println("white kill queen");
//        killedPiece = board[x1+1][y1-1].getPiece();
//        return MoveType.KILL;
//        }


//            if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+1][newY-1].hasPiece() && board[newX+1][newY-1].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill1: " + (newX+1) + " " + (newY-1));
//                    killedPiece = board[newX+1][newY-1].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+2][newY-2].hasPiece() && board[newX+2][newY-2].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill2: " + (newX+2) + " " + (newY-2));
//                    killedPiece = board[newX+2][newY-2].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+3][newY-3].hasPiece() && board[newX+3][newY-3].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill3: " + (newX+3) + " " + (newY-3));
//                    killedPiece = board[newX+3][newY-3].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+4][newY-4].hasPiece() && board[newX+4][newY-4].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill4: " + (newX+4) + " " + (newY-4));
//                    killedPiece = board[newX+4][newY-4].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+5][newY-5].hasPiece() && board[newX+5][newY-5].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill5: " + (newX+5) + " " + (newY-5));
//                    killedPiece = board[newX+5][newY-5].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite && board[newX+6][newY-6].hasPiece() && board[newX+6][newY-6].getPiece().getColor().equals(red)){
//                    System.out.println("white queen kill6: " + (newX+6) + " " + (newY-6));
//                    return MoveType.KILL;
//                    }


/// ####################################
//            if (newY - oldY == piece.getType().moveDir * newY && piece.getColor().equals(white) && piece.getType() == PieceType.queenWhite){
//                    /// move queen right
//                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1][y1].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (board[x1+1][y1-1].hasPiece() && board[x1+1][y1-1].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1+1][y1-1].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (board[x1+2][y1-2].hasPiece() && board[x1+2][y1-2].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1+2][y1-2].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (board[x1-1][y1+1].hasPiece() && board[x1-1][y1+1].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1-1][y1+1].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (board[x1-2][y1+2].hasPiece() && board[x1-2][y1+2].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1-2][y1+2].getPiece();
//                    return MoveType.KILL;
//                    }
//                    if (board[x1-3][y1+3].hasPiece() && board[x1-3][y1+3].getPiece().getColor().equals(red) && oldX - newX > 0){
//                    killedPiece = board[x1-3][y1+3].getPiece();
//                    return MoveType.KILL;
//                    }
//
//                    /// move queen left
//                    if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1][y1].getPiece();
//        return MoveType.KILL;
//        }
//        if (board[x1-1][y1-1].hasPiece() && board[x1-1][y1-1].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1-1][y1-1].getPiece();
//        return MoveType.KILL;
//        }
//        if (board[x1-2][y1-2].hasPiece() && board[x1-2][y1-2].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1-2][y1-2].getPiece();
//        return MoveType.KILL;
//        }
//        if (board[x1+1][y1+1].hasPiece() && board[x1+1][y1+1].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1+1][y1+1].getPiece();
//        return MoveType.KILL;
//        }
//        if (board[x1+2][y1+2].hasPiece() && board[x1+2][y1+2].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1+2][y1+2].getPiece();
//        return MoveType.KILL;
//        }
//        if (board[x1+3][y1+3].hasPiece() && board[x1+3][y1+3].getPiece().getColor().equals(red) && oldX - newX < 0){
//        killedPiece = board[x1+3][y1+3].getPiece();
//        return MoveType.KILL;
//        }
//        }