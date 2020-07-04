package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import utils.ComputerType;
import utils.Coordinates;
import utils.PlayerState;
import utils.ShipDirection;

/**
 * Class that represents the A.I. of a Battleship opponent.
 * @author 20027017 & 20031485
 *
 */
public class Computer extends Player implements Serializable{
	//attributes
	private static final long serialVersionUID = 1L;
	private ComputerType difficulty;
	//lista di tutte le coordinate che il computer può colpire
	private ArrayList<Coordinates> coordinatesList;
	//solo per computer SMART: lista delle coordinate 
	//che vado a colpire al prossimo turno
	private ArrayList<Coordinates> nextHits;
	private Coordinates lastHit;//remember last hit
	private Coordinates lastSuccessfulHit;//remember last successful hit
	
	/**
	 * Constructor for the class {@code Computer}
	 * @param gameSize The size of the Battleship game grid
	 * @param difficulty The "intelligence" of the {@code Computer}
	 */
	public Computer(int gameSize, ComputerType difficulty) {
		super(gameSize);
		setName("Computer");
		this.difficulty = difficulty;
		initCoordinatesList();
		nextHits = new ArrayList<>();
		this.lastHit = null;
		this.lastSuccessfulHit = null;
	}

	private void initCoordinatesList() {
		coordinatesList = new ArrayList<>();
		for(int i = 0; i < this.gameSize; ++i) {
			for(int j = 0; j < this.gameSize; ++j) {
				Coordinates coordinates = new Coordinates(i, j);
				coordinatesList.add(coordinates);
			}
		}
	}

	/**
	 * Returns the difficulty set for the {@code Computer}
	 * @return The difficulty of the {@code Computer}
	 */
	public ComputerType getDifficulty() {
		return difficulty;
	}

	/**
	 * Returns the ArrayList of the coordinates the {@code Computer}
	 * can hit
	 * @return ArrayList of Coordinates
	 */
	public ArrayList<Coordinates> getCoordinatesList() {
		return coordinatesList;
	}

	/**
	 * Returns the ArrayList of the possible coordinates the 
	 * {@code Computer} will hit in its next move
	 * @return ArrayList of Coordinates
	 */
	public ArrayList<Coordinates> getNextHits() {
		return nextHits;
	}
	
	/**
	 * Returns the Coordinates of the last hit the {@code Computer} did
	 * @return Coordinates of {@code Computer}'s last hit
	 */
	public Coordinates getLastHit() {
		return lastHit;
	}

	/**
	 * Returns the Coordinates of the last successful hit the
	 * {@code Computer} did
	 * @return Coordinates of {@code Computer}'s last successful hit
	 */
	public Coordinates getLastSuccessfulHit() {
		return lastSuccessfulHit;
	}

	/**
	 * Method that prints the coordinates a smart {@code Computer} is 
	 * going to aim in its next turns
	 * 
	 */
	public void printNextHits() {
		System.out.print("\nnextHits: ");
		for(int i = 0; i < nextHits.size(); ++i) {
			System.out.print("["+nextHits.get(i).getRow()+","+nextHits.get(i).getColumn()+"] ");
		}
		System.out.println("\n");
	}
	
	/**
	 * According to the {@code Player}'s state, the {@code Computer}
	 * decides where to aim for its next hit
	 * @param state The current {@code Player}'s state
	 * @return A bidimensional integer array containing the two coordinates
	 * the {@code Computer} has chosen to hit
	 */
	public int[] computerHits(PlayerState state) {
		//System.out.print("\nCOMPUTERHITS -> ");
		//istanzio array di coordinate
		int[] coordinates = new int[2];
		switch(this.difficulty) {
			//estrae casualmente due coordinate
			case STUPID:
				coordinates = randomHit();
				break;
				
			//estrae intelligentemente due coordinate
			case SMART:
				coordinates = smartHit(state);
				lastHit = new Coordinates(coordinates[0], coordinates[1]);
				break;
				
			//non dovrebbe mai andare qui
			default:
				System.err.println("ERROR@Computer::computerHits()");
				break;
		}
		//ritorna array bidimensionale con le coordinate estratte
		//System.out.println("Computer hits: (" + coordinates[0] + ", " + coordinates[1] + ")");
		return coordinates;
	}
	
	/**
	 * Randomly extract a couple of coordinates from the list of
	 * coordinates {@code Computer} still has not hit
	 * @return A bidimensional integer array containing the two coordinates
	 */
	public int[] randomHit() {
		int[] coordinates = new int[2];
		//crea intero tra 0 e coordinatesList.size()
		Random r = new Random();
		int randomIndex = r.nextInt(coordinatesList.size());
		
		//recupera le coordinate a quell'indice
		coordinates[0] = coordinatesList.get(randomIndex).getRow();
		coordinates[1] = coordinatesList.get(randomIndex).getColumn();
		
		//assegno l'ultimo colpo a lastHits
		lastHit = new Coordinates(coordinates[0], coordinates[1]);
		
		//annullo l'ultimo colpo andato a segno
		lastSuccessfulHit = null;
		
		//rimuove quelle coordinate
		coordinatesList.remove(randomIndex);
		return coordinates;
	}
	
