package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;

public class CheckerBoardAlternate extends JPanel implements MouseListener
{

        private JFrame frame;
        private JLayeredPane gameBoard;
        private Container gameBoardBackground;
        private Container gameBoardWithPieces;


        private CheckerBoardSpace[] boardSpaces;
        private GamePiece[] drawnPieces;

        /**
           Start a new game board.
        */
        public CheckerBoardAlternate() {
		this(CheckerBoardState.getStartingBoard());
        }

        /**
           Setup a checkerboard from player one and player two piece locations
        */
        public CheckerBoardAlternate(CheckerBoardState state)
        {

                super();

                /******************************** Display Characteristics ********************************/

                this.frame = new JFrame(); // for the first initializeSettings call
                this.initializeSettings();

                /********************************  ********************************/








                /******************************** Initializing Member Variables ********************************/

                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24]; // all of player one's pieces will be placed in this array before player two's pieces

                /********************************  ********************************/








                /******************************** Initial Board Spaces ********************************/

                int color = 0;
                int x = 0; // row number
                int y = 0; // column number

                // creates CheckerBoardSpace objects
                for(int A = 0 ; A < 64 ; A++)
                {

                        boardSpaces[A] = new CheckerBoardSpace(color, A, x, y);

                        if(y == 7) // when in last column
                        {
                                x++; // increment row
                                y = 0;
                                color = (color % 2); // makes sure that the next row begins with an alternating color
                        }
                        else
                        {
                                y++;
                                color++; // alternating the colors for the CheckerBoardSpace constructor
                        }

                }

                /****************************************************************/









                this.drawGameBoard(state); // draws the board at its initial state


                /****************************************************************/




