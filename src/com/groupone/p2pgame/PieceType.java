package com.groupone.p2pgame;

/**
   PieceType stores the type of the piece that is being used. This is
   very important because each piece type has different behaviors.
   @see CheckerBoardState
 */
public enum PieceType {
    /**
       Default empty value.
     */
    EMPTY,

    /**
       The starting piece for each player's pieces.
     */
    PAWN,

    /**
       Obtained once reached the top row for player two or the bottom
       row for player one.
    */
    KING,

    // more?
}
