package com.chess.engine.player;

public enum MoveStatus {

    DONE {
        @Override
        boolean isDone() {
            return isDone();
        }
    };

    abstract boolean isDone();
}
