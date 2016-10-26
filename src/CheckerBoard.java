import javax.swing.*;
import java.awt.*;
import java.lang.Math;


public class CheckerBoard extends JPanel
{

        public JFrame frame;
        public CheckerBoardSpace[] boardSpaces;
        public GamePiece[] drawnPieces;

        public Container gameBoard;
        public Container gameBoardBackground;
        public Container gameBoardPieces;


        public int[] redPiecesLocations;
        public int[] blackPiecesLocations;





        public CheckerBoard()
        {

                super();

                frame = new JFrame();
                frame.setSize(640, 640);
                frame.setTitle("CheckerBoard");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                gameBoard = new Container();
                gameBoard = frame.getContentPane();


                gameBoardBackground = new Container();
                gameBoardBackground.setLayout( new GridLayout(8,8) );


                gameBoardPieces = new Container();
                gameBoardPieces.setLayout( new GridLayout(8,8) );



                /********************************  ********************************/
                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24];







                /******************************** Initial Board Background ********************************/


                int color = 1;
                int x = 0;
                int y = 0;

                // creates CheckerBoardSpace objects
                for(int A = 0 ; A < 64 ; A++)
                {

                        boardSpaces[A] = new CheckerBoardSpace(color, A, x, y);

                        if(x % 7 == 0 && x > 0)
                        {
                                color = 0;
                                x = 0;
                                y++;
                        }
                        else
                        {
                                color++;
                                x++;
                        }

                }

                // draws the Checker board
                JPanel currentRectangle;
                Color currentSpaceColor;
                for(int A = 0 ; A < 8 ; A++)
                {

                        if( this.boardSpaces[A].spaceColor == "Red" )
                        {
                                currentSpaceColor = Color.RED;
                        }
                        else
                        {
                                currentSpaceColor = Color.BLACK;
                        }
                        for(int B = 0 ; B < 8 ; B++)
                        {
                                currentRectangle = new JPanel();

                                if(currentSpaceColor == Color.RED)
                                {
                                        currentSpaceColor = Color.BLACK;
                                }
                                else
                                {
                                        currentSpaceColor = Color.RED;
                                }

                                currentRectangle.setBackground(currentSpaceColor);
                                gameBoardBackground.add(currentRectangle);
                        }

                }
                gameBoard.add(gameBoardBackground);
                /****************************************************************/







                /******************************** Initial Piece Locations ********************************/


                redPiecesLocations = new int[] {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23}; // original locations for pieces in top half of board
                blackPiecesLocations = new int[] {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62}; // original locations for pieces in bottom half of board

                int curX; // for game piece construction
                int curY; // for game piece construction



                // places pieces initial positions
                for( int A = 0 ; A < 24 ; A++ )
                {

                        if(A < 12)
                        {
                                curX = ( redPiecesLocations[A] % 8 );
                                curY = ( redPiecesLocations[A] / 8 );
                                drawnPieces[A] = new GamePiece(Color.BLUE, redPiecesLocations[A], curX, curY);
                        }
                        else
                        {
                                curX = ( blackPiecesLocations[A-12] / 8 );
                                curY = ( blackPiecesLocations[A-12] % 8 );
                                drawnPieces[A] = new GamePiece(Color.GREEN, blackPiecesLocations[A-12], curX, curY);
                        }


                }






                /******************************** Adding Pieces to Board ********************************/

                GamePiece currentPiece;
                for( int A = 0 ; A < 24 ; A++ )
                {
                        currentPiece = drawnPieces[A];
                        gameBoardPieces.add(currentPiece, currentPiece.xBoardLocation, currentPiece.yBoardLocation); // adds the piece only to the appropriate place*/
                }

                //gameBoard.add(gameBoardPieces);

                /****************************************************************/



                //gameBoard.setComponentZOrder(gameBoardBackground, 0);
                //gameBoard.setComponentZOrder(gameBoardPieces, 1);




                frame.setVisible(true);

        }










        public static void main(String[] args)
        {

                CheckerBoard testBoard = new CheckerBoard();

        }

}