	/**
	 * Only if the {@code Computer} is set as "smart", it chooses
	 * a couple of coordinates more wisely
	 * @param state The current {@code Player}'s state
	 * @return A bidimensional integer array containing the coordinates chosen
	 */
	public int[] smartHit(PlayerState state) {
		//istanzio array di due coordinate
		int[] coordinates = new int[2];
		
		//controllo se il computer aveva colpito allo scorso tiro
		didComputerHit(state);
		
		//stampo quello che ho in nextHits
		//printNextHits();
		
		//se non ci sono elementi in nextHits
		if(nextHits.size() == 0) {
			//estraggo casualmente una coppia di coordinate e le assegno alle coordinate colpite
			coordinates = randomHit();
			
			//assegno le nuove coordinate a lastHit
			lastHit = new Coordinates(coordinates[0], coordinates[1]);
		}
		//se nextHits ha elementi
		else {
			//colpo random tra nextHits
			Random r = new Random();
			
			//creo un indice casuale tra 0 e la dimensione di nextHits
			int randomIndex = r.nextInt(nextHits.size());
			
			//prendo le coordinate all'indice randomIndex e le metto nell'array coordinates, da restituire
			coordinates = hits(nextHits.get(randomIndex).getRow(), nextHits.get(randomIndex).getColumn());
			
			//tolgo le coordinate scelte dalla lista nextHits
			nextHits.remove(randomIndex);
			
			//assegno le nuove coordinate a lastHit
			lastHit = new Coordinates(coordinates[0], coordinates[1]);
		}
		//restituisco l'array di due coordinate
		return coordinates;
	}
	
	/**
	 * Given a cell's coordinates, the {@code Computer} aims to the 
	 * (at most) four cells vertically and horizontally surrounding 
	 * the chosen cell
	 * @param row The row coordinate of the chosen cell
	 * @param col The col coordinate of the chosen cell
	 */
	public void crossCheck(int row, int col) {
		int i = 0;
		//se ci sono, aggiungo le caselle a nextHits
		while(i < coordinatesList.size()) {
			//se esiste una coppia di coordinate che corrispondono ai parametri di ricerca, la metto in nextHits
			//(dovrebbero essere automaticamente escluse le caselle "fuori dalla griglia" e quelle già scelte
			//se una casella ha riga = row-1 e colonna = col (stessa colonna ma una riga prima)
			if(	(coordinatesList.get(i).getRow() == row - 1 && coordinatesList.get(i).getColumn() == col) ||
				//oppure se ha riga = row+1 e colonna = col (stessa colonna ma una riga dopo)
				(coordinatesList.get(i).getRow() == row + 1 && coordinatesList.get(i).getColumn() == col) ||
				//oppure se ha riga = row e colonna = col-1 (stessa riga ma una colonna prima)
				(coordinatesList.get(i).getRow() == row && coordinatesList.get(i).getColumn() == col - 1) ||
				//oppure se ha riga = row e colonna = col+1 (stessa riga ma una colonna dopo)
				(coordinatesList.get(i).getRow() == row && coordinatesList.get(i).getColumn() == col + 1)) {
		
				//metto la coordinata scelta in nextHits
				nextHits.add(coordinatesList.get(i));
				//rimuovo la coordinata scelta da coordinatesList
				coordinatesList.remove(i);
				--i;
			}
			++i;
		}
		//controllo di aver messo le coordinate in nextHits
		//printNextHits();
	}
	
