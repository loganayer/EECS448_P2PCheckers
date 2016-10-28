package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class GamePiece extends JPanel
{


        public Color pieceColor;
        public int gameBoardIndex;
        public int xBoardLocation; // column ; multiple of 100
        public int yBoardLocation; // row ; multiple of 100


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
                graphics.fillOval(this.xBoardLocation, this.yBoardLocation, 60, 60);
        }

}
