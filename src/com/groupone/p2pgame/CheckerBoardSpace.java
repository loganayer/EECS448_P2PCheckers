package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckerBoardSpace extends JPanel implements MouseListener
{
        private Color spaceColor; // red or black
	private Color oldSpaceColor;
	private CheckerBoardAlternate board;
	private boolean isHighlighted;
	private int index;

	public CheckerBoardSpace(CheckerBoardAlternate board, int color, int index, int x, int y)
        {
		this.addMouseListener(this);

		this.board = board;
		this.index = index;
		this.isHighlighted = false;
                if(color % 2 == 0)
                {
                        this.spaceColor = Color.RED;
                }
                else
                {
                        this.spaceColor = Color.BLACK;
                }
        }


        public void paintComponent(Graphics graphics)
        {
                super.paintComponent(graphics);
                graphics.setColor(this.spaceColor);
                graphics.drawRect(0, 0, 80, 80);
                graphics.fillRect(0, 0, 80, 80);
        }

	public void setColor(Color spaceColor) {
		this.spaceColor = spaceColor;
		this.repaint();
	}

	public Color getSpaceColor() {
		return this.spaceColor;
	}

	public void highlight() {
		this.isHighlighted = true;
		this.oldSpaceColor = spaceColor;
		this.setColor(Color.BLUE);
	}

	public void dehighlight() {
		this.isHighlighted = false;
		if (this.oldSpaceColor != null) {
			this.setColor(oldSpaceColor);
		}
	}

        public void mousePressed(MouseEvent e) {
                if (this.isHighlighted) {
			this.board.moveTo(this.index);
		}
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
}
