package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/** abstract class to represent a move */
public abstract class Move {

    final Board board;
    final Piece movePiece;
    final int destinationCoordinate;


    private Move(final Board board,
                final Piece movePiece,
                final int destinationCoordinate) {
        this.board = board;
        this.movePiece = movePiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    /** Normal move on an unoccupied tile */
    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                         final Piece movePiece,
                         final int destinationCoordinate) {
            super(board, movePiece, destinationCoordinate);
        }
    }

    /** Attacking move to capture a piece */
    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board,
                          final Piece movePiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, movePiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

}

