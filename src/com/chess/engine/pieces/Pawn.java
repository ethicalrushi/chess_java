package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    private  final static int[] CANDIDATE_MOVE_COORDINATES = {7, 8, 9, 16};

    public Pawn(Alliance pieceAlliance, int piecePosition) {

        super(PieceType.PAWN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {

            int candidateDestinationPosition = this.piecePosition + this.getPieceAlliance().getDirection()*currentCandidateOffset;

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationPosition)) {
                continue;
            }

            /** Simple one move ahead for empty tile */
            if(currentCandidateOffset==8 && !board.getTile(candidateDestinationPosition).isTileOccupied()) {
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationPosition));
            }
            else if(currentCandidateOffset==16 && this.isFirstMove() /** Jump move */
                    &&(BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()
                    || BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {

                final int behindCandidateDestinationCoordinate = this.piecePosition + this.getPieceAlliance().getDirection()*8;

                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !board.getTile(candidateDestinationPosition).isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationPosition));
                }
            }
            else if(currentCandidateOffset==7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {

                if(board.getTile(candidateDestinationPosition).isTileOccupied()) {
                    final Piece pieceAttacked = board.getTile(candidateDestinationPosition).getPiece();
                    if(this.pieceAlliance!=pieceAttacked.getPieceAlliance()) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationPosition, pieceAttacked));
                    }
                }

            }
            else if(currentCandidateOffset==9&&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()))) {

                if(board.getTile(candidateDestinationPosition).isTileOccupied()) {
                    final Piece pieceAttacked = board.getTile(candidateDestinationPosition).getPiece();
                    if(this.pieceAlliance!=pieceAttacked.getPieceAlliance()) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationPosition, pieceAttacked));
                    }
                }

            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


}
