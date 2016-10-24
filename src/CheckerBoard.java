import javax.swing.*;
import java.awt.*;


public class CheckerBoard extends JPanel
{

        private JFrame frame;
        private CheckerBoardSpace[] boardSpaces;

        private Container gameBoard;

        public CheckerBoard()
        {

                super();

                frame = new JFrame();

                frame.setSize(640, 640);
                frame.setTitle("CheckerBoard");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                gameBoard = frame.getContentPane();
                gameBoard.setLayout( new GridLayout(8,8) );



                boardSpaces = new CheckerBoardSpace[64];
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
                                currentRectangle.setSize(100,100);

                                if(currentSpaceColor == Color.RED)
                                {
                                        currentSpaceColor = Color.BLACK;
                                }
                                else
                                {
                                        currentSpaceColor = Color.RED;
                                }

                                currentRectangle.setBackground(currentSpaceColor);
                                gameBoard.add(currentRectangle);
                        }

                }


                frame.setVisible(true);

        }








        public static void main(String[] args)
        {

                CheckerBoard testBoard = new CheckerBoard();

        }

}
