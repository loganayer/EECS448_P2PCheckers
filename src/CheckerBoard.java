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

        public boolean boardInitialize;
        public static int[] spaceValue = new int[64]; //-1 == black piece, 0 == empty space, 1 == red piece




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


                ImageIcon bs = new ImageIcon("img/blackSquare.png");
                ImageIcon rs = new ImageIcon("img/redSquare.png");
                ImageIcon rsrp = new ImageIcon("img/redSquareRedPiece.png");
                ImageIcon rsbp = new ImageIcon("img/redSquareBlackPiece.png");

                JLabel labels[] = new JLabel[(64)];
                JPanel currentRectangle;
                CheckerBoardSpace currentSpace;
                if(!boardInitialize)
                //red == {1, 3, 5, 7, 8, 10, 12, 14, 17, 19, 21, 23};
                //black == {40, 42, 44, 46, 49, 51, 53, 55, 56, 58, 60, 62};
                {

                  for(int A = 0 ; A < 64 ; A++)
                  {
                    if(A == 1 || A == 3 ||A == 5 ||A == 7 ||A == 8 ||A == 10 ||A == 12 ||A == 14 ||A == 17 ||A == 19 ||A == 21 ||A == 23)
                    {
                          labels[A] = new JLabel(rsrp);
                          spaceValue[A] = 1;
                    }
                    else if(A == 40 || A == 42 ||A == 44 ||A == 46 ||A == 49 ||A == 51 ||A == 53 ||A == 55 ||A == 56 ||A == 58 ||A == 60 ||A == 62)
                    {
                          labels[A] = new JLabel(rsbp);
                          spaceValue[A] = -1;
                    }
                    else if(A == 24 ||A == 26 ||A == 28 ||A == 30 ||A == 33 ||A == 35 ||A == 37 ||A == 39)
                    {
                          labels[A] = new JLabel(rs);
                          spaceValue[A] = 0;
                    }
                    else{
                      labels[A] = new JLabel(bs);
                      spaceValue[A] = 0;
                    }
                          currentRectangle = new JPanel();
                          currentSpace = boardSpaces[A];
                          currentRectangle.add(labels[A]);
                          gameBoardBackground.add(currentRectangle);

                  }
                  gameBoard.add(gameBoardBackground);
                  frame.pack();
                  boardInitialize=true;
              }









                // creates CheckerBoardSpace objects



                // draws the Checkers board


                /****************************************************************/







                /******************************** Initial Piece Locations ********************************/






                /******************************** Adding Pieces to Board ********************************/



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
