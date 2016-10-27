import javax.swing.*;
import java.awt.*;


public class CheckerBoardSpace extends JPanel
{
        public Color spaceColor; // red or black
        public int spaceIndex; // number 0-63, with left to right from top to bottom

        public int xBoardLocation;
        public int yBoardLocation;

        public CheckerBoardSpace(int color, int index, int x, int y)
        {

                if(color % 2 == 0)
                {
                        this.spaceColor = Color.RED;
                }
                else
                {
                        this.spaceColor = Color.BLACK;
                }

                this.spaceIndex = index;
                this.xBoardLocation = x;
                this.yBoardLocation = y;
        }


}
