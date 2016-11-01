package com.groupone.p2pgame;

/**
   PieceType stores the type of the piece that is being used. This is
   very important because each piece type has different behaviors.
   @see CheckerBoardState
 */
public enum PieceType {
    EMPTY, // the default
    PAWN, // the starting for each player's pieces
    KING, // obtained once reached the top row for player two or the
	  // bottom row for player one
    // more?
}
