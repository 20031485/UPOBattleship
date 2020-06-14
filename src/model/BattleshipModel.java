package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import java.util.Scanner;
//import java.util.Scanner;
import java.util.Timer;

import utils.BattleshipState;
import utils.ComputerType;
import utils.PlayerState;
import utils.ShipDirection;

//TODO notifyObservers() e setChange()

//BattleshipModel
public class BattleshipModel implements Serializable{
	//attributes
	private Player player = null;
	private Computer computer = null;
	private int gameSize;//5, 10, 15 where 5 means 5x5 and so on
	private BattleshipState state = BattleshipState.WELCOME;
	public static PlayerState playerState;
	private boolean timed;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	//private Timer timer;
	private static final String savedFileName = "battleship_saved_game.dat";
	//every move will set justSaved to false, but the method saveGame will set it to true
	private boolean justSaved = false;

	//constructors
	public BattleshipModel() {
		System.out.println("model created");
		this.gameSize = 10;
		this.timed = false;
		this.player = new Player(gameSize);
		this.computer = new Computer(gameSize, ComputerType.STUPID);
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}
	public BattleshipModel(int gameSize) {
		this.gameSize = gameSize;
		this.timed = false;
		this.player = new Player(gameSize);
		this.computer = new Computer(gameSize, ComputerType.STUPID);
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}
	
	public BattleshipModel(Player player, Computer computer, int gameSize, boolean timed) {
		this.player = player;
		this.computer = computer;
		this.gameSize = gameSize;
		this.timed = timed;
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}
	
	
	//methods
	public BattleshipState getState() {
		System.out.println("getState: " + this.state);
		return this.state;
	}
	
	public void setState(BattleshipState newState) {
		BattleshipState oldState = this.getState();
		this.state = newState;
		this.propertyChangeSupport.firePropertyChange("setState", oldState, newState);
		System.out.println("setState: " + state);
	}
	
	public static boolean savedGameExists(){
		File savedFile = new File(savedFileName);
		if(savedFile.exists()) {
			return true;
		}
		return false;
	}
	
	public void saveGame() {
		ObjectOutputStream outputStream = null;
		
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
	
	public void newGame(Player player, Computer computer, int gameSize, boolean timed) {
		setPlayer(player);
		setComputer(computer);
		setGameSize(gameSize);
		setTimed(timed);
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
			throw new FileNotFoundException("Saved file not found! Loading failed!");
		}
		return loadedGame;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public int getGameSize() {
		return gameSize;
	}

	public void setGameSize(int gameSize) {
		this.gameSize = gameSize;
	}
	
	public void setTimed(boolean timed) {
		this.timed = timed;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	//used for debugging purposes
	public boolean equals(Object o) {
		if(o instanceof BattleshipModel) {
			if(((BattleshipModel) o).getPlayer().equals(this.getPlayer())
					&& ((BattleshipModel) o).getComputer().equals(this.getComputer())
					&& ((BattleshipModel) o).getGameSize() == (this.getGameSize())
				) return true;
		}
		return false;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public String toString() {
		return 	"Game Size: "+ getGameSize() +
				"\nPlayer1:\n\tname: " + getPlayer().getName() +
				"\n\tscore: "+ getPlayer().getScore() +
				"\nPlayer2:\n\tname: " + getComputer().getName() +
				"\n\tscore: " + getComputer().getScore() + "\n\n";
	}
	
	//giocatore colpisce com e viene colpito da com
	public void hitAndGetHit(int row, int col) {
		int[] coordinates = new int[2];
		coordinates = player.hits(row, col);
		computer.isHit(coordinates[0], coordinates[1]);
		coordinates = computer.computerHits();
		player.isHit(coordinates[0], coordinates[1]);
	}
	
	//il giocatore posiziona le sue navi
	public void playerSetsShips() {
		Scanner s = new Scanner(System.in);
		while(!this.getPlayer().shipList.isEmpty()) {
			System.out.println("Next ship's dimension: " + player.shipList.get(0).getLength());
			System.out.print("Insert row: ");
			int row = s.nextInt();
			s.nextLine();
			System.out.print("Insert col: ");
			int col = s.nextInt();
			s.nextLine();
			int dir = s.nextInt();
			s.nextLine();
			ShipDirection direction = null;
			if(dir == 0)
				direction = ShipDirection.HORIZONTAL;
			else
				direction = ShipDirection.VERTICAL;
			player.setShip(0, row, col, direction);
			System.out.println(player.toString());
		}
	}
	
	//il computer posiziona le sue navi randomicamente
	public void computerSetsShips() {
		computer.computerSetShips();
	}
	
	/*
	 * MAIN
	 * 
	 */
	public static void main(String[] args) {
		BattleshipModel bm = new BattleshipModel(10);
		bm.newGame(new Player(10), new Computer(10, ComputerType.SMART), 10, false);
		System.out.println(bm.getPlayer().toString());
		System.out.println(bm.getComputer().toString());
		bm.playerSetsShips();
		bm.computerSetsShips();
		System.out.println(bm.getPlayer().toString());
		System.out.println(bm.getComputer().toString());
		Scanner s = new Scanner(System.in);
		while(!bm.getPlayer().isDefeated() || !bm.getComputer().isDefeated()) {
			System.out.print("Insert row: ");
			int row = s.nextInt();
			s.nextLine();
			System.out.print("Insert col: ");
			int col = s.nextInt();
			s.nextLine();
			bm.hitAndGetHit(row, col);
			System.out.println(bm.getPlayer().toString());
			System.out.println(bm.getComputer().toString());
		}
	}
}