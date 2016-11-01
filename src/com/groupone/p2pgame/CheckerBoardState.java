package com.groupone.p2pgame;

import java.util.List;
import java.util.ArrayList;

public class CheckerBoardState extends Board {
    private CheckerSquare[] squares;

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
        for (CheckerSquare square : squares) {
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

    public int[] getPlayerTwoPieceLocationInts() {
        return this.getPieceLocationsInts(new Piece(PieceType.PAWN, Player.TWO));
    }
}
