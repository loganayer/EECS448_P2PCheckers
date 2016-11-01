package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GamePiece extends JPanel implements MouseListener
{


        public Color pieceColor;
        public int gameBoardIndex;
        public int xBoardLocation; // column ; multiple of 100
        public int yBoardLocation; // row ; multiple of 100
        public boolean ispressed = false;
        public boolean isSelected = false;
        public int[] currentLocations = new int[] {0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
          2,0,2,0,2,0,2,0,0,2,0,2,0,2,0,2,2,0,2,2,0,2}; //1==p1, 0==empty, 2== p2

        public GamePiece(Color color, int boardIndex, int xLocation, int yLocation)
        {
                addMouseListener(this);
                this.pieceColor = color;
                this.gameBoardIndex = boardIndex;
                this.xBoardLocation = xLocation;
                this.yBoardLocation = yLocation;

        }

        public void paintComponent(Graphics graphics)
        {
                if(ispressed==false)
                {
		    super.paintComponent(graphics);
		    graphics.setColor(this.pieceColor);
		    graphics.drawOval(10, 10, 60, 60);
		    graphics.fillOval(10, 10, 60, 60);
                }
                if(ispressed==true) //selected border
                {
		    super.paintComponent(graphics);
		    graphics.setColor(Color.YELLOW);
		    graphics.drawOval(8, 8, 64, 64);
		    graphics.fillOval(8, 8, 64, 64);
		    graphics.setColor(this.pieceColor);
		    graphics.drawOval(10, 10, 60, 60);
		    graphics.fillOval(10, 10, 60, 60);
                }
        }

	public void onPressed() {
		
	}


	public void mousePressed(MouseEvent e) {
	if(currentLocations[this.xBoardLocation*8 + this.yBoardLocation]==2)
	    {
		ispressed=true;
		isSelected=true;
		System.out.println("testPress");
		repaint();
	    }

    }

    public void mouseReleased(MouseEvent e) {
	if(currentLocations[this.xBoardLocation*8 + this.yBoardLocation]==2 && currentLocations[(this.xBoardLocation-1)*8 + this.yBoardLocation-1]==0)
	    {
		currentLocations[this.xBoardLocation*8 + this.yBoardLocation]=0;
		this.xBoardLocation=this.xBoardLocation-1;
		this.yBoardLocation=this.yBoardLocation-1;
		currentLocations[this.xBoardLocation*8 + this.yBoardLocation]=2;
		repaint();
		System.out.println("testMoved");

	    }
	System.out.println("testReleased");

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {


	if(isSelected)
	    {
		ispressed=false;
		System.out.println("testClicked");
		repaint();
		//testBoard.removePiece(1, 3);

	    }

    }

}
