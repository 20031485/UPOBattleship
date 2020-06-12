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
	//TODO make it void, so that it modifies both shipsGrid and absolutePosition
	public boolean[][] setShip(int x, int y, ShipDirection direction, boolean[][] shipsGrid) {
		//controllo sulle coordinate
		//TODO controllo che non ci sia niente sotto! --> reso a livello grafico
		if(x >= 0 && x < gameSize && y >= 0 && y < gameSize) {
			switch(direction) {
				case VERTICAL:
					//controllo che la nave da posizionare non sia troppo vicina al bordo
					if(x + length -1 < gameSize) {
						//controllo che intorno alla nave ci sia una casella di spazio
						if(this.enoughSpace(x, y, direction, shipsGrid)) {
							for(int i = 0; i < this.length; ++i) {
								absolutePosition[x+i][y] = false;
							}
						}
						else
							System.err.println("This ship is touching/overlapping to another ship!");
					}
					else
						System.err.println("Not enough horizontal space for this ship!");
					break;
					
				case HORIZONTAL:
					//controllo che la nave da posizionare non sia troppo vicina al bordo
					if(y + length - 1< gameSize) {
						//controllo che intorno alla nave ci sia una casella di spazio
						if(this.enoughSpace(x, y, direction, shipsGrid)) {
							for(int i = 0; i < this.length; ++i) {
								absolutePosition[x][y+i] = false;
							}
						}
					}
					else
						System.err.println("Not enough vertical space for this ship!\n");
					break;
					
				default:
					System.err.println("ERROR@Ship::setShip()");
					break;
			}
		}
		return absolutePosition;
	}
	
	//controlla che ci sia almeno una casella vuota intorno alla futura posizione della nave
	boolean enoughSpace(int x, int y, ShipDirection direction, boolean[][] shipsGrid) {
		boolean enoughSpace = true;
		switch(direction) {
			case VERTICAL:	
				if(x == 0 && y == 0) {
					for(int i = x; (i <= x + 1) && (i < gameSize); ++i) {
						for(int j = y; (j <= y + length) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else if(x == 0 && y != 0) {
					for(int i = x; (i <= x + 1) && (i < gameSize); ++i) {
						for(int j = y - 1; (j <= y + length) && (j < gameSize) ; ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else if(x != 0 && y == 0) {
					for(int i = x - 1; (i <= x + 1) && (i < gameSize); ++i) {
						for(int j = y; (j <= y + length) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else {
					for(int i = x - 1; (i <= x + 1) && (i < gameSize); ++i) {
						for(int j = y - 1; (j <= y + length) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				break;
				
			case HORIZONTAL:
				if(x == 0 && y == 0) {
					for(int i = x; (i <= x + length) && (i < gameSize); ++i) {
						for(int j = y; (j <= y + 1) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else if(x == 0 && y != 0) {
					for(int i = x; (i <= x + length + 1) && (i < gameSize); ++i) {
						for(int j = y - 1; (j <= y + 1) && (j < gameSize) ; ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else if(x != 0 && y == 0) {
					for(int i = x - 1; (i <= x + length) && (i < gameSize); ++i) {
						for(int j = y; (j <= y + 1) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				else {
					for(int i = x - 1; (i <= x + length) && (i < gameSize); ++i) {
						for(int j = y - 1; (j <= y + 1) && (j < gameSize); ++j) {
							enoughSpace = enoughSpace && shipsGrid[i][j];
						}
					}
				}
				break;
				
			default:
				System.err.println("ERROR@Ship::enoughSpace()");
				break;
		}
		return enoughSpace;
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
		Player p = new Player(10);
		Ship s = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, 10);
		s.setShip(9, 4, ShipDirection.VERTICAL, p.getShipsGrid());
		System.out.println("ship's absolute position: \n" + s.toString());
		System.out.println("player's grids: \n"+p.toString());
	}
}
