package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.ShipDirection;
import utils.ShipLength;
import utils.ShipType;

public class Player implements Serializable{
	//attributes
	private String name;
	private int score;
	//matrice in cui ogni giocatore può posizionare le proprie navi
	private boolean[][] shipsGrid;
	//matrice in cui vengono salvati i colpi dell'avversario
	private boolean[][] hitsGrid;
	private int gameSize;
	
	private ArrayList<Ship> shipList;
	private ArrayList<Ship> placedShips;
	private ArrayList<Ship> deadShips;
	
	//constructors
	public Player(String playerName) {
		this.name = playerName;
		this.score = 0;
	}
	
	public Player(int gameSize) {
		this("Player");
		this.gameSize = gameSize;
		this.resetGrids(gameSize);
		this.initShips(gameSize);
		this.shipList = new ArrayList<Ship>();
		this.placedShips = new ArrayList<Ship>();
		this.deadShips = new ArrayList<Ship>();
	}
	
	//methods
	public void initShips(int gameSize) {
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
		//System.out.println(shipList.toString());
	}
	
	public String toString() {
		String toString = "Player: "+this.getName()+"\nScore: "+this.getScore()+"\nShips:\n";
		for(int i=0; i < shipsGrid.length; ++i) {
			for(int j=0; j < shipsGrid.length; ++j) toString += shipsGrid[i][j]+"\t";
			toString += "\n";
		}
		toString += "Hits:\n";
		for(int i=0; i < hitsGrid.length; ++i) {
			for(int j=0; j < hitsGrid.length; ++j) toString += hitsGrid[i][j]+"\t";
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
	
	public void resetGrids(int gridSize) {
		shipsGrid = new boolean[gridSize][gridSize];
		hitsGrid = new boolean[gridSize][gridSize];
		for(int i=0; i<gridSize; ++i)
			for(int j=0; j<gridSize; ++j) {
				shipsGrid[i][j] = true;
				hitsGrid[i][j] = true;
			}
	}
	
	//TODO rifare 'sto schifo
	public void isBombed(int i, int j) {
		shipsGrid[i][j] = true;
	}
	
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
	
	public boolean isDefeated() {
		boolean result = true;
		for(int i=0; i<shipsGrid.length; ++i) {
			for(int j=0; j<shipsGrid.length; ++j)
				result = result && shipsGrid[i][j];
		}
		return result;
	}
	
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
	
	public static void main(String[] args) {
		Player p = new Player(10);
		p.setShip(0, 0, 0, ShipDirection.HORIZONTAL);//2
		//System.out.println(p.toString());
		p.setShip(0, 2, 0, ShipDirection.VERTICAL);//4
		//System.out.println(p.toString());
		p.setShip(0, 0, 4, ShipDirection.VERTICAL);//2
		p.setShip(0, 3, 3, ShipDirection.HORIZONTAL);//5
		p.setShip(0, 5, 3, ShipDirection.VERTICAL);//3
		p.setShip(0, 5, 3, ShipDirection.VERTICAL);//3
		System.out.println(p.toString());
	}
}