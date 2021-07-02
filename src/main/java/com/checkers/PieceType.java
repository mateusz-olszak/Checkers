package com.checkers;

public enum PieceType {
    UP(-1),
    LEVEL(0),
    DOWN(1),
    queenUP(-1),
    queenDown(1);

    int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
