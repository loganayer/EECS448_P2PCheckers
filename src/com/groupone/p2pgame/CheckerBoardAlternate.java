package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;


public class CheckerBoardAlternate extends JPanel
{

        public JFrame frame;
        public JLayeredPane gameBoard;
        public Container gameBoardBackground;
        public Container gameBoardWithPieces;


        public CheckerBoardSpace[] boardSpaces;
        public GamePiece[] drawnPieces;


        public int[] playerOnePiecesLocations;
        public int[] playerTwoPiecesLocations;

        public int playerOnePiecesLeft;
        public int playerTwoPiecesLeft;





        public CheckerBoardAlternate()
        {

                super();

                /******************************** Display Characteristics ********************************/

                frame = new JFrame();
                frame.setSize(645, 675);
                frame.setTitle("CheckerBoardAlternate");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                gameBoard = new JLayeredPane();
                gameBoard = frame.getLayeredPane();
                gameBoard.setPreferredSize(new Dimension(640, 640));


                gameBoardWithPieces = new Container();
                gameBoardWithPieces.setSize(640, 640);
                gameBoardWithPieces.setMaximumSize( new Dimension(640, 640) );
                gameBoardWithPieces.setLayout( new GridLayout(8,8) );

                /********************************  ********************************/








                /******************************** Initializing Member Variables ********************************/

                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24]; // all of player one's pieces will be placed in this array before player two's pieces

                        // for beginning of game
                playerOnePiecesLeft = 12;
                playerTwoPiecesLeft = 12;

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









                /******************************** Adding Pieces to Board ********************************/

                playerOnePiecesLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23}; // original locations for pieces in top half of board
                playerTwoPiecesLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62}; // original locations for pieces in bottom half of board



                int curX; // row number
                int curY; // column number

                // creates the 24 game pieces --> player one's pieces are the first 12, and player two's pieces are second
                for( int A = 0 ; A < 24 ; A++ )
                {

                        if(A < 12)
                        {
                                curX = ( playerOnePiecesLocations[A] / 8 );
                                curY = ( playerOnePiecesLocations[A] % 8 );
                                drawnPieces[A] = new GamePiece(Color.BLUE, playerOnePiecesLocations[A], curX, curY);
                        }
                        else
                        {
                                curX = ( playerTwoPiecesLocations[A-12] / 8 );
                                curY = ( playerTwoPiecesLocations[A-12] % 8 );
                                drawnPieces[A] = new GamePiece(Color.GREEN, playerTwoPiecesLocations[A-12], curX, curY);
                        }


                }

                //drawGameBoard(); // initial drawing of board

                /****************************************************************/





                frame.setVisible(true);

        }






        // JavaDoc
        public void drawGameBoard() // use for updating display of game board
        {


                gameBoard = new JLayeredPane();
                gameBoard = frame.getLayeredPane();
                gameBoard.setPreferredSize(new Dimension(640, 640));

                JPanel currentPiece;
                CheckerBoardSpace currentBoardSpace;
                JLabel currentPieceNumber;

                boolean placeFirst = true;
                boolean placeSecond = true;

                int curLocation = 0;
                int curPieceIndexOne = 0; // for player one
                int curPieceIndexTwo = 0; // for player two


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


        }





        public void removePiece(int whichPlayer, int locationToRemoveFrom)     // locationToRemoveFrom will deal with drawnPieces array
        {

                        // array of game pieces and  that should remain after the removal
                int B = 0;

                if(whichPlayer == 1)
                {

                        int[] tempOneLocations = new int[ this.playerOnePiecesLocations.length - 1 ];
                        this.playerOnePiecesLeft--;

                        B = 0;
                        for(int A = 0 ; A < this.playerOnePiecesLocations.length ; A++)
                        {
                                if( this.drawnPieces[A].gameBoardIndex != locationToRemoveFrom )
                                {
                                        tempOneLocations[B] = this.playerOnePiecesLocations[A];
                                        B++;
                                }
                        }
                        this.playerOnePiecesLocations = new int [ tempOneLocations.length - 1 ];
                        this.playerOnePiecesLocations = tempOneLocations;

                }



                else
                {

                        int[] tempTwoLocations = new int[ this.playerTwoPiecesLocations.length - 1 ];
                        this.playerTwoPiecesLeft--;

                        B = 0;
                        for(int A = 0 ; A < this.playerTwoPiecesLocations.length ; A++)
                        {
                                if( this.drawnPieces[ A + this.playerOnePiecesLeft ].gameBoardIndex != locationToRemoveFrom )
                                {
                                        tempTwoLocations[B] = this.playerTwoPiecesLocations[A];
                                        B++;
                                }
                        }
                        this.playerTwoPiecesLocations = new int [ tempTwoLocations.length - 1 ];
                        this.playerTwoPiecesLocations = tempTwoLocations;

                }



                GamePiece[] tempPieces = new GamePiece[ this.drawnPieces.length - 1 ];
                B = 0;

                for( int A = 0 ; A < this.drawnPieces.length ; A++ )
                {
                        if( this.drawnPieces[A].gameBoardIndex != locationToRemoveFrom )
                        {
                                tempPieces[B] = this.drawnPieces[A];
                                B++;
                        }
                }
                this.drawnPieces = new GamePiece[ tempPieces.length ];
                this.drawnPieces = tempPieces;





        }








        public static void main(String[] args)
        {

                CheckerBoardAlternate testBoard = new CheckerBoardAlternate();
                testBoard.removePiece(1, 3);
                testBoard.removePiece(1, 5);
                testBoard.removePiece(2, 60);
                testBoard.removePiece(2, 44);
                testBoard.drawGameBoard();

        }

}
