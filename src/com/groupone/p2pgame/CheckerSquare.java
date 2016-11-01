package com.groupone.p2pgame;

public class CheckerSquare {
    private int index;
    private Piece piece;

    public CheckerSquare (int index) {
	super();
	this.index = index;
	this.piece = new Piece();
   }

    public int getX () {
	return this.index % 8;
    }

    public int getY () {
	return this.index / 8;
    }

    public int getIndex () {
	return this.index;
    }

    public Piece getPiece () {
	return this.piece;
    }

    public void setPiece(Piece piece) {
	this.piece = piece;
    }
}
