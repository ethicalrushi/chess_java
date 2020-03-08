package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;

/** abstract class to represent a move */
public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;


    private Move(final Board board,
                final Piece movedPiece,
                final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public abstract Board execute();

    /** Normal move on an unoccupied tile */
    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                         final Piece movePiece,
                         final int destinationCoordinate) {
            super(board, movePiece, destinationCoordinate);
        }

        @Override
        public Board execute() {

            final Builder builder = new Builder();

            for(final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if(!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }

            for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            //set the moved piece
            builder.setPiece(null);
            builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
            return null;
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

        @Override
        public Board execute() {
            return null;
        }
    }

}

