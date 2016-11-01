package com.groupone.p2pgame;

import java.util.List;
import java.util.ArrayList;

/**
   Checker board state holds all of the data about the checker board displayed in CheckerBoardAlternate.

   Some useful terms:

   A "pawn" is one of the 24 starting pieces. Pawns can only go
   towards the other end of the board.

     for player two:

     X | X | X    X | X | P2  P2| X | X
     X | P2| X -> X | X | X | X | X | X
     X | X | X    X | X | X   X | X | X

     or for player one:

     X | X | X    X | X | X   X | X | X
     X | P1| X -> X | X | X | X | X | X
     X | X | X    P1| X | X   X | X | P1

   A "king" is obtained once a pawn has reached the other side of the
   board. Kings can go any direction.

     X | X | X    K | X | X   X | X | K   X | X | X   X | X | X
     X | K | X -> X | X | X | X | X | X | X | X | X | X | X | X
     X | X | X    X | X | X   X | X | X   K | X | X   X | X | K

   A "single jump" in this context is a move diagonally obeying the
   usual rules of checkers. Each of the examples above is a single
   jump.

   A "double jump" is where the user moves twice diagonally in one
   direction while also passing a piece of the other player. This
   results in the other player's piece being removed from the game.

     X | X | X    X | X | P2
     X | P1| X -> X | X | X
     P2| X | X    X | X | X

*/
public class CheckerBoardState {
    private CheckerSquare[] squares;

    /**
       Get the starting board configuration, where each side has 12
       pieces and they are in the home rows.
       @return The state of the board in the starting configuration.
     */
    public static CheckerBoardState getStartingBoard() {
        // original locations for pieces in top half of board
        int[] playerOneLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23};

