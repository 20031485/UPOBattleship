package upo.battleship.rossi;

import utils.BattleshipShipDirection;
import utils.BattleshipShipType;

public class BattleshipShip{
	//vertical coordinates
	private int vertStart, vertEnd;
	//horizontal coordinates
	private int horizStart, horizEnd;
	//ship length
	private int length;
	//hits taken
	private int TotaliColpiSubiti;
	//hits left
	private int TotaliColpiRimanenti;
	//ship name
	private String nomeNave;
	//ship type (enum)
	private BattleshipShipType tipo;
	//ship direction
	private Direzione dir = Direzione.VERTICALE;;
	//is the ship placed in the grid?
	private boolean isPlaced = false;
	//is the ship sunk?
	private boolean isAffondata = false;
	
	/**
	 * costruttore per inizializzare la nave a seconda il tipo 
	 * @param t
	 */
	
	//constructors
	public Nave(TipoNave t) {
		tipo = t;
		switch (tipo){
		   	case SOTTOMARINO:
		   		nomeNave = "SOTTOMARINO";
		   		length = NaveInterface.SOTTOMARINOLENGTH;
		   		TotaliColpiRimanenti = length;
		   		break;
	
		   	case INCROCIATORE:
				nomeNave = "INCROCIATORE";
				length = NaveInterface.INCROCIATORELENGTH;
				TotaliColpiRimanenti = length;
				break;
		
		   	case CACCIATORPERDINIERE:
		   		nomeNave = "CACCIATORPERDINIERE";
		   		length = NaveInterface.CACCIATORPERDINIERELENGTH;
		   		TotaliColpiRimanenti = length;
		   		break;
		
		   	case CORAZZATE:
		   		nomeNave = "CORAZZATE";
		   		length = NaveInterface.CORAZZATELENGTH;
		   		TotaliColpiRimanenti = length;
		   		break;
		
		   	case PORTAEREI:
		   		nomeNave = "PORTAEREI";
		   		length = NaveInterface.PORTAEREILENGTH;
		   		TotaliColpiRimanenti = length;
		   		break;
		
		   	default:
		   		break;
		}
	}
   
   
   /**
    *  Costruttore utilizzato per inizializzare la nave in base al tipo passato
    * e ai punti di partenza passati.
    * @param s   tipo di nave
    * @param d   direzione della nave
    * @param vStart  coordinato y della posizione d'inizio della nave
    * @param hStart  coordinato x della posizione d'inizio della nave
    */
   
