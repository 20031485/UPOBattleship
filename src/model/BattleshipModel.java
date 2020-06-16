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

//BattleshipModel
public class BattleshipModel implements Serializable{
	//attributes
	private Player player = null;
	private Computer computer = null;
	private int gameSize;//5, 10, 15 where 5 means 5x5 and so on
	private BattleshipState state = BattleshipState.WELCOME;
	public static PlayerState playerState;//needed by Computer, to check if Player was hit or not
	private boolean timed;//creates timer if true
	//notify BattleshipView for every state BattleshipModel enters
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	//name of the saved file slot
	private static final String savedFileName = "battleship_saved_game.dat";
	//every move will set justSaved to false, but the method saveGame will set it to true
	private boolean justSaved = false;

	//constructors
	/*public BattleshipModel() {
		System.out.println("model created");
		this.gameSize = 10;
		this.timed = false;
		this.player = new Player(gameSize);
		this.computer = new Computer(gameSize, ComputerType.STUPID);
		
		if(timed) {
			//TODO come lo implementiamo? da thread o dalla gui?
		}
	}*/
	
	//default constructor for when you start the game - parameters are initialized later
	public BattleshipModel() {
		this.gameSize = 0;
		this.timed = false;
		this.player = null;
		this.computer = null;
	}
	
	public BattleshipModel(int gameSize) {
		this.gameSize = gameSize;
		this.timed = false;
		this.player = new Player(gameSize);
		this.computer = new Computer(gameSize, ComputerType.STUPID);
		
		if(timed) {
			//TODO implement timer
		}
	}
	
	//ComputerType is initialized from Computer's constructor (passed as new Computer(...))
	public BattleshipModel(Player player, Computer computer, int gameSize, boolean timed) {
		this.player = player;
		this.computer = computer;
		this.gameSize = gameSize;
		this.timed = timed;
		
		if(timed) {
			//TODO implement timer
		}
	}
	
	
	//methods
	
	//MVC VERSION
	public BattleshipState getState() {
		System.out.println("getState: " + this.state);
		return this.state;
	}
	
	//MVC VERSION
	public void setState(BattleshipState newState) {
		BattleshipState oldState = this.getState();
		this.state = newState;
		this.propertyChangeSupport.firePropertyChange("setState", oldState, newState);
		System.out.println("setState: " + state);
		//state changed --> you can press "save" again
		this.justSaved = false;
	}
	
	//MVC VERSION
	public static boolean savedGameExists(){
		File savedFile = new File(savedFileName);
		if(savedFile.exists()) {
			return true;
		}
		return false;
	}
	
	//MVC VERSION
	public void saveGame() {
		ObjectOutputStream outputStream = null;
		
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(savedFileName));
			outputStream.writeObject(this);
			outputStream.close();
			System.out.println(savedFileName+" written!");
			//you just pressed "save", so don't show (or disable) "save" button
			this.justSaved = true;
		} 
		catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
	
	//MVC VERSION
	public void newGame(Player player, Computer computer, int gameSize, boolean timed) {
		setPlayer(player);
		setComputer(computer);
		setGameSize(gameSize);
		setTimed(timed);
		//game created, so you might want to save it immediately
		this.justSaved = false;
	}
	
	//not launched if savedGameExists == false
	//MVC VERSION
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
			catch (IOException | ClassNotFoundException e) {
				// TODO something more intelligent?
				e.printStackTrace();
			}
		}
		else {
			System.err.println("Saved file not found!");
			//catch in controller and generate JDialog to communicate with user
			throw new FileNotFoundException("Saved file not found! Loading failed!");
		}
		return loadedGame;
	}
	
	//some getters and setters
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
	//TEST
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
	
	//utility
	public String toString() {
		return 	"Game Size: "+ getGameSize() +
				"\nPlayer1:\n\tname: " + getPlayer().getName() +
				"\n\tscore: "+ getPlayer().getScore() +
				"\nPlayer2:\n\tname: " + getComputer().getName() +
				"\n\tscore: " + getComputer().getScore() + "\n\n";
	}
	
	//TERMINAL VERSION
	//try{} suggested by compiler...
	public void turns() {
		try (Scanner s = new Scanner(System.in)) {
			while(!player.isDefeated() || !computer.isDefeated()) {
				System.out.print("Insert row: ");
				int row = s.nextInt();
				s.nextLine();
				System.out.print("Insert col: ");
				int col = s.nextInt();
				s.nextLine();
				hitAndGetHit(row, col);
				System.out.println(player.toString());
				System.out.println(computer.toString());
			}
		}
	}
	
	//player colpisce computer e viene colpito da computer
	//da lanciare DOPO il posizionamento delle navi
	//MVC VERSION
	public void hitAndGetHit(int row, int col) {
		int[] coordinates = new int[2];
		coordinates = player.hits(row, col);
		computer.isHit(coordinates[0], coordinates[1]);
		coordinates = computer.computerHits();
		player.isHit(coordinates[0], coordinates[1]);
	}
	
	//il giocatore posiziona le sue navi - terminal version
	//TERMINAL VERSION
	public void playerSetsShips() {
		try (Scanner s = new Scanner(System.in)) {
			while(!this.getPlayer().getShipList().isEmpty()) {
				System.out.println("Next ship's dimension: " + player.getShipList().get(0).getLength());
				System.out.print("Insert row: ");
				int row = s.nextInt();
				s.nextLine();
				System.out.print("Insert col: ");
				int col = s.nextInt();
				s.nextLine();
				System.out.print("Insert dir [0 = horizontal, 1 = vertical]: ");
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
	}
	
	//player sets ONE ship, the one at the index shipIndex in the view's comboBox
	//controller launches this, loaded with parameters from the view
	//MVC VERSION
	public void playerSetsShip(int shipIndex, int row, int col, ShipDirection direction) {
		//places the ship at the shipIndex-th index in shipList
		player.setShip(shipIndex, row, col, direction);
	}
	
	//MVC VERSION - perhaps useful
	public void playerRandomSetShips() {
		player.randomSetShips();
	}
	
	//computer sets ALL his ships randomly
	//MVC VERSION
	public void computerSetsShips() {
		computer.randomSetShips();
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
		bm.turns();
		
		
		/*FUNZIONAMENTO
		 * 1) imposto parametri partita (difficulty, gameSize, timed);
		 * 2) giocatore posiziona le navi;
		 * 3) computer posiziona le navi;
		 * 4) gioco finchè uno dei due non perde;
		 * */
	}
}