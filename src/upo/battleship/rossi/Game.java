package upo.battleship.rossi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	boolean savedGameExists(){
		try {
			FileReader savedFile = new FileReader("savedgame.txt");
			try {
				savedFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	void loadGame() {
		//TODO study exceptions and I/O before programming SHIT
		if(savedGameExists()) {
			System.out.println("file exists!");
		}
		else System.out.println("file not found!");
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.loadGame();
	}
}
