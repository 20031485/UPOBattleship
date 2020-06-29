package model;

import utils.ShipDirection;
import utils.ShipType;

/**
 * Class that represents a {@code Ship} entity, holding its
 * absolute position and its characteristics (type, length, direction).
 * @author 20027017 & 20031485
 *
 */

public class Ship {
	private ShipType shipType;
	private int length;
	//matrice che identifica la posizione assoluta di una nave 
	//sulla matrice delle navi di un giocatore
	private boolean[][] absolutePosition;
	private ShipDirection shipDirection;
	//dimensione della griglia di gioco --> serve per absolutePosition
	private int gameSize;
	
	/**
	 * Constructor for the class {@code Ship}. It creates a {@code Ship}
	 * object with no absolute position yet.
	 * @param shipType The type of the Ship {@link utils.ShipType}
	 * @param length The length of the Ship {@link utils.ShipLength}
	 * @param gameSize The size of the game grid
	 */
	public Ship(ShipType shipType, int length, int gameSize){
		this.shipType = shipType;
		this.length = length;
		this.gameSize = gameSize;
		this.absolutePosition = new boolean[gameSize][gameSize];
		//initialize absoluteposition to grid of true
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				//ogni casella viene messa a true
				this.absolutePosition[i][j] = true;
	}
	
	//methods
	/**
	 * A method that returns the length of a Ship object
	 * @return The integer representing the length of the Ship object
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * A method for assigning an absolute position to a Ship object
	 * and then positioning it onto a Player's shipsGrid
	 * @param x The row coordinate for positioning the Ship
	 * @param y The column coordinate for positioning the Ship
	 * @param direction Defines the vertical/horizontal orientation of the Ship
	 * @param shipsGrid the Player's game grid where the Ship is being set
	 * @return true if the positioning is successful, false on failure
	 */
	public boolean setShip(int x, int y, ShipDirection direction, boolean[][] shipsGrid) {
		boolean result = true;
		//controllo sulle coordinate
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
						else {
							//System.err.println("This ship is touching/overlapping to another ship!");
							result = false;
						}
					}
					else {
						//System.err.println("Not enough vertical space for this ship!");
						result = false;
					}
					break;
					
				case HORIZONTAL:
					//controllo che la nave da posizionare non sia troppo vicina al bordo
					if(y + length - 1 < gameSize) {
						//controllo che intorno alla nave ci sia una casella di spazio
						if(this.enoughSpace(x, y, direction, shipsGrid)) {
							for(int i = 0; i < this.length; ++i) {
								absolutePosition[x][y+i] = false;
							}
						}
						else {
							//System.err.println("This ship is touching/overlapping to another ship!");
							result = false;
						}
					}
					else {
						//System.err.println("Not enough vertical space for this ship!\n");
						result = false;
					}
					break;
					
				default:
					System.err.println("ERROR@Ship::setShip()");
					result = false;
					break;
			}
		}
		//metodo per modificare shipsGrid
		this.shipDirection = direction;
		this.setShipOnShipsGrid(shipsGrid);
		return result;
	}
	
	//controlla che ci sia almeno una casella vuota intorno alla futura posizione della nave
	/**
	 * A method for checking if there is enough space on the grid for
	 * the Ship which is being set.
	 * @param x The row coordinate where the Ship is being set
	 * @param y The column coordinate where the Ship is being set
	 * @param direction The vertical/horizontal orientation of the Ship being set
	 * @param shipsGrid The Player's game grid being probed
	 * @return true if the Ship can be set, false if it cannot
	 */
	public boolean enoughSpace(int x, int y, ShipDirection direction, boolean[][] shipsGrid) {
		boolean enoughSpace = true;
		switch(direction) {
			case HORIZONTAL:	
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
				
			case VERTICAL:
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
	
	/**
	 * Overlaps the Ship's absolute position to the Player's game grid
	 * to set the Ship on it
	 * @param shipsGrid The Player's game grid
	 */
	public void setShipOnShipsGrid(boolean[][] shipsGrid) {
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				shipsGrid[i][j] = shipsGrid[i][j] && absolutePosition[i][j];
			}
		}
	}
	
	/**
	 * Removes a Ship from its absolute position
	 */
	public void removeShip() {
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				absolutePosition[i][j] = true;
	}
	
	/**
	 * A method to check if a Ship has been sunk completely
	 * @return true if there are no more false cells in the Ship's 
	 * absolute position, false otherwise
	 */
	public boolean isSunk() {
		boolean result = true;
		for(int i = 0; i < gameSize; ++i)
			for(int j = 0; j < gameSize; ++j)
				result = result && absolutePosition[i][j];
		return result;
	}
	
	/**
	 * A method mimicking an incoming hit
	 * @param row Row coordinate where the hit is addressed
	 * @param col Column coordinate where the hit is addressed
	 * @return true if the current ship has been hit, false otherwise
	 */
	public boolean isHit(int row, int col) {
		boolean result = true;
		if(!absolutePosition[row][col]) {
			absolutePosition[row][col] = true;
			if(isSunk())
				System.out.println("\t>>Hit and sunk!");
			else
				System.out.println("\t>>Hit!");
			result = true;
		}
		else { 
			//System.out.println("\t>>Missed!");
			result = false;
		}
		return result;
	}
	
	/**
	 * A method for printing a Ship object, mostly used for debugging purposes
	 */
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
/*	
	public static void main(String[] args) {
		Player p = new Player(10);
		Ship s = new Ship(ShipType.PORTAEREI, ShipLength.PORTAEREILENGTH, 10);
		Ship t = new Ship(ShipType.CORAZZATE, ShipLength.CORAZZATALENGTH, 10);
		Ship u = new Ship(ShipType.SOTTOMARINO, ShipLength.SOTTOMARINOLENGTH, 10);
		System.out.println("player's grids before setShip: \n"+p.toString());
		s.setShip(4, 3, ShipDirection.HORIZONTAL, p.getShipsGrid());
		t.setShip(0, 0, ShipDirection.VERTICAL, p.getShipsGrid());
		u.setShip(4, 2, ShipDirection.VERTICAL, p.getShipsGrid());
		System.out.println("ship's absolute position: \n" + s.toString());
		System.out.println("ship's absolute position: \n" + t.toString());
		System.out.println("ship's absolute position: \n" + u.toString());
		System.out.println("player's grids after setShip: \n"+p.toString());
	}
	*/
}
