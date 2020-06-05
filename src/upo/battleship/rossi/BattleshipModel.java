package upo.battleship.rossi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Observable;
//import java.util.Scanner;
import java.util.Timer;

//BattleshipModel
public class BattleshipModel extends Observable implements Serializable{
	//attributes
	private Player player1 = null;
	private Player player2 = null;
	private int gameSize;//5, 10, 15 where 5 means 5x5 and so on
	private boolean timed;
	//private Timer timer;
	private static final String savedFileName = "battleship_saved_game.dat";
	//every move will set justSaved to false, but the method saveGame will set it to true
	private boolean justSaved = false;

	//constructors
	public BattleshipModel() {
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameSize = 10;
		this.timed = false;
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}
	public BattleshipModel(int gameSize) {
		this.player1 = new Player();
		this.player2 = new Player();
		this.gameSize = gameSize;
		this.timed = false;
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}
	
	
	//methods
	public static boolean savedGameExists(){
		File savedFile = new File(savedFileName);
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
			outputStream = new ObjectOutputStream(new FileOutputStream(savedFileName));
			outputStream.writeObject(this);
			outputStream.close();
			System.out.println(savedFileName+" written!");
			this.justSaved = true;
		} 
		catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	//not launched if savedGameExists == false
	public static BattleshipModel loadGame() throws FileNotFoundException {
		ObjectInputStream inputStream = null;
		BattleshipModel loadedGame = null;
		if(savedGameExists()) {
			try {
				inputStream = new ObjectInputStream(new FileInputStream(savedFileName));
				loadedGame = (BattleshipModel)inputStream.readObject();
				inputStream.close();
				System.out.println(savedFileName+" read!");
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Saved file not found!");
			throw new FileNotFoundException("Saved file not found! Cannot load!");
		}
		return loadedGame;
	}
	
	
	
	public String toString() {
		return 	"Game Size: "+ getGameSize() +
				"\nPlayer1:\n\tname: " + getPlayer1().getName() +
				"\n\tscore: "+ getPlayer1().getScore() +
				"\nPlayer2:\n\tname: " + getPlayer2().getName() +
				"\n\tscore: " + getPlayer2().getScore() + "\n\n";
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

	public static void main(String[] args) {
		BattleshipModel game1 = new BattleshipModel(7);
		BattleshipModel game2 = new BattleshipModel();
		game1.getPlayer1().setScore(15);
		game1.getPlayer2().setScore(23);
		System.out.println("Game1: \n"+game1.toString());
		System.out.println("Game2: \n"+game2.toString());
		//System.out.println("Saving Game1...\n\n");
		game1.saveGame();
		if(savedGameExists()) {
				System.out.println("file exists\n\n");
			try {
				System.out.println("Loading game1 onto game2...");
				game2 = loadGame();
			}
			catch(Exception e) {
				System.out.println("Exception");
				//e.printStackTrace();
			}
		}
		else 
			System.out.println("Saved file does not exist!");
		try{
			System.out.println("Game1: \n"+game1.toString());
			System.out.println("Game2: \n"+game2.toString());
		}
		catch(NullPointerException e) {
			System.out.println("game2 = null");
		}
	}
	
	//used for debugging purposes
	public boolean equals(Object o) {
		if(o instanceof BattleshipModel) {
			if(((BattleshipModel) o).getPlayer1().equals(this.getPlayer1())
					&& ((BattleshipModel) o).getPlayer2().equals(this.getPlayer2())
					&& ((BattleshipModel) o).getGameSize() == (this.getGameSize())
				) return true;
		}
		return false;
	}
}