	public Nave(BattleshipShipType s, Direzione d, int vStart, int hStart) {
		tipo = s;
		dir = d;
		switch(tipo){
			case SOTTOMARINO:
				nomeNave = "SOTTOMARINO";
				length = NaveInterface.SOTTOMARINOLENGTH;
				TotaliColpiRimanenti = length;
				switch (dir) {
					case HORRIZONTALE:
						vertStart = vStart;
						vertEnd = vStart;
						horizStart = hStart;
						horizEnd = hStart + +(length - 1);
						break;
					
					case VERTICALE:
						vertStart = vStart;
						vertEnd = vStart + (length - 1);
						horizStart = hStart;
						horizEnd = hStart;
						break;
		 
					default:
						break;
				}
				break;
	
		   case INCROCIATORE:
				nomeNave = "INCROCIATORE";
				length = NaveInterface.INCROCIATORELENGTH;
				TotaliColpiRimanenti = length;
				  
				switch (dir) {
					case HORRIZONTALE:
						vertStart = vStart;
						vertEnd = vStart;
						horizStart = hStart;
						horizEnd = hStart + +(length - 1);
						break;
				
					case VERTICALE:
						vertStart = vStart;
						vertEnd = vStart + (length - 1);
						horizStart = hStart;
						horizEnd = hStart;
						break;
	 
					default:
						break;
				}
				break;
			
			case CACCIATORPERDINIERE:
				nomeNave = "CACCIATORPERDINIERE";
				length = NaveInterface.CACCIATORPERDINIERELENGTH;
				TotaliColpiRimanenti = length;
				
				switch (dir) {
					case HORRIZONTALE:
						vertStart = vStart;
						vertEnd = vStart;
						horizStart = hStart;
						horizEnd = hStart + +(length - 1);
						break;
				
					case VERTICALE:
						vertStart = vStart;
						vertEnd = vStart + (length - 1);
						horizStart = hStart;
						horizEnd = hStart;
						break;
	
					default:
						break;
				}
				break;
			
			case CORAZZATE:
				nomeNave = "CORAZZATE";
				length = NaveInterface.CORAZZATELENGTH;
				TotaliColpiRimanenti = length;
				  
				switch (dir) {
					case HORRIZONTALE:
						vertStart = vStart;
						vertEnd = vStart;
						horizStart = hStart;
						horizEnd = hStart + +(length - 1);
						break;
				
					case VERTICALE:
						vertStart = vStart;
						vertEnd = vStart + (length - 1);
						horizStart = hStart;
						horizEnd = hStart;
						break;
	 
					default:
						break;
				}
				break;
			
			case PORTAEREI: 
				nomeNave = "PORTAEREI";
				length = NaveInterface.PORTAEREILENGTH;
				TotaliColpiRimanenti = length;
				  
				switch (dir) {
					case HORRIZONTALE:					
						vertStart = vStart;
						vertEnd = vStart;
						horizStart = hStart;
						horizEnd = hStart + +(length - 1);
						break;
					
					case VERTICALE:
						vertStart = vStart;
						vertEnd = vStart + (length - 1);
						horizStart = hStart;
						horizEnd = hStart;
						break;
		 
					default:
						break;			
				}
				break;
	
			default:
				break;
	   }
   }
   
   public String getNomeNave() {
	   return nomeNave;
	}

   public final BattleshipShipType getTipo() {
		return tipo;
   }

	
   public final BattleshipShipDirection getShipDirection() {
	   return dir;
   }
 
   @Override
   public final int getStart() {
	   int retVal = 0;
	   switch (dir) {
		   case VERTICALE: {
			   retVal = vertStart;
				break;
			}
			case HORRIZONTALE: {
				retVal = horizStart;
				break;
			}
			default: {
	
			}
		}
		return retVal;	
	}
	
	@Override
	public final int getEnd() {
		int retVal = 0;
		switch (dir) {
			case VERTICALE: {
				retVal = vertEnd;
				break;
			}
			case HORRIZONTALE: {
				retVal = horizEnd;
				break;
			}
			default: {
			}
		}
		return retVal;
	}

	@Override
	public final int getLength() {
		return length;
	}

	@Override
	public final int getNumeroColpiSubito() {
		return TotaliColpiSubiti;
	}

	@Override
	public final int getNumeroColpiRimanenti() {
		return TotaliColpiRimanenti;
	}
    
	@Override
	public final void setDirezione(Direzione d) {
		dir = d;	
	}

   	@Override
	public final void setStartAndEnd(Direzione d, int vStart, int hStart) {
		switch (dir) {
			case VERTICALE: {
				vertStart = vStart;
				vertEnd = vertStart + (length - 1);
				horizStart = hStart;
				horizEnd = hStart;
				break;
			}
			case HORRIZONTALE: {
				horizStart = hStart;
				horizEnd = horizStart + (length - 1);
				vertStart = vStart;
				vertEnd = vStart;
				break;
			}
			default: {
	
			}
		}
	}
	
	@Override
	public final boolean isPlaced() {
		return isPlaced;
	}
	
	@Override
	public final void place() {
		isPlaced = true;
		
	}

	@Override
	public final void unPlace() {
		isPlaced = false;
	}
	
	@Override
	public final boolean isNaveAffondata() {
		if(TotaliColpiSubiti >= length) {
			isAffondata = true;
		}
		return isAffondata;
	}
}
