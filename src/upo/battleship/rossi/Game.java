package upo.battleship.rossi;

import java.util.Timer;

public class Game {
	//attributes
	private Player player1 = null;
	private Player player2 = null;
	private int gameSize;//5, 10, 15 where 5 means 5x5 and so on
	private boolean timed;
	private Timer timer;

	//constructors
	public Game() {
		this.player1 = player1;
		this.player2 = player2;
		this.gameSize = gameSize;
		this.timed = timed;
		
		if(timed) {
			//set timer
		}
	}
	
	public Game(boolean load) {
		loadGame();
	}
	
	//methods
	void newGame(Player player1, Player player2, int gameSize, boolean timed) {
		//TODO
	}
	
	void loadGame() {
		//TODO
	}
}
