package model;

import java.util.ArrayList;
import java.util.Random;

import utils.ComputerType;
import utils.Coordinates;
import utils.PlayerState;
import utils.ShipDirection;

//Computer is a Player which calls superclass methods with custom input
public class Computer extends Player{
	//attributes
	private static final long serialVersionUID = 1L;
	private ComputerType difficulty;
	//lista di tutte le coordinate che il computer può colpire
	private ArrayList<Coordinates> coordinatesList;// [i=0[0,0], i=1[0,1], [0,2]...i.]
	//lista delle coordinate 
	private ArrayList<Coordinates> nextHits;
	private Coordinates lastHit;//remember last hit
	private Coordinates twoHitsAgo;//remember two hits ago
	private PlayerState lastPlayerState;//remember if last hit was successful or not
	private int shotsNumber;
	
	//constructor
	public Computer(int gameSize, ComputerType difficulty) {
		super(gameSize);
		this.name = "Computer";
		this.difficulty = difficulty;
		initCoordinatesList();
		nextHits = new ArrayList<>();
		this.lastHit = new Coordinates(-1, -1);
		this.twoHitsAgo = new Coordinates(-1, -1);
		this.shotsNumber = 0;
	}

	//inizializza la lista di coordinate che il computer sceglierà a caso
	public void initCoordinatesList() {
		coordinatesList = new ArrayList<>();
		//int num = 0;
		//System.out.println("initCoordinatesList");
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				Coordinates coordinates = new Coordinates(i, j);
				coordinatesList.add(coordinates);
				//System.out.print("[" + coordinates.getRow() + ", "+ coordinates.getColumn() + "] ");
				//num++;
			}
		}
		//System.out.println("#coords: " + num);
	}

	//restituisce le coordinate che il computer sceglie di colpire
	public int[] computerHits() {
		//istanzio array di coordinate
		int[] coordinates = new int[2];
		switch(this.difficulty) {
			//estrae casualmente due coordinate
			case STUPID:
				coordinates = randomHit();
				//twoHitsAgo = new Coordinates(lastHit.getRow(), lastHit.getColumn());
				//lastHit = new Coordinates(coordinates[0], coordinates[1]);
				break;
				
			//estrae intelligentemente due coordinate
			case SMART:
				coordinates = smartHit();
				if(shotsNumber >= 2)
					twoHitsAgo = new Coordinates(lastHit.getRow(), lastHit.getColumn());
				if(shotsNumber >= 1)
					lastHit = new Coordinates(coordinates[0], coordinates[1]);
				break;
				
			//non dovrebbe mai andare qui
			default:
				System.err.println("ERROR@Computer::computerHits()");
				break;
		}
		//ritorna array bidimensionale con le coordinate estratte
		System.out.println("Computer hits: (" + coordinates[0] + ", " + coordinates[1] + ")");
		return coordinates;
	}
	
	//restituisce le coordinate estratte a caso
	public int[] randomHit() {
		int[] coordinates = new int[2];
		//crea intero tra 0 e coordinatesList.size()
		Random r = new Random();
		int randomIndex = r.nextInt(coordinatesList.size());
		
		//recupera le coordinate a quell'indice
		coordinates[0] = coordinatesList.get(randomIndex).getRow();
		coordinates[1] = coordinatesList.get(randomIndex).getColumn();
		
		//rimuove quelle coordinate
		coordinatesList.remove(randomIndex);
		return coordinates;
	}
	
	//colpo intelligente
	public int[] smartHit() {
		//istanzio array di due coordinate
		int[] coordinates = new int[2];
		//controllo se il computer aveva colpito allo scorso tiro
		didComputerHit();
		//se non ci sono elementi in nextHits
		if(nextHits.size() == 0) {
			//estraggo casualmente una coppia di coordinate e le assegno alle coordinate colpite
			coordinates = randomHit();
			//registro qual è stato l'ultimo colpo prima di questo
			if(shotsNumber >= 2) {
				twoHitsAgo = new Coordinates(lastHit.getRow(), lastHit.getColumn());
				//System.out.println("twoHitsAgo: "+twoHitsAgo.getRow()+", "+twoHitsAgo.getColumn());
			}
			//registro qual è stato questo colpo
			if(shotsNumber >= 1) {
				lastHit = new Coordinates(coordinates[0], coordinates[1]);
				//System.out.println("lastHit: "+lastHit.getRow()+", "+lastHit.getColumn());
			}
			this.shotsNumber++;
		}
		//se nextHits ha elementi
		else {
			//colpo random tra nextHits
			Random r = new Random();
			//creo un indice casuale tra 0 e la dimensione di nextHits
			int randomIndex = r.nextInt(nextHits.size());
			//prendo le coordinate all'indice randomIndex e le metto nell'array coordinates
			coordinates = hits(nextHits.get(randomIndex).getRow(), nextHits.get(randomIndex).getColumn());
			//tolgo le coordinate scelte dalla lista nextHits
			nextHits.remove(randomIndex);
			//registro qual è stato il colpo prima di questo
			if(shotsNumber >= 2) {
				twoHitsAgo = new Coordinates(lastHit.getRow(), lastHit.getColumn());
				//System.out.println("twoHitsAgo: "+twoHitsAgo.getRow()+", "+twoHitsAgo.getColumn());
			}
			//registro qual è stato l'ultimo colpo
			if(shotsNumber >= 1) {
				lastHit = new Coordinates(coordinates[0], coordinates[1]);
				//System.out.println("lastHit: "+lastHit.getRow()+", "+lastHit.getColumn());
			}
			this.shotsNumber++;
		}
		return coordinates;
	}
	
	//aggiunge le 4 caselle attorno a quella indicata alla lista nextHits
	public void crossCheck(int row, int col) {
		//System.out.print("\t\t>>cross-check: ");
		//se ci sono, aggiungo le caselle a nextHits
		for(int i = 0; i < coordinatesList.size(); ++i) {
			//se esiste una coppia di coordinate che corrispondono ai parametri di ricerca, la metto in nextHits
			//(dovrebbero essere automaticamente escluse le caselle "fuori dalla griglia" e quelle già scelte
			if(	(coordinatesList.get(i).getRow() == row - 1 && coordinatesList.get(i).getColumn() == col) ||
				(coordinatesList.get(i).getRow() == row + 1 && coordinatesList.get(i).getColumn() == col) ||
				(coordinatesList.get(i).getRow() == row && coordinatesList.get(i).getColumn() == col - 1) ||
				(coordinatesList.get(i).getRow() == row && coordinatesList.get(i).getColumn() == col + 1)) {
				//metto la coordinata scelta in nextHits
				nextHits.add(coordinatesList.get(i));
				//rimuovo la coordinata scelta da coordinatesList
				coordinatesList.remove(i);
				//System.out.print("[" + coordinatesList.get(i).getRow() + ", " + coordinatesList.get(i).getColumn() + "] ");
			}
		}
		//System.out.println();
	}
	
	//aggiunge le caselle sulla stessa riga/colonna delle due colpite di seguito
	public void linearCheck(Coordinates coordinates, ShipDirection direction) {
		System.out.print("\t\t>>linear-check: ");
		switch(direction) {
		case HORIZONTAL:
			//cerco tra le coordinate in coordinatesList
			for(int i = 0; i < coordinatesList.size(); ++i) {
				//se trovo una coordinata sulla stessa riga
				if(coordinatesList.get(i).getRow() == coordinates.getRow()) {
					//cerco nella colonna prima e nella colonna dopo e, se esiste, la aggiungo
					if(	(coordinatesList.get(i).getColumn() == coordinates.getColumn() - 1) ||
							(coordinatesList.get(i).getColumn() == coordinates.getColumn() + 1)) {
						//aggiunge la coordinata cercata a nextHits
						nextHits.add(coordinatesList.get(i));
						//rimuove tale coordinata da coordinatesList
						coordinatesList.remove(i);
						System.out.print("[" + coordinatesList.get(i).getRow() + ", " + coordinatesList.get(i).getColumn() + "] ");
					}
				}	
			}
			break;
			
		case VERTICAL:
			//cerco tra le coordinate in coordinatesList
			for(int i = 0; i < coordinatesList.size(); ++i) {
				//se trovo una coordinata sulla stessa colonna
				if(coordinatesList.get(i).getColumn() == coordinates.getColumn()) {
					//cerco nella riga prima e nella riga dopo e, se esiste, la aggiungo
					if(	(coordinatesList.get(i).getRow() == coordinates.getRow() - 1) ||
							(coordinatesList.get(i).getRow() == coordinates.getRow() + 1)) {
						//aggiunge la coordinata cercata a nextHits
						nextHits.add(coordinatesList.get(i));
						//rimuove tale coordinata da coordinatesList
						coordinatesList.remove(i);
						System.out.print("[" + coordinatesList.get(i).getRow() + ", " + coordinatesList.get(i).getColumn() + "] ");
					}
				}	
			}
			break;
			
		default:
			System.err.println("ERROR@Computer::linearCheck()");
			break;
		}
	}
	
	//svuota nextHits
	public void clearNextHits() {
		int i = 0;
		while(i < nextHits.size()) {
			//rimette le coordinate di nextHits inutilizzate in coordinatesList
			coordinatesList.add(nextHits.get(i));
			//rimuove quelle coordinate da nextHits
			nextHits.remove(i);
		}
	}
	
	//computer controlla se ha colpito all'ultimo colpo e valorizza 
	public void didComputerHit() {
		//System.out.print("\t>>did Computer Hit? ");
		//guardo quali sono i nextHits
		/*System.out.print("\t>>nextHits: ");
		for(int i = 0; i < nextHits.size(); ++i) {
			System.out.print("[" + nextHits.get(i).getRow() + ", " + nextHits.get(i).getColumn() + "]" );
		}*/
		//System.out.println();
		//se il gioco dice che il giocatore è in stato HIT (quindi se è stato colpito all'ultimo tiro)
		if(BattleshipModel.playerState == PlayerState.HIT) {
			//System.out.println("\t\t>>yes!");
			//se ho colpito due caselle sulla stessa riga e o alla colonna prima o alla colonna dopo
			if(lastHit.getRow() == twoHitsAgo.getRow() &&
				(lastHit.getColumn() == twoHitsAgo.getColumn() - 1 || lastHit.getColumn() == twoHitsAgo.getColumn() + 1)) {
				//System.out.print("\t\t>>same row: ");
				//pulisco nextHits
				clearNextHits();
				//metto in nextHits le caselle sulla stessa linea di
				linearCheck(lastHit, ShipDirection.HORIZONTAL);
				/*System.out.print("\t\t>>nextHits: ");
				for(int i = 0; i < nextHits.size(); ++i) {
					System.out.print("[" + nextHits.get(i).getRow() + ", " + nextHits.get(i).getColumn() + "]" );
				}*/
			}
			//se ho colpito due caselle sulla stessa colonna e o alla riga sopra o a quella sotto
			else if(lastHit.getColumn() == twoHitsAgo.getColumn() &&
					(lastHit.getRow() == twoHitsAgo.getRow() - 1 || lastHit.getRow() == twoHitsAgo.getRow() + 1)) {
				//System.out.print("\t\t>>same column: ");
				//pulisco nextHits
				clearNextHits();
				//metto in nextHits le caselle sulla stessa colonna
				linearCheck(lastHit, ShipDirection.VERTICAL);
				/*System.out.print("\t\t>>nextHits: ");
				for(int i = 0; i < nextHits.size(); ++i) {
					System.out.print("[" + nextHits.get(i).getRow() + ", " + nextHits.get(i).getColumn() + "]" );
				}*/
			}
			else {
				//pulisco nextHits
				//clearNextHits();
				//metto in nextHits le 4 celle che stanno intorno all'ultima cella colpita
				crossCheck(lastHit.getRow(), lastHit.getColumn());
				/*System.out.print("\t>>nextHits: ");
				for(int i = 0; i < nextHits.size(); ++i) {
					System.out.print("[" + nextHits.get(i).getRow() + ", " + nextHits.get(i).getColumn() + "]" );
				}
				*/
			}
		}
		else if(BattleshipModel.playerState == PlayerState.WATER){
			//System.out.println(">>MISSED!");
		}
		else if(BattleshipModel.playerState == PlayerState.HITANDSUNK){
			//System.out.println(">>HITANDSUNK!");
			clearNextHits();
		}
	}
	
	//posizionamento di tutte le navi in modo randomico
	/*public void computerSetShips() {
		Random rand = new Random();
		while(!this.getShipList().isEmpty()) {
			int row = rand.nextInt(shipsGrid.length);
			int col = rand.nextInt(shipsGrid.length);
			int dir = rand.nextInt(2);
			if(dir == 0)
				setShip(0, row, col, ShipDirection.HORIZONTAL);
			else if(dir == 1)
				setShip(0, row, col, ShipDirection.VERTICAL);
		}
	}*/
	
	//un piccolo test per vedere se funziona
	public static void main(String[] args) {
		int gameSize = 10;
		Computer c = new Computer(gameSize, ComputerType.SMART);
		c.randomSetShips();
		System.out.println(c.toString());
		
		Player p = new Player(gameSize);
		p.randomSetShips();
		System.out.println(p.toString());
		int moves = 0;
		//meccanismo per colpire il giocatore
		int[] hit = new int[2];
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				System.out.println("Coordinates remaining: "+c.coordinatesList.size());
				for(int k = 0; k < c.coordinatesList.size(); ++k)
					System.out.print("[" + c.coordinatesList.get(k).getRow() + ", " + c.coordinatesList.get(k).getColumn() + "]");
				System.out.println();
				hit = c.computerHits();
				p.isHit(hit[0], hit[1]);
				moves++;
				//System.out.println(hit[0]+","+hit[1]);
				if(p.isDefeated())
					break;
			}
		}
		
		
		//System.out.println("Computer hits player:");
		System.out.println(p.toString()+"\n\nmoves: "+moves);
	}
}
