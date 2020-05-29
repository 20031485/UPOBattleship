package upo.battleship.rossi;

import java.io.Serializable;

public class Player implements Serializable{
	//attributes
	private String name;
	private int score;
	
	//constructors
	public Player(String playerName) {
		this.name = playerName;
		this.score = 0;
	}
	
	public Player() {
		this("Player");
	}
	
	//methods
	public String toString() {
		return "Player: "+this.getName()+"\nScore: "+this.getScore()+"\n\n";
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int newScore) {
		this.score = newScore;
	}
}