                frame.setVisible(true);

        }



        //MOUSE locations THIS DOESNT DO ANYTHING THE REAL CODE IS FAR BELOW
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
        }
        
        public void initializeSettings()
        {

                this.frame.dispose();
                this.frame = new JFrame();
                this.frame.setSize(645, 675);
                this.frame.setResizable(false);
                this.frame.setTitle("CheckerBoardAlternate");
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                this.gameBoard = new JLayeredPane();
                this.gameBoard = frame.getLayeredPane();
                this.gameBoard.setPreferredSize(new Dimension(640, 640));


                this.gameBoardWithPieces = new Container();
                this.gameBoardWithPieces.setSize(640, 640);
                this.gameBoardWithPieces.setMaximumSize( new Dimension(640, 640) );
                this.gameBoardWithPieces.setLayout( new GridLayout(8,8) );
                this.gameBoardWithPieces.setBackground(Color.BLACK);


        }








        // JavaDoc
        public void drawGameBoard(CheckerBoardState state) // use for updating display of game board
        {


                /******************************** Adding Pieces to Board ********************************/

		CheckerSquare[] squares = state.getSquares();

                // creates the 24 game pieces --> player one's pieces are the first 12, and player two's pieces are second
		int n = 0;
                for( int i = 0 ; i < squares.length ; i++ )
                {

			if (squares[i].getPiece().getType() != PieceType.EMPTY) {
				if (squares[i].getPiece().getPlayer() == Player.ONE) {
					drawnPieces[n++] = new GamePiece(Color.BLUE, squares[i].getIndex(), squares[i].getX(), squares[i].getY());
				} else if (squares[i].getPiece().getPlayer() == Player.TWO) {
					drawnPieces[n++] = new GamePiece(Color.GRAY, squares[i].getIndex(), squares[i].getX(), squares[i].getY());
				}
			}


                }


                // this.initializeSettings();

                JPanel currentPiece;

                CheckerBoardSpace currentBoardSpace;
                JLabel currentPieceNumber;

                boolean placeFirst = true;
                boolean placeSecond = true;

                int curLocation = 0;
                int curPieceIndexOne = 0; // for player one
                int curPieceIndexTwo = 0; // for player two

		int[] playerOnePiecesLocations = state.getPlayerOnePieceLocationsInts();
		int playerOnePiecesLeft = playerOnePiecesLocations.length; 
		int[] playerTwoPiecesLocations = state.getPlayerTwoPieceLocationsInts();
		int playerTwoPiecesLeft = playerTwoPiecesLocations.length;

                while( curLocation < 64 ) // cycles through full board
                {

                        currentBoardSpace = boardSpaces[curLocation];


                        if( placeFirst && curLocation == playerOnePiecesLocations[curPieceIndexOne] ) // if a one of player one's pieces should be placed here
                        {
                                        // selects one of player one's pieces for adding it to the game display
                                currentPiece = drawnPieces[curPieceIndexOne];
                                currentPiece.setBackground(currentBoardSpace.spaceColor);
                                gameBoardWithPieces.add(currentPiece);
                                curPieceIndexOne++;

                                if(curPieceIndexOne == playerOnePiecesLeft)
                                {
                                        placeFirst = false; // all of player one's pieces have been placed
                                }
                        }

                        else if( placeSecond && curLocation == playerTwoPiecesLocations[curPieceIndexTwo] ) // if one of player two's pieces should be placed here
                        {
                                        // selects one of player two's pieces for adding it to the display
                                currentPiece = drawnPieces[curPieceIndexTwo+playerOnePiecesLeft]; // index is calculated this way due to structure of drawnPieces array
                                currentPiece.setBackground(currentBoardSpace.spaceColor);
                                gameBoardWithPieces.add(currentPiece);
                                curPieceIndexTwo++;

                                if(curPieceIndexTwo == playerTwoPiecesLeft)
                                {
                                        placeSecond = false; // all of player two's pieces have been placed
                                }
                        }


                        else
                        {
                                gameBoardWithPieces.add(currentBoardSpace); // the current space is empty
                        }
                        curLocation++; // go to next square
                }

                gameBoard.add(gameBoardWithPieces, 0);
                this.frame.setVisible(true);
        }





        // public void removePiece(int whichPlayer, int locationToRemoveFrom)     // locationToRemoveFrom = 0 to 63
        // {
        //                 // array of game pieces and  that should remain after the removal
        //         int B = 0;

        //         if(whichPlayer == 1)
        //         {

        //                 int[] tempOneLocations = new int[ this.playerOnePiecesLocations.length - 1 ];
        //                 this.playerOnePiecesLeft--;

        //                 B = 0;
        //                 for(int A = 0 ; A < this.playerOnePiecesLocations.length ; A++)
        //                 {
        //                         if( this.drawnPieces[A].gameBoardIndex != locationToRemoveFrom )
        //                         {
        //                                 tempOneLocations[B] = this.playerOnePiecesLocations[A];
        //                                 B++;
        //                         }
        //                 }
        //                 this.playerOnePiecesLocations = new int [ tempOneLocations.length - 1 ];
        //                 this.playerOnePiecesLocations = tempOneLocations;

        //         }

        //         else
        //         {
        //                 int[] tempTwoLocations = new int[ this.playerTwoPiecesLocations.length - 1 ];
        //                 this.playerTwoPiecesLeft--;


        //                 B = 0;
        //                 for(int A = 0 ; A < this.playerTwoPiecesLocations.length ; A++)
        //                 {
        //                         if( this.drawnPieces[ A + this.playerOnePiecesLeft ].gameBoardIndex != locationToRemoveFrom )
        //                         {
        //                                 tempTwoLocations[B] = this.playerTwoPiecesLocations[A];
        //                                 B++;
        //                         }
        //                 }
        //                 this.playerTwoPiecesLocations = new int [ tempTwoLocations.length - 1 ];
        //                 this.playerTwoPiecesLocations = tempTwoLocations;
        //         }

        //         GamePiece[] tempPieces = new GamePiece[ this.drawnPieces.length - 1 ];
        //         B = 0;

        //         for( int A = 0 ; A < this.drawnPieces.length ; A++ )
        //         {
        //                 if( this.drawnPieces[A].gameBoardIndex != locationToRemoveFrom )
        //                 {
        //                         tempPieces[B] = this.drawnPieces[A];
        //                         B++;
        //                 }
        //         }
        //         this.drawnPieces = new GamePiece[ tempPieces.length ];
        //         this.drawnPieces = tempPieces;

        // }






        public static void main(String[] args)
        {
                CheckerBoardAlternate testBoard = new CheckerBoardAlternate();
                /*
                testBoard.removePiece(1, 3);
                testBoard.removePiece(1, 5);
                testBoard.removePiece(2, 60);
                testBoard.removePiece(2, 44);

                testBoard.drawGameBoard();
                */
        }
}
