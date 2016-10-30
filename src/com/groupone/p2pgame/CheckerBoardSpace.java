package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;


public class CheckerBoardSpace extends JPanel
{
        private Color spaceColor; // red or black
        private int spaceIndex; // number 0-63, with left to right from top to bottom

        private int xBoardLocation;
        private int yBoardLocation;

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


        public void paintComponent(Graphics graphics)
        {
                super.paintComponent(graphics);
                graphics.setColor(this.spaceColor);
                graphics.drawRect(0, 0, 80, 80);
                graphics.fillRect(0, 0, 80, 80);
        }

	public Color getSpaceColor()
	{
		return this.spaceColor;
	}
}
