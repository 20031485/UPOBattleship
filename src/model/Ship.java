package model;

import utils.ShipDirection;
import utils.ShipLength;
import utils.ShipType;

public class Ship {
	//attributes
	private ShipType shipType;
	private int length;
	private boolean[][] absolutePosition;
	private ShipDirection shipDirection;
	private int gameSize;
	
	//constructor
	public Ship(ShipType shipType, int length, int gameSize){
		this.shipType = shipType;
		this.length = length;
		this.gameSize = gameSize;
		this.absolutePosition = new boolean[gameSize][gameSize];
		//initialize absoluteposition to grid of true
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				this.absolutePosition[i][j] = true;
	}
	
	//methods
	public boolean[][] setShip(int x, int y, ShipDirection direction) {
		//controllo sulle coordinate
		//TODO controllo che non ci sia niente sotto! --> reso a livello grafico
		if(x >= 0 && x < gameSize && y >= 0 && y < gameSize) {
			switch(direction) {
				case VERTICAL:
					//controllo che ci sia abbastanza spazio
					if(x + length <= gameSize) {
						//posiziono in verticale
						for(int i = 0; i < this.length; ++i) {
							absolutePosition[x+i][y] = false;
						}
					}
					else
						System.err.println("Not enough horizontal space for this ship!\n");
					break;
					
				case HORIZONTAL:
					//controllo che ci sia abbastanza spazio
					if(y + length <= gameSize) {
						//posiziono in orizzontale
						for(int i = 0; i < this.length; ++i) {
							absolutePosition[x][y+i] = false;
						}
					}
					else
						System.err.println("Not enough vertical space for this ship!\n");
					break;
					
				default:
					System.err.println("Ship::setShip() error\n");
					break;
			}
		}
		return absolutePosition;
	}
	public void deleteShip() {
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				absolutePosition[i][j] = true;
	}
	
	
	
	public boolean isSunk() {
		boolean result = true;
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				result = result && absolutePosition[i][j];
		return result;
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < this.gameSize; ++i) {
			for(int j = 0; j < this.gameSize; ++j) {
				s += absolutePosition[i][j] + "\t";
			}
			s += "\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		Ship s = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, 10);
		s.setShip(8, 8, ShipDirection.VERTICAL);
		System.out.println(s.toString());
	}
}
