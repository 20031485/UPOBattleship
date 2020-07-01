package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import utils.PlayerState;
import utils.ShipDirection;
import utils.ShipLength;
import utils.ShipType;

/**
 * Class representing a Player in the Battleship game. 
 * It holds its default name, a matrix for each ship the Player sets
 * and a matrix for each hit the Player receives. For every incoming hit,
 * the Player changes its state according to the result of the hit.
 * When a Player has no Ships on his shipsGrid, it is considered defeated.
 * @author 20027017 & 20031485
 *
 */
public class Player extends AbstractPlayer implements Serializable{
	private static final long serialVersionUID = 1L;
	//attributes
	private String name;
	//matrice in cui ogni giocatore può posizionare le proprie navi
	private boolean[][] shipsGrid;
	//matrice in cui vengono salvati i colpi dell'avversario
	private boolean[][] hitsGrid;
	// gameSize is protected because it must be visible from Player's derivative classes
	protected int gameSize;	
	private PlayerState state;
	
	private ArrayList<Ship> shipList;
	private ArrayList<Ship> placedShips;
	private ArrayList<Ship> deadShips;
	
	//constructors
	/**
	 * Constructor for the class {@code Player}
	 * @param playerName The name of the {@code Player}
	 */
	public Player(String playerName) {
		this.name = playerName;
	}
	
