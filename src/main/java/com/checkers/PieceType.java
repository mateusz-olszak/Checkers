package com.checkers;

public enum PieceType {
    whiteDown(1),
    white(0),
    whiteUp(-1),
    redDown(1),
    red(0),
    redUp(-1);

    int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
