##Final Product Backlog
#####Estimation: increased amount of importance/ required effort reflected in ascending numbers

1.	“Peer-to-Peer”
 -	Two players, should be able to access and interact with a single instance of the game on separate machines/ in separate windows and have those interactions be communicated to the other player via the game server.
 -	Estimation: 6 
 
 

2.	Detection of Players
 -	The game should initialize once two players have connected to the server and have decided to begin the game.
 -	Estimation: 3

 
 
3.	Basic Game Display 
 -	The program should display a standard checkerboard background along with a select number of circular icons that represent each player’s pieces.
 -	Estimation: 2



4.	Movement of Pieces
 -	The game’s players should be able to interact with each of their own pieces and move them throughout the board.  The program should be designed in a way to prevent illegal moves from occurring, and each player’s movements should be communicated to the server so that the other player’s display is updated according to the new board layout.
 -	Estimation: 4



5.	Capturing Pieces
 -	When a player makes a move that results in the capture of one or more of the other player’s pieces, the game should register the locations of the captured pieces, the number of pieces captured, and the player to whom those pieces belong.  The game should remove those pieces from the board as well as remove the respective player’s ability to interact with those pieces.
 -	Estimation: 4



6.	Win Conditions
 -	The server should be able to end the game when the game’s win conditions, as defined by the standard Checkers rules, have been satisfied. A screen should also be shown indicating who has won
 -	Estimation: 2



7.	Handling Connection Problems
 -	During an active game, the server should be able to confirm that both players have an unbroken connection to the game.  Otherwise, the game should be able to save its current state, pause, and wait for both players to have an established connection.
 -	Estimation: 3



8.	Updating Game Displays
 -	The boards should update based on the move made by the player and the data sent from the server.
 -	Estimation: 2



9.	User Interaction
 -	The user should be able to click on a piece and move it.
 -	Estimation: 4


10. Indicate whose turn it is
 - There should be some clear way to say "this is Player 1/2's turn"
 - Estimation: 1

11. Drag and drop piece
 - Make game closer to real checkers where player clicks and drags checkers piece to correct play
 - Estimation: 2

12. Add undo button
 - The player may accidentally make a move that was not indended. Have a button to undo that.
 - Estimation: 4

13. Add piece move animation
 - When on networked mode, show the piece moving clearly with an animation
 - Estimation: 3

14. Indicate which pieces are kings
 - Right now, there is no indicator for which piece is a king or not. This will probably require making image assets.
 - Estimation: 1

15. Add user profiles
 - Keep track of user's stats from game to game.
 - Estimation: 5
