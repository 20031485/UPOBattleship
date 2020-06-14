package model;

import java.io.Serializable;
import java.util.ArrayList;

import utils.PlayerState;
import utils.ShipDirection;
import utils.ShipLength;
import utils.ShipType;

public class Player implements Serializable{
	//attributes
	protected String name;
	//il punteggio probabilmente è inutile
	private int score;
	//matrice in cui ogni giocatore può posizionare le proprie navi
	protected boolean[][] shipsGrid;
	//matrice in cui vengono salvati i colpi dell'avversario
	protected boolean[][] hitsGrid;
	protected int gameSize;	
	//protected PlayerState state;
	
	private ArrayList<Ship> shipList;
	private ArrayList<Ship> placedShips;
	private ArrayList<Ship> deadShips;
	
	//constructors
	public Player(String playerName) {
		this.name = playerName;
		this.score = 0;
	}
	
	public Player(int gameSize) {
		this.name = "Player";
		this.gameSize = gameSize;
		this.initGrids(gameSize);
		this.initShips(gameSize);
	}
	
	
	//methods
	
	//inizializza le liste di navi e le navi su shipList
	public void initShips(int gameSize) {
		this.shipList = new ArrayList<Ship>();
		this.placedShips = new ArrayList<Ship>();
		this.deadShips = new ArrayList<Ship>();
		Ship ship = null;
		switch(gameSize) {
		case 10:
			ship = new Ship(ShipType.CACCIATORPERDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
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
			ship = new Ship(ShipType.CACCIATORPERDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
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
			ship = new Ship(ShipType.CACCIATORPERDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
			shipList.add(ship);
			ship = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, gameSize);
			shipList.add(ship);
			ship = new Ship(ShipType.INCROCIATORE, ShipLength.INCROCIATORELENGTH, gameSize);
			shipList.add(ship);
			ship = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, gameSize);
			shipList.add(ship);
			ship = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, gameSize);
			shipList.add(ship);
			ship = new Ship(ShipType.CACCIATORPERDINIERE, ShipLength.CACCIATORPEDINIERELENGTH, gameSize);
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
	
	public String toString() {
		String toString = "Name: "+this.getName()+"\nScore: "+this.getScore()+"\nShips:\n";
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
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
	
	public boolean[][] getShipsGrid(){
		return this.shipsGrid;
	}
	
	public boolean[][] getHitsGrid(){
		return this.hitsGrid;
	}
	
	//inizializza le griglie di gioco con la dimensione giusta e le riempie di true
	public void initGrids(int gridSize) {
		shipsGrid = new boolean[gridSize][gridSize];
		hitsGrid = new boolean[gridSize][gridSize];
		for(int i=0; i<gridSize; ++i)
			for(int j=0; j<gridSize; ++j) {
				shipsGrid[i][j] = true;
				hitsGrid[i][j] = true;
			}
	}
	
	//funzione da chiamare quando viene ricevuto un colpo
	public void isHit(int row, int col) {
		shipsGrid[row][col] = true;
		for(int i = 0; i < placedShips.size(); ++i) {
			Ship ship = this.placedShips.get(i);
			if(ship.isHit(row, col)) {
				BattleshipModel.playerState = PlayerState.HIT;
				if(ship.isSunk()) {
					deadShips.add(placedShips.get(i));
					placedShips.remove(i);
					BattleshipModel.playerState = PlayerState.HITANDSUNK;
				}
			}
			else
				BattleshipModel.playerState = PlayerState.WATER;
		}
	}
	
	//metodo da chiamare quando il giocatore vuole colpire
	//restituisce un array di due coordinate [row, col] da passare al modello per colpire l'avversario
	public int[] hits(int row, int col){
		int[] coordinates = new int[2];
		coordinates[0] = row;
		coordinates[1] = col;
		return coordinates;
	}
	
	//metodo per posizionare una nave
	public void setShip(int shipIndex, int row, int col, ShipDirection direction) {
		//se ho posizionato la nave, la rimuovo dalla lista
		try{
			if(this.shipList.get(shipIndex).setShip(row, col, direction, this.shipsGrid)) {
				//tolgo una nave dalla lista delle navi disponibili e la aggiungo alla lista delle navi piazzate
				this.placedShips.add(this.shipList.get(shipIndex));
				this.shipList.remove(shipIndex);
			}
			else
				System.err.println("No room for this ship!");
		}
		catch(IndexOutOfBoundsException e) {
			System.err.println("No more ships for this player!");
		}
	}
	
	//rimuove tutte le navi da placedShips e le mette in shipList (serve nel SetShipsPanel)
	public void clearShips() {
		for(int i = 0; i < placedShips.size(); ++i) {
			//aggiungo una nave che c'è sulla griglia alla lista delle navi disponibili
			shipList.add(placedShips.get(i));
			//resetto la sua absolutePosition (campo di Ship)
			placedShips.get(i).removeShip();
			//rimuovo la nave dalla lista delle navi posizionate
			placedShips.remove(i);
		}
		resetShipsGrid();
	}
	
	//svuoto la shipsGrid (tutte le celle a true)
	public void resetShipsGrid() {
		for(int i = 0; i < shipsGrid.length; ++i)
			for(int j = 0; j < shipsGrid.length; ++j)
				shipsGrid[i][j] = true;
	}
	
	//controlla se ci sono ancora (pezzi di) navi sulla griglia del giocatore
	//se non ce ne sono, il giocatore ha perso
	public boolean isDefeated() {
		boolean result = true;
		for(int i=0; i<shipsGrid.length; ++i) {
			for(int j=0; j<shipsGrid.length; ++j)
				result = result && shipsGrid[i][j];
		}
		return result;
	}
	
	//serve per i test
	public boolean equals(Object o) {
		if(o instanceof Player) {
			if(((Player) o).getName().equals(this.getName())
				&& ((Player) o).getScore() == this.getScore()
				/*&& ((Player)o).getShipsGrid().equals(this.getShipsGrid())
				&& ((Player)o).getHitsGrid().equals(this.getHitsGrid())*/)
			return true;
		}
		return false;
	}
	
	//serve per fare un primo test
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
}