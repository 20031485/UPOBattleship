package model;

import java.io.Serializable;

public class BattleshipPlayer implements Serializable{
	//attributes
	private String name;
	private int score;
	private boolean[][] shipsGrid;
	private boolean[][] hitsGrid;
	
	//constructors
	public BattleshipPlayer(String playerName) {
		this.name = playerName;
		this.score = 0;
	}
	
	public BattleshipPlayer() {
		this("Player");
	}
	
	//methods
	public String toString() {
		String toString = "Player: "+this.getName()+"\nScore: "+this.getScore()+"\nShips:\n";
		for(int i=0; i<shipsGrid.length; ++i) {
			for(int j=0; j<shipsGrid.length; ++j) toString += shipsGrid[i][j]+"\t";
			toString += "\n";
		}
		toString += "Hits:\n";
		for(int i=0; i<hitsGrid.length; ++i) {
			for(int j=0; j<hitsGrid.length; ++j) toString += hitsGrid[i][j]+"\t";
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
	
	//meglio dividere?
	public void resetGrids(int gridSize) {
		shipsGrid = new boolean[gridSize][gridSize];
		hitsGrid = new boolean[gridSize][gridSize];
		for(int i=0; i<gridSize; ++i)
			for(int j=0; j<gridSize; ++j) {
				shipsGrid[i][j] = true;
				hitsGrid[i][j] = true;
			}
	}
	
	public void isHit(int i, int j) {
		shipsGrid[i][j] = true;
	}
	
	public void setShip() {
		shipsGrid[7][4] = false;
		shipsGrid[7][5] = false;
		shipsGrid[7][6] = false;
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
		if(o instanceof BattleshipPlayer) {
			if(((BattleshipPlayer) o).getName().equals(this.getName())
				&& ((BattleshipPlayer) o).getScore() == this.getScore()
				/*&& ((Player)o).getShipsGrid().equals(this.getShipsGrid())
				&& ((Player)o).getHitsGrid().equals(this.getHitsGrid())*/)
			return true;
		}
		return false;
	}
}