package com.groupone.p2pgame;

public class Piece {
    private PieceType type;
    private Player player;

    public Piece() {
	this(PieceType.EMPTY, Player.NONE);
    }

    public Piece(PieceType type, Player player) {
	this.type = type;
	this.player = player;
    }

    public PieceType getType() {
	return this.type;
    }

    public void king() {
	this.type = PieceType.KING;
    }

    public Player getPlayer() {
	return this.player;
    }

    public boolean matches(Piece b) {
	return this.type == b.type && this.player == b.player;
    }
}
