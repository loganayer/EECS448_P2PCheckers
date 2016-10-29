package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;


public class CheckerBoardAlternate extends JPanel
{

        public JFrame frame;
        public JLayeredPane lp;
        public CheckerBoardSpace[] boardSpaces;
        public GamePiece[] drawnPieces;

        public Container gameBoard;
        public Container gameBoardBackground;
        public Container gameBoardPieces;


        public int[] redPiecesLocations;
        public int[] blackPiecesLocations;





        public CheckerBoardAlternate()
        {

                super();

                frame = new JFrame("CheckerBoardAlternate");
                frame.setSize(645, 675);
                frame.setTitle("Title");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                lp = new JLayeredPane();
                lp = frame.getLayeredPane();
                lp.setPreferredSize(new Dimension(640, 640));

                gameBoard = new Container();
                gameBoard = frame.getContentPane();


                gameBoardBackground = new Container();
                gameBoardBackground.setSize(640, 640);
                gameBoardBackground.setMaximumSize( new Dimension(640, 640) );
                gameBoardBackground.setLayout( new GridLayout(8,8) );


                gameBoardPieces = new Container();
                gameBoardPieces.setSize(640, 640);
                gameBoardPieces.setMaximumSize( new Dimension(640, 640) );
                gameBoardPieces.setLayout( new GridLayout(8,8) );


                /********************************  ********************************/
                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24];







                /******************************** Initial Board Background ********************************/

                int color = 0;
                int x = 0;
                int y = 0;

                // creates CheckerBoardSpace objects
                for(int A = 0 ; A < 64 ; A++)
                {

                        boardSpaces[A] = new CheckerBoardSpace(color, A, x, y);

                        if(y == 7)
                        {
                                x++;
                                y = 0;
                                color = (color % 2); // makes sure that the next row begins with an alternating color
                        }
                        else
                        {
                                y++;
                                color++;
                        }

                }

                // draws the Checkers board
                CheckerBoardSpace currentRectangle;
                for(int A = 0 ; A < 64 ; A++)
                {
                        currentRectangle = boardSpaces[A];
                        gameBoardBackground.add(currentRectangle);
                }
                //gameBoard.add(gameBoardBackground);






                redPiecesLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23}; // original locations for pieces in top half of board
                blackPiecesLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62}; // original locations for pieces in bottom half of board

                int curX; // for game piece construction
                int curY; // for game piece construction




                for( int A = 0 ; A < 24 ; A++ )
                {

                        if(A < 12)
                        {
                                curX = ( redPiecesLocations[A] % 8 );
                                curY = ( redPiecesLocations[A] / 8 );
                                drawnPieces[A] = new GamePiece(Color.BLUE,0,0,0);
                        }
                        else
                        {
                                curX = ( blackPiecesLocations[A-12] / 8 );
                                curY = ( blackPiecesLocations[A-12] % 8 );
                                drawnPieces[A] = new GamePiece(Color.GREEN, 0,0,0);
                        }


                }






                /******************************** Adding Pieces to Board ********************************/

                JPanel currentPiece;
                boolean placeRed = true;
                int curLocationIndex = 0;
                for( int A = 0 ; A < 64 ; A++ )
                {

                        if( placeRed )
                        {

                                if( A == redPiecesLocations[curLocationIndex] )
                                {
                                        currentPiece = drawnPieces[curLocationIndex];
                                        currentPiece.setBackground(boardSpaces[A].spaceColor);
                                        gameBoardPieces.add(currentPiece); // adds the piece only to the appropriate place
                                        curLocationIndex++;
                                }
                                else
                                {
                                        currentPiece = boardSpaces[A];
                                        gameBoardPieces.add(boardSpaces[A]);
                                }


                                if (curLocationIndex == 12)
                                {
                                        placeRed = false; // all red pieces have been placed
                                        curLocationIndex = 0; // restart for the black pieces
                                }


                        }


                        else if( curLocationIndex < 12 ) // will only run for the black pieces
                        {

                                if( A == blackPiecesLocations[curLocationIndex] )
                                {
                                        currentPiece = drawnPieces[curLocationIndex+12];
                                        currentPiece.setBackground(boardSpaces[A].spaceColor);
                                        gameBoardPieces.add(currentPiece); // adds the piece only to the appropriate place
                                        curLocationIndex++;
                                }
                                else
                                {
                                        gameBoardPieces.add(boardSpaces[A]);
                                }

                        }

                        else
                        {
                                gameBoardPieces.add(boardSpaces[A]);
                        }


                }













                //lp.add(gameBoardBackground, 0);
                lp.add(gameBoardPieces, 1);
                /****************************************************************/



                frame.setVisible(true);

        }










        public static void main(String[] args)
        {

                CheckerBoardAlternate testBoard = new CheckerBoardAlternate();

        }

}