        // original locations for pieces in bottom half of board
        int[] playerTwoLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};

        // create a blank board
        CheckerBoardState state = new CheckerBoardState();

        // add pieces for player one
        for (int index : playerOneLocations) {
            state.addPieceAtIndex(new Piece(PieceType.PAWN, Player.ONE), index);
        }

        // add pieces for player two
        for (int index : playerTwoLocations) {
            state.addPieceAtIndex(new Piece(PieceType.PAWN, Player.TWO), index);
        }

        // return the state
        return state;
    }

    /**
       Initialize the checker board with no pieces.
     */
    public CheckerBoardState () {
        this.squares = new CheckerSquare[64];
        for (int index = 0; index < this.squares.length; index++) {
            this.squares[index] = new CheckerSquare(index);
        }
    }

    /**
       Get all 64 squares in one array.
       @return All of the squares
    */
    public CheckerSquare[] getSquares() {
        return this.squares;
    }

    /**
       Get the square at the index.
       @param index The index to use to lookup.
       @return The checker square at that index.
    */
    public CheckerSquare getSquare(int index) {
        return this.squares[index];
    }

    /**
       Get the checker square from the x y coordinates.
       @param x The x axis to find the square in.
       @param y The y axis to find the square in.
       @return The checker square found at that spot.
     */
    public CheckerSquare getSquareAtPoint(int x, int y) {
        return this.getSquare(y * 8 + x);
    }

    /**
       Add the given piece at the index.
       @param piece The piece to add to the board.
       @param index The place to add the piece to the board.
     */
    public void addPieceAtIndex(Piece piece, int index) {
        this.squares[index].setPiece(piece);
    }

    /**
       Get the index of the pieces on the board that match the example piece.
       @param piece The example piece to compare other pieces to.
       @return A list of Integers where each Integer is board index.
    */
    public List<Integer> getPieceLocations(Piece piece) {
        // start empty
        List result = new ArrayList<Integer>();

        // go through each square
        for (CheckerSquare square : this.squares) {
            // check if piece1 matches piece2
            if (square.getPiece().matches(piece)) {
                // add it to the result
                result.add(square.getIndex());
            }
        }

        // return result
        return result;
    }

    /**
       Get the index of the pieces on the board that match the example piece.
       @param piece The example piece to compare other pieces to.
       @return An int[] array where each element is a board index.
    */
    public int[] getPieceLocationsInts(Piece piece) {
        // call above method to start off
        List<Integer> result = this.getPieceLocations(piece);

        // create empty return array
        int[] ret = new int [result.size()];

        // go through for each element in return array
        for (int i = 0 ; i < ret.length; i++) {
            // set value to piece of result from above
            ret[i] = result.get(i).intValue();
        }

        // return the new int array
        return ret;
    }
    // based off of http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to-primitive-int-array

    /**
       Get the player one piece locations. Used as a compatability
       helper for CheckerBoardAlternate.
       @return List of indices of player one.
     */
    public int[] getPlayerOnePieceLocationsInts() {
        return this.getPieceLocationsInts(new Piece(PieceType.PAWN, Player.ONE));
    }

    /**
       Get the player two piece locations. Used as a compatability
       helper for CheckerBoardAlternate.
       @return List of indices of player two.
     */
    public int[] getPlayerTwoPieceLocationsInts() {
        return this.getPieceLocationsInts(new Piece(PieceType.PAWN, Player.TWO));
    }

    /**
       Is the move a valid move for this board?
       @param move The move to check validity of.
       @return Whether it is a valid move
     */
    public boolean isValidMove(CheckerMove move) {
        // Board newBoard = (Board) board.clone();

        // Make sure we are withihn the board's bounds
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

        // check directions, either up or down
        if (dy >= 1 && move.getStart().getPiece().getPlayer() == Player.ONE) { // player two moves up
            // okay
        } else if (dy <= -1 && move.getStart().getPiece().getPlayer() == Player.TWO) { // player one moves down
            // okay
        } else if (move.getStart().getPiece().getType() == PieceType.KING) {
            // okay (kings can go any direction)
        } else {
            return false;
        }

        // check diagonal
        if (move.isSingleJump()) { // single jump
            // okay
        } else if (move.isDoubleJump()) { // double jump

            // find the piece that is jumped over
            CheckerSquare middle = this.getMiddleSquare(move);

            // make sure that piece is actually real
            if (middle.getPiece().getType() == PieceType.EMPTY) {
                return false;
            } else if (move.getStart().getPiece().getPlayer() == middle.getPiece().getPlayer()) { // make sure we aren't jumping over ourself
                return false;
            }

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

    /**
       Get valid moves from a square. See top for more indepth explanation.
       @param square1 The square to start from.
       @return A list of possible moves
    */
    public List<CheckerMove> getValidMoves(CheckerSquare square1) {
        // start empty
        List<CheckerMove> moves = new ArrayList<CheckerMove>();

        // go through each square on this board
        for (CheckerSquare square2 : getSquares()) {
            // create a possible move from it
            CheckerMove move = new CheckerMove(square1, square2);

            // check if that move is valid
            if (isValidMove(move)) {
                moves.add(move);
            }
        }
        return moves;
    }

    /**
       Get valid "double jumps" from a square. See top for more
       indepth explanation.
       @param square1 The square to start from.
       @return A list of possible moves that are "double jumps"
    */
    public List<CheckerMove> getValidDoubleJumps(CheckerSquare square1) {
        // start empty
        List<CheckerMove> moves = new ArrayList<CheckerMove>();

        // go through each square on this board
        for (CheckerSquare square2 : getSquares()) {
            // create a possible move from it
            CheckerMove move = new CheckerMove(square1, square2);

            // check if that move is valid
            // then check if it is a double jump
            if (isValidMove(move) && move.isDoubleJump()) {
                moves.add(move);
            }
        }

        // return result
        return moves;
    }

    /**
       Execute the move on this board.
       @param move The move to run on the board.
    */
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

        // king me if on the last row for p1
        if (move.getEnd().getPiece().getPlayer() == Player.ONE &&
            move.getEnd().getY() == 7) {
            move.getEnd().getPiece().king();
        // king me if on the first row for p2
        } else if (move.getEnd().getPiece().getPlayer() == Player.TWO &&
                   move.getEnd().getY() == 0) {
            move.getEnd().getPiece().king();
        }
    }
}
