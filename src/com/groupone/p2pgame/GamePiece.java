package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class GamePiece extends JPanel
{


        private Color pieceColor;
        private int gameBoardIndex;
        private int xBoardLocation; // column ; multiple of 100
        private int yBoardLocation; // row ; multiple of 100


        public GamePiece(Color color, int boardIndex, int xLocation, int yLocation)
        {

                this.pieceColor = color;
                this.gameBoardIndex = boardIndex;
                this.xBoardLocation = xLocation;
                this.yBoardLocation = yLocation;

        }

        public void paintComponent(Graphics graphics)
        {
                super.paintComponent(graphics);
                graphics.setColor(this.pieceColor);
                graphics.drawOval(10, 10, 60, 60);
                graphics.fillOval(10, 10, 60, 60);
        }

	public int getGameBoardIndex()
	{
		return this.gameBoardIndex;
	}
}
