package com.groupone.p2pgame;

import java.util.List;
import java.util.ArrayList;

public class CheckerBoardState {
    private CheckerSquare[] squares;

    public static CheckerBoardState getStartingBoard() {
        // original locations for pieces in top half of board
        // original locations for pieces in bottom half of board
        int[] playerOneLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23};
        int[] playerTwoLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};

        CheckerBoardState state = new CheckerBoardState();
        for (int index : playerOneLocations) {
            state.addPieceAtIndex(new Piece(PieceType.PAWN, Player.ONE), index);
        }
        for (int index : playerTwoLocations) {
            state.addPieceAtIndex(new Piece(PieceType.PAWN, Player.TWO), index);
        }
        return state;
    }

    public CheckerBoardState () {
        this.squares = new CheckerSquare[64];
        for (int index = 0; index < this.squares.length; index++) {
            this.squares[index] = new CheckerSquare(index);
        }
    }

    public CheckerSquare[] getSquares() {
        return this.squares;
    }

    public CheckerSquare getSquare(int index) {
        return this.squares[index];
    }

    public void addPieceAtIndex(Piece piece, int index) {
        this.squares[index].setPiece(piece);
    }

    public List<Integer> getPieceLocations(Piece piece) {
        List result = new ArrayList<Integer>();
        for (CheckerSquare square : this.squares) {
            if (square.getPiece().matches(piece)) {
                result.add(square.getIndex());
            }
        }
        return result;
    }

    // based off of http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array
    public int[] getPieceLocationsInts(Piece piece) {
        List<Integer> result = this.getPieceLocations(piece);
        int[] ret = new int [result.size()];
        for (int i = 0 ; i < ret.length; i++) {
            ret[i] = result.get(i).intValue();
        }
        return ret;
    }

    public int[] getPlayerOnePieceLocationsInts() {
        return this.getPieceLocationsInts(new Piece(PieceType.PAWN, Player.ONE));
    }

    public int[] getPlayerTwoPieceLocationsInts() {
        return this.getPieceLocationsInts(new Piece(PieceType.PAWN, Player.TWO));
    }

    /**
       Is the move a valid move for this board?
     */
    public boolean isValidMove(CheckerMove move) {
        // Board newBoard = (Board) board.clone();

        if (move.getStart().getIndex() < 0 || move.getStart().getIndex() >= 64
            || move.getEnd().getIndex() < 0 || move.getEnd().getIndex() >= 64) {
            return false;
        }

        // Make sure the end spot is empty
        if (move.getEnd().getPiece().getType() != PieceType.EMPTY) {
            return false;
        }

        int dx = move.getEnd().getX() - move.getStart().getX();
        int dy = move.getEnd().getY() - move.getStart().getY();

        // check directions
        if (dy >= 1 && move.getStart().getPiece().getPlayer() == Player.ONE) { // player two moves up
            // okay
        } else if (dy <= -1 && move.getStart().getPiece().getPlayer() == Player.TWO) { // player one moves up
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

    public CheckerSquare getMiddleSquare(CheckerMove move) {
        int dx = move.getEnd().getX() - move.getStart().getX();
        int dy = move.getEnd().getY() - move.getStart().getY();
        return this.getSquareAtPoint(move.getStart().getX() + dx / 2, move.getStart().getY() + dy / 2);
    }

    public List<CheckerMove> getValidMoves(CheckerSquare square1) {
        List<CheckerMove> moves = new ArrayList<CheckerMove>();
        for (CheckerSquare square2 : getSquares()) {
            CheckerMove move = new CheckerMove(square1, square2);
            if (isValidMove(move)) {
                moves.add(move);
            }
        }
        return moves;
    }

    public List<CheckerMove> getValidDoubleJumps(CheckerSquare square1) {
        List<CheckerMove> moves = new ArrayList<CheckerMove>();
        for (CheckerSquare square2 : getSquares()) {
            CheckerMove move = new CheckerMove(square1, square2);
            if (isValidMove(move) && move.isDoubleJump()) {
                moves.add(move);
            }
        }
        return moves;
    }

    public void executeMove(CheckerMove move) {
        // move piece up to target
        this.squares[move.getEnd().getIndex()].setPiece(move.getStart().getPiece());

        // set square to empty piece
        this.squares[move.getStart().getIndex()].setPiece(new Piece());

        // capture piece on double jump
        if (move.isDoubleJump()) {
            CheckerSquare square = this.getMiddleSquare(move);
            this.squares[square.getIndex()].setPiece(new Piece());
        }

        // king me
        if (move.getStart().getPiece().getPlayer() == Player.ONE &&
            move.getEnd().getY() == 0) {
            move.getStart().getPiece().king();
        } else if (move.getStart().getPiece().getPlayer() == Player.TWO &&
                   move.getEnd().getY() == 7) {
            move.getStart().getPiece().king();
        }
    }
}
