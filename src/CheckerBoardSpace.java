import javax.swing.*;
import java.awt.*;


public class CheckerBoardSpace extends JComponent
{
        public String spaceColor; // red or black
        public int spaceIndex; // number 0-63, with left to right from top to bottom

        public int xLocation;
        public int yLocation;

        public CheckerBoardSpace(int color, int index, int x, int y)
        {

                if(color % 2 == 0)
                {
                        this.spaceColor = "Red";
                }
                else
                {
                        this.spaceColor = "Black";
                }

                this.spaceIndex = index;
                this.xLocation = x;
                this.yLocation = y;
        }


}
