package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private  final static int[] CANDIDATE_MOVE_COORDINATES = {8};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
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
        }
        return legalMoves;
    }
}
