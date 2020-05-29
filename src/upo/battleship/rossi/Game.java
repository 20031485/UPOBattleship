package upo.battleship.rossi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class Game implements Serializable{
	//attributes
	private Player player1 = null;
	private Player player2 = null;
	private int gameSize;//5, 10, 15 where 5 means 5x5 and so on
	private boolean timed;
	private Timer timer;
	private String savedFileName = "battleship_saved_game.dat";
	//every move will set justSaved to false, but the method saveGame will set it to true
	private boolean justSaved = false;

	//constructors
	public Game(int gameSize) {
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameSize = gameSize;
		this.timed = false;
		
		if(timed) {
			//set timer
		}
	}
	
	//default constructor
	public Game() {
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameSize = 10;
		this.timed = false;
		
		if(timed) {
			//set timer
		}
	}
	
	
	
	//methods
	void newGame(Player player1, Player player2, int gameSize, boolean timed) {
		//TODO
	}
	
	boolean savedGameExists(){
		File savedFile = new File(this.savedFileName);
		if(savedFile.exists())
			return true;
		return false;
	}
	
	void saveGame() {
		ObjectOutputStream outputStream = null;
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		//Date date = new Date();
		//String fileName = "battleship_"+dateFormat.format(date)+".dat";
		//String fileName = this.savedFileName;
		//System.out.println(this.savedFileName);
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(this.savedFileName));
			outputStream.writeObject(this);
			outputStream.close();
			System.out.println(this.savedFileName+" written!");
			this.justSaved = true;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//not launched if savedGameExists == false
	public Game loadGame() throws FileNotFoundException {
		//TODO study exceptions and I/O before programming SHIT
		ObjectInputStream inputStream = null;
		Game loadedGame = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(this.getSavedFileName()));
			loadedGame = (Game)inputStream.readObject();
			inputStream.close();
			System.out.println(this.getSavedFileName()+" read!");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loadedGame;
	}
	
	public String toString() {
		return 	"Game Size: "+this.getGameSize()+
				"\nPlayer1's name: "+getPlayer1().getName()+
				"\nPlayer1's score: "+getPlayer1().getScore()+
				"\nPlayer2's name: "+getPlayer2().getName()+
				"\nPlayer2's score: "+getPlayer2().getScore()+"\n\n";
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getGameSize() {
		return gameSize;
	}

	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public static void main(String[] args) {
		Game game = new Game();
		System.out.println("this is the current game:");
		System.out.println(game.toString());
		try {
			//TODO fix loadGame - it doesn't load the game LOL
			game.loadGame();
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}
		System.out.println("this was the saved game:");
		System.out.println(game.toString());
		System.out.println("this is the new game:");
		game = new Game(7);
		System.out.println(game.toString());
		game.saveGame();
	}
}