	/**
	 * Constructor for the class {@code Player}
	 * @param gameSize The size of the game grid
	 */
	public Player(int gameSize) {
		this.name = "Player";
		this.gameSize = gameSize;
		this.state = PlayerState.WATER;
		this.initGrids(gameSize);
		this.initShips(gameSize);
	}
	
	
	//methods
	private void initShips(int gameSize) {
		this.shipList = new ArrayList<Ship>();
		this.placedShips = new ArrayList<Ship>();
		this.deadShips = new ArrayList<Ship>();
		Ship ship = null;
		switch(gameSize) {
			case 5:
				ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				break;
				
			case 10:
				ship = new Ship(ShipType.CACCIATORPEDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				break;
				
			case 15:
				ship = new Ship(ShipType.CACCIATORPEDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				break;
				
			case 20:
				ship = new Ship(ShipType.CACCIATORPEDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CACCIATORPEDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
				shipList.add(ship);
				ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
				shipList.add(ship);
				break;
		}
	}
	
	/**
	 * Gets the name of the {@code Player}
	 * @return A String containing the name of the {@code Player}
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name of the {@code Player}
	 * @param newName String containing the new {@code Player}'s name
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Gets the matrix of the {@code Player}'s set {@code Ship}s
	 * @return The boolean matrix of the {@code Player}'s set {@code Ship}s
	 */
	public boolean[][] getShipsGrid(){
		return this.shipsGrid;
	}
	
	/**
	 * Gets the matrix of the {@code Player}'s received hits
	 * @return The boolean matrix of the {@code Player}'s received hits
	 */
	public boolean[][] getHitsGrid(){
		return this.hitsGrid;
	}
	
	/**
	 * Gets the list of the {@code Player}'s {@code Ship}s
	 * @return The list of the {@code Player}'s {@code Ship}s
	 */
	public ArrayList<Ship> getShipList(){
		return this.shipList;
	}
	
	/**
	 * Sets a new {@code Player}'s state
	 * @param state The new {@code Player}'s state
	 */
	public void setState(PlayerState state) {
		this.state = state;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gets the current {@code Player}'s state
	 * @return The current {@code Player}'s state
	 */
	public PlayerState getState() {
		return this.state;
	}
	
	private void initGrids(int gridSize) {
		shipsGrid = new boolean[gridSize][gridSize];
		hitsGrid = new boolean[gridSize][gridSize];
		for(int i=0; i<gridSize; ++i)
			for(int j=0; j<gridSize; ++j) {
				shipsGrid[i][j] = true;
				hitsGrid[i][j] = true;
			}
	}
	
	//funzione da chiamare quando viene ricevuto un colpo
	/**
	 * Method mimicking an incoming hit to the {@code Player}
	 * @param row The row coordinate being hit
	 * @param col The column coordinate being hit
	 */
	public void isHit(int row, int col) {
		hitsGrid[row][col] = false;
		shipsGrid[row][col] = true;
		PlayerState newState = PlayerState.WATER;
		for(int i = 0; i < placedShips.size(); ++i) {
			Ship ship = this.placedShips.get(i);
			if(ship.isHit(row, col)) {
				newState = PlayerState.HIT;
				if(ship.isSunk()) {
					deadShips.add(placedShips.get(i));
					placedShips.remove(i);
					newState = PlayerState.HITANDSUNK;
				}
			}
		}
		setState(newState);
		//the next two instructions are included in "setState()"
		//setChanged();
		//notifyObservers();
	}
	
	/**
	 * Returns the coordinates the {@code Player} wants to hit
	 * @param row The row coordinate
	 * @param col the column coordinate
	 * @return An integer bidimensional array containing the two coordinates
	 */
	public int[] hits(int row, int col){
		int[] coordinates = new int[2];
		coordinates[0] = row;
		coordinates[1] = col;
		return coordinates;
	}
	

	/**
	 * Sets a single {@code Player}'s {@code Ship} onto the {@code Player}'s game grid
	 * @param shipIndex The index of the {@code Ship}
	 * @param row The row coordinate the {@code Ship} is going to be set to
	 * @param col The column coordinate the {@code Ship} is going to be set to
	 * @param direction The vertical/horizontal direction of the {@code Ship}
	 */
	public void setShip(int shipIndex, int row, int col, ShipDirection direction) {
		//se ho posizionato la nave, la rimuovo dalla lista
		try{
			if(this.shipList.get(shipIndex).setShip(row, col, direction, this.shipsGrid)) {
				//tolgo una nave dalla lista delle navi disponibili e la aggiungo alla lista delle navi piazzate
				this.placedShips.add(this.shipList.get(shipIndex));
				this.shipList.remove(shipIndex);
				setChanged();
				notifyObservers();
			}
			//else
				//System.err.println("No room for this ship!");
		}
		catch(IndexOutOfBoundsException e) {
			System.err.println("No more ships for this player!");
		}
	}
	
	/**
	 * Randomly sets all {@code Player}'s {@code Ship}s
	 */
	public void randomSetShips() {
		Random rand = new Random();
		while(!this.shipList.isEmpty()) {
			int row = rand.nextInt(this.gameSize);
			int col = rand.nextInt(this.gameSize);
			int dir = rand.nextInt(2);
			if(dir == 0)
				setShip(0, row, col, ShipDirection.HORIZONTAL);
			else
				setShip(0, row, col, ShipDirection.VERTICAL);
			//notify observers in setShip
		}
	}
	
	/**
	 * Removes all {@code Player}'s {@code Ship}s from its game grid
	 */
	public void clearShips() {
		for(int i = 0; i < placedShips.size(); ++i) {
			//aggiungo una nave che c'è sulla griglia alla lista delle navi disponibili
			shipList.add(placedShips.get(i));
			//resetto la sua absolutePosition (campo di Ship)
			placedShips.get(i).removeShip();
			//rimuovo la nave dalla lista delle navi posizionate
			placedShips.remove(i);
			setChanged();
			notifyObservers();
		}
		resetShipsGrid();
	}
	
	/**
	 * Returns the number of the non-completely-destroyed {@code Ship}s of the {@code Player}
	 * @return An integer stating the number of "alive" {@code Ship}s
	 */
	public int shipsLeft() {
		return placedShips.size();
	}
	
	//svuoto la shipsGrid (tutte le celle a true)
	private void resetShipsGrid() {
		for(int i = 0; i < shipsGrid.length; ++i)
			for(int j = 0; j < shipsGrid.length; ++j)
				shipsGrid[i][j] = true;
		setChanged();
		notifyObservers();
	}
	
	//controlla se ci sono ancora (pezzi di) navi sulla griglia del giocatore
	//se non ce ne sono, il giocatore ha perso
	/**
	 * Check if the {@code Player} is defeated
	 * @return true if {@code Player} is defeated, false otherwise
	 */
	public boolean isDefeated() {
		boolean result = true;
		for(int i=0; i<shipsGrid.length; ++i) {
			for(int j=0; j<shipsGrid.length; ++j)
				result = result && shipsGrid[i][j];
		}
		return result;
	}
	
	/**
	 * Utility method for comparing two {@code Player} objects, 
	 * mostly used for testing and debugging purposes
	 * @param o {@code Object} to probe
	 * @return true if {@code o} and the calling {@code Player} are the same 
	 * object with the same attributes, false otherwise
	 */
	public boolean equals(Object o) {
		if(o instanceof Player) {
			if(((Player) o).getName().equals(this.getName())
				&& ((Player) o).getState() == this.getState()
				/*&& ((Player)o).getShipsGrid().equals(this.getShipsGrid())
				&& ((Player)o).getHitsGrid().equals(this.getHitsGrid())*/)
			return true;
		}
		return false;
	}
	
	/**
	 * Utility method for printing a {@code Player} object, mostly used for debugging purposes
	 */
	public String toString() {
		String toString = "Name: "+this.getName()+"\nShips:\n";
		for(int i=0; i < shipsGrid.length; ++i) {
			for(int j=0; j < shipsGrid.length; ++j) {
				if(!shipsGrid[i][j])
					toString += "[X]";
				else
					toString += "[ ]";
			}
			toString += "\n";
		}
		toString += "Hits:\n";
		for(int i=0; i < hitsGrid.length; ++i) {
			for(int j=0; j < hitsGrid.length; ++j) {
				if(!hitsGrid[i][j])
					toString += "[X]";
				else
					toString += "[ ]";
			}
			toString += "\n";
		}
		toString += "Is defeated: "+isDefeated()+"\n";
		return toString;
	}
	
	/*
	public static void main(String[] args) {
		Player p = new Player(10);
		p.setShip(0, 0, 0, ShipDirection.HORIZONTAL);//2
		//System.out.println(p.toString());
		p.setShip(0, 2, 0, ShipDirection.VERTICAL);//4
		//System.out.println(p.toString());
		p.setShip(0, 0, 4, ShipDirection.VERTICAL);//2
		p.setShip(0, 3, 3, ShipDirection.HORIZONTAL);//5
		p.setShip(0, 5, 3, ShipDirection.VERTICAL);//3
		System.out.println(p.toString());
		p.isHit(0, 0);
		System.out.println(p.toString());
		p.isHit(0, 1);
		System.out.println(p.toString());
	}
	*/
}