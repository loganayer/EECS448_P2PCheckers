package com.groupone.p2pgame;

import java.util.List;
import java.util.ArrayList;

public class CheckerRules {
    public CheckerRules () {
    }

    /**
       Is the move a valid move for this board?
     */
    public boolean isValidMove(CheckerBoardState board, CheckerMove move) {
        // Board newBoard = (Board) board.clone();

        // Make sure the end spot is empty
        if (move.getEnd().getPiece().getType() != PieceType.EMPTY) {
            return false;
        }

        int dx = move.getEnd().getX() - move.getStart().getX();
        int dy = move.getEnd().getY() - move.getStart().getY();

        // check directions
        if (dy >= 1 && move.getStart().getPiece().getPlayer() == Player.TWO) { // player two moves up
            // okay
        } else if (dy <= -1 && move.getStart().getPiece().getPlayer() == Player.ONE) { // player one moves up
            // okay
        } else if (move.getStart().getPiece().getType() == PieceType.KING) {
            // okay
        } else {
            return false;
        }

        // check diagonal
        if (move.isSingleJump()) { // single jump
            // okay
        } else if (move.isDoubleJump()) { // double jump
            // okay
        } else {
            return false;
        }

        return true;
    }

    public List<CheckerMove> getValidMoves(CheckerBoardState board, CheckerSquare square1) {
        List<CheckerMove> moves = new ArrayList<CheckerMove>();
        for (CheckerSquare square2 : board.getSquares()) {
            CheckerMove move = new CheckerMove(square1, square2);
            if (isValidMove(board, move)) {
                moves.add(move);
            }
        }
        return moves;
    }
}
