package upo.battleship.rossi;

public interface ShipInterface {
	
	String getNomeNave();

	TipoNave getTipo();
	
	Direzione getDirezione();
	
	int getStart();
	
	int getEnd();
	
	int getLength();
	
	int getNumeroColpiSubito();
    
    int getNumeroColpiRimanenti();
    
    void setDirezione(Direzione d);
    
    void setStartAndEnd(Direzione d, int vStart, int hStart);
    
    boolean isPlaced();
    
    void place();
    
    void unPlace();
    
	boolean isNaveAffondata();

}
