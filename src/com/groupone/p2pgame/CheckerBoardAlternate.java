package com.groupone.p2pgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.util.List;

public class CheckerBoardAlternate extends JPanel implements MouseListener
{

        private JFrame frame;
        private JLayeredPane gameBoard;
        private Container gameBoardBackground;
        private Container gameBoardWithPieces;


        private CheckerBoardSpace[] boardSpaces;
        private GamePiece[] drawnPieces;

        private CheckerBoardState state;

        private CheckerSquare selectedSquare;

        private Player activePlayer;
        private boolean extraJumpMode;

        /**
           Start a new game board.
        */
        public CheckerBoardAlternate() {
                this(CheckerBoardState.getStartingBoard());
        }

        /**
           Setup a checkerboard from player one and player two piece locations
        */
        public CheckerBoardAlternate(CheckerBoardState state)
        {

                super();

		this.state = state;

		// player two goes first
		this.activePlayer = Player.TWO;

		// set to true when you want to disable everything but
		// the second jump.
		this.extraJumpMode = false;

                /******************************** Display Characteristics ********************************/

                this.frame = new JFrame(); // for the first initializeSettings call
                this.initializeSettings();

                /********************************  ********************************/








                /******************************** Initializing Member Variables ********************************/

                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24]; // all of player one's pieces will be placed in this array before player two's pieces

                /********************************  ********************************/








                /****************************************************************/








                this.drawGameBackground();
                this.drawGameBoard(state); // draws the board at its initial state


                /****************************************************************/