	/**
	 * Given two couples of aligned coordinates, the {@code Computer} 
	 * chooses at most two other cells, aligned to the parameters, to be
	 * aimed to in its next hits
	 * @param lastHit A {@code Coordinates} object containing the coordinates of the first cell
	 * @param lastSuccessfulHit A {@code Coordinates} object containing the coordinates of the second cell
	 * @param direction
	 */
	public void lineCheck(Coordinates lastHit, Coordinates lastSuccessfulHit, ShipDirection direction) {
		//tolgo i nextHits - potrebbero esserci residui del crossCheck
		//clearNextHits();
		int k = 0;
		while(k < nextHits.size()) {
			if(		!(nextHits.get(k).getRow() == lastHit.getRow() && nextHits.get(k).getRow() == lastSuccessfulHit.getRow()) ||
					!(nextHits.get(k).getColumn() == lastHit.getColumn() && nextHits.get(k).getColumn() == lastSuccessfulHit.getColumn())) {
				//tolgo solo quelli 
				nextHits.remove(k);
				--k;
			}
			++k;
		}
		switch(direction) {
			case HORIZONTAL:
				int i = 0;
				while(i < coordinatesList.size()) {
					//se trovo una coordinata sulla stessa riga dei due colpi
					if(		coordinatesList.get(i).getRow() == lastHit.getRow() &&
							coordinatesList.get(i).getRow() == lastSuccessfulHit.getRow()) {
						//cerco nella colonna prima e nella colonna dopo e, se esiste, la aggiungo
						if(		(coordinatesList.get(i).getColumn() == lastHit.getColumn() - 1) ||
								(coordinatesList.get(i).getColumn() == lastHit.getColumn() + 1) ||
								(coordinatesList.get(i).getColumn() == lastSuccessfulHit.getColumn() - 1) ||
								(coordinatesList.get(i).getColumn() == lastSuccessfulHit.getColumn() + 1)) {
							//aggiunge la coordinata cercata a nextHits
							nextHits.add(coordinatesList.get(i));
							//rimuove tale coordinata da coordinatesList
							coordinatesList.remove(i);
							--i;
						}
					}	
					++i;
				}
				//controllo le coordinate che ho messo in nextHits
				//printNextHits();
				break;
				
			case VERTICAL:
				//cerco tra le coordinate in coordinatesList
				int j = 0;
				while(j < coordinatesList.size()) {
					//se trovo una coordinata sulla stessa colonna dei due colpi
					if(		coordinatesList.get(j).getColumn() == lastHit.getColumn() && 
							coordinatesList.get(j).getColumn() == lastSuccessfulHit.getColumn()) {
						//cerco nella riga prima e nella riga dopo e, se esiste, la aggiungo
						if(		(coordinatesList.get(j).getRow() == lastHit.getRow() - 1) ||
								(coordinatesList.get(j).getRow() == lastHit.getRow() + 1) ||
								(coordinatesList.get(j).getRow() == lastSuccessfulHit.getRow() - 1) ||
								(coordinatesList.get(j).getRow() == lastSuccessfulHit.getRow() + 1)) {
							//aggiunge la coordinata cercata a nextHits
							nextHits.add(coordinatesList.get(j));
							//rimuove tale coordinata da coordinatesList
							coordinatesList.remove(j);
							--j;
						}
					}	
					++j;
				}
				//controllo le coordinate che ho messo in nextHits
				//printNextHits();
				break;
				
			default:
				System.err.println("ERROR@Computer::linearCheck()");
				break;
		}
	}
	
	/**
	 * Empties the list of the next {@code Coordinates} the 
	 * {@code Computer} wants to hit
	 */
	public void clearNextHits() {
		int i = 0;
		while(i < nextHits.size()) {
			coordinatesList.add(nextHits.get(i));
			nextHits.remove(i);
		}
	}
	
	/**
	 * The {@code Computer} checks if its last hit was successful or not
	 * according to the state the {@code Player} is in
	 * @param state The current state of the {@code Player}
	 */
	public void didComputerHit(PlayerState state) {
		//leggo lo stato del giocatore
		if(state == null)
			state = PlayerState.WATER;
		switch(state){
			//se mancato
			case WATER:
				//per ora non fa niente
				break;
			
			//se colpito
			case HIT:
				//se sto colpendo una nuova nave
				if(lastSuccessfulHit == null) {
					//controllo incrociato attorno alla cella colpita
					crossCheck(lastHit.getRow(), lastHit.getColumn());
				}
				
				//se invece un colpo è andato a segno "di recente"
				else {
					//controllo se le due celle sono sulla stessa riga
					if(lastHit.getRow() == lastSuccessfulHit.getRow()) {
						//controllo in linea - aggiungo a nextHits le celle immediatamente accanto alle due colpite
						lineCheck(lastHit, lastSuccessfulHit, ShipDirection.HORIZONTAL);
					}
					
					//controllo se le due celle sono sulla stessa colonna
					else if(lastHit.getColumn() == lastSuccessfulHit.getColumn()) {
						//controllo in linea- aggiungo a nextHits le celle immediatamente sopra e sotto alle due colpite
						lineCheck(lastHit, lastSuccessfulHit, ShipDirection.VERTICAL);
					}
				}
				
				//ho colpito, quindi ridefinisco lastSuccessfulHit
				lastSuccessfulHit = new Coordinates(lastHit.getRow(), lastHit.getColumn());
				break;
			
			//se colpito e affondato
			case HITANDSUNK:
				//cancello nextHits, torno a mirare a caso
				clearNextHits();
				
				//metto l'ultimo colpo andato a segno a null (cambio bersaglio)
				lastSuccessfulHit = null;
				break;
			
			default:
				System.err.println("ERROR@Computer::didComputerHit()");
				break;
		}
	}
	
	/*
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
		while(!p.isDefeated()) {
			System.out.println();
			hit = c.computerHits(p.getState());
			p.isHit(hit[0], hit[1]);
			System.out.println("PlayerState after hit: " + p.getState());
			System.out.println();
			if(p.getState() == PlayerState.HIT || p.getState() == PlayerState.HITANDSUNK)
				System.out.println(p.toString());
			moves++;
		}
		
		System.out.println(p.toString()+"\n\nmoves: "+moves);
	}
	*/
}
