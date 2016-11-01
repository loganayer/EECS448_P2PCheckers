package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.event.*;

public class GamePiece extends JPanel implements MouseListener
{


        private Color pieceColor;
        private boolean isPressed = false;
        private int gameBoardIndex;
        private Player player;

        private CheckerBoardAlternate board;

        public GamePiece(CheckerBoardAlternate board, Color color, int index, Player player)
        {
                this.addMouseListener(this);
                this.pieceColor = color;
		this.board = board;
                this.gameBoardIndex = index;
                this.player = player;
        }

        public void paintComponent(Graphics graphics)
        {
                super.paintComponent(graphics);
                if (isPressed) {
                        graphics.setColor(Color.YELLOW);
                        graphics.drawOval(8, 8, 64, 64);
                        graphics.fillOval(8, 8, 64, 64);
                }
                graphics.setColor(this.pieceColor);
                graphics.drawOval(10, 10, 60, 60);
                graphics.fillOval(10, 10, 60, 60);

        }

        public void select() {
                if (this.board.getActivePlayer() == this.player) {
                        isPressed=true;
                        repaint();
                        this.board.setSelected(gameBoardIndex);
                }
        }

        public void deselect() {
                isPressed = false;
                repaint();
        } 
        
        public void mousePressed(MouseEvent e) {
                if (!this.board.isInExtraJumpMode()) {
                        this.board.clearHighlights();
                        select();
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

        public int getIndex() {
                return this.gameBoardIndex;
        }
}
