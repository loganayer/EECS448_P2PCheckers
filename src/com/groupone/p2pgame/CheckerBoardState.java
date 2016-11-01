package com.groupone.p2pgame;

import java.util.List;
import java.util.ArrayList;

public class CheckerBoardState {
    private CheckerSquare[] squares;

    public static CheckerBoardState getStartingBoard() {
        // original locations for pieces in top half of board
        // original locations for pieces in bottom half of board
        int[] playerTwoLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23};
        int[] playerOneLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};

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
}
