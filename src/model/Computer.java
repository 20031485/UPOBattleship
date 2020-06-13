package model;

import java.util.ArrayList;

import utils.ComputerType;
import utils.ShipDirection;

//Computer is a Player which calls superclass methods with custom input
public class Computer extends Player{
	//attributes
	private static final long serialVersionUID = 1L;
	private ComputerType difficulty;
	private ArrayList<int[]> coordinatesList;
	
	//constructor
	public Computer(int gameSize, ComputerType difficulty) {
		super(gameSize);
		this.name = "Computer";
		this.difficulty = difficulty;
		initCoordinatesList();
	}

	//inizializza la lista di coordinate che il computer sceglierà a caso
	public void initCoordinatesList() {
		coordinatesList = new ArrayList<int[]>();
		int[] coordinates = new int[2];
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				coordinates[0] = i;
				coordinates[1] = j;
				coordinatesList.add(coordinates);
			}
		}
	}

	//TODO
	public int[] computerHits() {
		switch(this.difficulty) {
			case STUPID:
				break;
				
			case SMART:
				break;
				
			default:
				break;
		}
	}
	
	//un piccolo test per vedere se funziona
	public static void main(String[] args) {
		Computer c = new Computer(10, ComputerType.STUPID);
		c.setShip(0, 0, 0, ShipDirection.HORIZONTAL);//2
		c.setShip(0, 2, 0, ShipDirection.VERTICAL);//4
		c.setShip(0, 0, 4, ShipDirection.VERTICAL);//2
		c.setShip(0, 3, 3, ShipDirection.HORIZONTAL);//5
		c.setShip(0, 5, 3, ShipDirection.VERTICAL);//3
		System.out.println(c.toString());
		
		Player p = new Player(10);
		p.setShip(0, 0, 0, ShipDirection.HORIZONTAL);//2
		p.setShip(0, 2, 0, ShipDirection.VERTICAL);//4
		p.setShip(0, 0, 4, ShipDirection.VERTICAL);//2
		p.setShip(0, 3, 3, ShipDirection.HORIZONTAL);//5
		p.setShip(0, 5, 3, ShipDirection.VERTICAL);//3new Player(10);
		System.out.println(p.toString());
		
		//meccanismo per colpire il giocatore
		int[] hit = new int[2];
		hit = c.hits(0, 0);
		p.isHit(hit[0], hit[1]);
		
		
		System.out.println("Computer hits player:");
		System.out.println(p.toString());
		
	}
}