                frame.setVisible(true);

        }



        //MOUSE locations THIS DOESNT DO ANYTHING THE REAL CODE IS FAR BELOW
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
        }
        
        public void initializeSettings()
        {

                this.frame.dispose();
                this.frame = new JFrame();
                this.frame.setSize(645, 675);
                this.frame.setResizable(false);
                this.frame.setTitle("CheckerBoardAlternate");
                this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                this.gameBoard = new JLayeredPane();
                this.gameBoard = frame.getLayeredPane();
                this.gameBoard.setPreferredSize(new Dimension(640, 640));


                this.gameBoardWithPieces = new Container();
                this.gameBoardWithPieces.setSize(640, 640);
                this.gameBoardWithPieces.setMaximumSize( new Dimension(640, 640) );
                this.gameBoardWithPieces.setLayout( new GridLayout(8,8) );
                this.gameBoardWithPieces.setBackground(Color.BLACK);


        }







	public void drawGameBackground() {

                /******************************** Initial Board Spaces ********************************/

                int color = 0;
                int x = 0; // row number
                int y = 0; // column number

                // creates CheckerBoardSpace objects
                for(int A = 0 ; A < 64 ; A++)
                {

                        boardSpaces[A] = new CheckerBoardSpace(this, color, A, x, y);

                        if(y == 7) // when in last column
                        {
                                x++; // increment row
                                y = 0;
                                color = (color % 2); // makes sure that the next row begins with an alternating color
                        }
                        else
                        {
                                y++;
                                color++; // alternating the colors for the CheckerBoardSpace constructor
                        }

                }

	}

        // JavaDoc
        public void drawGameBoard(CheckerBoardState state) // use for updating display of game board
        {


                /******************************** Adding Pieces to Board ********************************/

                // creates the 24 game pieces --> player one's pieces are the first 12, and player two's pieces are second
		int n = 0;
                for( CheckerSquare square : state.getSquares())
                {

			if (square.getPiece().getType() != PieceType.EMPTY) {
				if (square.getPiece().getPlayer() == Player.ONE) {
					drawnPieces[n++] = new GamePiece(this, Color.BLUE, square.getIndex(), square.getPiece().getPlayer());
				} else if (square.getPiece().getPlayer() == Player.TWO) {
					drawnPieces[n++] = new GamePiece(this, Color.GRAY, square.getIndex(), square.getPiece().getPlayer());
				}
			}


                }


                // this.initializeSettings();

                JPanel currentPiece;

                CheckerBoardSpace currentBoardSpace;
                JLabel currentPieceNumber;


		int[] playerOnePiecesLocations = state.getPlayerOnePieceLocationsInts();
		int[] playerTwoPiecesLocations = state.getPlayerTwoPieceLocationsInts();

		int curLocation = 0;

                while( curLocation < 64 ) // cycles through full board
                {

                        currentBoardSpace = boardSpaces[curLocation];

			boolean found = false;
			for (GamePiece piece : drawnPieces) {
				if (piece != null && piece.getIndex() == curLocation) {
					currentPiece = piece;
					currentPiece.setBackground(currentBoardSpace.getSpaceColor());
					gameBoardWithPieces.add(currentPiece);
					found = true;
					break;
				}
			}
			if (!found) {
                                gameBoardWithPieces.add(currentBoardSpace); // the current space is empty
			}

                        curLocation++; // go to next square
                }

                gameBoard.add(gameBoardWithPieces, 0);
                this.frame.setVisible(true);
        }


	public void clearHighlights() {
		if (extraJumpMode) {
			return;
		}
		for (GamePiece piece : this.drawnPieces) {
			if (piece != null) {
				piece.deselect();
			}
		}
		for (CheckerBoardSpace space : this.boardSpaces) {
			if (space != null) {
				space.dehighlight();
			}
		}
	}

	public void showAllowedMoves(CheckerSquare square) {
		List<CheckerMove> moves = this.state.getValidMoves(square);
		for (CheckerMove move : moves) {
			this.boardSpaces[move.getEnd().getIndex()].highlight();
		}
	}


        public void setSelected(int index) {
		CheckerSquare square = this.state.getSquare(index);

		// only let active player select
		if (square.getPiece().getPlayer() == this.activePlayer) {
			this.selectedSquare = square;
                        this.showAllowedMoves(this.selectedSquare);
		}
	}

        public boolean isInExtraJumpMode() {
                return this.extraJumpMode;
        }

	public void moveTo(int index) {
		this.extraJumpMode = false;

		CheckerSquare targetSquare = this.state.getSquare(index);
		CheckerMove move = new CheckerMove(this.selectedSquare, targetSquare);

		// actually modify the board
		this.state.executeMove(move);

		// cleanup everything
		this.removeAll();

		// re init everything
                this.gameBoard = new JLayeredPane();
                this.gameBoard = frame.getLayeredPane();
                this.gameBoard.setPreferredSize(new Dimension(640, 640));

                this.gameBoardWithPieces = new Container();
                this.gameBoardWithPieces.setSize(640, 640);
                this.gameBoardWithPieces.setMaximumSize( new Dimension(640, 640) );
                this.gameBoardWithPieces.setLayout( new GridLayout(8,8) );
                this.gameBoardWithPieces.setBackground(Color.BLACK);

		this.gameBoardWithPieces.addMouseListener(this);

                boardSpaces = new CheckerBoardSpace[64];
                drawnPieces = new GamePiece[24]; // all of player one's pieces will

		// redraw
		this.drawGameBackground();
                this.drawGameBoard(state); // draws the board at its initial state

		// handle extra jumps
		List<CheckerMove> extraJumps = this.state.getValidDoubleJumps(move.getEnd());
		if (move.isDoubleJump() && extraJumps.size() > 0) {
			this.extraJumpMode = true;
			for (GamePiece piece : this.drawnPieces) {
				if (piece != null && piece.getIndex() == move.getEnd().getIndex()) {
					piece.select();
				}
			}
			for (CheckerMove move2 : extraJumps) {
				this.boardSpaces[move2.getEnd().getIndex()].highlight();
			}
		} else {
			this.switchPlayer();
		}
	}

	public void switchPlayer() {
		if (this.activePlayer == Player.ONE) {
			this.activePlayer = Player.TWO;
		} else if (this.activePlayer == Player.TWO) {
			this.activePlayer = Player.ONE;
		}
	}

	public Player getActivePlayer() {
		return this.activePlayer;
	}

        public static void main(String[] args)
        {
                CheckerBoardAlternate testBoard = new CheckerBoardAlternate();
                /*
                testBoard.removePiece(1, 3);
                testBoard.removePiece(1, 5);
                testBoard.removePiece(2, 60);
                testBoard.removePiece(2, 44);

                testBoard.drawGameBoard();
                */
        }
}
